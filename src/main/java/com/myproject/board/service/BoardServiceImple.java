package com.myproject.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myproject.board.dao.BoardDao;
import com.myproject.board.vo.BoardVO;

@Service
public class BoardServiceImple implements BoardService{
	
	@Inject
	private BoardDao dao;
	
	//게시글 작성
	@Override
	public void write (BoardVO boardVO) throws Exception{
		dao.write(boardVO);
	}
	
	//게시글 조회
	public List<BoardVO> list() throws Exception{
		return dao.list();
	}
	
	//게시글 조회
	/*
	 * public List<BoardVO> detaillist(String bno) throws Exception{ return
	 * dao.detaillist(bno); }
	 */
}