package mk.ukim.finki.mp.roommates.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mk.ukim.finki.mp.roommates.util.CustomLocalDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "listing")
public class Listing extends BaseEntity{
	
	@ManyToOne
	private Flat flat;
	
	@ManyToOne
	private Member member;
	
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	private Date dateFrom;
	
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	private Date dateTo;
	
	private int price;
	
	private String title;
	
	private String information;

	public Flat getFlat() {
		return flat;
	}

	public void setFlat(Flat flat) {
		this.flat = flat;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	
		
}