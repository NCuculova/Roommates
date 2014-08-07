package mk.ukim.finki.mp.roommates.service.impl;

import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.mp.roommates.model.Member;
import mk.ukim.finki.mp.roommates.repository.MemberRepository;
import mk.ukim.finki.mp.roommates.service.MembersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembersServiceImpl implements MembersService {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public void save(Member entity) {
		memberRepository.save(entity);
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<Member>(memberRepository.findAll());
	}

	@Override
	public Member findById(Long id) {
		return memberRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		memberRepository.delete(id);
	}

}
