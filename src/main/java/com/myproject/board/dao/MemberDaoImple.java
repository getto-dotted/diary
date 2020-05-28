package com.myproject.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myproject.board.vo.MemberVO;

@Repository
public class MemberDaoImple implements MemberDao{
	@Inject
	private SqlSession sqlSession;
	
	//회원가입
	@Override
	public void save(MemberVO memberVO) throws Exception{
		sqlSession.insert("MemberMapper.insert", memberVO);
	}
	
	//회원정보 수정
	@Override
	public int update(MemberVO memberVO) throws Exception{
		return sqlSession.update("MemberMapper.update");
	}
	
	//회원정보 탈퇴
	@Override
	public int delete(String member_no) throws Exception{ 
		return sqlSession.delete("MemberMapper.delete"); 
	}
	
	//로그인
	@Override
	public List<MemberVO> login(MemberVO memberVO) throws Exception{ 
		return sqlSession.selectList("MemberMapper.login",memberVO);  
	}
	
	//회원조회
	public int isMember(MemberVO memberVO) throws Exception{
		return sqlSession.selectOne("MemberMapper.isMember", memberVO);
	}
}