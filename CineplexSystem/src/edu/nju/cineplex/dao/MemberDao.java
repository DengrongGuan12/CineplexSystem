package edu.nju.cineplex.dao;

import java.util.List;

import edu.nju.cineplex.model.Member;

public interface MemberDao {
	public boolean insert(Member member);
	public List<Member> getAllMembers();
	public String getPasswdByEmail(String email);
	public Member getMemberByEmail(String email);
	public Member getMemberById(int id);
	public Member getMemberByCardId(String cardId);
	public void update(Member member);
}
