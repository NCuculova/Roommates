
package mk.ukim.finki.mp.roommates.repository;

import mk.ukim.finki.mp.roommates.model.MemberProfile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long> {
	
}