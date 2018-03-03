import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * 创建工作流的表
 * @author Ingta037
 *
 */
public class CreateTable {

	@Test
	public void createTable() {
		// 工作流引擎的全部配置
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
				.createStandaloneProcessEngineConfiguration();
		// 连接数据的配置
		processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
		processEngineConfiguration.setJdbcUrl(
				"jdbc:mysql://localhost:3306/activiti1?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf8");
		processEngineConfiguration.setJdbcUsername("root");
		processEngineConfiguration.setJdbcPassword("123456");
		// 如果表不存在自动创建表
		processEngineConfiguration.setDatabaseSchemaUpdate(processEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		// 工作流的和新对象，ProcessEnginee对象
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		System.out.println(processEngine);
	}
	
	@Test
	public void createTable3() {
		// 工作流引擎的全部配置
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
				.createStandaloneProcessEngineConfiguration();
		// 连接数据的配置
		processEngineConfiguration.setJdbcDriver("oracle.jdbc.driver.OracleDriver");
		processEngineConfiguration.setJdbcUrl(
				"jdbc:oracle:thin:@localhost:1521:xe");
		processEngineConfiguration.setJdbcUsername("tlps");
		processEngineConfiguration.setJdbcPassword("tlps");
		processEngineConfiguration.setDatabaseSchema("activiti");
		// 如果表不存在自动创建表
		processEngineConfiguration.setDatabaseSchemaUpdate(processEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		// 工作流的和新对象，ProcessEnginee对象
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		System.out.println(processEngine);
	}

	@Test
	public void createTable2() {

		// 通过让工作流引擎的全部配置对象来执行配置文件中的内容来创建流程引擎对象
		ProcessEngine processEngine = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
		System.out.println("ProcessEngine:" + processEngine);

	}
}
