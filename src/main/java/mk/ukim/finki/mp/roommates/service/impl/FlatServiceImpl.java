package mk.ukim.finki.mp.roommates.service.impl;

import java.util.List;

import mk.ukim.finki.mp.roommates.model.Flat;
import mk.ukim.finki.mp.roommates.repository.FlatRepository;
import mk.ukim.finki.mp.roommates.service.FlatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of adequate service.
 */

@Service
public class FlatServiceImpl  implements FlatService{

	@Autowired
	private FlatRepository flatRepository ;
	
	@Override
	public void save(Flat entity) {
		flatRepository.save(entity);
	}

	@Override
	public List<Flat> findAll() {
		return flatRepository.findAll();
	}

	@Override
	public Flat findById(Long id) {
		return flatRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		flatRepository.delete(id);
	}

	@Override
	public List<Flat> findAllByMemberId(Long id) {
		return flatRepository.findAllByMemberId(id);
	}

	

}
