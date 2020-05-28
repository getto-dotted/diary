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
	public List<BoardVO> list(BoardVO boardVO) throws Exception{
		return dao.list(boardVO);
	}
	
	//게시글 조회
	
	public List<BoardVO> detaillist(String bno) throws Exception{ 
		return	dao.detaillist(bno); 
	}
	
	//게시글 수정
	public List<BoardVO> update(BoardVO boardVO) throws Exception{
		return dao.update(boardVO);
	}
	 
}