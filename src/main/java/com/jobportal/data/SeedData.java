package com.jobportal.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jobportal.model.Job;
import com.jobportal.model.Role;
import com.jobportal.model.User;
import com.jobportal.repository.JobRepo;
import com.jobportal.repository.UserRepo;

@Configuration
public class SeedData implements CommandLineRunner {

	private final UserRepo userRepo;
	private final JobRepo jobRepo;
	private final BCryptPasswordEncoder enc;

	public SeedData(UserRepo userRepo, JobRepo jobRepo, BCryptPasswordEncoder enc) {
		this.userRepo = userRepo;
		this.jobRepo = jobRepo;
		this.enc = enc;
	}

	@Override
	public void run(String... args) {
		if (userRepo.findByEmail("employer@test.com").isEmpty()) {
			User emp = new User();
			emp.setEmail("employer@test.com");
			emp.setFullName("Demo Employer");
			emp.setPassword(enc.encode("password"));
			emp.getRoles().add(Role.EMPLOYER);
			userRepo.save(emp);

			Job j = new Job();
			j.setTitle("Java Developer");
			j.setDescription("Build Spring Boot APIs and integrate with MySQL.");
			j.setLocation("Hyderabad");
			j.setSalary(800000);
			j.setEmployer(emp);
			jobRepo.save(j);
		}
		if (userRepo.findByEmail("applicant@test.com").isEmpty()) {
			User app = new User();
			app.setEmail("applicant@test.com");
			app.setFullName("Demo Applicant");
			app.setPassword(enc.encode("password"));
			app.getRoles().add(Role.APPLICANT);
			userRepo.save(app);
		}
	}
}
