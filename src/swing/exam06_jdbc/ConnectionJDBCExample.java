package swing.exam06_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBCExample {
	 Connection conn;
		public ConnectionJDBCExample() {
			
			
			String driverName ="com.mysql.jdbc.Driver";
			String url ="jdbc:mysql://localhost:3306/world";
			String user="root";
			String password ="0000";
			
			try{
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, user, password);
			}catch (ClassNotFoundException e){
				System.out.println("mysql jdbc driver can't loading..");
				//e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("커넥션이 연결하지 못하였습니다.");
			} 
		}
		
		
		public static void main(String[] args) {
			new ConnectionJDBCExample();
		}

}
