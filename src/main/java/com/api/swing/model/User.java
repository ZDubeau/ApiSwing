package com.api.swing.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User", uniqueConstraints = @UniqueConstraint(columnNames = "mail"))

/**
 * @NoArgsConstructor annotation for generating a constructor with no parameters
 */
@NoArgsConstructor

/**
 * @AllArgsConstructor annotation for generating a constructor with 1 parameter
 *                     for each field
 */
@AllArgsConstructor
@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private int id;

	@Pattern(regexp = "^[A-Za-z0-9]{4,15}", message = "length must be between 4 - 15 letters.")
	@Column(name = "username", length = 50)
	private String username;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Pattern(regexp = "^(.+\\@.+\\..+)$", message = "{invalid.mail}")
	@Column(name = "mail", unique = true)
	private String mail;

	@Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){6,200}$", message = "length must be minimum 6, use Upper, Lower, letter and special characters")
	@Column(name = "password", nullable = false)
	private String password;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Objects> obj;
}