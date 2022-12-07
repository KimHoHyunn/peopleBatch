package com.people.batch.tasklet;

import java.util.List;
import java.util.Map;

import org.springframework.batch.core.StepContribution;
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
public class StartingActionTasklet implements Tasklet, InitializingBean {
	
	@Autowired BatchService batchService; 

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        int number = (int) chunkContext.getStepContext()
                                       .getStepExecution()
                                       .getJobExecution()
                                       .getExecutionContext()
                                       .get("incrementNumber");
//        log.info(""+batchService.getList().toString());
        log.info("STARTING ACTION: Number is "+ number);
        
//        List<Map<String, Object>> map = batchService.getList();
        
        return RepeatStatus.FINISHED;
    }

    public void afterPropertiesSet() throws Exception { /* do nothing */ }
}