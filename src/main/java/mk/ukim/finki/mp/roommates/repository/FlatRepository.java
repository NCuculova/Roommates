package mk.ukim.finki.mp.roommates.repository;

import java.util.List;

import mk.ukim.finki.mp.roommates.model.Flat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlatRepository extends JpaRepository<Flat, Long> {
	/**
	* Added function that gets all flats of a given member 
	* Input: Member ID
	* Output: List of Flats
	*/
	List<Flat> findAllByMemberId(Long id);
}
