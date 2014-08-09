package mk.ukim.finki.mp.roommates.web.rest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import mk.ukim.finki.mp.roommates.model.Flat;
import mk.ukim.finki.mp.roommates.service.FlatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/rest/flat")
public class FlatResource {

	@Autowired
	private FlatService service;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Flat create(@RequestBody @Valid Flat entity) {
		service.save(entity);
		return entity;
	}

	/*
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
	 */	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		service.delete(id);
	}
}
