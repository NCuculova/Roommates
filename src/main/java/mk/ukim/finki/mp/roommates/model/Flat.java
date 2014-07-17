package mk.ukim.finki.mp.roommates.model;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="flats")
public class Flat extends BaseEntity{
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	private String location;
	
	private String address;
	
	private int area;
	
	private Blob  image;
}
