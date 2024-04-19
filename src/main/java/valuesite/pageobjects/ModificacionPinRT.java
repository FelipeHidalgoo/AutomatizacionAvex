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
	
	@FindBy(id="btnLimpiarPin")
	public
	WebElement btnCancelar;
	
	@FindBy(id="btnActualizaPin")
	public
	WebElement btnActualizar;
	
	@FindBy(id="txtPin")
	public
	WebElement campoPin;
	
	@FindBy(css=".alert.alert-danger")
	public
	WebElement msjError;
	
	@FindBy(css=".alert.alert-success")
	public
	WebElement msjExito;
	
	// PAGE FACTORY
	
	public void ingresoMantenedorPin() {
		tabAdministracion.click();
		tabModificacionPin.click();
		}
	
	public void ingresaPin(String pin) {
		campoPin.sendKeys(pin);
	}
	
	public void actualizaPin (String pin) {
		campoPin.sendKeys(pin);
		btnActualizar.click();
	}
	
	public String obtienePIN() {
		String pin;
		pin = campoPin.getAttribute("value");
		return pin;
	}
	
	// Método para obtener el valor visual de un elemento
    public String obtenerPinEnPantalla(WebDriver driver, WebElement element) {
        // Crea una instancia de JavascriptExecutor
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        
        // Ejecuta un script para obtener el valor visual del elemento
        String valorEnPantalla = (String) jsExecutor.executeScript("return arguments[0].innerText || arguments[0].textContent", element);
        
        // Retorna el valor visual
        return valorEnPantalla;
    }
}
