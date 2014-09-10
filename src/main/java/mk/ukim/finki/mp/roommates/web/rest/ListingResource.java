package mk.ukim.finki.mp.roommates.web.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import mk.ukim.finki.mp.roommates.model.Listing;
import mk.ukim.finki.mp.roommates.service.ListingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController with adequate CRUD methods for Listing 
 * Added method for getting list of Listings by a given Member ID
 */

@RestController
@RequestMapping("/data/rest/listings")
public class ListingResource {

	@Autowired
	private ListingService service;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Listing create(@RequestBody @Valid Listing entity) {
		service.save(entity);
		return entity;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Listing> getAll() {
		Collection<Listing> listings = service.findAll();
		return new ArrayList<Listing>(listings);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Listing get(@PathVariable Long id, HttpServletResponse response) {
		Listing listing = service.findById(id);
		if (listing == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return listing;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		service.delete(id);
	}

	
	/**
	 * Method for getting list of Listings by a given Member ID
	 * Input: Member ID
	 * Output: List of Listings
	 */
	
	@RequestMapping(value = "/member/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<Listing> findAllByMemberId(@PathVariable Long id, HttpServletResponse response) {
		return service.findAllByMemberId(id);
	}
	
}
