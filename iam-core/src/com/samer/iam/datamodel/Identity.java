/**
 * 
 */
package com.samer.iam.datamodel;

import java.util.Date;

/**
 * @author samer
 *
 */
public class Identity {
	
	private String uid;
	private String firstname;
	private String lastname;
	private String email;
	private Date birthdate;
	
	
	/**
	 * @param uid
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param birthdate
	 */
	public Identity(String uid, String firstname, String lastname, String email, Date birthdate) {
		super();
		this.uid = uid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.birthdate = birthdate;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}
	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Identity [uid=" + uid + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", birthdate=" + birthdate + "]";
	}
	

	
	
}
