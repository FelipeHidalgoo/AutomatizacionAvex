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

import valuesite.componentesreusables.ActionsHelper;
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
		modifpincli = new ModificacionPinCliente(getDriver());
		componentesReusables = new ComponentesReusables(getDriver());
		
//		String path = System.getProperty("user.dir")+"\\reportes\\reporte.html";
//		ExtentSparkReporter reporte = new ExtentSparkReporter(path);
//		
//		reporte.config().setReportName("Resultados de pruebas automatizadas");
//		reporte.config().setDocumentTitle("Resultados de pruebas");
//		
//		extent = new ExtentReports();
//		extent.attachReporter(reporte);
//		extent.setSystemInfo("Tester", "ASUS (Valuesite19) OFICINA");
    }
    
    @BeforeClass
    public void ingresaMantenedor() {
		Login login = new Login(getDriver());
		
		// Pasa como parametro correo y contraseña para ingresar a la web
		login.iniciarSesion("userautocliente@aquivoy.cl", "123456");
		
		//AreaRT areart = new AreaRT(driver);
		
		//ModificacionPinRT modifpinrt = new ModificacionPinRT(driver);
		modifpincli.ingresoMantenedorPin();
	}
	
	
    @Test(priority=1)
    public void vaciaCampo() {
//        ExtentTest test = extent.createTest("Borra los datos ingresados en el campo");
        
        componentesReusables.waitForWebElementToAppear(modifpincli.campoPin);
        
        modifpincli.ingresaPin("7546");
        modifpincli.campoPin.clear();
        
        String pin = modifpincli.obtienePIN();
        //System.out.println(pin);
        
        if (pin.isEmpty()) {
            System.out.println("El campo se vacio correctamente\n");
            Assert.assertTrue(true);
        } else {
            System.out.println("El campo NO se vacio correctamente\n");
            Assert.assertTrue(false);
        }
        
//        test.fail("El resultado no es correcto");
//        
//        extent.flush();
    }

	
    @Test(priority=2)
	public void ingresarPin() {
		String pin = "7546";
		componentesReusables.waitForWebElementToAppear(modifpincli.campoPin);
		
		// Vacia el campo, ingresa un dato y guarda el dato que hay actualmente en el campo en "nuevoPin" 
		//despues de ingresarlo
		modifpincli.campoPin.clear();
		modifpincli.ingresaPin(pin);
		String nuevoPin = modifpincli.obtienePIN();
		
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
	public void actualizaPin() throws InterruptedException {
		String pin = "9876";
		componentesReusables.waitForWebElementToAppear(modifpincli.campoPin);
		
		modifpincli.campoPin.clear();
		modifpincli.actualizaPin(pin);
		
		componentesReusables.waitForWebElementToAppear(modifpincli.msjExito);
		if (modifpincli.msjExito.isDisplayed()) {
			System.out.println("El pin fue actualizado correctamente");
			Assert.assertTrue(true);
		}else {
			System.out.println("El pin no fue actualizado");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=4)
	public void actualizaSinDatos() {
		componentesReusables.waitForWebElementToAppear(modifpincli.campoPin);
		
		modifpincli.campoPin.clear();
		modifpincli.btnActualizar.click();
		
		if (modifpincli.msjError.isDisplayed()) {
			System.out.println("No permite actualizar sin ingresar datos");
			Assert.assertTrue(true);
		}else {
			System.out.println("Permite actualizar sin ingresar datos");
			Assert.assertTrue(false);
		}
	}
	
	
	@Test (priority=5)
	public void actualizaConCaracteresEspeciales() throws InterruptedException {
		String caracteresEspeciales = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
		componentesReusables.waitForWebElementToBeClickable(modifpincli.campoPin);
		
		modifpincli.campoPin.clear();
		//componentesReusables.waitForElementToDisappear(driver, modifpinrt.msjErrorBy);
		modifpincli.actualizaPin(caracteresEspeciales);
		
		//componentesReusables.waitForWebElementToAppear(modifpinrt.msjError);
//		if (modifpinrt.msjError.isDisplayed()) {
//			System.out.println("No permite actualizar con caracteres especiales");
//			Assert.assertTrue(true);
//		}else {
//			System.out.println("Permite actualizar con caracteres especiales");
//			Assert.assertTrue(false);
//		}
		
		Thread.sleep(500);
		getDriver().navigate().refresh();
		
		String pin = modifpincli.obtienePIN();
		
		// Verificar si el campo contiene algún carácter especial
	    boolean contieneCaracteresEspeciales = modifpincli.contieneCaracteresEspeciales(pin, caracteresEspeciales);
	    
	    // Imprimir el resultado y realizar la aserción
	    if (contieneCaracteresEspeciales) {
	        System.out.println("El campo se guardo con caracteres especiales");
	        Assert.assertTrue(false);
	    } else {
	        System.out.println("No se guardaron caracteres especiales en el campo");
	        Assert.assertTrue(true);
	    }
		
		
	}
	
	@Test(priority=6)
	public void limpiaCampo() {
		modifpincli.campoPin.clear();
		modifpincli.ingresaPin("5465");
		
		modifpincli.btnCancelar.click();
		
		String pinActual = modifpincli.obtienePIN();
		if (pinActual.isEmpty()) {
			System.out.println("Se limpio correctamente el campo PIN");
			Assert.assertTrue(true);
		}else {
			System.out.println("El campo PIN no se limpio correctamente");
			Assert.assertTrue(false);
		}
	}	

    
	 // Valida que no se puedan ingresar carcateres especiales en el campo pin
	  @Test(priority=7)
		public void ingresaCaracteresEspeciales() {
			componentesReusables.waitForWebElementToAppear(modifpincli.campoPin);
			
			String caracteresEspeciales = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
			
			modifpincli.campoPin.clear();
			modifpincli.ingresaPin(caracteresEspeciales);
			
			
			String valorPin = modifpincli.obtienePIN();
			
			
			// Verificar si el campo contiene caracteres especiales
		    boolean contieneCaracteresEspeciales = false;
		    for (char c : caracteresEspeciales.toCharArray()) {
		        if (valorPin.contains(String.valueOf(c))) {
		            contieneCaracteresEspeciales = true;
		            break;
		        }
		    }
		    
		    // Imprimir el resultado y realizar la aserción
		    if (contieneCaracteresEspeciales) {
		        System.out.println("Se ingresaron caracteres especiales en el campo \n");
		        Assert.assertTrue(false);
		    } else {
		        System.out.println("No se ingresaron caracteres especiales en el campo \n");
		        Assert.assertTrue(true);
		    }	
		}
}
