package mk.ukim.finki.mp.roommates.model;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
* Class for the images for the flats with getters and setters for all properties
*/

@Entity
@Table(name = "flat_images")
public class FlatImage extends BaseEntity {

	@JsonIgnore
	private Blob image;

	@ManyToOne
	private Flat flat;

	private String fileName;
	
	private String fileType;

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Flat getFlat() {
		return flat;
	}

	public void setFlat(Flat flat) {
		this.flat = flat;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
