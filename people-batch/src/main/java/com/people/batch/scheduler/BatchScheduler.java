package com.people.batch.scheduler;

import java.util.List;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@EnableScheduling
@RequiredArgsConstructor
@Component
class BatchScheduler {
	@Autowired private ApplicationContext context;
	
	//@Scheduled(fixedRateString = "5", initialDelay = 3000)
	/**
	 * 이 주석을 풀면 스케쥴 작동됨. BatchApplication에서 Job 실행 안되도록 설정 변경할 것
	 * 
	 * @Scheduled(fixedDelay = 10000)
	 */
	
	/**
	 * 
	 * run()은 java -jar batchexample1-0.0.1-SNAPSHOT.jar --spring.batch.job.names=[JobName]
	 * 로 실행했을 때 job name을 get 하여 해당 job을 실행한다. 만약 여러개인경우 BatchApplication 처리 for 처리해야함.
	 * 
	 * @Scheduled(fixedDelay = 10000)
	public void run() {

//		 JobParameters jobParameters = new JobParametersBuilder()
//                                                  .addString("input.file.name", fileName)
//                                                  .addLong("time", System.currentTileMillis())
//                                                  .toJobParameters();

		JobParameters jobParameters = new JobParametersBuilder()
		        .addLong("time", System.currentTimeMillis())
		        .toJobParameters()
		        ;
        
		try {
			
			Environment environment = context.getEnvironment();
	        String jobName = environment.getProperty("spring.batch.job.names");
	        
	        log.info("\n\n\n");
	        log.info("---------------------------------   "+jobName+" start ----------------");
	        log.info("\n\n\n");
	        
	        
	        
	        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
	        Job job = context.getBean(jobName, Job.class);
			
			JobExecution jobExecution = jobLauncher.run(job, jobParameters);
			BatchStatus jobStatus = jobExecution.getStatus();
            if (!BatchStatus.COMPLETED.equals(jobStatus)) {
                List<Throwable> exceptions = jobExecution.getAllFailureExceptions();
                for (Throwable throwable : exceptions) {
                    log.error("Batch Failure Exceptions:", throwable);
                }
            }
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	*/
	
	//@Scheduled(fixedDelay = 30000)
	public void peopleJobRun() {

		JobParameters jobParameters = new JobParametersBuilder()
		        .addLong("time", System.currentTimeMillis())
		        .toJobParameters()
		        ;
       
		try {
	        String jobName = "peopleJob";
	        
	        log.info("\n\n\n");
	        log.info("---------------------------------   "+jobName+" start ----------------");
	        log.info("\n\n\n");
	        
	        
	        
	        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
	        Job job = context.getBean(jobName, Job.class);
			
			JobExecution jobExecution = jobLauncher.run(job, jobParameters);
			BatchStatus jobStatus = jobExecution.getStatus();
           if (!BatchStatus.COMPLETED.equals(jobStatus)) {
               List<Throwable> exceptions = jobExecution.getAllFailureExceptions();
               for (Throwable throwable : exceptions) {
                   log.error("Batch Failure Exceptions:", throwable);
               }
           }
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	//@Scheduled(fixedDelay = 3000)
	public void twoJobRun() {

		JobParameters jobParameters = new JobParametersBuilder()
		        .addLong("time", System.currentTimeMillis())
		        .toJobParameters()
		        ;
       
		try {
	        String jobName = "twoJob";
	        
	        log.info("\n\n\n");
	        log.info("---------------------------------   "+jobName+" start ----------------");
	        log.info("\n\n\n");
	        
	        
	        
	        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
	        Job job = context.getBean(jobName, Job.class);
			
			JobExecution jobExecution = jobLauncher.run(job, jobParameters);
			BatchStatus jobStatus = jobExecution.getStatus();
           if (!BatchStatus.COMPLETED.equals(jobStatus)) {
               List<Throwable> exceptions = jobExecution.getAllFailureExceptions();
               for (Throwable throwable : exceptions) {
                   log.error("Batch Failure Exceptions:", throwable);
               }
           }
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
}