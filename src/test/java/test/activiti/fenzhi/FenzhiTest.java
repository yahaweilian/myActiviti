package test.activiti.fenzhi;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class FenzhiTest {

	/**
	 * 部署流程
	 */
	@Test
	public void deploymentProcessDefinition(){
		// 创建核心引擎对象
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的service
				.createDeployment()// 创建一个部署对象
				.name("分支流程")// 添加部署名称
				.addClasspathResource("diagrams/fenzhi/bingxing.bpmn")// classpath的资源中加载，一次只能加载
				.addClasspathResource("diagrams/fenzhi/bingxing.png")
				.deploy();// 完成部署
		System.out.println("部署ID:"+deployment.getId());
		System.out.println("部署名称："+deployment.getName());
	}
	
	
	/**
	 * 启动流程
	 */
	@Test
	public void startProcessInstance(){
		// 流程定义的key
		String processDefinitionKey = "bingxing";
		ProcessEngine processEngine  = ProcessEngines.getDefaultProcessEngine();
		ProcessInstance pi = processEngine.getRuntimeService()// 于正在执行的流程实例和执行对象相关的service
				.startProcessInstanceByKey(processDefinitionKey);//使用流程定义的key启动流程实例，key对应
		// helloWorld.bpmn中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
		System.out.println("流程实例ID:"+pi.getId());// 流程实例ID 
		System.out.println("流程定义ID:"+pi.getProcessDefinitionId());// 流程定义ID
	}
	
	/**
	 * 完成任务
	 */
	@Test
	public void completeMyPersonTask() {
		// 任务id
		String taskId = "65005";
		Map varb = new HashMap<>();
		varb.put("message", "同意");
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		processEngine.getTaskService()// 与正在执行的任务管理相关的Service
				.complete(taskId,varb);
		System.out.println("完成任务：任务ID:" + taskId);
	}
}
