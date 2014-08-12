package mk.ukim.finki.mp.roommates.service;

import java.util.List;

import mk.ukim.finki.mp.roommates.model.FlatImage;

public interface FlatImageService {

	public void save(FlatImage entity);

	public List<FlatImage> findAll();

	public FlatImage findById(Long id);

	public void delete(Long id);

}