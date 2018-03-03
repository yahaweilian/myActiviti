import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.junit.Test;

public class GqJh {

	/**
	 * 流程挂起
	 */
	@Test
	public void guaQi() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        
        String processInstanceId="27505";
        //根据一个流程实例的id挂起该流程实例
        runtimeService.suspendProcessInstanceById(processInstanceId);
	}
	
	/**
	 * 流程激活
	 */
	@Test
	public void Jihuo() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        
        String processInstanceId="27505";
        
        runtimeService.activateProcessInstanceById(processInstanceId);
	}
}
