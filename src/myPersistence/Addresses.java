package myPersistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
* Program Name: CMIS440 Lab 4 Address Book Web App
* @author Brandon R Russell
* @Course CMIS440
* Date: Jan 6, 2011
*/

/** This class creates a object of the back-end table to be used.
*|----------------------------------------------------------------------------|
*|                             CRC: Addresses                                 |
*|----------------------------------------------------------------------------|
*|Used as a object representative of the table to write to       AddressBean  |
*|																 AddressesDAO |
*|----------------------------------------------------------------------------|
*
* @TheCs Cohesion - All methods in this class work together on similar task.
* Completeness - Completely creates a object of the back-end table to be used.
* Convenience - There are sufficient methods and variables to complete the
*                required task.
* Clarity - The methods and variables are distinguishable and work in a
*           uniform manner to provide clarity to other programmers.
* Consistency - All names,parameters ,return values , and behaviors follow
*               the same basic rules.
*/

/**
 * Annotations are used here to tell java this is an entity instead
 * of using xml definitions. I think it reads better this way.
 */
@Entity
@Table(name = "ADDRESSES", schema = "TEST")
public class Addresses implements java.io.Serializable {

	// Fields
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
	/** Constructs Addresses Entity of back-end table.
	 *@TheCs Cohesion - Constructs Addresses Entity of back-end table.
	 * Completeness - Completely constructs Addresses Entity of back-end table.
	 * Convenience - Simply constructs Addresses Entity of back-end table.
	 * Clarity - It is simple to understand that this constructs Addresses Entity
	 *           of back-end table.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.            
	 */	 
	public Addresses() {
	}

	/** Constructs Addresses Entity of back-end table.
	 *@TheCs Cohesion - Constructs Addresses Entity of back-end table.
	 * Completeness - Completely constructs Addresses Entity of back-end table.
	 * Convenience - Simply constructs Addresses Entity of back-end table.
	 * Clarity - It is simple to understand that this constructs Addresses Entity
	 *           of back-end table.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param addressid Unique ID of Addresses object
	 * @param firstname First Name of Addresses object
	 * @param lastname Last Name of Addresses object
	 * @param street Street of Addresses object
	 * @param city City of Addresses object
	 * @param state State of Addresses object
	 * @param zip Zip Code of Addresses object
	 * @param emailaddress Email Address of Addresses object
	 * @param phonenumber Phone Number of Addresses object                           
	 */	 
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

	/** Retrieves unique ID of Addresses object.
	 *@TheCs Cohesion - Retrieves unique ID of Addresses object.
	 * Completeness - Completely retrieves unique ID of Addresses object.
	 * Convenience - Simply retrieves unique ID of Addresses object.
	 * Clarity - It is simple to understand that this retrieves unique ID
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @return addressid Unique ID of Addresses object                          
	 */	 
	/**
	 * Note the use of Annotations again. The Id and GeneratedValue
	 * annotations let Java know this field is automatically 
	 * generated so shouldn't be modified by the user.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADDRESSID", unique = true, nullable = false)
	public Long getAddressid() {
		return this.addressid;
	}

	/** Sets unique ID of Addresses object.
	 *@TheCs Cohesion - Sets unique ID of Addresses object.
	 * Completeness - Completely sets unique ID of Addresses object.
	 * Convenience - Simply sets unique ID of Addresses object.
	 * Clarity - It is simple to understand that this sets unique ID
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param addressid Unique ID of Addresses object                          
	 */		
	public void setAddressid(Long addressid) {
		this.addressid = addressid;
	}

	/** Retrieves first name of Addresses object.
	 *@TheCs Cohesion - Retrieves first name of Addresses object.
	 * Completeness - Completely retrieves first name of Addresses object.
	 * Convenience - Simply retrieves first name of Addresses object.
	 * Clarity - It is simple to understand that this retrieves first name
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @return firstname first name of Addresses object                          
	 */		
	@Column(name = "FIRSTNAME", nullable = false, length = 30)
	public String getFirstname() {
		return this.firstname;
	}

	/** Sets first name of Addresses object.
	 *@TheCs Cohesion - Sets first name of Addresses object.
	 * Completeness - Completely sets first name of Addresses object.
	 * Convenience - Simply sets first name of Addresses object.
	 * Clarity - It is simple to understand that this sets first name
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param firstname first name of Addresses object                          
	 */		
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/** Retrieves last name of Addresses object.
	 *@TheCs Cohesion - Retrieves last name of Addresses object.
	 * Completeness - Completely retrieves last name of Addresses object.
	 * Convenience - Simply retrieves last name of Addresses object.
	 * Clarity - It is simple to understand that this retrieves last name
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @return lastname last name of Addresses object                          
	 */			
	@Column(name = "LASTNAME", nullable = false, length = 30)
	public String getLastname() {
		return this.lastname;
	}

