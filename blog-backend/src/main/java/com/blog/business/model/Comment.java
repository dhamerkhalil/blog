package com.blog.business.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "COMMENT")
public class Comment  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1991107400811845773L;

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
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User commentUser;
    
    @ManyToOne
    @JoinColumn(name = "statusid",  referencedColumnName = "id")
    private Status commentStatus;

	public Long getId() {
		return id;
	}

    @JsonBackReference(value = "commentUser")
    public User getCommentUser() {
		return commentUser;
	}


	public void setCommentUser(User commentUser) {
		this.commentUser = commentUser;
	}


	@JsonBackReference(value = "commentStatus")
	public Status getCommentStatus() {
		return commentStatus;
	}


	public void setCommentStatus(Status commentStatus) {
		this.commentStatus = commentStatus;
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
 
}
