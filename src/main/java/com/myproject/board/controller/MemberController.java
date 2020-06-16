package com.myproject.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.board.service.BoardService;
import com.myproject.board.service.MemberService;
import com.myproject.board.vo.MemberVO;

@Controller
public class MemberController {

	@Inject
	MemberService service;
	@Inject
	BoardService service2;

	// 회원가입
	@RequestMapping(value = "save", method=RequestMethod.POST)
	public ModelAndView writeView(MemberVO memberVO, HttpServletRequest request) throws Exception {
		
		
		ModelAndView mv = new ModelAndView();

		MemberVO vo = new MemberVO();

		vo.setMember_id(request.getParameter("member_id"));
		vo.setPassword(request.getParameter("member_password"));
		vo.setName(request.getParameter("member_name"));
		

		String birth = request.getParameter("member_birth1") + "-" + request.getParameter("member_birth2") + "-"
				+ request.getParameter("member_birth3");
		vo.setBirth(birth);

		vo.setMail(request.getParameter("member_email"));
		vo.setPhone(request.getParameter("member_phone"));
		vo.setAddress(request.getParameter("member_address"));		
		
		try {
			String imagePath = request.getSession().getServletContext().getRealPath("resources/image/");
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
			//업로드폼의 file속성 필드의 이름을 모두 읽어온다.
			Iterator<String> itr = mhsr.getFileNames();			
			//업로드된 파일명 처리를 위한 변수와 컬렉션 생성
			MultipartFile mfile = null;
			String fileName ="";
			List resultList = new ArrayList();
			
			//업로드폼의 file속성 필드갯수만큼 반복함(2회)
			while(itr.hasNext()) {
				fileName = (String)itr.next();
				
				//서버로 업로드된 임시파일명 가져오기
				mfile = mhsr.getFile(fileName);
				System.out.println("mfile="+mfile);
				
				//한글깨짐방지 처리후 업로드된 파일명을 가져옴
				String ofile = new String(mfile.getOriginalFilename().getBytes(),"UTF-8");
				if("".equals(ofile)) {
					/*
					만약 업로드된 파일명이 공백문자라면 업로드가 되지 않은 것으로 간주하고
					반복의 처음으로 이동한다.
					 */
					continue;
				}
				//파일의 확장자 가져오기
				String ext = ofile.substring(ofile.lastIndexOf('.'));
				//UUID를 통해 생성된 문자열과 확장자 결합
				String nfile = UUID.randomUUID().toString() +ext;
				/*
				File.separator : 윈도우와 리눅스는 서로 디렉토리를 구분하는
				기호가 다르므로, 해당 OS에 맞는 기호를 자동으로 붙여준다.
				윈도우는 \(역슬러시), 리눅스는 /(슬러시)를 사용하게 된다.
				 */
				File serverFullName = new File(imagePath+File.separator+nfile);
				//조립된 경로에 해당 파일 저장
				mfile.transferTo(serverFullName);
				//새로 저장된 파일명을 vo객체에 세팅
				vo.setProfilepic(nfile);				
				
				System.out.println("nfile="+nfile);
				System.out.println("imagePath="+imagePath);
				
				vo.setGender(Integer.parseInt(request.getParameter("member_gender")));
			}	
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("파일업로드 관련 오류");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("알 수 없는 오류");
		}

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
			String profilepic = "";
			
			for(MemberVO name:list) {
				username = name.getName();
				userid = name.getMember_id();
				profilepic = name.getProfilepic();
			}
			//세션영역에 유저이름 입력/ 아이디도 필요하게 될것 같은데../그러하다
			session.setAttribute("username", username);
			session.setAttribute("userid", userid);
			session.setAttribute("profilepic", profilepic);

			mv.addObject(list); 
			mv.setViewName("writeView");
			 

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