package com.api.swing.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Object")

/**
 * @NoArgsConstructor annotation for generating a constructor with no parameters
 */
@NoArgsConstructor

/**
 * @AllArgsConstructor annotation for generating a constructor with 1 parameter
 *                     for each field
 */
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Objects implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private int id;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private ObjStatus status;

	@Column(name = "title", nullable = false, unique = true)
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "age_min", nullable = false)
	private int ageMin;

	@Column(name = "age_max", nullable = false)
	private int ageMax;
	
//	@JsonIgnore
	@Column(name = "user_id", nullable = false)
	private int user_id;
	
	
//	@ManyToOne(cascade=CascadeType.ALL , fetch = FetchType.EAGER)
//	@JoinColumn(name = "user_id", nullable = false,  insertable = false, updatable = false)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	@JsonIgnoreProperties(value = {"object", "handler", "hibernateLazyInitializer"}, allowSetters=true)
//	@JsonBackReference
//	//@JsonIgnore
//	private User user;
//	
//	@Override
//	public String toString() {
//		return "Object[id="+id+"status="+status+"title="+title+"description="+description+"age_min="+ageMin+"age_max="+ageMax+"user_id="+user_id+"]";
//	}
}