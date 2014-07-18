package mk.ukim.finki.mp.roommates.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "members")
public class Member extends BaseEntity {

	private String email;

	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
