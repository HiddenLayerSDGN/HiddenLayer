package com.hiddenlayer.dalabel.manageLabeling;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.hiddenlayer.dalabel.dolabeling.LabelDoList;
import com.hiddenlayer.dalabel.manageBundle.ManageSelector;
import com.hiddenlayer.dalabel.session.ProjectSession;
import com.hiddenlayer.dalabel.util.PageOption;

@Service
public class ManageLabelingDAO {
	// 등록 - 데이터 선택해서 오픈하기.
	@Autowired
	private SqlSession ss;

	@Autowired
	private ProjectSession ps;

	@Autowired
	private PageOption po;

	public void regLabelingProject(LabelingProject lp, HttpServletRequest req) {
		lp.setProject_requestor((String) req.getSession().getAttribute("loginUserID"));
		ss.getMapper(ManageLabelingMapper.class).regLabelingProject(lp);
		if (req.getSession().getAttribute("projectCount") != null) {
			req.getSession().setAttribute("projectCount", (Integer) req.getSession().getAttribute("projectCount") + 1);
		}
	}

	public void getUploadedProject(int page, HttpServletRequest req) {
		String user = (String) req.getSession().getAttribute("loginUserID");
		if (req.getSession().getAttribute("projectCount") == null) {
			req.getSession().setAttribute("projectCount",
					ss.getMapper(ManageLabelingMapper.class).getAllMyProjectCount(user));
		}

		int projectPageCount = (int) Math
				.ceil((Integer) req.getSession().getAttribute("projectCount") / (double) po.getProjectPerPage());
		int start = (page - 1) * po.getProjectPerPage() + 1;
		int end = page * po.getProjectPerPage();
		ManageSelector ms = new ManageSelector(user, start, end);
		List<LabelingProject> projects = ss.getMapper(ManageLabelingMapper.class).getMyLabelingProject(ms);
		req.setAttribute("projects", projects);
		req.setAttribute("projectPageCount", projectPageCount);
		req.setAttribute("page", page);
	}

	public void getMyDeatilProject(HttpServletRequest req) {
		LabelingProject project = ss.getMapper(ManageLabelingMapper.class)
				.getMyDeatilProject(Integer.parseInt(req.getParameter("project_no")));
		req.setAttribute("project", project);
	}

	// 프로젝트 권한설정 관리
	public void updateProjectAccessLevel(LabelingProject lp, HttpServletRequest req) {
		ss.getMapper(ManageLabelingMapper.class).updateProjectAccessLevel(lp);
		if (lp.getProject_access_level().intValue() != 0) {
			ps.createDoLabeling(lp.getProject_no(), ss.getMapper(ManageLabelingMapper.class).getFileCount(lp),
					lp.getProject_access_level());
		} else {
			ps.terminateDoLabeling(lp.getProject_no());
		}
	}

	// 정산시작하기
	// 참여인원 관리 - 조회, 대기, 수락, 거부, 밴, (초대)
	public void select(int no, HttpServletRequest req) {
		req.setAttribute("mlu", ss.getMapper(ManageLabelingMapper.class).selectLabelingUser(no)); // 모든 대기다 명단이 나옴
	}

//	
	public void changeUserAccess(LabelDoList ld, HttpServletRequest req) {
		ss.getMapper(ManageLabelingMapper.class).changeUserAccess(ld); // 수락, 거부, 밴할 때 호출할 함수
		if(ld.getDolabel_state().intValue()==3) {
			ps.removeUserIDWithProjectNo(ld.getDolabel_user());
		}
	}

	public void downloadFile(HttpServletRequest req, int project_no, HttpServletResponse response) {
		String userid = (String)req.getSession().getAttribute("loginUserID");
		ArrayList<LabelingResult> results = ss.getMapper(ManageLabelingMapper.class).getResults(project_no, userid);
		System.out.println(results.size());
		OutputStream os = null;
		response.setHeader("Content-Disposition", "attachment;filename=" + "result.csv"+ ";");

		try {
			os = response.getOutputStream();
			for (LabelingResult labelingResult : results) {
				os.write(String.format("%s,%s\n", labelingResult.getData_no(), labelingResult.getLabel_result()).getBytes());
			}
			os.flush();
			os.close();
			response.flushBuffer();
		} catch (Exception e) {
			try {
				os.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			// TODO: handle exception
		}

	}
}
