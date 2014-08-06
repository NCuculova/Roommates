package mk.ukim.finki.mp.roommates.model;

import java.util.Date;

import javax.persistence.OneToOne;

public class MemberProfile extends BaseEntity{
	
	private int age;
	
	private String sex;
	
	private Date moveDate;
	
	private String tags;
	
	private String occupation;
	
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getMoveDate() {
		return moveDate;
	}

	public void setMoveDate(Date moveDate) {
		this.moveDate = moveDate;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}


}
