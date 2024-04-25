package valuesite.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import valuesite.pageobjects.Login;

public class BaseTest {

	private static final Duration IMPLICIT_WAIT_TIMEOUT = Duration.ofSeconds(3);

	public static WebDriver driver;
	public Login loginPage;

	public WebDriver inicializarDriver() throws IOException {

		// Clase de propiedades

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\valuesite\\Resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();

		} else if (browserName.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT);
		return driver;
	}
	
	public String tomarCaptura(String nombreDePrueba, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reportes\\screenshots\\\\" + nombreDePrueba + ".png");
		FileUtils.copyFile(source,  file);
		return System.getProperty("user.dir") + "\\reportes\\screenshots\\" + nombreDePrueba + ".png";
	}

	@BeforeTest
	public void lanzarNavegador() throws IOException {
		// Clase de propiedades
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\valuesite\\Resources\\GlobalData.properties");
		prop.load(fis);

		driver = inicializarDriver();
		// Constructor de elementos y acciones (PageFactory)
		loginPage = new Login(driver);

		// Link de pagina (Variables globales en archivo config.properties)
		loginPage.irLogin(prop.getProperty("URLQA"));
	}

	@AfterTest
	public void Finalizar() throws InterruptedException {
		//Thread.sleep(500);
		driver.quit();
	}
}
