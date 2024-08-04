package com.green.model;


import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
public class CustomerDTO {
	
	@Column(unique = true)
	private String uuid;
    
    @JsonProperty("first_name")
    private String firstName;
    
    @JsonProperty("last_name")
    private String lastName;
    
    private String street;
    private String address;
    private String city;
    private String state;
    private String email;
    private String phone;
    
    @PrePersist
    public void generateUUID() {
        this.uuid = UUID.randomUUID().toString();
    }
}