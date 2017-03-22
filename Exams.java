package alarMe;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class Exams extends LoginProcess {

    private HashMap<String, String> exams;
  
    public Exams(){
    	super(username,password,driver);
    	this.exams = new HashMap<String, String>();
      }

    public HashMap<String, String> getExams() {
        return exams;
    }
    
    public void setExams() throws InterruptedException{
    	//STUDWEB
        driver.get("https://idp.feide.no/simplesaml/module.php/feide/login.php?asLen=169&AuthState=_"
                + "d3cf8da4fdb8785ba65151ba2683aca1150fe3bfc2%3Ahttps%3A%2F%2Fidp.feide.no%2Fsimplesaml%"
                + "2Fsaml2%2Fidp%2FSSOService.php%3Fspentityid%3Dhttps%253A%252F%252Ffsweb.no"
                + "%252Fstudentweb%26cookieTime%3D1487768961");
        Thread.sleep(5000);

        //Choose NTNU as the institution
        chooseNTNU();

        //innlogging med brukerinput
        login();

        //need to choose NTNU as the institution one more time
        chooseNTNUAgain();

        //adds the exam dates to this.exams as <subject code subject name, date>
        List<WebElement> datesAndSubjects = driver.findElements(By.className("infoLinje"));
        for (WebElement element : datesAndSubjects){
            int index = datesAndSubjects.indexOf(element);
        	if (index == 0 || index % 3 == 0) {
        	    //DateFormat df = new SimpleDateFormat("mm.dd.yyyy");
                //String date1 = df.parse(element.getText());
                String date = datesAndSubjects.get(index).getText();
                String courseCode = datesAndSubjects.get(index + 1).getText();
                String courseName = datesAndSubjects.get(index + 2).getText();
            	this.exams.put(courseCode + " " + courseName, date);
            }
            

//            String subject_code = items.get(1);
//            String subject_name = items.get(2);
//            String date_string = items.get(0);
//            DateFormat df = new SimpleDateFormat("mm.dd.yyyy");
//            Date date = df.parse(date_string);
//            this.exams.put(subject_code + " "  + subject_name, date);
        }

    }


}
