package com.people.batch.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.people.batch.listner.LastListener;
import com.people.batch.listner.SecondActionListener;
import com.people.batch.listner.StartingActionListener;
import com.people.batch.tasklet.LastTasklet;
import com.people.batch.tasklet.SecondActionTasklet;
import com.people.batch.tasklet.StartingActionTasklet;

@Configuration
@EnableBatchProcessing
public class BatchStepConfig {

	private StepBuilderFactory stepBuilderFactory;
	
    @Autowired
    public BatchStepConfig (StepBuilderFactory stepBuilderFactory) {
    	this.stepBuilderFactory = stepBuilderFactory;
    }
    
    @Bean
    @JobScope
    public Step getStartingStep(@Qualifier("startingActionTasklet") final StartingActionTasklet tasklet,
                                @Qualifier("startingActionListener") final StartingActionListener listener) {
        return stepBuilderFactory.get("getStartingStep")
                                 .tasklet(tasklet)
                                 .listener(listener)
                                 .build();
    }

    @Bean
    @JobScope
    public Step getSecondStep(@Qualifier("secondActionTasklet") final SecondActionTasklet tasklet,
                              @Qualifier("secondActionListener") final SecondActionListener listener) {
        return stepBuilderFactory.get("getSecondStep")
                                 .tasklet(tasklet)
                                 .listener(listener)
                                 .build();
    }
    
    @Bean
    @JobScope
    public Step getLastStep(@Qualifier("lastTasklet") final LastTasklet tasklet,
                              @Qualifier("lastListener") final LastListener listener) {
        return stepBuilderFactory.get("getLastStep")
                                 .tasklet(tasklet)
                                 .listener(listener)
                                 .build();
    }
}