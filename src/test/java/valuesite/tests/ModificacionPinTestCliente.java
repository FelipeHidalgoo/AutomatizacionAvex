package valuesite.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import valuesite.componentesreusables.ComponentesReusables;
import valuesite.pageobjects.Login;
import valuesite.pageobjects.ModificacionPinCliente;
import valuesite.pageobjects.ModificacionPinRT;
import valuesite.testcomponents.BaseTest;

public class ModificacionPinTestCliente extends BaseTest{
	
	ModificacionPinCliente modifpincli;
	ComponentesReusables componentesReusables;
	ExtentReports extent;
	
	
	@BeforeTest
	public void setUp() {
		// 	Inicializa Page Object de la vista, este contiene metodos y web elements tales como botones, campos, etc
		modifpincli = new ModificacionPinCliente(getDriver());
		
		// Inicializa la clase que contiene los metodos de tiempos de espera explicito, entre otros metodos reusables
		componentesReusables = new ComponentesReusables(getDriver());
    }
    
    @BeforeClass
    public void ingresaMantenedor() {
		Login login = new Login(getDriver());
		
		// Pasa como parametro correo y contraseña para ingresar a la web
		login.iniciarSesion("automcli@aquivoy.cl", "123456");
		
		// Metodo que ingresa a mantenedor pin una vez la sesion este iniciada
		modifpincli.ingresoMantenedorPin();
	}
	
	
    // Testea que se pueda vaciar manualmente el campo PIN //
    @Test(priority=1)
    public void vaciaCampo() {
    	// Espera a que aparezca el campo pin
        componentesReusables.waitForWebElementToAppear(modifpincli.campoPin);
        
        // Ingresa un pin y luego lo borra manualmente
        modifpincli.ingresaPin("7546");
        modifpincli.campoPin.clear();
        
        // Obtiene el valor del campo pin despues de borrarlo
        String pin = modifpincli.obtienePIN();
        //System.out.println(pin);
        
        // Valida si el campo quedo vacio luego de borrar el valor
        if (pin.isEmpty()) {
            System.out.println("El campo se vacio correctamente\n");
            Assert.assertTrue(true);
        } else {
            Assert.fail("El campo NO se vacio correctamente\n");
        }
       
    }

	
    // Testea que se esten ingresando valores correctamente en el campo //
    @Test(priority=2)
	public void ingresarPin() {
		
    	//Define el pin a utilizar y espera a que aparezca el campo pin
    	String pin = "7546";
		componentesReusables.waitForWebElementToAppear(modifpincli.campoPin);
		
		// Vacia el campo, ingresa un dato y guarda el dato que hay actualmente en el campo en "nuevoPin" 
		//despues de ingresarlo
		modifpincli.campoPin.clear();
		modifpincli.ingresaPin(pin);
		String nuevoPin = modifpincli.obtienePIN();
		
		// Valida que "nuevoPin" con "pin" sean iguales para comprobar que el dato efectivamente se ingreso en el campo
		if (nuevoPin.equals(pin)) {
			System.out.println("El pin se ingreso correctamente");
			Assert.assertTrue(true);
		}else {
			Assert.fail("El pin no fue ingresado correctamente");
		}
	}
	
