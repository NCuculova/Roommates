package mk.ukim.finki.mp.roommates.model;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "flats")
public class Flat extends BaseEntity {
	
	@ManyToOne
	private Member member;

	private String city;
	
	private String district;

	private String address;

	private int area;
	
	private int heating;
	
	private int numRooms;

	private Blob image;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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

	public int getHeating() {
		return heating;
	}

	public void setHeating(int heating) {
		this.heating = heating;
	}

	public int getNumRooms() {
		return numRooms;
	}

	public void setNumRooms(int numRooms) {
		this.numRooms = numRooms;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

}
