package org.example.be.polling;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PollingController {

	Map<String, Job> jobs = new ConcurrentHashMap<>();

	ExecutorService executorService = Executors.newFixedThreadPool(4);

	@PostMapping("/submit")
	public String submit() {
		String jobId = UUID.randomUUID().toString().substring(0, 8);
		updateJob(jobId);
		return "\n" + jobId + "\n\n";
	}

	public void updateJob(String jobId) {
		Job job = new Job(jobId);
		jobs.put(jobId, job);
		executorService.execute(job);
	}

	@GetMapping("/job/{jobId}/status")
	public String checkStatus(@PathVariable String jobId) {
		Job target = jobs.get(jobId);
		return "\nJobStatus : " + target.prg + "%\n\n";
	}
}
