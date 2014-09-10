package mk.ukim.finki.mp.roommates.repository;

import mk.ukim.finki.mp.roommates.model.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

	/**
	* Added function that gets the member by his e-mail
	* Input: String (e-mail)
	* Output: Member
	*/
	Member findByEmail(String email);

	/**
	* Added function that gets the member by his e-mail and password
	* Input: Strings (e-mail and password)
	* Output: Member
	*/
	Member findByEmailAndPassword(String email, String password);
	
}
