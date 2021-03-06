package mk.ukim.finki.mp.roommates.web.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import mk.ukim.finki.mp.roommates.model.ListLook;
import mk.ukim.finki.mp.roommates.service.ListLookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController with adequate CRUD methods for Bookmarked Listings 
 * Added method for getting list of Bookmarked Listings by a given Member ID
 */


@RestController
@RequestMapping("/data/rest/listingslook")
public class ListLookResource {

	@Autowired
	private ListLookService service;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ListLook create(@RequestBody @Valid ListLook entity) {
		entity.setDate(new Date());
		ListLook list =service.findByMemberIdAndListingId(entity.getMember().getId(),entity.getListing().getId());
		if (list == null) {
			service.save(entity);
		}
		return entity;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<ListLook> getAll() {
		Collection<ListLook> listingsLook = service.findAll();
		return new ArrayList<ListLook>(listingsLook);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ListLook get(@PathVariable Long id, HttpServletResponse response) {
		ListLook listing = service.findById(id);
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
	 * Added method for getting list of Bookmarked Listings by a given Member ID
	 * Input: Member ID
	 * Output: List of Bookmarked Listings
	 */
	@RequestMapping(value = "/member/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<ListLook> getAllByMemberId(@PathVariable Long id, HttpServletResponse response) {
		List<ListLook> listings = service.findAllByMemberId(id);
		return listings;

	}

}
