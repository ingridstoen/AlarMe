package alarMe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class AlarMeProgram {
	
	//private static WebDriver driver1 = new ChromeDriver();
	Connection connection;
	String username, user_password;
	String db = "sql11163131";
	
	public AlarMeProgram(){
		
	}
	
	public void init() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Heidi\\tdt4100-2017-master2\\"
        		+ "ws\\chromedriver_win32\\chromedriver.exe");
    }

    	
    public static void main(String[] args) throws InterruptedException, SQLException {
	   	AlarMeProgram program = new AlarMeProgram();
	   	program.setConnection();
	   	program.init();
	   	program.getNewUser().split("");
	   	String username = program.getNewUser().substring(0);
	   	String password = program.getNewUser().substring(1);
        Assignments assignments = new Assignments();
        assignments.setAssignments();
		System.out.println(assignments.getAssignments());
        Exams exams = new Exams();
        exams.setExams();
        System.out.println(exams.getExams());

    }
    
	
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

