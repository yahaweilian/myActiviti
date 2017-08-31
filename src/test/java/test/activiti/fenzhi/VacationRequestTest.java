package test.activiti.fenzhi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;


public class VacationRequestTest {

	Logger log = Logger.getLogger(VacationRequestTest.class.getName());
	
	ProcessEngine processEngine  = ProcessEngines.getDefaultProcessEngine();
	RepositoryService repositoryService = processEngine.getRepositoryService();
	TaskService taskService = processEngine.getTaskService();
	ManagementService managementService = processEngine.getManagementService();
	
	@Rule 
	public ActivitiRule activitiRule = new ActivitiRule();
	
	
	/**
	 * 部署流程
	 */
	@Test
	public void deploymentProcessDefinition(){
		// 创建核心引擎对象
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的service
				.createDeployment()// 创建一个部署对象
				.category("")//类别
				.name("分支流程")// 添加部署名称
				.addClasspathResource("diagrams/VacationRequest.bpmn20.xml")// classpath的资源中加载，一次只能加载
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
		String processDefinitionKey = "vacationRequest";
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "Kermit");
		variables.put("numberOfDays", new Integer(4));
		variables.put("vacationMotivation", "I'm really tired!");
		
		ProcessEngine processEngine  = ProcessEngines.getDefaultProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance pi = processEngine.getRuntimeService()// 于正在执行的流程实例和执行对象相关的service
				.startProcessInstanceByKey(processDefinitionKey,variables);//使用流程定义的key启动流程实例，key对应
		// helloWorld.bpmn中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
		System.out.println("流程实例ID:"+pi.getId());// 流程实例ID 
		System.out.println("流程定义ID:"+pi.getProcessDefinitionId());// 流程定义ID
		Logger.getLogger(VacationRequestTest.class.getName()).info("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
	}
	
	/**
	 * 完成任务
	 */
	@Test
	public void completeMyPersonTask() {
		// 获取management组的所有任务
		ProcessEngine processEngine  = ProcessEngines.getDefaultProcessEngine();
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
		for (Task task : tasks) {
		log.info("Task available: " + task.getName());
		}
		// 结束任务
		Task task = tasks.get(0);
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("vacationApproved", "false");
		taskVariables.put("managerMotivation", "We have a tight deadline!");
		taskService.complete(task.getId(), taskVariables);
	}
	
	/**
	 * 暂停流程定义
	 * 当流程定义暂停后，不能再创建新的流程实例（会抛出异常）
	 */
	@Test
	public void suspendingProcess() {
		ProcessEngine processEngine  = ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();
				
		repositoryService.suspendProcessDefinitionByKey("vacationRequest");
		try {
		runtimeService.startProcessInstanceByKey("vacationRequest");
		} catch (ActivitiException e) {
		e.printStackTrace();
		}
	}
	
	/**
	 * 激活暂停的流程
	 * 
	 * 
	 */
	@Test
	public void activatingProcess() {
		repositoryService.activateProcessDefinitionByKey("vacationRequest");
	}
	
	/**
	 * JUnit 4风格的测试与 ActivitiRule 的用法
	 */
	@Test
	@org.activiti.engine.test.Deployment
	public void activatingProcess2() {
		
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		repositoryService.activateProcessDefinitionByKey("vacationRequest");
	}
	
	/**
	 * 查询
	 */
	@Test
	public void query() {
		List<Task> tasks = taskService.createTaskQuery()
				.taskAssignee("kermit")
				.processVariableValueEquals("orderId", "10002")
				.orderByDueDate().asc()
				.list();
	}
	
	/**
	 * 原生SQL语句查询
	 */
	@Test
	public void query2(){
		List<Task> tasks = taskService.createNativeTaskQuery()
				.sql("SELECT count(*) FROM " + managementService.getTableName(Task.class) + " T WHERE T.NAME_ = #{taskName}")
				.parameter("taskName", "gonzoTask")
				.list();
				long count = taskService.createNativeTaskQuery()
				.sql("SELECT count(*) FROM " + managementService.getTableName(Task.class) + " T1, "
				+ managementService.getTableName(VariableInstanceEntity.class) + " V1 WHERE V1.TASK_ID_ = T1.ID_")
				.count();
				System.out.println(count);
	}
	
	
	
}
