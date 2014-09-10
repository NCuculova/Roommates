package mk.ukim.finki.mp.roommates.repository;

import java.util.List;

import mk.ukim.finki.mp.roommates.model.FlatImage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlatImageRepository extends JpaRepository<FlatImage, Long> {

	/**
	* Added function that gets the flat images from the given flat ID
	* Input: Flat ID
	* Output: List of Flat Images
	*/
	List<FlatImage> getImagesByFlatId(Long id);
	
}
