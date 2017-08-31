import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

/**
 * 部署流程定义
 * @author Ingta037
 *
 */
public class Bushu {

	@Test
	public void deploymentProcessDefinition(){
		// 创建核心引擎对象
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的service
				.createDeployment()// 创建一个部署对象
				.name("helloworld 入门程序")// 添加部署名称
				.addClasspathResource("diagrams/helloWorld/helloWorld.bpmn")// classpath的资源中加载，一次只能加载
				.addClasspathResource("diagrams/helloWorld/helloWorld.png")
				.deploy();// 完成部署
		System.out.println("部署ID:"+deployment.getId());
		System.out.println("部署名称："+deployment.getName());
	}
	
}
