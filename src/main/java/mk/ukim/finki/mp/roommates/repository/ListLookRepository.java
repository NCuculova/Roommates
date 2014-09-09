package mk.ukim.finki.mp.roommates.repository;

import mk.ukim.finki.mp.roommates.model.ListLook;


//import mk.ukim.finki.mp.roommates.model.Flat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListLookRepository extends JpaRepository<ListLook, Long> {

	ListLook findByMemberIdAndListingId(Long id, Long id2);
	
	
}