	/** Sets last name of Addresses object.
	 *@TheCs Cohesion - Sets last name of Addresses object.
	 * Completeness - Completely sets last name of Addresses object.
	 * Convenience - Simply sets last name of Addresses object.
	 * Clarity - It is simple to understand that this sets last name
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param lastname Unique ID of Addresses object                          
	 */			
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/** Retrieves street of Addresses object.
	 *@TheCs Cohesion - Retrieves street of Addresses object.
	 * Completeness - Completely retrieves street of Addresses object.
	 * Convenience - Simply retrieves street of Addresses object.
	 * Clarity - It is simple to understand that this retrieves street
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @return street street of Addresses object                          
	 */		
	@Column(name = "STREET", nullable = false, length = 150)
	public String getStreet() {
		return this.street;
	}

	/** Sets street of Addresses object.
	 *@TheCs Cohesion - Sets street of Addresses object.
	 * Completeness - Completely sets street of Addresses object.
	 * Convenience - Simply sets street of Addresses object.
	 * Clarity - It is simple to understand that this sets street
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param street street of Addresses object                          
	 */		
	public void setStreet(String street) {
		this.street = street;
	}

	/** Retrieves city of Addresses object.
	 *@TheCs Cohesion - Retrieves city of Addresses object.
	 * Completeness - Completely retrieves city of Addresses object.
	 * Convenience - Simply retrieves city of Addresses object.
	 * Clarity - It is simple to understand that this retrieves city
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @return city city of Addresses object                          
	 */		
	@Column(name = "CITY", nullable = false, length = 30)
	public String getCity() {
		return this.city;
	}

	/** Sets city of Addresses object.
	 *@TheCs Cohesion - Sets city of Addresses object.
	 * Completeness - Completely sets city of Addresses object.
	 * Convenience - Simply sets city of Addresses object.
	 * Clarity - It is simple to understand that this sets city
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param city city of Addresses object                          
	 */		
	public void setCity(String city) {
		this.city = city;
	}

	/** Retrieves state of Addresses object.
	 *@TheCs Cohesion - Retrieves state of Addresses object.
	 * Completeness - Completely retrieves state of Addresses object.
	 * Convenience - Simply retrieves state of Addresses object.
	 * Clarity - It is simple to understand that this retrieves state
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @return state state of Addresses object                          
	 */		
	@Column(name = "STATE", nullable = false, length = 2)
	public String getState() {
		return this.state;
	}

	/** Sets state of Addresses object.
	 *@TheCs Cohesion - Sets state of Addresses object.
	 * Completeness - Completely sets state of Addresses object.
	 * Convenience - Simply sets state of Addresses object.
	 * Clarity - It is simple to understand that this sets state
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param state state of Addresses object                          
	 */			
	public void setState(String state) {
		this.state = state;
	}

	/** Retrieves zip code of Addresses object.
	 *@TheCs Cohesion - Retrieves zip code of Addresses object.
	 * Completeness - Completely retrieves zip code of Addresses object.
	 * Convenience - Simply retrieves zip code of Addresses object.
	 * Clarity - It is simple to understand that this retrieves zip code
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @return zip zip code of Addresses object                          
	 */			
	@Column(name = "ZIP", nullable = false, length = 5)
	public String getZip() {
		return this.zip;
	}

	/** Sets zip code of Addresses object.
	 *@TheCs Cohesion - Sets zip code of Addresses object.
	 * Completeness - Completely sets zip code of Addresses object.
	 * Convenience - Simply sets zip code of Addresses object.
	 * Clarity - It is simple to understand that this sets zip code
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param zip zip code of Addresses object                          
	 */				
	public void setZip(String zip) {
		this.zip = zip;
	}

	/** Retrieves email address of Addresses object.
	 *@TheCs Cohesion - Retrieves email address of Addresses object.
	 * Completeness - Completely retrieves email address of Addresses object.
	 * Convenience - Simply retrieves email address of Addresses object.
	 * Clarity - It is simple to understand that this retrieves email address
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @return emailaddress Email Address of Addresses object                          
	 */				
	@Column(name = "EMAILADDRESS", nullable = false, length = 50)
	public String getEmailaddress() {
		return this.emailaddress;
	}

