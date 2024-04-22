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
	
	
	@Test(priority=1)
	public void vaciaCampo() {
		componentesReusables.waitForWebElementToAppear(modifpinrt.campoPin);
		
		modifpinrt.ingresaPin("7546");
		modifpinrt.campoPin.clear();
		
		String pin = modifpinrt.obtienePIN();
		//System.out.println(pin);
		
		if (pin.isEmpty()) {
			System.out.println("El campo se vacio correctamente\n");
			Assert.assertTrue(true);
		}else {
			System.out.println("El campo NO se vacio correctamente\n");
			Assert.assertTrue(false);
		}
	}
	
    @Test(priority=2)
	public void ingresarPin() {
		String pin = "7546";
		componentesReusables.waitForWebElementToAppear(modifpinrt.campoPin);
		
		// Vacia el campo, ingresa un dato y guarda el dato que hay actualmente en el campo en "nuevoPin" 
		//despues de ingresarlo
		modifpinrt.campoPin.clear();
		modifpinrt.ingresaPin(pin);
		String nuevoPin = modifpinrt.obtienePIN();
		
		// Compara "nuevoPin" con "pin" para comprobar que el dato efectivamente se ingreso en el campo
		if (nuevoPin.equals(pin)) {
			System.out.println("El pin se ingreso correctamente");
			Assert.assertTrue(true);
		}else {
			System.out.println("El pin no fue ingresado");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=3)
	public void actualizaPin() {
		String pin = "9876";
		componentesReusables.waitForWebElementToAppear(modifpinrt.campoPin);
		
		modifpinrt.campoPin.clear();
		modifpinrt.actualizaPin(pin);
		
		if (modifpinrt.msjExito.isDisplayed()) {
			System.out.println("El pin fue actualizado correctamente");
			Assert.assertTrue(true);
		}else {
			System.out.println("El pin no fue actualizado");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=4)
	public void actualizaSinDatos() {
		componentesReusables.waitForWebElementToAppear(modifpinrt.campoPin);
		
		modifpinrt.campoPin.clear();
		modifpinrt.btnActualizar.click();
		
		if (modifpinrt.msjError.isDisplayed()) {
			System.out.println("No permite actualizar sin ingresar datos");
			Assert.assertTrue(true);
		}else {
			System.out.println("Permite actualizar sin ingresar datos");
			Assert.assertTrue(false);
		}
	}
	
	
	@Test (priority=5)
	public void actualizaConCaracteresEspeciales() throws InterruptedException {
		componentesReusables.waitForElementToDisappear(modifpinrt.msjError);
		
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
	
	@Test(priority=6)
	public void limpiaCampo() {
		modifpinrt.campoPin.clear();
		modifpinrt.ingresaPin("5465");
		
		modifpinrt.btnCancelar.click();
		
		String pinActual = modifpinrt.obtienePIN();
		if (pinActual.isEmpty()) {
			System.out.println("Se limpio correctamente el campo PIN");
			Assert.assertTrue(true);
		}else {
			System.out.println("El campo PIN no se limpio correctamente");
			Assert.assertTrue(false);
		}
	}	

    
    
// EL SIGUIENTE TEST NO SE PUEDE REALIZAR CORRECTAMENTE YA QUE EL ATRIBUTO "Value" DEL INPUT DE PIN 
// NO GUARDA CARACTERES ESPECIALES

	
//	 // Valida que no se puedan ingresar carcateres especiales en el campo pin
//	  @Test
//		public void ingresaCaracteresEspeciales() {
//			componentesReusables.waitForWebElementToAppear(modifpinrt.campoPin);
//			
//			String caracteresEspeciales = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
//			
//			modifpinrt.campoPin.clear();
//			modifpinrt.ingresaPin(caracteresEspeciales);
//			
//			
//			String valorPin = modifpinrt.obtienePIN();
//			
//			
//			// Verificar si el campo contiene caracteres especiales
//		    boolean contieneCaracteresEspeciales = false;
//		    for (char c : caracteresEspeciales.toCharArray()) {
//		        if (valorPin.contains(String.valueOf(c))) {
//		            contieneCaracteresEspeciales = true;
//		            break;
//		        }
//		    }
//		    
//		    // Imprimir el resultado y realizar la aserción
//		    if (contieneCaracteresEspeciales) {
//		        System.out.println("Se ingresaron caracteres especiales en el campo.");
//		        Assert.assertTrue(false);
//		    } else {
//		        System.out.println("No se ingresaron caracteres especiales en el campo.");
//		        Assert.assertTrue(true);
//		    }	
//		}
}
