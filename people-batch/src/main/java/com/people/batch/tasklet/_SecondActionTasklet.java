package com.people.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class _SecondActionTasklet implements Tasklet, InitializingBean {
	
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        int number = (int) chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .get("incrementNumber");

        log.info("SECOND ACTION: Number is " + number);

        number++;

        log.info("SECOND ACTION: Number is now " + number);
        chunkContext.setAttribute("last", "11111111");
        chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .put("incrementNumber", number);

        return RepeatStatus.FINISHED;

    }

    public void afterPropertiesSet() throws Exception {
        //do nothing
    }
}