package mk.ukim.finki.mp.roommates.web.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import mk.ukim.finki.mp.roommates.model.Flat;
import mk.ukim.finki.mp.roommates.model.FlatImage;
import mk.ukim.finki.mp.roommates.service.FlatImageService;
import mk.ukim.finki.mp.roommates.service.FlatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/data/rest/flatImages")
public class FlatImageResource {

	@Autowired
	private FlatImageService service;
	
	@Autowired
	private FlatService flatService;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public FlatImage create(@RequestBody @Valid FlatImage entity) {
		service.save(entity);
		return entity;
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<FlatImage> getAll() {
		Collection<FlatImage> flats = service.findAll();
		return new ArrayList<FlatImage>(flats);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public FlatImage get(@PathVariable Long id, HttpServletResponse response) {
		FlatImage flat = service.findById(id);
		if (flat == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return flat;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		service.delete(id);
	}
	
	@RequestMapping(value = "/upload/{id}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public FlatImage uploadFile(MultipartFile file, @PathVariable Long id)
			throws IOException, SerialException, SQLException {
		Flat flat = flatService.findById(id);
		FlatImage flatImage = new FlatImage();
		flatImage.setFlat(flat);
		flatImage.setImage(new SerialBlob(file.getBytes()) );
		flatImage.setFileName(file.getOriginalFilename());
		flatImage.setFileType(file.getContentType());
		service.save(flatImage);
		return flatImage;
		
	}
	
}