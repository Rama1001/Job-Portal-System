package com.jobportal.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobportal.model.Job;
import com.jobportal.repository.JobRepo;

@Controller
public class HomeController {

	private final JobRepo jobRepo;

	public HomeController(JobRepo jobRepo) {
		this.jobRepo = jobRepo;
	}

	@GetMapping("/")
	public String home(@RequestParam(defaultValue = "") String q, @RequestParam(defaultValue = "0") int page,
			Model model) {
		Page<Job> jobs = q.isBlank() ? jobRepo.findAll(PageRequest.of(page, 10, Sort.by("createdAt").descending()))
				: jobRepo.findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCase(q, q, PageRequest.of(page, 10));
		model.addAttribute("jobs", jobs);
		model.addAttribute("q", q);
		return "home";
	}
}
