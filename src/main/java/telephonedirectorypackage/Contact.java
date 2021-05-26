package telephonedirectorypackage;

import java.util.UUID;

/**
 * Contact class
 * @author Ismail
 *
 */
public class Contact {

	private UUID uid;
	private String firstname;
	private String lastname;
	private String phone;
	
	/**
	 * Contact Constructor with params
	 * @param firstname
	 * @param lastname
	 * @param phone
	 */
	public Contact(String firstname, String lastname, String phone) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		// generate unique identifier
		this.uid = UUID.randomUUID();
		
	}
	
	/**
	 * Contact Constructor - no-params
	 */
	public Contact() {
		// generate unique identifie
		this.uid = UUID.randomUUID();		
	}
	
	/**
	 * get unique id
	 * @return UUID
	 */
	public UUID getUid() {
		return uid;
	}
	
	/**
	 * get firstname
	 * @return string
	 */
	public String getFirstname() {
		return firstname;
	}
	
	/**
	 * get lastname
	 * @return string
	 */
	public String getLastname() {
		return lastname;
	}
	
	/**
	 * get phone
	 * @return string
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * set firstname
	 * @param firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	/**
	 * set lastname
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	/**
	 * set phone
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
