package mk.ukim.finki.mp.roommates.service;

import java.util.List;

import mk.ukim.finki.mp.roommates.model.ListLook;

public interface ListLookService {

	public void save(ListLook entity);

	public List<ListLook> findAll();

	public ListLook findById(Long id);

	public void delete(Long id);
}
