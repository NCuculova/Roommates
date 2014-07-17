package mk.ukim.finki.mp.roommates.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import mk.ukim.finki.mp.roommates.model.Member;
import mk.ukim.finki.mp.roommates.repository.MemberRepository;
import mk.ukim.finki.mp.roommates.service.MembersService;

public class MembersServiceImpl implements MembersService{
	
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public void saveMember(Member member) {
		memberRepository.save(member);
		
	}

}
