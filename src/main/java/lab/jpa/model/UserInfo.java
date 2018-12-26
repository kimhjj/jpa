package lab.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="userinfo")
public class UserInfo {
	@Id
	private String userid;
	private String username;
	private String userpwd;
	private String address;
	private String email;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date  createDate;
   
	public UserInfo() {
		super();
	}
	public UserInfo(String userid, String username, String userpwd, String address, String email, Date createDate) {
		super();
		this.userid = userid;
		this.username = username;
		this.userpwd = userpwd;
		this.address = address;
		this.email = email;
		this.createDate = createDate;
	}
	public String getUserid() {
		return userid;
	}
	public String getUsername() {
		return username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public String getAddress() {
		return address;
	}
	public String getEmail() {
		return email;
	}
	public Date getCreateDate() {
		return createDate;
	}
	
	@Override
	public String toString() {
		return "UserInfo [userid=" + userid + ", username=" + username + ", userpwd=" + userpwd + ", address=" + address
				+ ", email=" + email + "]";
	}

	public void changeName(String newName) {
		this.username = newName;
	}
}
