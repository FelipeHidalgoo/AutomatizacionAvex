package valuesite.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import valuesite.componentesreusables.ActionsHelper;
import valuesite.componentesreusables.ComponentesReusables;
import valuesite.pageobjects.Login;
import valuesite.pageobjects.ModificacionPinRT;
import valuesite.testcomponents.BaseTest;

public class ModificacionPinTestRT extends BaseTest{
	
	ModificacionPinRT modifpinrt;
	ComponentesReusables componentesReusables;
	
	@BeforeTest
	public void setUp() {
		modifpinrt = new ModificacionPinRT(driver);
		componentesReusables = new ComponentesReusables(driver);
    }
    
    @BeforeClass
    public void ingresaMantenedor() {
		Login login = new Login(driver);
		
		// Pasa como parametro correo y contraseña para ingresar a la web
		login.iniciarSesion("userauto@aquivoy.cl", "123456");
		
		//AreaRT areart = new AreaRT(driver);
		
		//ModificacionPinRT modifpinrt = new ModificacionPinRT(driver);
		modifpinrt.ingresoMantenedorPin();
	}
	
    @Test
	public void ingresarPin() {
    	//ModificacionPinRT modifpinrt = new ModificacionPinRT(driver);
		String pin = "7546";
		componentesReusables.waitForWebElementToAppear(modifpinrt.campoPin);
		modifpinrt.campoPin.clear();
		modifpinrt.ingresaPin(pin);
		String nuevoPin = modifpinrt.obtienePIN();
		if (nuevoPin.equals(pin)) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void vaciaCampo() {
		componentesReusables.waitForWebElementToAppear(modifpinrt.campoPin);
		//ModificacionPinRT modifpinrt = new ModificacionPinRT(driver);
		modifpinrt.ingresaPin("7546");
		modifpinrt.campoPin.clear();
		if (modifpinrt.campoPin.getText().isEmpty()) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void actualizaPin() {
		//ModificacionPinRT modifpinrt = new ModificacionPinRT(driver);
		String pin = "9876";
		componentesReusables.waitForWebElementToAppear(modifpinrt.campoPin);
		modifpinrt.campoPin.clear();
		modifpinrt.actualizaPin(pin);
		if (modifpinrt.msjExito.isDisplayed()) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void actualizaSinDatos() {
		//ModificacionPinRT modifpinrt = new ModificacionPinRT(driver);
		componentesReusables.waitForWebElementToAppear(modifpinrt.campoPin);
		modifpinrt.campoPin.clear();
		modifpinrt.btnActualizar.click();
		if (modifpinrt.msjError.isDisplayed()) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}
	
	
	@Test
	public void actualizaConCaracteresEspeciales() throws InterruptedException {
		//ModificacionPinRT modifpinrt = new ModificacionPinRT(driver);
		String caracteresEspeciales = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
		componentesReusables.waitForWebElementToBeClickable(modifpinrt.campoPin);
		modifpinrt.campoPin.clear();
		modifpinrt.actualizaPin(caracteresEspeciales);
		componentesReusables.waitForWebElementToAppear(modifpinrt.msjError);
		if (modifpinrt.msjError.isDisplayed()) {
			System.out.println("No permite actualizar con caracteres especiales");
			Assert.assertTrue(true);
		}else {
			System.out.println("Permite actualizar con caracteres especiales");
			Assert.assertTrue(false);
		}
	}
	

    
    
// LOS SIGUIENTES TEST NO SE PUEDEN REALIZAR CORRECTAMENTE YA QUE EL PIN SE GUARDA EN EL ATRIBUTO "value" DEL INPUT, 
// Y ESTE NO SE ACTUALIZA EN TIEMPO REAL, SOLO SE ACTUALIZA AL DARLE EL BOTON ACTUALIZAR, POR LO CUAL ES IMPOSIBLE OBTENER EL VALOR DE PIN
// QUE SE ESTA VIENDO EN PANTALLA
    
//	@Test
//	public void limpiaCampo() {
//		//ModificacionPinRT modifpinrt = new ModificacionPinRT(driver);
//		modifpinrt.campoPin.clear();
//		modifpinrt.ingresaPin("5465");
//		modifpinrt.btnCancelar.click();
//		String pinActual = modifpinrt.obtienePIN();
//		if (pinActual.isEmpty()) {
//			System.out.println("Se limpio correctamente el campo PIN");
//			Assert.assertTrue(true);
//		}else {
//			System.out.println("El campo PIN no se limpio correctamente");
//			Assert.assertTrue(false);
//		}
//	}
    
//    @Test
//	public void ingresaCaracteresEspeciales() {
//		//ModificacionPinRT modifpinrt = new ModificacionPinRT(driver);
//		componentesReusables.waitForWebElementToAppear(modifpinrt.campoPin);
//		String caracteresEspeciales = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
//		modifpinrt.campoPin.clear();
//		modifpinrt.ingresaPin(caracteresEspeciales);
//		String valorPin = modifpinrt.obtienePIN();
//		
//		
//		// Verificar si el campo contiene caracteres especiales
//	    boolean contieneCaracteresEspeciales = false;
//	    for (char c : caracteresEspeciales.toCharArray()) {
//	        if (valorPin.contains(String.valueOf(c))) {
//	            contieneCaracteresEspeciales = true;
//	            break;
//	        }
//	    }
//	    
//	    // Imprimir el resultado y realizar la aserción
//	    if (contieneCaracteresEspeciales) {
//	        System.out.println("Se ingresaron caracteres especiales en el campo.");
//	        Assert.assertTrue(false);
//	    } else {
//	        System.out.println("No se ingresaron caracteres especiales en el campo.");
//	        Assert.assertTrue(true);
//	    }	
//	}
	
	

}
