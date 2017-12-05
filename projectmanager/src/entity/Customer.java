package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.apache.openjpa.persistence.jdbc.ForeignKey;
import org.apache.openjpa.persistence.jdbc.Unique;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String companyname;


	private String companynumber;

	private String contact;


	private String email;

	
	private String phone;
	@ManyToOne
	@JoinColumn(name="user")
	private User user;
	
	@Transient
    private boolean isActive;
	
	
	 
	 
	public Customer() {

	}

	public Customer(String companyname, String companynumber, String contact, String email, String phone,User user) {

		this.companyname = companyname;
		this.companynumber = companynumber;
		this.contact = contact;
		this.email = email;
		this.phone = phone;
		this.user=user;
	}
	public Customer(int id, String companyname, String companynumber, String contact, String email, String phone) {
		this.id=id;
		this.companyname = companyname;
		this.companynumber = companynumber;
		this.contact = contact;
		this.email = email;
		this.phone = phone;
		
	}

	

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	 public boolean isIsActive() {
	 return isActive;
	 }

	 public void setIsactive(boolean isActive) {
	 this.isActive = isActive;
	 }
	public String getCompanynumber() {
		return companynumber;
	}

	public void setCompanynumber(String companynumber) {
		this.companynumber = companynumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
