package mk.ukim.finki.mp.roommates.repository;

import mk.ukim.finki.mp.roommates.model.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
}
