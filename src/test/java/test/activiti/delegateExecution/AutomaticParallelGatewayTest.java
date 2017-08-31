package test.activiti.delegateExecution;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;
import org.shirdrn.workflow.activiti.AbstractTest;

/**自动执行
 * @author Ingta037
 *
 */
public class AutomaticParallelGatewayTest extends AbstractTest {  
  
    private String deploymentId;  
      
    @Override  
    protected void initialize() throws Exception {  
        deploymentId = repositoryService.createDeployment()  
            .addClasspathResource("diagrams/GatewayTest.testAutomaticForkJoin.bpmn20.xml")  
            .deploy().getId();  
    }  
  
    @Override  
    protected void destroy() throws Exception {  
        repositoryService.deleteDeployment(deploymentId, true);  
    }  
      
    @Deployment  
    public void testForkJoin() {  
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("AutomaticParalellBasedForkJoin");  
        assertEquals(true, pi.isEnded());  
    }  
}  
