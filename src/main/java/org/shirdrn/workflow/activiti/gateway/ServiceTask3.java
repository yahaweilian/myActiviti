package org.shirdrn.workflow.activiti.gateway;

import java.util.logging.Logger;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ServiceTask3 implements JavaDelegate {  
  
    private final Logger log = Logger.getLogger(ServiceTask3.class.getName());  
  
    @Override  
    public void execute(DelegateExecution execution) throws Exception {  
        Thread.sleep(10000);  
        log.info("variavles=" + execution.getVariables());  
        execution.setVariable("task3", "I am task 3");  
        log.info("I am task 3.");         
    }  
}  
