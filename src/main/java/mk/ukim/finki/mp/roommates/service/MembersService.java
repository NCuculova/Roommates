package mk.ukim.finki.mp.roommates.service;

import java.util.List;

import mk.ukim.finki.mp.roommates.model.Member;

/**
 * Default functions defined in JPARepository.
 * Added functions explained in the adequate repository.
 */

public interface MembersService {

	public void save(Member entity);

	public List<Member> findAll();

	public Member findById(Long id);

	public void delete(Long id);

	public Member findByEmail(String email);

	public Member findByEmailAndPassword(String email, String password);
}
