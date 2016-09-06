package com.iyzico.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table(name = "iyzico_user")
public class User extends BaseEntity  implements  java.io.Serializable{
 
	@Column(name = "username", unique = true, nullable = false, length = 30)
	@NotNull(message = "{error.username.null}")
	@Length(max = 60,message = "{error.username.max}")
	private String username; 

	
	@Column(name = "password", nullable = false)
	@NotNull
	@NotEmpty
	@Length(min=6)
	private String password;
	
	
	// only alpha, @Pattern(regex="[]+"), 
	@Column(name = "name", length = 50)
	@NotNull(message = "{error.name.null}")
	@NotEmpty(message = "{error.name.notempty}")
//	@NotBlank(message = "{error.name.notblank}")
	@Length(max = 50,message = "{error.name.max}")
	private String name;

	// only alpha, @Pattern(regex="[]+"), 
	@Column(name = "surname", length = 150)
	@NotNull(message = "{error.surname.null}")
	@NotEmpty(message = "{error.surname.notempty}")
	@Length(max = 150,message = "{error.surname.max}")
	private String surname;

	@ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinColumn(name = "role_id")
	private Role role;
	
	@Column(name = "address", length = 150)
	private String address;	


	 
	public User() {
	}


	//	@Unique(entityName ="User",fieldName = "username",message="Alrady Exits!")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	//@Type(type="encryptedString") 
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}