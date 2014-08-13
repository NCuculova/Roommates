package mk.ukim.finki.mp.roommates.repository;

import java.util.List;

import mk.ukim.finki.mp.roommates.model.FlatImage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlatImageRepository extends JpaRepository<FlatImage, Long> {

	List<FlatImage> getImagesByFlatId(Long id);
	
}
