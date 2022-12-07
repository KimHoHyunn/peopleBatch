package com.people.batch;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableBatchProcessing
@SpringBootApplication
//@Import //(각종기타 설정 import)
public class PeopleBatchApplication {//extends DefaultBatchConfigurer {
	
	/**
	 * Job이 샘플로 peopleJob과 twoJob 두개가 있음.
	 * 각각의 Job을 실행하도록 할려면 
	 * 
	 * java -jar batchexample1-0.0.1-SNAPSHOT.jar --spring.batch.job.names=[JobName] 으로 해야한다.
	 *   예) java -jar batchexample1-0.0.1-SNAPSHOT.jar --spring.batch.job.names=peopleJob
	 *   
	 * 배치 스케쥴링 솔루션(apache air flow 등)이 있으면 한번만 호출하는 것으로 jobname을 바꿔서 호출하면
	 * 배치 프로젝트를 여러개로 나누지 않아도 된다. ( 실제 업무에서는 자체 스케쥴러[BatchScheduler]를 사용할 일이 없을 듯.)
	 * 
	 * 
	 * job 두개를 한꺼번에 실행하고 싶으면 
	 * java -jar batchexample1-0.0.1-SNAPSHOT.jar --spring.batch.job.names=[JobName],[JobName] 으로 ~ 실행
	 *   예) java -jar batchexample1-0.0.1-SNAPSHOT.jar --spring.batch.job.names=peopleJob,twoJob
	 * 
	 * @param args
	 */
	
	
	
    public static void main(String[] args) {
    	/**
    	 * 이것 만 있는 경우 스케쥴러를 넣을 것
    	 * >>> SpringApplication.run(BatchApplication.class, args);
    	 */
    	//SpringApplication.run(BatchApplication.class, args);

    	/**
    	 * 스케쥴 없이 호출할 때문 수행하게 하는 것...
    	 * 스케쥴적용할 거면 아래는 전부 주석 처리할 것.
    	 */
        SpringApplication application = new SpringApplication(PeopleBatchApplication.class);
        //application.setWebEnvironment(false);
        
        
        ApplicationContext context = application.run(args);
        Environment environment = context.getEnvironment();
        
        String jobName = environment.getProperty("spring.batch.job.names");
        String[] jobArr = jobName.split(",");
        
        for(int i=0; i<jobArr.length;i++) {
            log.info("\n\n\n\n\n\n"+ jobArr[i]);
            
	        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
	        Job job = context.getBean(jobArr[0], Job.class);
	        
	        
	        try {
	            JobParameters jobParameters = new JobParametersBuilder()
	                    .addLong("timestamp", System.currentTimeMillis())
	                    .toJobParameters();
	
	            JobExecution execution = jobLauncher.run(job, jobParameters);
	            BatchStatus status = execution.getStatus();
	
	            log.debug("Exit Status : {}", status);
	            if (!BatchStatus.COMPLETED.equals(status)) {
	                List<Throwable> exceptions = execution.getAllFailureExceptions();
	                for (Throwable throwable : exceptions) {
	                    log.error("Batch Failure Exceptions:", throwable);
	                }
	            }
	        } catch (JobExecutionAlreadyRunningException e) {
	            log.error("Job execution already running:", e);
	        } catch (JobRestartException e) {
	            log.error("Illegal attempt to restart a job:", e);
	        } catch (JobInstanceAlreadyCompleteException e) {
	            log.error("Illegal attempt to restart a job that was already completed successfully", e);
	        } catch (JobParametersInvalidException e) {
	            log.error("Invalid job parameter:", e);
	        }
        }

        log.debug("Done");
    }

 /**
    // spring.batch.job.names 를 지정하지 않으면 모든 Job이 실행돼 버리기 때문에
    // 방어차원에서 넣은 job.names validation 처리 
    @Value("${spring.batch.job.names:NONE}")
    private String jobNames;
    @PostConstruct
    public void validateJobNames() {
//        try {
//			log.info("============= "+SystemUtil.getApplicationProperties().getProperty("spring.batch.job.names"));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
        log.info("+++ parameter jobNames : {}", jobNames);
        if (jobNames.isEmpty() || jobNames.equals("NONE")) {
            throw new IllegalStateException("spring.batch.job.names=job1,job2 형태로 실행을 원하는 Job을 명시해야만 합니다!");
        }
    
    }
    */
}