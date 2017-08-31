package org.shirdrn.workflow.activiti;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

import junit.framework.TestCase;

/**
 * 
 * 这里面，主要是在测试之前做一些初始化工作，主要包括流程引擎实例的构建，及其流程提供的基本服务。下面测试会用到该抽象类。
 * @author Ingta037
 *
 */
public abstract class AbstractTest  extends TestCase{

	private ProcessEngine processEngine;  
    protected String deploymentId;  
    protected RepositoryService repositoryService;  
    protected RuntimeService runtimeService;  
    protected TaskService taskService;  
    protected FormService formService;  
    protected HistoryService historyService;  
    protected IdentityService identityService;  
    protected ManagementService managementService;  
    
    @Override  
    protected void setUp() throws Exception {  
        super.setUp();  
        if(processEngine==null) {  
            processEngine = ProcessEngines.getDefaultProcessEngine();  
        }  
        repositoryService = processEngine.getRepositoryService();  
        runtimeService = processEngine.getRuntimeService();  
        taskService = processEngine.getTaskService();  
        formService = processEngine.getFormService();  
        historyService = processEngine.getHistoryService();  
        identityService = processEngine.getIdentityService();  
        managementService = processEngine.getManagementService();  
        initialize();  
    }  
      
    @Override  
    protected void tearDown() throws Exception {  
        super.tearDown();  
        destroy();  
    }  
      
    protected abstract void initialize() throws Exception;  
      
    protected abstract void destroy() throws Exception;  
    
}
