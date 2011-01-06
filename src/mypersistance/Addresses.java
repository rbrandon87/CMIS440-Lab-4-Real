package mypersistance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Addresses entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ADDRESSES", schema = "TEST")
public class Addresses implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long addressid;
	private String firstname;
	private String lastname;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String emailaddress;
	private String phonenumber;
	private boolean editable = false;
	// Constructors

	/** default constructor */
	public Addresses() {
	}

	/** full constructor */
	public Addresses(Long addressid, String firstname, String lastname,
			String street, String city, String state, String zip,
			String emailaddress, String phonenumber) {
		this.addressid = addressid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.emailaddress = emailaddress;
		this.phonenumber = phonenumber;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADDRESSID", unique = true, nullable = false)
	public Long getAddressid() {
		return this.addressid;
	}

	public void setAddressid(Long addressid) {
		this.addressid = addressid;
	}

	@Column(name = "FIRSTNAME", nullable = false, length = 30)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "LASTNAME", nullable = false, length = 30)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "STREET", nullable = false, length = 150)
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "CITY", nullable = false, length = 30)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "STATE", nullable = false, length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ZIP", nullable = false, length = 5)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "EMAILADDRESS", nullable = false, length = 50)
	public String getEmailaddress() {
		return this.emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	@Column(name = "PHONENUMBER", nullable = false, length = 30)
	public String getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	@Transient
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}		
}