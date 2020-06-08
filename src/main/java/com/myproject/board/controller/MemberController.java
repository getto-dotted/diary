package com.myproject.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.board.service.BoardService;
import com.myproject.board.service.MemberService;
import com.myproject.board.vo.BoardVO;
import com.myproject.board.vo.MemberVO;

@Controller
public class MemberController {

	@Inject
	MemberService service;
	@Inject
	BoardService service2;

	// 회원가입
	@RequestMapping(value = "save")
	public ModelAndView writeView(MemberVO memberVO, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();

		MemberVO vo = new MemberVO();

		vo.setMember_id(request.getParameter("member_id"));
		vo.setPassword(request.getParameter("member_password"));
		vo.setName(request.getParameter("member_name"));
		vo.setGender(Integer.parseInt(request.getParameter("member_gender")));

		String birth = request.getParameter("member_birth1") + "-" + request.getParameter("member_birth2") + "-"
				+ request.getParameter("member_birth3");
		vo.setBirth(birth);

		vo.setMail(request.getParameter("member_email"));
		vo.setPhone(request.getParameter("member_phone"));
		vo.setAddress(request.getParameter("member_address"));

		service.save(vo);

		mv.setViewName("redirect:/");

		return mv;
	}

	//로그아웃
	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		//세션영역을 비운 후 메인페이지로 이동한다.
		session.setAttribute("username", null);
		session.setAttribute("userid", null);
		return "redirect:/";
	}
	// 로그인
	@RequestMapping(value = "login")
	public ModelAndView loginMember(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();

		MemberVO vo = new MemberVO();

		vo.setMember_id(request.getParameter("member_id"));
		vo.setPassword(request.getParameter("password"));
		
		int memberChk = service.isMember(vo);
		
		if(memberChk>0) {
			List<MemberVO> list = service.login(vo);
			
			String username = "";
			String userid = "";
			
			for(MemberVO name:list) {
				username = name.getName();
				userid = name.getMember_id();
			}
			//세션영역에 유저이름 입력/ 아이디도 필요하게 될것 같은데../그러하다
			session.setAttribute("username", username);
			session.setAttribute("userid", userid);

			mv.addObject(list); 
			mv.setViewName("redirect:/");
			 

		}
		else {
			String errorMsg = "존재하지 않는 회원입니다.";
			System.out.println(errorMsg);
			mv.addObject(errorMsg);
		}
		
		return mv;
	}
	
	
	//회원탈퇴
	@RequestMapping(value = "secession")
	public String memberDelete(MemberVO vo, HttpSession se) throws Exception {
		//세션영역에 현재 저장된 아이디를 탈퇴시킨다.
		String member_id = (String) se.getAttribute("userid");
		
		vo.setMember_id(member_id);
		
		service.delete(vo);
		
		return "redirect:/";
	}
	
	//회원정보 수정
	@RequestMapping(value = "memberUpdate")
	public String memberUpdate(MemberVO vo, HttpSession se, HttpServletRequest req) throws Exception {
		
		String member_id = "";
		
		if(se.getAttribute("userid") != null) {
			member_id = (String) se.getAttribute("userid");
			
		}
		else {
			return "redirect:/";
		}
		
		vo.setMember_id(member_id);
		vo.setPassword(req.getParameter("member_password"));
		vo.setName(req.getParameter("member_name"));
		vo.setGender(Integer.parseInt(req.getParameter("member_gender")));
		vo.setMail(req.getParameter("member_email"));
		vo.setPhone(req.getParameter("member_phone"));
		vo.setAddress(req.getParameter("member_address"));
		String birth = req.getParameter("member_birth1") + "-" + req.getParameter("member_birth2") + "-"
				+ req.getParameter("member_birth3");
		vo.setBirth(birth);

		
		service.update(vo);
		
		return "redirect:/";
	}
}