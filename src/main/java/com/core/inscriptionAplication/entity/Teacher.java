package com.core.inscriptionAplication.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Teacher implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Column
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO )
	private long id;

	@OneToOne
	private User user;

	@NotBlank
	@Column
	private String name;
	@NotBlank
	@Column
	private String surname;
	@Column
	@NotNull(message="no valido")
	private int dni;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public Teacher() {
	}

	public Teacher(long id, User user, String name, String surname, int dni) {
		this.id = id;
		this.user = user;
		this.name = name;
		this.surname = surname;
		this.dni = dni;
	}
}
