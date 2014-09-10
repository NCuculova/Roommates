package mk.ukim.finki.mp.roommates.service;

import java.util.List;

import mk.ukim.finki.mp.roommates.model.MemberProfile;

/**
 * Default functions defined in JPARepository.
 * Added function explained in the adequate repository.
 */

public interface MemberProfileService {

	public void save(MemberProfile entity);

	public List<MemberProfile> findAll();

	public MemberProfile findById(Long id);

	public void delete(Long id);

	public MemberProfile findByMemberId(Long id);
}
