package com.jobportal.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobportal.model.Application;
import com.jobportal.model.Job;
import com.jobportal.model.User;
import com.jobportal.repository.ApplicationRepo;
import com.jobportal.repository.JobRepo;
import com.jobportal.repository.UserRepo;

@Controller
@RequestMapping("/applicant")
public class ApplicationController {

	private final JobRepo jobRepo;
	private final ApplicationRepo appRepo;
	private final UserRepo userRepo;

	public ApplicationController(JobRepo jobRepo, ApplicationRepo appRepo, UserRepo userRepo) {
		this.jobRepo = jobRepo;
		this.appRepo = appRepo;
		this.userRepo = userRepo;
	}

	@PostMapping("/apply/{jobId}")
	public String apply(@PathVariable Long jobId, @AuthenticationPrincipal UserDetails ud,
			@RequestParam(defaultValue = "") String coverLetter) {
		Job job = jobRepo.findById(jobId).orElseThrow();
		User applicant = userRepo.findByEmail(ud.getUsername()).orElseThrow();
		Application app = new Application();
		app.setJob(job);
		app.setApplicant(applicant);
		app.setCoverLetter(coverLetter);
		appRepo.save(app);
		return "redirect:/applicant/applications";
	}

	@GetMapping("/applications")
	public String myApplications(@AuthenticationPrincipal UserDetails ud, Model model) {
		User applicant = userRepo.findByEmail(ud.getUsername()).orElseThrow();
		model.addAttribute("applications", appRepo.findByApplicant(applicant));
		return "applicant-apps";
	}
}
