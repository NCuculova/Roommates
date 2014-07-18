package mk.ukim.finki.mp.roommates.service;

import java.util.List;

import mk.ukim.finki.mp.roommates.model.Member;

public interface MembersService {

	public void save(Member entity);

	public List<Member> findAll();

	public Member findById(Long id);

	public void delete(Long id);
}
