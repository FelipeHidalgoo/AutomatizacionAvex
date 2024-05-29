package valuesite.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import valuesite.componentesreusables.ComponentesReusables;
import valuesite.pageobjects.Login;
import valuesite.pageobjects.ModificacionPinRT;
import valuesite.testcomponents.BaseTest;

public class ModificacionPinTestRT extends BaseTest{
	
	ModificacionPinRT modifpinrt;
	ComponentesReusables componentesReusables;
	
	@BeforeTest
	public void setUp() {
		// Inicializa Page Object de vista, contiene metodos y web elements para utilizar en el test
		modifpinrt = new ModificacionPinRT(getDriver());
		
		// Inicializa la clase que contiene los metodos de tiempos de espera explicito, entre otros metodos reusables
		componentesReusables = new ComponentesReusables(getDriver());
    }
    
    @BeforeClass
    public void ingresaMantenedor() {
		Login login = new Login(getDriver());
		
		// Pasa como parametro correo y contraseña para ingresar a la web
		login.iniciarSesion("automrt@aquivoy.cl", "123456");
		
		// Metodo que ingresa a mantenedor pin una vez la sesion este iniciada
		modifpinrt.ingresoMantenedorPin();
	}
	
	// Testea que se pueda vaciar manualmente el campo //
	@Test(priority=1)
	public void vaciaCampo() {
		// Espera a que aparezca el campo pin
		componentesReusables.waitForWebElementToAppear(modifpinrt.campoPin);
		
		// Ingresa un pin y luego lo borra manualmente
		modifpinrt.ingresaPin("7546");
		modifpinrt.campoPin.clear();
		
		// Obtiene el valor del campo pin despues de borrarlo
		String pin = modifpinrt.obtienePIN();
		//System.out.println(pin);
		
		// Valida si el campo quedo vacio luego de borrar el valor
		if (pin.isEmpty()) {
			System.out.println("El campo se vacio correctamente\n");
			Assert.assertTrue(true);
		}else {
			Assert.fail("El campo NO se vacio correctamente\n");
		}
	}
	
	
	// Testea que se esten ingresando valores correctamente en el campo //
    @Test(priority=2)
	public void ingresarPin() {
    	
    	//Define el pin a utilizar y espera a que aparezca el campo pin
		String pin = "7546";
		componentesReusables.waitForWebElementToAppear(modifpinrt.campoPin);
		
		// Vacia el campo, ingresa un dato y guarda el dato que hay actualmente en el campo en "nuevoPin" 
		//despues de ingresarlo
		modifpinrt.campoPin.clear();
		modifpinrt.ingresaPin(pin);
		String nuevoPin = modifpinrt.obtienePIN();
		
		// Valida que "nuevoPin" con "pin" sean iguales para comprobar que el dato efectivamente se ingreso en el campo
		if (nuevoPin.equals(pin)) {
			System.out.println("El pin se ingreso correctamente");
			Assert.assertTrue(true);
		}else {
			Assert.fail("El pin no fue ingresado");
		}
	}
	
    // Comprueba que el pin se este actualizando correctamente //
	@Test(priority=3)
	public void actualizaPin() {
		
		// Define un pin y espera a que aparezca el campo pin
		String pin = "9876";
		componentesReusables.waitForWebElementToAppear(modifpinrt.campoPin);
		
		// Limpia el campo en caso de que ya tenga un dato y actualiza con el pin definido
		modifpinrt.campoPin.clear();
		modifpinrt.actualizaPin(pin);
		
		// Espera a que aparezca el mensaje de exito
		componentesReusables.waitForWebElementToAppear(modifpinrt.msjExito);
		
		// Si se despliega el mensaje de exito pasa la prueba, si no, falla la prueba
		if (modifpinrt.msjExito.isDisplayed()) {
			System.out.println("El pin fue actualizado correctamente");
			Assert.assertTrue(true);
		}else {
			Assert.fail("El pin no fue actualizado");
		}
	}
	
	// Testea que no se pueda actualizar sin ingresar nada //
	@Test(priority=4)
	public void actualizaSinDatos() {
		componentesReusables.waitForWebElementToAppear(modifpinrt.campoPin);
		
		// Limpia el campo y presiona boton actualizar
		modifpinrt.campoPin.clear();
		modifpinrt.btnActualizar.click();
		
		componentesReusables.waitForWebElementToAppear(modifpinrt.msjError);
		
		// Verifica si se despliega el mensaje de error
		if (modifpinrt.msjError.isDisplayed()) {
			System.out.println("No permite actualizar sin ingresar datos");
			Assert.assertTrue(true);
		}else {
			Assert.fail("Permite actualizar sin ingresar datos");
		}
	}
	
	// Testea que no se pueda actualizar ingresando caracteres especiales //
	@Test (priority=5)
	public void actualizaConCaracteresEspeciales() throws InterruptedException {
		
		// Define los caracteres especiales a utilizar, estan incluidos todos los posibles
		String caracteresEspeciales = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
		
		// Espera a que el campo pin sea clickeable
		componentesReusables.waitForWebElementToBeClickable(modifpinrt.campoPin);
		
		// Limpia el campo y actualiza con los caracteres especiales definidos
		modifpinrt.campoPin.clear();
		modifpinrt.actualizaPin(caracteresEspeciales);
		
		// Espera 700 milisegundos y refresca la pagina
		Thread.sleep(700);
		getDriver().navigate().refresh();
		
		// Obtiene el valor del campo despues de actualizar la pagina
		String pin = modifpinrt.obtienePIN();
		
		// Verificar si el campo contiene algún carácter especial
	    boolean contieneCaracteresEspeciales = modifpinrt.contieneCaracteresEspeciales(pin, caracteresEspeciales);
	    
	    // Comprueba si el campo tiene caracteres especiales
	    if (contieneCaracteresEspeciales) {
	    	Assert.fail("No se guardaron caracteres especiales en el campo");
	    } else {
	    	Assert.assertTrue(true);
	    }
		
		
	}
	
	// Comprueba si se limpia el campo con el boton cancelar
	@Test(priority=6)
	public void limpiaCampo() {
		
		// Limpia el campo e ingresa un pin
		modifpinrt.campoPin.clear();
		modifpinrt.ingresaPin("5465");
		
		// Presiona boton cancelar
		modifpinrt.btnCancelar.click();
		
		// Obtiene el valor actual del campo luego de presionar boton cancelar
		String pinActual = modifpinrt.obtienePIN();
		
		// Valida que el pin que devolvio este vacio
		if (pinActual.isEmpty()) {
			System.out.println("Se limpio correctamente el campo PIN");
			Assert.assertTrue(true);
		}else {
			//System.out.println("El campo PIN no se limpio correctamente");
			Assert.fail("El campo PIN no se limpio correctamente al presionar cancelar");
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
