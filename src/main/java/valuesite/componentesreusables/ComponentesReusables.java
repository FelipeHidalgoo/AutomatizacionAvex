package valuesite.componentesreusables;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComponentesReusables {

	public static final Duration EXPLICIT_WAIT_TIMEOUT = Duration.ofSeconds(10);
	public static final Duration FLUENT_WAIT_TIMEOUT = Duration.ofSeconds(10);

	WebDriver driver;

	public ComponentesReusables(WebDriver driver) {

		this.driver = driver;
	}
	
	WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitForWebElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(3000);
//		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
//		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public void waitForElementToDisappear(WebDriver driver, By locator) {
		// Definir la espera con FluentWait
		Wait<WebDriver> fwait = new FluentWait<>(driver).withTimeout(FLUENT_WAIT_TIMEOUT) // Definir el tiempo máximo
																							// de espera
				.pollingEvery(Duration.ofMillis(500)); // Controlar la frecuencia de comprobación
//				.ignoring(NoSuchElementException.class); // Ignorar excepciones si no se encuentra el elemento
//															// inmediatamente

		// Esperar a que el elemento desaparezca
		fwait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
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
