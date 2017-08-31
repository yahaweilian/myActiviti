package test.activiti.delegateExecution;

import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
import org.activiti.engine.repository.Deployment;  
import org.activiti.engine.runtime.Execution;  
import org.activiti.engine.runtime.ProcessInstance;  
import org.shirdrn.workflow.activiti.AbstractTest;  
import org.shirdrn.workflow.activiti.subprocess.Merchant;  
  
 
/**
 * @author Ingta037
 *
 */
public class MyReceiveTaskTest extends AbstractTest {  
  
    @Override  
    protected void initialize() throws Exception {  
        Deployment deployment = repositoryService  
        .createDeployment()  
        .addClasspathResource(  
                "diagrams/Task.ReceiveTask.bpmn20.xml")  
        .deploy();    
        deploymentId = deployment.getId();  
    }  
  
    @Override  
    protected void destroy() throws Exception {  
        repositoryService.deleteDeployment(deploymentId, true);   
    }  
      
    public void testSubProcess() {  
        // prepare data packet  
        Map<String, Object> variables = new HashMap<String, Object>();  
        Map<String, Object> subVariables = new HashMap<String, Object>();  
        variables.put("maxTransCount", 1000000);  
        variables.put("merchant", new Merchant("ICBC"));  
        variables.put("protocol", "UM32");  
        variables.put("repository", "D:\\mvn_repo\\pository");  
        variables.put("in", subVariables);  
        variables.put("out", new HashMap<String, Object>());  
          
        // start process instance  
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("MyReceiveTask", variables);  
        List<Execution> executions = runtimeService.createExecutionQuery().list();  
        assertEquals(1, executions.size());  
          
        Execution execution = runtimeService.createExecutionQuery().singleResult();  
        runtimeService.setVariable(execution.getId(), "type", "receiveTask");  
        runtimeService.signal(execution.getId());  
        assertEquals(1, executions.size());  
          
        execution = runtimeService.createExecutionQuery().list().get(0);  
        assertNotNull(execution);  
        runtimeService.setVariable(execution.getId(), "oper", "shirdrn");  
        runtimeService.signal(execution.getId());  
    }  
  
}  
