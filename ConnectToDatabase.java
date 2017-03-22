package alarMe;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.glass.ui.Cursor;


public abstract class ConnectToDatabase {
	
	Connection connection;
	//String status = "new";
	String username, user_password;
	String db;
	
	//public ConnectToDatabase(Connection connection, String db){
		//this.connection = connection;
		//this.db = db;
	//}
	public void setConnection(){
        try {
        	connection = null;
            //Class.forName("com.myql.jdbc.Driver");
            String server = "sql11.freemysqlhosting.net";
            String database_username = "sql11163131";
            String database_password = "wi4gXfVvT3";
            String connectionString = "jdbc:mysql://" + server + "/" + db + "?user=" + database_username + "&password=" + database_password;
            connection = DriverManager.getConnection(connectionString);
            
        } catch (Exception e){
            e.printStackTrace();}
  }

	
	public String getNewUser() throws SQLException{
		ArrayList<String> user = new ArrayList<String>();
		setConnection();
		Statement stm = null;
		String query = "select * from Student";
		try{
			int row_database = 0;
			int student_id = 15;
			stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(query);
			//siden vi tenker at det bare blir lagt til én bruker om gangen
			while(rs.next()){
				row_database += 1;
				student_id += 1;
	            username = rs.getString("username");
	            user_password = rs.getString("user_password");				
			}
            user.add(username);
			user.add(user_password);
			System.out.println("brukernavn = " + username + " passord = " + user_password + " student_id = " + student_id);
			
		}catch(Exception e){
			System.out.println("Ingen nye brukere lagt til" + e);
		
		}finally {
	        if (stm != null) { stm.close(); }
	    }
		return (user.get(0) + user.get(1));
	}


}