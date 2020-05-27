package com.myproject.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.codec.binary.Base64;

import java.io.FileOutputStream;

import com.myproject.board.service.BoardService;
import com.myproject.board.vo.BoardVO;
import java.util.List;
import java.util.UUID;

@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	BoardService service;

	// 게시판 글 화면 진입
	@RequestMapping(value = "/")
	public ModelAndView writeView(BoardVO boardVO) throws Exception {
		ModelAndView mv = new ModelAndView();

		List<BoardVO> list = service.list();

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

		System.out.println("title :: " + title);
		System.out.println("content :: " + content);
		System.out.println("writer :: " + writer);
		System.out.println("filepathurl :: " + filepathurl);
		// 파일 인코딩 시작
		filepathtrue = filepath(filepathurl);
		System.out.println("filepathtrue :: " + filepathtrue);

		boardVO.setTitle(title);
		boardVO.setContent(content);
		boardVO.setWriter(writer);
		boardVO.setFilepath(filepathtrue);

		service.write(boardVO);

		mv.setViewName("board/writeView");

		return mv;
	}

	/*
	 * @RequestMapping(value = "detailwriteView") public ModelAndView
	 * DetailWrite(BoardVO VO, HttpServletRequest request) throws Exception {
	 * ModelAndView mv = new ModelAndView();
	 * 
	 * String bno = request.getParameter("bno");
	 * 
	 * service.detaillist(bno);
	 * 
	 * mv.setViewName("board/writeView");
	 * 
	 * return mv; }
	 */

	public String filepath(String filepathurl) throws Exception {
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
					"C:\\Users\\a\\Desktop\\workspace\\Board\\src\\main\\webapp\\resources\\image\\"
							+ fileName + ".png");
			stream.write(file);
			stream.close();
			savefile = fileName + ".png";
			System.out.println("파일 작성 완료");
			System.out.println("파일 생성 경로" + savefile);
		} catch (Exception e) {
			System.out.println("파일이 정상적으로 넘어오지 않았습니다");
		} finally {
			stream.close();
		}
		return savefile;
	}

	// 회원가입 페이지 이동
	@RequestMapping(value = "signUp")
	public ModelAndView signup() throws Exception {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("signUp");

		return mv;
	}
}