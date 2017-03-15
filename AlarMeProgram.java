package alarMe2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlarMeProgram extends LoginProcess {
	
	public static void main(String[] args) throws InterruptedException, ParseException, com.steadystate.css.parser.ParseException {
	    AlarMeProgram program = new AlarMeProgram();
	    program.init();
	    String username = "brukernavn"; //Hentes fra database
	    String password = "passord"; //Hentes fra database
	    ArrayList<String> assignmentsList = setAssignments(username, password);
	    Assignments assignments = new Assignments(assignmentsList);
	    HashMap<String, Date> examsHash = setExams(username, password); 
	    Exams exams = new Exams(examsHash);
	    System.out.println(assignments.getAssignments());
	    System.out.println(exams.getExams());
	}

    public void init() {
        System.setProperty("webdriver.chrome.driver",
                "/Users/ingridstoen/Documents/workspace/alarMe/chromedriver.exe");
    }
    
    public static ArrayList<String> setAssignments(String username, String password) throws InterruptedException {
    	ArrayList<String> assignments = new ArrayList<String>();
    	WebDriver driver = new ChromeDriver();

        //BLACKBOARD
        driver.get("https://ntnu.blackboard.com/webapps/portal/execute/tabs/tabAction?tab_tab_group_id=_70_1");
        driver.findElement(By.className("loginPrimary")).click();
        Thread.sleep(5000);

        //Choose NTNU as the institution
        chooseNTNU(driver);

        //Fill in username and password to log in
       login(username, password, driver);

        //klikke inn på "varsler"
        driver.findElement(By.linkText("Varsler")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("headerTextheader::1-dueView::1-dueView_4")).click();
        driver.findElement(By.id("headerTextheader::1-dueView::1-dueView_3")).click();
        driver.findElement(By.id("headerTextheader::1-dueView::1-dueView_2")).click();

        //hent alle øvinger fra "fremtiden"
        List<WebElement> assignment_future = driver.findElements(By.className("itemGroups"));
        for (WebElement a : assignment_future) {
            assignments.add(a.getText());
        }

        //henter alle øvinger fra "imorgen"
        List<WebElement> assignment_tomorrow = driver.findElements(By.className("itemGroups"));
        for (WebElement a : assignment_tomorrow) {
            assignments.add(a.getText());
        }

        //henter alle øvinger fra "idag"
        List<WebElement> assignment_thisweek = driver.findElements(By.className("itemGroups"));
        for (WebElement a : assignment_thisweek){
            assignments.add(a.getText());
        }
        
        return assignments;

        //ITSLEARNING

    }
    
    public static HashMap<String, Date> setExams(String username, String password) throws InterruptedException, ParseException {
        WebDriver driver = new ChromeDriver();
        HashMap<String, Date> exams = new HashMap<String, Date>();
        
    	//STUDWEB
        driver.get("https://idp.feide.no/simplesaml/module.php/feide/login.php?asLen=169&AuthState=_"
                + "d3cf8da4fdb8785ba65151ba2683aca1150fe3bfc2%3Ahttps%3A%2F%2Fidp.feide.no%2Fsimplesaml%"
                + "2Fsaml2%2Fidp%2FSSOService.php%3Fspentityid%3Dhttps%253A%252F%252Ffsweb.no"
                + "%252Fstudentweb%26cookieTime%3D1487768961");
        Thread.sleep(5000);

        //Choose NTNU as the institution
        chooseNTNU(driver);

        //innlogging med brukerinput
        login(username, password, driver);

        //need to choose NTNU as the institution one more time
        chooseNTNUAgain(driver);

        //adds the exam dates to this.exams as <subject code subject name, date>
        List<WebElement> datesAndSubjects = driver.findElements(By.className("infoLinje"));
        for (WebElement element : datesAndSubjects){
            int index = datesAndSubjects.indexOf(element);
        	if (index == 0 || index % 3 == 0) {
        	    DateFormat df = new SimpleDateFormat("mm.dd.yyyy");
                Date date = df.parse(element.getText());
                String courseCode = datesAndSubjects.get(index + 1).getText();
                String courseName = datesAndSubjects.get(index + 2).getText();
            	exams.put(courseCode + " " + courseName, date);
            }
            

//            String subject_code = items.get(1);
//            String subject_name = items.get(2);
//            String date_string = items.get(0);
//            DateFormat df = new SimpleDateFormat("mm.dd.yyyy");
//            Date date = df.parse(date_string);
//            this.exams.put(subject_code + " "  + subject_name, date);
        }
         return exams;
    }


}
