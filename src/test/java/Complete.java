import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

/**
 * 完成任务
 * 
 * @author Ingta037
 *
 */
public class Complete {

	@Test
	public void completeMyPersonTask() {
		// 任务id
		String taskId = "25002";
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		processEngine.getTaskService()// 与正在执行的任务管理相关的Service
				.complete(taskId);
		System.out.println("完成任务：任务ID:" + taskId);
	}
}
