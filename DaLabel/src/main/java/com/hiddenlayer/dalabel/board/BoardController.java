package com.hiddenlayer.dalabel.board;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hiddenlayer.dalabel.TokenGenerator;
import com.hiddenlayer.dalabel.member.MemberDAO;

@Controller
public class BoardController {

	@Autowired
	private MemberDAO mDAO;

	@Autowired
	private BoardDAO bDAO;

	@RequestMapping(value = "/board.go", method = RequestMethod.GET)
	public String boardLists(HttpServletRequest req) {
		if (mDAO.isLogined(req)) {
			bDAO.clearSearch(req);
			bDAO.getAllPost(1, req);
			TokenGenerator.generate(req);
			return "community_board/lists";
//			req.setAttribute("contentPage", "community_board/lists.jsp");
		} else {
			return "home";
//			req.setAttribute("contentPage", "home.jsp");
		}
//		return "home";
	}

	@RequestMapping(value = "/board.post.delete", method = RequestMethod.GET)
	public String deletePost(@RequestParam(value = "board_id") BigDecimal board_id, HttpServletRequest req) {
		if (mDAO.isLogined(req)) {
			bDAO.deletePost(board_id, req);
			bDAO.clearSearch(req);
			bDAO.getAllPost(1, req);
			TokenGenerator.generate(req);
//			req.setAttribute("contentPage", "community_board/detail.jsp");
			return "community_board/lists";
		} else {
//			req.setAttribute("contentPage", "home.jsp");
			return "home";
		}
//		return "home";
	}

	@RequestMapping(value = "/board.page.change", method = RequestMethod.GET)
	public String writePageChange(@RequestParam(value = "p") int p, HttpServletRequest req) {
		if (mDAO.isLogined(req)) {
			bDAO.getAllPost(p, req);
			TokenGenerator.generate(req);
//			req.setAttribute("contentPage", "community_board/write.jsp");
			return "community_board/write";
		} else {
//			req.setAttribute("contentPage", "home.jsp");
			return "home";
		}
//		return "home";
	}

	@RequestMapping(value = "/board.get.detail", method = RequestMethod.GET)
	public String getDetailBoard(@RequestParam(value = "board_id") int board_id, HttpServletRequest req) {
		bDAO.getDetailBoard(board_id, req);
		return "community_board/details";
	}
	
	@RequestMapping(value = "/board.post.write.go", method = RequestMethod.GET)
	public String goWritePost() {
		return "community_board/write";
	}
	
	@RequestMapping(value = "/board.post.write.do", method = RequestMethod.POST)
	public String writePost(Board b, HttpServletRequest req) {
		if (mDAO.isLogined(req)) {
			bDAO.writePost(b, req);
			bDAO.clearSearch(req);
			bDAO.getAllPost(1, req);
			TokenGenerator.generate(req);
//			req.setAttribute("contentPage", "community_board/write.jsp");
			return "community_board/lists";
		} else {
//			req.setAttribute("contentPage", "home.jsp");
		}
		return "home";
	}

	@RequestMapping(value = "/board.search", method = RequestMethod.GET)
	public String writeSearch(@RequestParam(value = "search") String search, HttpServletRequest req) {
		if (mDAO.isLogined(req)) {
			bDAO.search(search, req);
			bDAO.getAllPost(1, req);
			TokenGenerator.generate(req);
//			req.setAttribute("contentPage", "community_board/lists.jsp");
			return "community_board/lists";
		} else {
//			req.setAttribute("contentPage", "home.jsp");
			return "home";
		}
//		return "home";
	}

	@RequestMapping(value = "/board.comment.write", method = RequestMethod.GET)
	public String writeComment(BoardComment bc, HttpServletRequest req) {
		if (mDAO.isLogined(req)) {
			bDAO.writeComment(bc, req);
//			bDAO.clearSearch(req);
//			bDAO.getAllPost(1, req);
			TokenGenerator.generate(req);
//			req.setAttribute("contentPage", "community_board/detail.jsp");
			return "community_board/detail";
		} else {
//			req.setAttribute("contentPage", "home.jsp");
			return "home";
		}
//		return "home";
	}

}
