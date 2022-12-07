package com.people.batch.config;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
//@ComponentScan(basePackages = "com.example.*")
// 아래를 통해 실행 대상 job으로 들어갔을 때만 해당 Job 설정이 수행되게 하면 불필요한 설정 부담을 줄일 수 있고
// 실수로 job이 실행되는 것을 막을 수 있다.
//@ConditionalOnProperty(name = "spring.batch.job.names", havingValue = "exampleJob")
public class BatchConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean(name = "peopleJob")
    public Job peopleJob(@Qualifier("getStartingStep") final Step startingStep,
			             @Qualifier("getSecondStep") final Step secondStep,
			             @Qualifier("getLastStep") final Step lastStep) {
        return jobBuilderFactory.get("peopleJob")
                .start(startingStep)
                	.on(ExitStatus.EXECUTING.toString())
                	.to(secondStep)
                .from(startingStep)
                	.on(ExitStatus.COMPLETED.toString())
                	.to(lastStep)
                .from(secondStep)
                	.next(startingStep)
                	.build()
                .build();

//        return jobBuilderFactory.get("peopleJob")
//                .start(startingStep)
//                .next(secondStep)
//                .next(lastStep)
//                .build()
//                ;
    }
 
}
