package mk.ukim.finki.mp.roommates.web.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import mk.ukim.finki.mp.roommates.model.Member;
import mk.ukim.finki.mp.roommates.service.MembersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/rest/members")
public class MembersResource {

	@Autowired
	private MembersService service;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> create(@RequestBody @Valid Member entity) {
		Map<String, Object> result = new HashMap<String, Object>();
		Member member = service.findByEmail(entity.getEmail());
		if (member != null) {
			result.put("success", false);
			result.put("result", member);
			result.put("message", "Duplicate email");
		} else {
			result.put("success", true);
			service.save(entity);
			result.put("result", entity);
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Member> getAll() {
		Collection<Member> members = service.findAll();
		return new ArrayList<Member>(members);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Member get(@PathVariable Long id, HttpServletResponse response) {
		Member member = service.findById(id);
		if (member == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return member;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		service.delete(id);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> login(@RequestBody @Valid Member entity) {
		Map<String, Object> result = new HashMap<String, Object>();
		Member member = service.findByEmailAndPassword(entity.getEmail(), entity.getPassword());
		if(member == null){
			result.put("success", false);
		}
		else{
			result.put("success", true);
			result.put("member", member);
		}
		return result;
	}
}
