package org.shirdrn.workflow.activiti.gateway;

import java.util.logging.Logger;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.impl.pvm.delegate.TaskListener;

public class Task3Listener implements TaskListener {  
  
    private final Logger log = Logger.getLogger(Task3Listener.class.getName());  
      
    @Override  
    public void notify(DelegateTask delegateTask) {  
        try {  
            Thread.sleep(5000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        log.info("I am task 3.");  
    }  
}  
