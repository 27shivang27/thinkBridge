package AzureSignUp;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import graphql.Assert;

public class AzureSignUp {

public static void main(String[] args) {
	
	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	
	ChromeDriver driver = new ChromeDriver();
	
	driver.manage().window().maximize();
	
	driver.get("https://jt-dev.azurewebsites.net/#/SignUp");
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	
	// Validate that the dropdown has "English" and "Dutch"

	List<String> actualValues = new ArrayList<String>();

	driver.findElement(By.xpath("//*[@id='language']")).click();

	List<WebElement> allOptions = driver.findElements(By.xpath("//div[@class=\"ng-binding ng-scope\"]"));

	for(WebElement s: allOptions) {
		
		actualValues.add(s.getText());
	}
	
	Assert.assertTrue(actualValues.contains("English"));

	Assert.assertTrue(actualValues.contains("Dutch"));


	//Fill your name in Full Name Field

	driver.findElement(By.xpath("//*[@id='name']")).sendKeys("Shivang Shrivastava");

	
	//Fill your name in Organization Name Field

	driver.findElement(By.xpath("//*[@id='orgName']")).sendKeys("Shivang Shrivastava");

	
	//Fill your email address with @yahoo.com

	driver.findElement(By.xpath("//*[@id='singUpEmail']")).sendKeys("chouhanjitesh@yahoo.com");

	
	//Click on "I agree to the Terms And Conditions"

	driver.findElement(By.xpath("//*[@class=\"black-color ng-binding\"]")).click();
	
	
	//Click on Get Started

	driver.findElement(By.xpath("//*[@class='form-group custom-form-group']")).click();

	
	//Validate that email is recieved
	
	// Open New Tab and redirect to yahoo login page
	
	driver.switchTo().newWindow(WindowType.TAB);
	
	driver.get("https://in.mail.yahoo.com/");
	
	WebElement signInButton = driver.findElement(By.xpath("//*[@class='fuji-button-link fuji-button-inverted signin']"));
	if(signInButton.isDisplayed()) {
		signInButton.click();
	}
	
	
	//give login username with @yahoo.com
	
	driver.findElement(By.xpath("//*[@id='login-username']")).sendKeys("chouhanjitesh@yahoo.com");
	
	
	//click on Next button to enter password
	
	driver.findElement(By.xpath("//*[@id='login-signin']")).click();
	
	
	//Enter Password
	
	driver.findElement(By.xpath("//*[@id='login-passwd']")).sendKeys("Shivang@1997");
	
	
	//click on Next button to login
	
	driver.findElement(By.xpath("//*[@id='login-signin']")).click();
	
	
	//Search for the email in the mailbox
	
	List<WebElement> emailThreads = new ArrayList<WebElement>(driver.findElements(By.xpath("//*[@class='D_F o_h ab_C H_6D6F a_3vhr3 em_qk ej_0']")));
	
	for(int i=0; i< emailThreads.size(); i++) {
		if(emailThreads.get(i).getText().contains("JabaTalks Development")) {
			emailThreads.get(i).click();
			break;
		}
	}
	
	
	//Validate the email
	
	String subjectLine = driver.findElement(By.xpath("//*[@class='D_F ab_C em_N o_h A_6FsP P_3Y3Gk']")).getText();
	Assert.assertTrue(subjectLine.contains("Please Complete JabaTalks Account"));

	 driver.quit();
}
}