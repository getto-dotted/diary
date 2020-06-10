package com.myproject.board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.board.service.BoardService;
import com.myproject.board.service.MemberService;
import com.myproject.board.service.TBoardService;
import com.myproject.board.vo.BoardVO;
import com.myproject.board.vo.MemberVO;

@Controller
public class BoardController {

	//private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	BoardService service;
	@Inject
	MemberService service2;
	@Inject
	TBoardService service3;

	// 게시판 글 화면 진입
	@RequestMapping(value = "/")
	public ModelAndView writeView(BoardVO boardVO, HttpSession se) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		if(se.getAttribute("userid")!=null) {
			boardVO.setWriter((String)se.getAttribute("userid"));
		}
		
		List<BoardVO> list = service.list(boardVO);

		mv.addObject("list", list);
		mv.setViewName("writeView");

		return mv;
	}

	// 게시판 글 작성
	@RequestMapping(value = "write")
	public ModelAndView SaveWrite(BoardVO boardVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ModelAndView mv = new ModelAndView();

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		// 인코딩 되기 전 파일 경로
		String filepathurl = request.getParameter("filepathurl");
		// 인코딩 된 파일 경로 담을 변수 초기화
		String filepathtrue = null;

		String imagePath = request.getSession().getServletContext().getRealPath("resources/image/");
		
		System.out.println("title :: " + title);
		System.out.println("content :: " + content);
		System.out.println("writer :: " + writer);
		System.out.println("filepathurl :: " + filepathurl);
		// 파일 인코딩 시작
		filepathtrue = filepath(filepathurl,imagePath);
		System.out.println("filepathtrue :: " + filepathtrue);

		boardVO.setTitle(title);
		boardVO.setContent(content);
		boardVO.setWriter(writer);
		boardVO.setFilepath(filepathtrue);

		service.write(boardVO);

		mv.setViewName("board/writeView");

		return mv;
	}
	
	//게시글 수정하기
	@RequestMapping(value = "update")
	public String modify(BoardVO VO, HttpServletRequest req, HttpSession se)throws Exception{
		
		String bno1 = req.getParameter("bno");
		String content = req.getParameter("content");
		int bno = Integer.parseInt(bno1);
		
		VO.setBno(bno);
		VO.setContent(content);
		
		service.update(VO);

		return "detailwriteView";
	}
	//게시글 상세보기
	@RequestMapping(value = "/detailwriteView") 
	public ModelAndView DetailWrite(BoardVO VO, HttpServletRequest request, HttpSession se) 
			 throws Exception {
	 
	ModelAndView mv = new ModelAndView();
	 
	if(se.getAttribute("userid")==null) {
		 mv.setViewName("redirect:/");
		 
		 return mv; 
	}
	
	String bno = request.getParameter("bno");
	List<BoardVO> list = service.detaillist(bno);
	 
	VO.setWriter((String)se.getAttribute("userid"));
	List<BoardVO> list2 = service.list(VO);
	 
	mv.addObject("list2", list);
	mv.addObject("list", list2);
	mv.setViewName("detailwriteView");
	 
	return mv; 
	}
	 
		
	//파일경로설정
	public String filepath(String filepathurl, String imagePath) throws Exception {
		String binaryData = filepathurl;
		String savefile = null;
		FileOutputStream stream = null;
		
		
		
		try {
			System.out.println("binary file " + binaryData);
			if (binaryData == null || binaryData == "") {
				throw new Exception();
			}
			binaryData = binaryData.replaceAll("data:image/png;base64,", "");
			byte[] file = Base64.decodeBase64(binaryData);
			System.out.println("file :::::::: " + file + " || " + file.length);
			String fileName = UUID.randomUUID().toString();
			System.out.println("fileName :::::::: " + UUID.randomUUID().toString());
			
			stream = new FileOutputStream(
					imagePath+fileName+".png");
			stream.write(file);
			stream.close();
			savefile = fileName + ".png";
			System.out.println("파일 작성 완료");
			System.out.println("파일 생성 경로" + savefile);
			
			System.out.println("파일경로"+imagePath+fileName+".png");	
			System.out.println(new File(imagePath+fileName+".png").exists());	
			
		} catch (Exception e) {
			System.out.println("파일이 정상적으로 넘어오지 않았습니다");
		} finally {
			stream.close();
		}
		return savefile;
	}

	//게시글 삭제하기
	@RequestMapping(value="delete")
	public ModelAndView delete(ModelAndView mv, BoardVO vo, HttpServletRequest req, HttpSession se) throws Exception {
		
		mv.setViewName("redirect:/");
		
		if(se.getAttribute("userid")==null) {
			return mv;
		}
		
		String bno = req.getParameter("bno");
		List<BoardVO> list = service.detaillist(bno);
		
		int dbBno = 0;
		for(BoardVO dto:list) {
			dbBno = dto.getBno();
		}
		
		int bno2 = Integer.parseInt(bno);
		
		if(dbBno == bno2) {			
			service.delete(bno);			
			return mv;
		}		
		
		return mv;
	}
	
	
	// 회원가입 페이지 이동
	@RequestMapping(value = "signUp")
	public ModelAndView signup() throws Exception {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("signUp");

		return mv;
	}
	
	//회원정보 수정 페이지 이동
	@RequestMapping(value = "memberinfo")
	public ModelAndView memberinfo(ModelAndView mv, MemberVO vo, HttpServletRequest req, HttpSession se) throws Exception {
		
		String member_id = "";
		
		if(se.getAttribute("userid") != null) {
			member_id = (String) se.getAttribute("userid");
			
		}
		else {
			mv.setViewName("redirect:/");
		}
		
		vo.setMember_id(member_id);
		
		List<MemberVO> list = service2.memberinfo(vo);
		
		mv.addObject("list", list);
		mv.setViewName("memberinfo");
		
		return mv;
	}
}