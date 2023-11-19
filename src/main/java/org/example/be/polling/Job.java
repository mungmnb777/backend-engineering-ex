package org.example.be.polling;

public class Job implements Runnable {

	String jobId;
	int prg;

	public Job(String jobId) {
		this.jobId = jobId;
		this.prg = 0;
	}

	@Override
	public void run() {
		try {
			while (prg < 100) {
				Thread.sleep(5000);
				prg += 10;
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
