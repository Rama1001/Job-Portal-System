package com.jobportal.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobportal.model.Job;
import com.jobportal.model.User;
import com.jobportal.repository.JobRepo;
import com.jobportal.repository.UserRepo;

@Controller
@RequestMapping("/employer")
public class JobController {

	private final JobRepo jobRepo;
	private final UserRepo userRepo;

	public JobController(JobRepo jobRepo, UserRepo userRepo) {
		this.jobRepo = jobRepo;
		this.userRepo = userRepo;
	}

	@GetMapping("/jobs")
	public String list(@AuthenticationPrincipal UserDetails ud, Model model,
			@RequestParam(defaultValue = "0") int page) {
		User employer = userRepo.findByEmail(ud.getUsername()).orElseThrow();
		model.addAttribute("jobs",
				jobRepo.findByEmployer(employer, PageRequest.of(page, 10, Sort.by("createdAt").descending())));
		return "employer-jobs";
	}

	@GetMapping("/jobs/new")
	public String newForm(Model model) {
		model.addAttribute("job", new Job());
		return "job-form";
	}

	@PostMapping("/jobs")
	public String create(@AuthenticationPrincipal UserDetails ud, Job job) {
		User employer = userRepo.findByEmail(ud.getUsername()).orElseThrow();
		job.setEmployer(employer);
		jobRepo.save(job);
		return "redirect:/employer/jobs";
	}
}
