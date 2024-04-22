package valuesite.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import valuesite.componentesreusables.ComponentesReusables;

public class ModificacionPinRT extends ComponentesReusables {

	WebDriver driver;

	// Incializa driver y PageFactory
	public ModificacionPinRT(WebDriver driver) {

		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	// PAGE FACTORY

	// Pestaña de adminstracion en navBar
	@FindBy(xpath = "//a[normalize-space()='Administración']")
	WebElement tabAdministracion;

	// Pestaña de area cliente en navBar
	@FindBy(xpath = "//a[normalize-space()='Modificación PIN']")
	WebElement tabModificacionPin;

	@FindBy(id = "btnLimpiarPin")
	public WebElement btnCancelar;

	@FindBy(id = "btnActualizaPin")
	public WebElement btnActualizar;

	@FindBy(id = "txtPin")
	public WebElement campoPin;

	@FindBy(css = ".alert.alert-danger")
	public WebElement msjError;

	@FindBy(css = ".alert.alert-success")
	public WebElement msjExito;

	// PAGE FACTORY

	public void ingresoMantenedorPin() {
		tabAdministracion.click();
		tabModificacionPin.click();
	}

	public void ingresaPin(String pin) {
		campoPin.sendKeys(pin);
	}

	public void actualizaPin(String pin) {
		campoPin.sendKeys(pin);
		btnActualizar.click();
	}

	public String obtienePIN() {
		String pin = campoPin.getAttribute("value");

		// Decodificar caracteres especiales si es necesario
		pin = decodeSpecialCharacters(pin);

		return pin;
	}

	// Decodifica caracteres especiales para poder retornalos como texto
	private String decodeSpecialCharacters(String input) {
		input = input.replaceAll("&amp;", "&").replaceAll("&lt;", "<").replaceAll("&gt;", ">")
				.replaceAll("&quot;", "\"").replaceAll("&apos;", "'").replaceAll("&#x21;", "!")
				.replaceAll("&#x22;", "\"").replaceAll("&#x23;", "#").replaceAll("&#x24;", "$")
				.replaceAll("&#x25;", "%").replaceAll("&#x26;", "&").replaceAll("&#x27;", "'").replaceAll("&#x28;", "(")
				.replaceAll("&#x29;", ")").replaceAll("&#x2A;", "*").replaceAll("&#x2B;", "+").replaceAll("&#x2C;", ",")
				.replaceAll("&#x2D;", "-").replaceAll("&#x2E;", ".").replaceAll("&#x2F;", "/").replaceAll("&#x3A;", ":")
				.replaceAll("&#x3B;", ";").replaceAll("&#x3C;", "<").replaceAll("&#x3D;", "=").replaceAll("&#x3E;", ">")
				.replaceAll("&#x3F;", "?").replaceAll("&#x40;", "@").replaceAll("&#x5B;", "[")
				.replaceAll("&#x5C;", "\\").replaceAll("&#x5D;", "]").replaceAll("&#x5E;", "^")
				.replaceAll("&#x5F;", "_").replaceAll("&#x60;", "`").replaceAll("&#x7B;", "{").replaceAll("&#x7C;", "|")
				.replaceAll("&#x7D;", "}").replaceAll("&#x7E;", "~");

		return input;
	}

}
