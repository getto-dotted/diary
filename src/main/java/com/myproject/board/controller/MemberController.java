package com.myproject.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.board.service.MemberService;
import com.myproject.board.vo.MemberVO;

@Controller
public class MemberController {

	@Inject
	MemberService service;

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

		mv.setViewName("writeView");

		return mv;
	}

	// 로그인
	@RequestMapping(value = "login")
	public ModelAndView loginMember(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();

		MemberVO vo = new MemberVO();

		vo.setMember_id(request.getParameter("member_id"));
		vo.setPassword(request.getParameter("password"));

		List<MemberVO> list = service.login(vo);
		if (list == null) {
			// 로그인에 실패한 경우
			mv.addObject("LoginNg", "아이디/패스워드가 틀렸습니다.");

			mv.setViewName("redirect:/save");
			System.out.println("로그인 실패");

		} else {

			System.out.println("로그인 성공");
			mv.setViewName("redirect:/");

		}
		
		mv.addObject(list); 
		mv.setViewName("redirect:writeView");
		 

		return mv;
	}
}