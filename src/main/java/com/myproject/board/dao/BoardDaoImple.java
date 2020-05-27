package com.myproject.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myproject.board.vo.BoardVO;

@Repository
public class BoardDaoImple implements BoardDao{
	@Inject
	private SqlSession sqlSession;
	
	// 게시글 작성
	@Override
	public void write(BoardVO boardVO) throws Exception{
		sqlSession.insert("BoardMapper.insert", boardVO);
	}
	
	// 게시글 조회
	public List<BoardVO> list() throws Exception{
		return sqlSession.selectList("BoardMapper.list");
	}
	
	// 게시글 조회
	/*
	 * public List<BoardVo> detaillist(String bno) throws Exception{ return
	 * sqlSession.DetailList("BoardMapper.list"); }
	 */
}