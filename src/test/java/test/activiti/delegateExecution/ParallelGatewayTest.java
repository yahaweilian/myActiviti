package test.activiti.delegateExecution;

import java.util.Date;
import java.util.List;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;
import org.shirdrn.workflow.activiti.AbstractTest;

public class ParallelGatewayTest extends AbstractTest {  
  
    private String deploymentId;  
    private Date start = null;  
    private Date end = null;  
      
    @Override  
    protected void initialize() throws Exception {  
        deploymentId = repositoryService.createDeployment()  
            .addClasspathResource("diagrams/Task.UserTask.bpmn20.xml")  
            .deploy().getId();  
    }  
  
    @Override  
    protected void destroy() throws Exception {  
        repositoryService.deleteDeployment(deploymentId, true);  
    }  
      
    @Deployment  
    public void testUnbalancedForkJoin() {  
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("ParalellBasedForkJoin");  
        TaskQuery query = taskService.createTaskQuery().processInstanceId(pi.getId()).orderByTaskName().asc();  
  
        List<Task> tasks = query.list();        
        assertEquals(3, tasks.size());  
        start = new Date();  
        for(Task task : tasks) {  
            taskService.complete(task.getId());  
            end = new Date();  
            System.out.println("" + (end.getTime()-start.getTime()) + "ms.");  
        }  
  
        tasks = query.list();  
        assertEquals(1, tasks.size());  
        for(Task task : tasks) {  
            taskService.complete(task.getId());  
            end = new Date();  
            System.out.println("" + (end.getTime()-start.getTime()) + "ms.");  
        }  
        end = new Date();  
        System.out.println("" + (end.getTime()-start.getTime()) + "ms.");  
    }  
}  
