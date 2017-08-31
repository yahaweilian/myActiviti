package org.shirdrn.workflow.activiti.task;

import java.util.HashMap;
import java.util.logging.Logger;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * 手工触发执行 对应的处理类
 * @author Ingta037
 *
 */
public class CheckBankReceiveTask implements JavaDelegate {  
  
    private final Logger log = Logger.getLogger(CheckBankReceiveTask.class.getName());  
  
    @SuppressWarnings("unchecked")  
    @Override  
    public void execute(DelegateExecution execution) throws Exception {
        log.info("i am CheckBankReceiveTask.");  
        System.out.println("in : " + execution.getVariables());  
        ((HashMap<String, Object>)execution.getVariables().get("in")).put("next", "CheckBankTask");  
        ((HashMap<String, Object>)execution.getVariables().get("out")).put("reponse", "subprocess:CheckBankReceiveTask->CheckMerchantReceiveTask");          
    }  
}  
