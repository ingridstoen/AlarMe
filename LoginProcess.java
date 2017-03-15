package alarMe2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public abstract class LoginProcess {
	
	public static void chooseNTNU(WebDriver driver) throws InterruptedException {
        Select objectSelect = new Select(driver.findElement(By.id("org")));
        objectSelect.selectByValue("ntnu.no");
        driver.findElement(By.className("submit")).click();
        Thread.sleep(5000);
    }

    public static void chooseNTNUAgain(WebDriver driver) throws InterruptedException {
        Select schoolSelect = new Select(driver.findElement(By.id("institusjonsvalg:institusjonsMenu")));
        schoolSelect.selectByValue("FSNTNU");
        driver.findElement(By.name("institusjonsvalg:j_idt121")).click();
        Thread.sleep(5000);
        driver.findElement(By.linkText("Pålogging via Feide")).click();
        Thread.sleep(5000);
    }

    public static void login(String username, String password, WebDriver driver) throws InterruptedException {
        WebElement usernameField = driver.findElement(By.name("feidename"));
        WebElement passwordField = driver.findElement(By.name("password"));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        driver.findElement(By.className("submit")).click();
        Thread.sleep(5000);
    }

}
