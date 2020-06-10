package com.myproject.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myproject.board.vo.TmpBoardVO;

@Repository
public class TBoardDaoImple implements TBoardDao{
	@Inject
	private SqlSession sqlSession;
	
	// 게시글 작성
	@Override
	public void write(TmpBoardVO tmpboardVO) throws Exception{
		sqlSession.insert("TmpBoardMapper.insert", tmpboardVO);
	}
	
	// 게시글 조회
	public List<TmpBoardVO> list(TmpBoardVO tmpboardVO) throws Exception{
		return sqlSession.selectList("TmpBoardMapper.list", tmpboardVO);
	}
	
	// 게시글 조회
	
	public List<TmpBoardVO> detaillist(String bno) throws Exception{ 
		return	sqlSession.selectList("TmpBoardMapper.DetailList", bno); 
	}
	
	//게시글 삭제
	public List<TmpBoardVO> delete(String bno) throws Exception{
		return sqlSession.selectList("TmpBoardMapper.delete", bno);
	}
}