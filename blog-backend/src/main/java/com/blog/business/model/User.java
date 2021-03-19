package com.blog.business.model;

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

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "USER")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;

	@Column(name = "name", length = 30, nullable = false)
    private String fullName;
    
    @Column(name = "email", length = 60, nullable = false)
    private String email;
    
    @Column(name = "password", length = 60, nullable = false)
    private String password;
 
    @OneToMany(mappedBy = "statusUser", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "statusUser")
    private Set<Status> status;
    
    @OneToMany(mappedBy = "commentUser", fetch = FetchType.EAGER,  cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "commentUser")
    private Set<Comment> comments;
    
    
    public Set<Status> getStatus() {
		return status;
	}

	public void setStatus(Set<Status> status) {
		this.status = status;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Long getId() {
    	return id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    public String getFullName() {
    	return fullName;
    }
    
    public void setFullName(String fullName) {
    	this.fullName = fullName;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setPassword(String password) {
    	String encoded = new BCryptPasswordEncoder().encode(password);
    	this.password = encoded;
    }

}
