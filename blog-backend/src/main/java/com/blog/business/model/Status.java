package com.blog.business.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "STATUS")
public class Status  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2481694097176585852L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "text", length = 30, nullable = false)
    private String text;
    
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", length = 60, nullable = false)
    private Date date;
    
    @ManyToOne
    @JoinColumn(name = "userid")
    private User statusUser;
    
    @OneToMany(mappedBy = "commentStatus", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "commentStatus")
    private Set<Comment> statusComments;
 
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@JsonBackReference(value = "statusUser")
	public User getStatusUser() {
		return statusUser;
	}

	public void setStatusUser(User statusUser) {
		this.statusUser = statusUser;
	}

	public Set<Comment> getStatusComments() {
		return statusComments;
	}

	public void setStatusComments(Set<Comment> statusComments) {
		this.statusComments = statusComments;
	}

	

}
