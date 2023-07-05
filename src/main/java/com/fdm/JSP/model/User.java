package com.fdm.JSP.model;

import java.util.List;

import javax.persistence.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * User Model Class which is annotated as an entity. this is the blueprint that
 * is used to create instances of user in the database. The user ID is annotated
 * and auto generated with the database driver. the NotEmpty annotation makes
 * sure these fields are not left unfilled to prevent null values in the
 * database
 * 
 * @author Mohammad Shakir
 *
 */
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true, length = 45)
	private String username;
	@Column(name = "first_name", nullable = false, length = 20)
	private String fName;
	@Column(name = "last_name", nullable = false, length = 20)
	private String lName;
	@Column(nullable = false, length = 64)
	private String password;
	@Column(nullable = false, unique = true, length = 45)
	private String email;
	@Column(nullable = false)
	private String role;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Rental> rentals;
	
	
	/**
	 * Zero Argument Constructor
	 */
	public User() {
		super();
	}

	/**
	 * field Constructor to instantiate a user object
	 * 
	 * @param username instance of username	
	 * @param fName instance of the firstname	
	 * @param lName instance of the lastname
	 * @param password instance of the password
	 * @param email instance of the email address
	 */
	public User(String username, String fName, String lName, String password, String email, String role) {
		super();
		this.username = username;
		this.fName = fName;
		this.lName = lName;
		this.password = password;
		this.email = email;
		this.role = role;	
	}
	
	
	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public User(List<Rental> rentals) {
		super();
		this.rentals = rentals;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fName=" + fName + ", lName=" + lName + ", password="
				+ password + ", email=" + email + ", role=" + role + "]";
	}

}
