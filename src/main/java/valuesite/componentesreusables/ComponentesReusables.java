package valuesite.componentesreusables;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComponentesReusables {
	
	public static final Duration EXPLICIT_WAIT_TIMEOUT = Duration.ofSeconds(10);

	WebDriver driver;

	public ComponentesReusables(WebDriver driver) {
		
		this.driver = driver;
	}


	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	

	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
//		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void waitForInvisibilityOfElement(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.invisibilityOf(findBy));
	}
	
	public void waitForWebElementToBeClickable(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}

}
