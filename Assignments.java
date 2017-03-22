package alarMe;

import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.steadystate.css.parser.ParseException;

public class Assignments extends LoginProcess {
	private ArrayList<String> assignments;
    
    //konstruktør
    public Assignments(){
    	super(username, password, driver); //henter konstruktøren fra LoginProcess
    	this.assignments = new ArrayList<String>();
    }

    //hent assignments
    public ArrayList<String> getAssignments() {
        return assignments;
    }
    
    public void setAssignments() throws InterruptedException{
    	//BLACKBOARD
        driver.get("https://ntnu.blackboard.com/webapps/portal/execute/tabs/tabAction?tab_tab_group_id=_70_1");
        driver.findElement(By.className("loginPrimary")).click();
        Thread.sleep(5000);

        //Choose NTNU as the institution
        chooseNTNU();

        //Fill in username and password to log in
       login();

        //klikke inn på "varsler"
        driver.findElement(By.linkText("Varsler")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("headerTextheader::1-dueView::1-dueView_4")).click();
        driver.findElement(By.id("headerTextheader::1-dueView::1-dueView_3")).click();
        driver.findElement(By.id("headerTextheader::1-dueView::1-dueView_2")).click();

        //hent alle Ã¸vinger fra "fremtiden"
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

        //ITSLEARNING
        
        driver.get("http://www.ilearn.sexy"); //loads the webpage
		Thread.sleep(5000);
		
		driver.switchTo().frame(driver.findElement(By.name("mainmenu")));
		List <WebElement> active_assignements = driver.findElements(By.cssSelector("a > .h-va-baseline"));
        System.out.println(active_assignements.size());
		for (WebElement a : active_assignements){
			assignments.add(a.getText());
        	System.out.println(a.getText());

    }
		driver.quit();
		

    }
  
}
