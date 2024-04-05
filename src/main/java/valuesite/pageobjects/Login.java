package valuesite.pageobjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

	WebDriver driver;

	// Constructor para inicializar driver
	public Login(WebDriver driver) {
		// Inicializar
		this.driver = driver;

		// Inicializar PageFactory
		PageFactory.initElements(driver, this);
	}

// PAGE FACTORY

	// Elemento campo Correo
	@FindBy(id = "txtCorreo")
	WebElement userEmail;

	// Elemento campo Contraseña
	@FindBy(id = "txtContraseña")
	WebElement userPass;

	// Elemento boton Ingresar
	@FindBy(id = "btnLoginModal")
	WebElement ingresar;

// PAGE FACTORY	

	public void iniciarSesion(String correo, String contraseña) {
		userEmail.sendKeys(correo);
		userPass.sendKeys(contraseña);
		ingresar.click();
	}

	public void irLogin(String URL) throws IOException {
		// Clase de propiedades

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\valuesite\\Resources\\GlobalData.properties");
		prop.load(fis);
		
		driver.get(URL);
	}
}
