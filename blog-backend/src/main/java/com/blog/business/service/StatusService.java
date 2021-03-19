package com.blog.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.business.model.Status;
import com.blog.business.repository.StatusRepository;

@Service
public class StatusService {

	@Autowired
	private StatusRepository statusRepository;
	
	public Status getStatusById(Long id) {
		return statusRepository.findById(id).get();
	}
	
	public List<Status> getAllStatus() {
		List<Status> statusList = new ArrayList<>();
		statusRepository.findAll(Sort.by(Sort.Direction.DESC, "date")).forEach(statusList::add);
		return statusList;
	}

	public void addStatus(Status status) {
		statusRepository.save(status);
	}
	
	public void deleteStatus(Status status) {
		statusRepository.delete(status);
	}
}
