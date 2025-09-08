package com.jobportal.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "applications")
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false)
	private Job job;
	@ManyToOne(optional = false)
	private User applicant;

	@Column(length = 2000)
	private String coverLetter;
	private String status = "SUBMITTED"; // SUBMITTED / REVIEW / REJECTED / ACCEPTED
	private LocalDateTime appliedAt = LocalDateTime.now();

	public Application() {

	}

	public Application(Long id, Job job, User applicant, String coverLetter, String status, LocalDateTime appliedAt) {
		super();
		this.id = id;
		this.job = job;
		this.applicant = applicant;
		this.coverLetter = coverLetter;
		this.status = status;
		this.appliedAt = appliedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public User getApplicant() {
		return applicant;
	}

	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}

	public void setAppliedAt(LocalDateTime appliedAt) {
		this.appliedAt = appliedAt;
	}

}
