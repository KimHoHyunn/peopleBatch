package com.people.batch.listner;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class LastListener implements StepExecutionListener {
	
    public void beforeStep(StepExecution stepExecution) {

    }
    
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.debug("LastActionListener - afterStep");

        return null;
    }
}