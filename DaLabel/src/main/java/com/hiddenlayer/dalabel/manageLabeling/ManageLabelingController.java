package com.hiddenlayer.dalabel.manageLabeling;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hiddenlayer.dalabel.dolabeling.LabelDoList;

@Controller
public class ManageLabelingController {

	@Autowired
	private ManageLabelingDAO mlDAO;

	@RequestMapping(value = "/reg.needlogin.labeling.go", method = RequestMethod.GET)
	public String goRegLabeling(@RequestParam(value = "bundle_name") String bundle_name,
			@RequestParam(value = "bundle_no") BigDecimal bundle_no, HttpServletRequest req) {
		req.setAttribute("bundle_name", bundle_name);
		req.setAttribute("bundle_no", bundle_no);
		req.setAttribute("contentPage", "manage_labeling/reg_labeling.jsp");
		return "index";
	}

	@RequestMapping(value = "/reg.needlogin.labeling.do", method = RequestMethod.POST)
	public String doRegLabeling(LabelingProject lp, HttpServletRequest req) {
		mlDAO.regLabelingProject(lp, req);
		req.setAttribute("contentPage", "manage_labeling/manage_labeling.jsp");
		mlDAO.getUploadedProject(1, req);
		return "index";
	}

	@RequestMapping(value = "/get.needlogin.myUpload.labeling", method = RequestMethod.GET)
	public String getManageLabeling(HttpServletRequest req) {
		mlDAO.getUploadedProject(1, req);
		req.setAttribute("contentPage", "manage_labeling/manage_labeling.jsp");
		return "index";
	}

	@RequestMapping(value = "project.needlogin.page.change", method = RequestMethod.GET)
	public String pageChangeProject(@RequestParam(value = "page") int page, HttpServletRequest req) {
		mlDAO.getUploadedProject(page, req);
		req.setAttribute("contentPage", "manage_labeling/manage_labeling.jsp");
		return "index";
	}

	@RequestMapping(value = "/get.needlogin.detail.myproject", method = RequestMethod.GET)
	public String goDetailProject(HttpServletRequest req) {
		mlDAO.getMyDeatilProject(req);
		req.setAttribute("contentPage", "manage_labeling/manage_detail_labeling.jsp");
		return "index";
	}

	@RequestMapping(value = "/update.needlogin.project.accessLevel", method = RequestMethod.GET)
	public String updateAccessLevel(LabelingProject lp, HttpServletRequest req) {
		mlDAO.updateProjectAccessLevel(lp, req);
		mlDAO.getUploadedProject(1, req);
		req.setAttribute("contentPage", "manage_labeling/manage_labeling.jsp");
		return "index";
	}

	@RequestMapping(value = "/manage.needlogin.labeling.user", method = RequestMethod.GET)
	public String getLabelingUser(@RequestParam(value = "project_no") int project_no, HttpServletRequest req) {
		mlDAO.select(project_no, req);
		return "manage_labeling/manage_labeling_user";
	}

	@RequestMapping(value = "/update.needlogin.user.access", method = RequestMethod.GET)
	public String updateUserLevel(@RequestParam(value = "dolabel_project_no") int project_no, LabelDoList ld, HttpServletRequest req) {
		mlDAO.changeUserAccess(ld, req); // change
		mlDAO.select(project_no, req);
		return "manage_labeling/manage_labeling_user";
	}
	@RequestMapping(value = "/download.needlogin.projectresult", method = RequestMethod.GET)
	public void downloadProjectResult(HttpServletRequest req, @RequestParam(value="project_no")int project_no, HttpServletResponse res) {
		mlDAO.downloadFile(req, project_no, res);
	}

	@RequestMapping(value = "/want.needlogin.labeling.result", method = RequestMethod.GET)
	public @ResponseBody String getResult(HttpServletRequest req, @RequestParam(value = "project_no") int no) {
		return mlDAO.getResult(req, no);
	}
	
}
