package org.shirdrn.workflow.activiti.task;

import java.util.HashMap;
import java.util.logging.Logger;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class CheckMerchantReceiveTask implements JavaDelegate {  
  
    private final Logger log = Logger.getLogger(CheckMerchantReceiveTask.class.getName());  
      
    @SuppressWarnings("unchecked")  
    @Override  
    public void execute(DelegateExecution execution) throws Exception {  
        log.info("i am CheckMerchantReceiveTask.");  
        System.out.println("in : " + execution.getVariables());  
        ((HashMap<String, Object>)execution.getVariables().get("in")).put("previous", "CheckMerchantReceiveTask");  
    }  
}  
