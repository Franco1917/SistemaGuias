package com.core.inscriptionAplication.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Deliver implements Serializable {


	private static final long serialVersionUID = 1L;

	@Column
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO )
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "guide_id")
	private Guide guide;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private User user;

	@CreationTimestamp
	private LocalDateTime creationDate;

	@Column()
	private  int note;

	@Column
	private  String file;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Guide getGuide() {
		return guide;
	}

	public void setGuide(Guide guide) {
		this.guide = guide;
	}

	public User getUse() {
		return user;
	}

	public void setUse(User use) {
		this.user = use;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}




	public Deliver() {
	}

	public Deliver(long id, Guide guide, User user, LocalDateTime creationDate, int note, String file) {
		this.id = id;
		this.guide = guide;
		this.user = user;
		this.creationDate = creationDate;
		this.note = note;
		this.file = file;
	}
}
