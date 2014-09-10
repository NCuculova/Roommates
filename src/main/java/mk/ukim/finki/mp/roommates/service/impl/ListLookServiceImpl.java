package mk.ukim.finki.mp.roommates.service.impl;

import java.util.List;

import mk.ukim.finki.mp.roommates.model.ListLook;
import mk.ukim.finki.mp.roommates.repository.ListLookRepository;
import mk.ukim.finki.mp.roommates.service.ListLookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ListLookServiceImpl implements ListLookService{

	@Autowired
	private ListLookRepository listLookRepository;

	@Override
	public void save(ListLook entity) {
		listLookRepository.save(entity);
	}

	@Override
	public List<ListLook> findAll() {
		return listLookRepository.findAll();
	}

	@Override
	public ListLook findById(Long id) {
		return listLookRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		listLookRepository.delete(id);
	}

	@Override
	public ListLook findByMemberIdAndListingId(Long id, Long id2) {
		return listLookRepository.findByMemberIdAndListingId(id,id2);
	}

	@Override
	public List<ListLook> findAllByMemberId(Long id) {
		return listLookRepository.findByMemberId(id);
	}

	
}
