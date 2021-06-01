package com.api.swing.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
public class Objects implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private int id;

	@Enumerated(EnumType.STRING)
	private ObjStatus status;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "age_min", nullable = false)
	private int ageMin;

	@Column(name = "age_max", nullable = false)
	private int ageMax;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}