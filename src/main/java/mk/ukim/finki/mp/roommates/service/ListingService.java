package mk.ukim.finki.mp.roommates.service;

import java.util.List;

import mk.ukim.finki.mp.roommates.model.Listing;

public interface ListingService {
	
	public void save(Listing entity);

	public List<Listing> findAll();

	public Listing findById(Long id);

	public void delete(Long id);
	
	public List<Listing> findAllByMemberId(Long id);

}
