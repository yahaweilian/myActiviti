package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class DataController implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("666666666666666666666666666666666666666666");
		// 1.加载驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		// 2.获得数据库的连接
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql:/localhost:3306/activiti1?useUnicode=true&characterEncoding=utf8", "root", "123456");
		// 3.通过数据库的连接操作数据库，实现增删改查
		Statement stmt = conn.createStatement();
		System.out.println("77777777777777777777777777777777777777777");
		ResultSet rs = stmt.executeQuery("select id_,name_ from act_ru_task");// 选择import java.sql.ResultSet;
		while (rs.next()) {// 如果对象中有数据，就会循环打印出来
			
			System.err.println(rs.getString("id_") + "," + rs.getInt("name_"));
		}

	}

}
