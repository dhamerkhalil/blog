package com.blog.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.business.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{

}
