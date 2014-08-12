package mk.ukim.finki.mp.roommates.service;

import java.util.List;

import mk.ukim.finki.mp.roommates.model.Flat;
import mk.ukim.finki.mp.roommates.model.Member;

public interface FlatService {
	
	public void save(Flat entity);

	public List<Flat> findAll();

	public Flat findById(Long id);

	public void delete(Long id);
	
	public List<Flat> findFlatsByMember(Member member);

}
