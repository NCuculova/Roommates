package mk.ukim.finki.mp.roommates.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "members")
public class Member extends BaseEntity {

	@NotEmpty
	private String email;

	@NotEmpty
	private String password;

	@OneToOne
	private MemberProfile profile;

	public MemberProfile getProfile() {
		return profile;
	}

	public void setProfile(MemberProfile profile) {
		this.profile = profile;
	}

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