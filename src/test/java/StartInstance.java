import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/**
 * @author Ingta037
 * 启动流程实例
 */
public class StartInstance {

	@Test
	public void startProcessInstance(){
		// 流程定义的key
		String processDefinitionKey = "helloWorld";
		ProcessEngine processEngine  = ProcessEngines.getDefaultProcessEngine();
		ProcessInstance pi = processEngine.getRuntimeService()// 于正在执行的流程实例和执行对象相关的service
				.startProcessInstanceByKey(processDefinitionKey);//使用流程定义的key启动流程实例，key对应
		// helloWorld.bpmn中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
		System.out.println("流程实例ID:"+pi.getId());// 流程实例ID 
		System.out.println("流程定义ID:"+pi.getProcessDefinitionId());// 流程定义ID
	}
}
