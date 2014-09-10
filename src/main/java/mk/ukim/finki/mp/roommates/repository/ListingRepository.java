package mk.ukim.finki.mp.roommates.repository;

import mk.ukim.finki.mp.roommates.model.Listing;
import java.util.List;
//import mk.ukim.finki.mp.roommates.model.Flat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listing, Long> {
	/**
	* Added function that gets all listings of a given member 
	* Input: Member ID
	* Output: List of Listings
	*/
	List<Listing> findAllByMemberId(Long id);
}
