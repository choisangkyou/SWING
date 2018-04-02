package swing.exam06_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionJDBCExample {
	 Connection conn=null;
	 Statement stmt =null;
	 ResultSet rs = null;
		public ConnectionJDBCExample() {
			
			
			String driverName ="com.mysql.jdbc.Driver";
			//String url ="jdbc:mysql://localhost:3306/world";
			String url = "jdbc:mysql://127.0.0.1:3306/world?useSSL=true&verifyServerCertificate=false";//SSL 에러발생시..
			
			String user="root";
			String password ="0000";
			
			try{
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, user, password);
				//System.out.println("JDBC Driver Connection Success");
				
				// [select] 
				String str = "select * from person";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(str);
				//System.out.println("sql:"+ str);
				
				System.out.println("이름\t전화\t\t메일");
				while(rs.next()) {
					System.out.print(rs.getString("name") + "\t" );
					System.out.print(rs.getString("phone") + "\t" );
					System.out.print(rs.getString("email") );
					System.out.println();
					//
				}
				
				/*insert
				String str_insert = "insert into person value('김자광','010-3456-7787','rrte@daum.net')";
				stmt.executeUpdate(str_insert);
				*/
				
					
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
