package com.people.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.people.batch.service.BatchService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
@JobScope
public class LastTasklet implements Tasklet, InitializingBean {
	
	@Autowired BatchService batchService; 

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

    	log.info("\n\n"+batchService.getList().toString());
    	log.info("\n\n");
    	log.info(String.valueOf(chunkContext.getAttribute("last")));
    	log.info("\n\n");
    	
    	
        return RepeatStatus.FINISHED;
    }

    public void afterPropertiesSet() throws Exception { /* do nothing */ }
}