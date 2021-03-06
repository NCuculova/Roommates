package mk.ukim.finki.mp.roommates.web.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import mk.ukim.finki.mp.roommates.model.MemberProfile;
import mk.ukim.finki.mp.roommates.service.MemberProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController with adequate CRUD methods for MemberProfile 
 * Added method for getting the profile of a Member by a given Member ID
 */


@RestController
@RequestMapping("/data/rest/memberProfile")
public class MemberProfileResource {

	@Autowired
	private MemberProfileService service;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public MemberProfile create(@RequestBody @Valid MemberProfile entity) {
		service.save(entity);
		return entity;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<MemberProfile> getAll() {
		Collection<MemberProfile> memberProfiles = service.findAll();
		return new ArrayList<MemberProfile>(memberProfiles);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public MemberProfile get(@PathVariable Long id, HttpServletResponse response) {
		MemberProfile memberProfile = service.findById(id);
		if (memberProfile == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return memberProfile;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		service.delete(id);
	}
	
	/**
	 * Method for getting the profile of a Member by a given Member ID
	 * Input: Member ID
	 * Output: Member Profile
	 */
	@RequestMapping(value = "/member/{id}", method = RequestMethod.GET, produces = "application/json")
	public MemberProfile getByMemberId(@PathVariable Long id, HttpServletResponse response) {
		MemberProfile memberProfile = service.findByMemberId(id);
		if (memberProfile == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return memberProfile;
	}
}
