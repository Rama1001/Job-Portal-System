package com.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.model.Application;
import com.jobportal.model.Job;
import com.jobportal.model.User;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Long> {

	List<Application> findByApplicant(User applicant);

	List<Application> findByJob(Job job);
}
