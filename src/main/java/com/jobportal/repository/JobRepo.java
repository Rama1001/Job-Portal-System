package com.jobportal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.model.Job;
import com.jobportal.model.User;

@Repository
public interface JobRepo extends JpaRepository<Job, Long> {
	Page<Job> findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCase(String title, String location,
			Pageable pageable);

	Page<Job> findByEmployer(User employer, Pageable pageable);
}
