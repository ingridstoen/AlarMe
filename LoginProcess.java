package alarMe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public abstract class LoginProcess {
	
	protected static String username; //hentes fra database;
	protected static String password; //hentes fra database
	protected static WebDriver driver;
			
	public LoginProcess(String username, String password, WebDriver driver){
		//hente username og password fra database. - kaller metode getUsername....
		//setter også webDriver her.
		LoginProcess.driver = driver;
		//fjerner da driver fra alle metodene.
		LoginProcess.password = this.password;
		LoginProcess.username = this.username;
	}
	
	//public String getPasswordFromDatabase(){
		//kaller getConnectionMedDatabase
		//hent ut blabla.
		//Connect();
	//}
	
	//public String getUsernameFromDatabase(){
		
	//}
	
	public void chooseNTNU() throws InterruptedException {
        Select objectSelect = new Select(driver.findElement(By.id("org")));
        objectSelect.selectByValue("ntnu.no");
        driver.findElement(By.className("submit")).click();
        Thread.sleep(5000);
    }

    public void chooseNTNUAgain() throws InterruptedException {
        Select schoolSelect = new Select(driver.findElement(By.id("institusjonsvalg:institusjonsMenu")));
        schoolSelect.selectByValue("FSNTNU");
        driver.findElement(By.name("institusjonsvalg:j_idt121")).click();
        Thread.sleep(5000);
        driver.findElement(By.linkText("Pålogging via Feide")).click();
        Thread.sleep(5000);
    }

    public void login() throws InterruptedException {
    	//tror ikke vi trenger å ta inn username og password her da om vi setter det i konstruktøren.
        WebElement usernameField = driver.findElement(By.name("feidename"));
        WebElement passwordField = driver.findElement(By.name("password"));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        driver.findElement(By.className("submit")).click();
        Thread.sleep(5000);
    }

}
