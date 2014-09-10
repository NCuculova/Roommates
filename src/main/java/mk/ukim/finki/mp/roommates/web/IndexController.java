package mk.ukim.finki.mp.roommates.web;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import mk.ukim.finki.mp.roommates.model.FlatImage;
import mk.ukim.finki.mp.roommates.service.FlatImageService;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class IndexController {

	@Autowired
	FlatImageService flatImageService;

	/**
	 * Method that loads index.jsp on page load and refresh
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView result = new ModelAndView("index");
		return result;
	}

	/**
	 * Method for downloading image. Uses writeFileToResponse.
	 * Input: ID of an image
	 * Output: null
	 */
	@RequestMapping("/flatImage/download/{id}")
	public String downloadImageById(@PathVariable("id") Long id,
			HttpServletResponse response) {
		FlatImage flatImage = flatImageService.findById(id);
		writeFileToResponse(flatImage, response);
		return null;
	}

	/**
	 * Method that gets image in the server response
	 * Input: Flat Image
	 * Output: none
	 */
	private void writeFileToResponse(FlatImage flatImage,
			HttpServletResponse response) {
		try {
			OutputStream out = response.getOutputStream();
			response.setContentType(flatImage.getFileType());
			response.setContentLength((int) flatImage.getImage().length());
			IOUtils.copy(flatImage.getImage().getBinaryStream(), out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
	}

	}
}