	/** Sets email address code of Addresses object.
	 *@TheCs Cohesion - Sets email address of Addresses object.
	 * Completeness - Completely sets email address of Addresses object.
	 * Convenience - Simply sets email address of Addresses object.
	 * Clarity - It is simple to understand that this sets email address
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param emailaddress email address code of Addresses object                          
	 */					
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	/** Retrieves phone number of Addresses object.
	 *@TheCs Cohesion - Retrieves phone number of Addresses object.
	 * Completeness - Completely retrieves phone number of Addresses object.
	 * Convenience - Simply retrieves phone number of Addresses object.
	 * Clarity - It is simple to understand that this retrieves phone number
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @return phonenumber phone number of Addresses object                          
	 */					
	@Column(name = "PHONENUMBER", nullable = false, length = 30)
	public String getPhonenumber() {
		return this.phonenumber;
	}

	/** Sets phone number code of Addresses object.
	 *@TheCs Cohesion - Sets phone number of Addresses object.
	 * Completeness - Completely sets phone number of Addresses object.
	 * Convenience - Simply sets phone number of Addresses object.
	 * Clarity - It is simple to understand that this sets phone number
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param phonenumber phone number code of Addresses object                          
	 */						
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	/** Retrieves editable variable of Addresses object.
	 *@TheCs Cohesion - Retrieves editable variable of Addresses object.
	 * Completeness - Completely retrieves editable variable of Addresses object.
	 * Convenience - Simply retrieves editable variable of Addresses object.
	 * Clarity - It is simple to understand that this retrieves editable variable
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @return editable editable variable of Addresses object                          
	 */		
	/**
	 * The use of the Transient Annotation here means that the editable variable
	 * is used by the Addresses object, but is not stored in the Addresses table.
	 */
	@Transient
	public boolean isEditable() {
		/**
		 * The editable variable is used to determine if the user can or cannot edit
		 * the record in question on the front-end table.
		 */
		return editable;
	}
	
	/** Sets editable variable code of Addresses object.
	 *@TheCs Cohesion - Sets editable variable of Addresses object.
	 * Completeness - Completely sets editable variable of Addresses object.
	 * Convenience - Simply sets editable variable of Addresses object.
	 * Clarity - It is simple to understand that this sets editable variable
	 *           of Addresses object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param editable editable variable code of Addresses object                          
	 */		
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
    /**
	    * This method takes the author first/last name and the book isbn, and
	    * multiplies their hashCodes together and by 17, a prime number,
	    * to create a unique hashCode that will later be used to test for equality
	    * of Book objects in the BooksQuery class.
	    * @TheCs Cohesion - Sets the hashCode of the Book object to 17 times
	    *                   the first/last name and isbn. 17 used since it's a prime
	    * Completeness - Completely sets the hashCode of the Book object.
	    * Convenience -  Simply sets the hashCode of the Book object.
	    * Clarity - It is simple to understand that this sets the hashCode of the
	    *           Book object.
	    * Consistency - It uses the same syntax rules as the rest of the class and
	    *               continues to use proper casing and indentation.
	    * @return unique hashCode of book object
	    */
	    @Override
	    public int hashCode(){
	        return 17 * getLastname().hashCode();
	    }

	    /**
	    * This method first checks to see if the object in question is null and
	    * if so returns false. It then test to see if the objects refer to the same
	    * object and returns true if they do. It then checks to see if obj is an
	    * instance of Book and returns false if it is not or if so it then cast
	    * the object to an Book object and test the hashCode of each object and
	    * returns true or false depending on if the hashCodes are equals or not.
	    * @TheCs Cohesion - Overrides the equals method. Test for equality of two
	    *                   objects.
	    * Completeness - Completely test for equality of two objects.
	    * Convenience -  Simply test for equality of two objects.
	    * Clarity - It is simple to understand that this test for equality of two
	    *           Book objects.
	    * Consistency - It uses the same syntax rules as the rest of the class and
	    *               continues to use proper casing and indentation.
	    * @param obj Check for equality against this object.
	    * @precondition obj be a Book object, otherwise return false.
	    * @return true or false depending on if objects are equal or not
	    */
	    @Override
	    public boolean equals(Object obj){

	        if (obj == null) return false;
	        if (this == obj) return true;
	        if (obj instanceof Addresses){
	            Addresses other = (Addresses) obj;
	            return this.hashCode() == other.hashCode();
	        }else{
	            return false;
	        }

	    }	
}