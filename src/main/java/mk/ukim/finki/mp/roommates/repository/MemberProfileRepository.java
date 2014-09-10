
package mk.ukim.finki.mp.roommates.repository;

import mk.ukim.finki.mp.roommates.model.MemberProfile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long> {

	/**
	* Added function that gets the profile of a member by his ID 
	* Input: Member ID
	* Output: Member Profile
	*/
	MemberProfile findByMemberId(Long id);
}