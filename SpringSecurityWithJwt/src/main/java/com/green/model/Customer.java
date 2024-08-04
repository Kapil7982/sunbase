package com.green.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer custId;
	
	@Column(unique = true)
	private String uuid;
	
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String state;
	private String phone;
	
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String address;
	

	@PrePersist
    public void generateUUID() {
        this.uuid = UUID.randomUUID().toString();
    }

}
