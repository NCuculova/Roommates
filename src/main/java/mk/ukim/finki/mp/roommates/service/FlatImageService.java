package mk.ukim.finki.mp.roommates.service;

import java.util.List;

import mk.ukim.finki.mp.roommates.model.FlatImage;


/**
 * Default functions defined in JPARepository.
 * Added function explained in the adequate repository.
 */

public interface FlatImageService {

	public void save(FlatImage entity);

	public List<FlatImage> findAll();

	public FlatImage findById(Long id);

	public void delete(Long id);

	public List<FlatImage> getImagesByFlatId(Long id);


}