	// Comprueba que el pin se este actualizando correctamente //
    @Test(priority=3)
	public void actualizaPin() throws InterruptedException {
    	
    	// Define un pin y espera a que aparezca el campo pin
		String pin = "9876";
		componentesReusables.waitForWebElementToAppear(modifpincli.campoPin);
		
		// Limpia el campo en caso de que ya tenga un dato y actualiza con el pin definido
		modifpincli.campoPin.clear();
		modifpincli.actualizaPin(pin);
		
		// Espera a que aparezca el mensaje de exito
		componentesReusables.waitForWebElementToAppear(modifpincli.msjExito);
		
		// Si se despliega el mensaje de exito pasa la prueba, si no, falla la prueba
		if (modifpincli.msjExito.isDisplayed()) {
			System.out.println("El pin fue actualizado correctamente");
			Assert.assertTrue(true);
		}else {
			Assert.fail("El pin no fue actualizado");
		}
	}
	
    
    // Testea que no se pueda actualizar sin ingresar nada //
	@Test(priority=4)
	public void actualizaSinDatos() {
		componentesReusables.waitForWebElementToAppear(modifpincli.campoPin);
		
		// Limpia el campo y presiona boton actualizar
		modifpincli.campoPin.clear();
		modifpincli.btnActualizar.click();
		
		// Verifica si se despliega el mensaje de error
		if (modifpincli.msjError.isDisplayed()) {
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
		componentesReusables.waitForWebElementToBeClickable(modifpincli.campoPin);
		
		// Limpia el campo y actualiza con los caracteres especiales definidos
		modifpincli.campoPin.clear();
		modifpincli.actualizaPin(caracteresEspeciales);
		
		// Espera 500 milisegundos y refresca la pagina
		Thread.sleep(500);
		getDriver().navigate().refresh();
		
		// Obtiene el valor del campo despus de actualizar la pagina
		String pin = modifpincli.obtienePIN();
		
		// Verificar si el campo contiene algún carácter especial
	    boolean contieneCaracteresEspeciales = modifpincli.contieneCaracteresEspeciales(pin, caracteresEspeciales);
	    
	    // Comprueba si el campo tiene caracteres especiales
	    if (contieneCaracteresEspeciales) {
	        Assert.fail("El campo se guardo con caracteres especiales");
	    } else {
	        System.out.println("No se guardaron caracteres especiales en el campo");
	        Assert.assertTrue(true);
	    }
		
		
	}
	
	
	// Comprueba si se limpia el campo con el boton cancelar
	@Test(priority=6)
	public void limpiaCampo() {
		
		// Limpia el campo e ingresa un pin
		modifpincli.campoPin.clear();
		modifpincli.ingresaPin("5465");
		
		// Presiona boton cancelar
		modifpincli.btnCancelar.click();
		
		// Obtiene el valor actual del campo luego de presionar boton cancelar
		String pinActual = modifpincli.obtienePIN();
		
		// Valida que el pin que devolvio este vacio
		if (pinActual.isEmpty()) {
			System.out.println("Se limpio correctamente el campo PIN");
			Assert.assertTrue(true);
		}else {
			Assert.fail("El campo PIN no se limpio correctamente");
		}
	}	

    
	 // Valida que no se puedan ingresar carcateres especiales en el campo pin
	  @Test(priority=7)
		public void ingresaCaracteresEspeciales() {
		  
		  	// Espera a que aparezca el campo pin
			componentesReusables.waitForWebElementToAppear(modifpincli.campoPin);
			
			// Define los caracteres especiales a utilizar, incluye todos
			String caracteresEspeciales = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
			
			// Limpia el campo pin e ingresa los caracteres definidos
			modifpincli.campoPin.clear();
			modifpincli.ingresaPin(caracteresEspeciales);
			
			// Obtiene el valor del campo luego de utilizar los caracteres
			String valorPin = modifpincli.obtienePIN();
			
			
			// Verificar si el campo contiene caracteres especiales
		    boolean contieneCaracteresEspeciales = false;
		    for (char c : caracteresEspeciales.toCharArray()) {
		        if (valorPin.contains(String.valueOf(c))) {
		            contieneCaracteresEspeciales = true;
		            break;
		        }
		    }
		    
		    // Valida si se ingresaron caracteres especiales en el campo
		    if (contieneCaracteresEspeciales) {
		        Assert.fail("Se ingresaron caracteres especiales en el campo \n");
		    } else {
		        System.out.println("No se ingresaron caracteres especiales en el campo \n");
		        Assert.assertTrue(true);
		    }	
		}
}
