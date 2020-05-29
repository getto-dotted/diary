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
	public List<BoardVO> list(BoardVO boardVO) throws Exception{
		return sqlSession.selectList("BoardMapper.list", boardVO);
	}
	
	// 게시글 조회
	
	public List<BoardVO> detaillist(String bno) throws Exception{ 
		return	sqlSession.selectList("BoardMapper.DetailList", bno); 
	}

	//게시글 수정
	public List<BoardVO> update(BoardVO boardVO) throws Exception{
		return sqlSession.selectList("BoardMapper.update", boardVO);
	}
	
	//게시글 삭제
	public List<BoardVO> delete(String bno) throws Exception{
		return sqlSession.selectList("BoardMapper.delete", bno);
	}
}