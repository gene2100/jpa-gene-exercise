package com.gene.exercise.jpageneexercise.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

@Entity
public class Contract {
	
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "uuid2")
	private UUID id;
	
	@Size(min = 4)
	@NotNull
	private String firstname;
	
	@Size(min = 4)
	@NotNull
	private String lastname;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Gender gender;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Submited Channel cannot be null")
	private SubmitedChannel submitedChannel;
	
	public Contract() {
		
	}
	
	public Contract(String firstname, String lastname, Gender gender,
			SubmitedChannel submitedChannel) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.submitedChannel = submitedChannel;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public SubmitedChannel getSubmitedChannel() {
		return submitedChannel;
	}

	public void setSubmitedChannel(SubmitedChannel submitedChannel) {
		this.submitedChannel = submitedChannel;
	}
	
	public UUID getId() {
		return id;
	}

	@Override
	public String toString() {
		return "contract [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", gender=" + gender
				+ ", submitedChannel=" + submitedChannel + "]";
	}
}
