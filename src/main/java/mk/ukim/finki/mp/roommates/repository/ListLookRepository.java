package mk.ukim.finki.mp.roommates.repository;

import java.util.List;

import mk.ukim.finki.mp.roommates.model.ListLook;



//import mk.ukim.finki.mp.roommates.model.Flat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListLookRepository extends JpaRepository<ListLook, Long> {
	
	/**
	* Added function that gets bookmarked flat by a given member and listing ID 
	* Input: Member ID and Listing ID
	* Output: Bookmarked flat
	*/
	ListLook findByMemberIdAndListingId(Long id, Long id2);

	/**
	* Added function that gets all flats bookmarked by a given member 
	* Input: Member ID
	* Output: List of bookmarked flats
	*/
	List<ListLook> findByMemberId(Long id);
}
