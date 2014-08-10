package mk.ukim.finki.mp.roommates.web.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
@RequestMapping("/data/rest/flats")
public class FlatResource {

	@Autowired
	private FlatService service;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Flat create(@RequestBody @Valid Flat entity) {
		service.save(entity);
		return entity;
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Flat> getAll() {
		Collection<Flat> flats = service.findAll();
		return new ArrayList<Flat>(flats);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Flat get(@PathVariable Long id, HttpServletResponse response) {
		Flat flat = service.findById(id);
		if (flat == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return flat;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		service.delete(id);
	}
}
