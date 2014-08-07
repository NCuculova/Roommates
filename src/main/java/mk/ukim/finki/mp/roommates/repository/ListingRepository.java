package mk.ukim.finki.mp.roommates.repository;

import mk.ukim.finki.mp.roommates.model.Listing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listing, Long> {
	
}
