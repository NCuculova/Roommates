package mk.ukim.finki.mp.roommates.repository;

import mk.ukim.finki.mp.roommates.model.Flat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlatRepository extends JpaRepository<Flat, Long> {
	
}
