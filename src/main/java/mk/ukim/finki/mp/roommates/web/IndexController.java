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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView result = new ModelAndView("index");
		return result;
	}

	@RequestMapping("/flatImage/download/{id}")
	public String downloadPaperById(@PathVariable("id") Long id,
			HttpServletResponse response) {
		FlatImage flatImage = flatImageService.findById(id);
		writeFileToResponse(flatImage, response);
		return null;
	}

	private void writeFileToResponse(FlatImage flatImage,
			HttpServletResponse response) {
		try {
			OutputStream out = response.getOutputStream();
			//response.setHeader("Content-Disposition", flatImage.getFileName());
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
