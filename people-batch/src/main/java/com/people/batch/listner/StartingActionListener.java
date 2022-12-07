package com.people.batch.listner;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class StartingActionListener implements StepExecutionListener {


    public void beforeStep(StepExecution stepExecution) {
        log.debug("StartingActionListener - beforeStep");

        // Get incrementNumber from job execution context
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();
        Integer incrementNumber = (Integer) jobContext.get("incrementNumber");

        if (incrementNumber == null) {
            jobContext.put("incrementNumber", 0);
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.debug("StartingActionListener - afterStep");

        // Get incrementNumber from job execution context
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();
        Integer incrementNumber = (Integer) jobContext.get("incrementNumber");

        // Continue job execution if have more feed, stop otherwise
        if (incrementNumber >= 3) {
            return new ExitStatus(ExitStatus.COMPLETED.toString());
        } else {
            return new ExitStatus(ExitStatus.EXECUTING.toString());
        }
    }
}