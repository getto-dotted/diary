package com.myproject.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myproject.board.dao.MemberDao;
import com.myproject.board.vo.MemberVO;

@Service
public class MemberServiceImple implements MemberService{
	
	@Inject
	private MemberDao dao;
	
	//회원가입
	@Override
	public void save(MemberVO memberVO) throws Exception{
		dao.save(memberVO);
	}
	
	//회원정보 수정
	public int update(MemberVO memberVO) throws Exception{
		return dao.update(memberVO);
	}
	
	//회원정보 탈퇴
	public int delete(String member_no) throws Exception{
		return dao.delete(member_no);
	}
	
	//로그인
	public List<MemberVO> login(MemberVO memberVO) throws Exception{
		return dao.login(memberVO);
	}
}