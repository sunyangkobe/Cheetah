package com.cheetah.core.shared.models;

import java.io.Serializable;

/**
 * Database model representing a person.
 * 
 * @author Marcel
 */
public class Person implements Serializable {

	private static final long serialVersionUID = -6159274211641038418L;

	/**
	 * Person ID.
	 */
	public int uid;

	/**
	 * Last Name.
	 */
	public String lname;

	/**
	 * First Name.
	 */
	public String fname;

	/**
	 * Email.
	 */
	public String email;

	/**
	 * Username.
	 */
	public String username;

	/**
	 * Password.
	 */
	public String password;

	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname
	 *            the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname
	 *            the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Default constructor with no information. Required by MyBatis.
	 */
	@SuppressWarnings("unused")
	private Person() {
	}

	/**
	 * Constructor with username/password combination.
	 * 
	 * @param username
	 * @param password
	 */
	public Person(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * Constructor used to search person based on uid.
	 * 
	 * @param uid
	 */
	public Person(int uid) {
		this.uid = uid;
	}

}
