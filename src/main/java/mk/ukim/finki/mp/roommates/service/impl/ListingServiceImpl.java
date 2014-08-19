package mk.ukim.finki.mp.roommates.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import mk.ukim.finki.mp.roommates.model.Listing;
import mk.ukim.finki.mp.roommates.repository.ListingRepository;
import mk.ukim.finki.mp.roommates.service.ListingService;

import org.springframework.beans.factory.annotation.Autowired;
@Service
public class ListingServiceImpl implements ListingService{

	@Autowired
	private ListingRepository listingRepository;

	@Override
	public void save(Listing entity) {
		listingRepository.save(entity);
		
	}

	@Override
	public List<Listing> findAll() {
		return listingRepository.findAll();
	}

	@Override
	public Listing findById(Long id) {
		return listingRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		listingRepository.delete(id);
	}

	@Override
	public List<Listing> findAllByMemberId(Long id) {
		return listingRepository.findAllByMemberId(id);
	}
}
