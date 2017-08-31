
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.Task;
import org.junit.Test;

/**查询
 * @author Ingta037
 *
 */
public class Find {

	@Test
	public void findMyPersonTask(){
		String assignee = "张三";
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		List<Task> list = processEngine.getTaskService()//与正在执行的认为管理相关的Service
				.createTaskQuery()//创建任务查询对象
				.taskAssignee(assignee)//制定个人认为查询，指定办理人
				.list();
		
		if(list !=null && list.size() > 0){
			for(Task task:list){
				System.out.println("任务ID:"+task.getId());
				System.out.println("任务名称:"+task.getName());
				System.out.println("任务创建时间:"+task);
				System.out.println("任务办理人:"+task.getAssignee());
				System.out.println("流程实例ID:"+task.getProcessInstanceId());
				System.out.println("执行对象ID:"+task.getExecutionId());
				System.out.println("流程定义ID:"+task.getProcessDefinitionId());
				System.out.println("########################################");
			}
		}
	}
}
