package com.isa.booking_entities.models.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="authority")
public class Authority implements GrantedAuthority {

	@Id
	@SequenceGenerator(name = "mySeqGenAuthority", sequenceName = "mySeqAuthority", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenAuthority")
    private long id;

    @Column(name="name")
    String name;

    public Authority() {
		// TODO Auto-generated constructor stub
	}
    
    public Authority(long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
    public String getAuthority() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
