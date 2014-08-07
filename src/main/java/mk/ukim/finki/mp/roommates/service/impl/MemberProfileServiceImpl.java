package mk.ukim.finki.mp.roommates.service.impl;

import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.mp.roommates.model.Member;
import mk.ukim.finki.mp.roommates.model.MemberProfile;
import mk.ukim.finki.mp.roommates.repository.MemberProfileRepository;
import mk.ukim.finki.mp.roommates.service.MemberProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberProfileServiceImpl implements MemberProfileService {

	@Autowired
	private MemberProfileRepository memberProfileRepository;

	@Override
	public void save(MemberProfile entity) {
		memberProfileRepository.save(entity);
	}

	@Override
	public List<MemberProfile> findAll() {
		return new ArrayList<MemberProfile>(memberProfileRepository.findAll());
	}

	@Override
	public MemberProfile findById(Long id) {
		return memberProfileRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		memberProfileRepository.delete(id);
	}

}
