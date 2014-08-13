package mk.ukim.finki.mp.roommates.service.impl;

import java.util.List;

import mk.ukim.finki.mp.roommates.model.FlatImage;
import mk.ukim.finki.mp.roommates.repository.FlatImageRepository;
import mk.ukim.finki.mp.roommates.service.FlatImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlatImageServiceImpl  implements FlatImageService{

	@Autowired
	private FlatImageRepository flatImageRepository ;
	
	@Override
	public void save(FlatImage entity) {
		flatImageRepository.save(entity);
	}

	@Override
	public List<FlatImage> findAll() {
		return flatImageRepository.findAll();
	}

	@Override
	public FlatImage findById(Long id) {
		return flatImageRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		flatImageRepository.delete(id);
	}

	@Override
	public List<FlatImage> getImagesByFlatId(Long id) {
		return flatImageRepository.getImagesByFlatId(id);
	}

}
