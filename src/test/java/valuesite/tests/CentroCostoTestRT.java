package valuesite.tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import valuesite.componentesreusables.ComponentesReusables;
import valuesite.pageobjects.CentroCostoRT;
import valuesite.pageobjects.Login;
import valuesite.testcomponents.BaseTest;

public class CentroCostoTestRT extends BaseTest{

	CentroCostoRT cc;
	ComponentesReusables cp;

	@BeforeTest
	public void setUp() {
		cc = new CentroCostoRT(getDriver());
		cp = new ComponentesReusables(getDriver());
	}
	
	@BeforeClass
	public void ingresaMantenedor() throws InterruptedException {
		Login login = new Login(getDriver());

		// Pasa como parametro correo y contraseña para ingresar a la web
		login.iniciarSesion("automrt@aquivoy.cl", "123456");

		// Ingresa al mantenedor una vez la sesion este iniciada
		cc.ingresoMantenedorCentroCostoCli();
		
		//Pasa como parametro el cliente a buscar
		cc.buscaCliente("001");
	}
	
//	@Test(priority=1)
//	public void campoNombreObligatorio() throws InterruptedException {
//		cc.btnCancelar.click();
//		cc.ingresarCodigoFormulario("01");
//		cc.btnCrear.click();
//		cp.waitForWebElementToAppear(cc.msjError);
//		if (cc.msjError.getText().contains("Campo Nombre obligatorio")) {
//			Assert.assertTrue(true);
//		}else {
//			Assert.fail("Campo nombre no se pide obligatoriamente o no lo indica por mensaje de error");
//		}
//	}
//	
//	@Test(priority=2)
//	public void campoCodigoObligatorio() throws InterruptedException {
//		cc.btnCancelar.click();
//		cc.ingresarNombreFormulario("TEST CODIGO OBLIGATORIO");
//		cc.btnCrear.click();
//		cc.msjError=getDriver().findElement(By.xpath("//div[@class='alert alert-danger'][2]"));
//		cp.waitForWebElementToAppear(cc.msjError);
//		if (cc.msjError.getText().contains("Campo Código obligatorio")) {
//			Assert.assertTrue(true);
//		}else {
//			Assert.fail("Campo codigo no se pide obligatoriamente o no lo indica por mensaje de error");
//		}
//	}
//	
//	@Test(priority=3)
//	public void campoPresupuestoObligatorio() throws InterruptedException {
//	        // Verificar si el elemento checkDefinir está presente y visible
//	        if (cc.checkDefinir.isDisplayed()) {
//	            // Continuar con el resto del método
//	            cc.btnCancelar.click();
//	            cc.ingresarCodigoFormulario("01");
//	            cc.ingresarNombreFormulario("TEST PRESUPUESTO OBLIGATORIO");
//	            cc.definirPresupuesto("");
//	            cc.btnCrear.click();
//	            
//	        	cc.msjError=getDriver().findElement(By.xpath("//div[@class='alert alert-danger'][3]"));
//	        	cp.waitForWebElementToAppear(cc.msjError);
//	            
//	            // Verificar el contenido del mensaje de error
//	            if (cc.msjError.getText().contains("Por favor, ingrese solo numeros mayores a cero en \"Monto presupuesto\"")) {
//	                Assert.assertTrue(true);
//	            } else {
//	                Assert.fail("Campo Monto presupuesto no se pide obligatoriamente o no lo indica por mensaje de error");
//	            }
//	        } else {
//	        	System.out.println("Saltando metodo 'campoPresupuestoObligatorio()' ya que no esta activo el presupuesto por CC");
//				return;
//	        }
//	}
	
//	@Test(priority=4)
//	public void campoResponsableCcObligatorio() throws InterruptedException {
//	    try {
//	        getDriver().navigate().refresh();
//	        cc.buscaCliente("001");
//	        cc.ingresarCodigoFormulario("01");
//	        cc.ingresarNombreFormulario("TEST RESPONSABLE CC OBLIGATORIO");
//	        cc.notificarServicio("");
//	        cc.tipoCorreo("todos");
//	        cp.waitForWebElementToBeClickable(cc.btnCrear);
//	        cc.btnCrear.click();
//
//	        Thread.sleep(750);
//
//	        try {
//	            cp.waitForVisibilityOfElement(cc.msjError);
//	            boolean msj = cc.msjError.isDisplayed();
//
//	            if (msj) {
//	                if (cc.msjError.getText().contains("Campo Responsable del Centro Costo obligatorio")) {
//	                    Assert.assertTrue(true);
//	                } else {
//	                    Assert.fail("Campo Responsable del Centro Costo no se pide obligatoriamente o no lo indica por mensaje de error");
//	                }
//	            } else {
//	                Assert.fail("No se esta mostrando el mensaje de error");
//	            }
//	        } catch (TimeoutException e) {
//	            Assert.fail("El mensaje de error no se encontro en el tiempo esperado");
//	        }
//	    } catch (Exception e) {
//	        Assert.fail("Ocurrio un error inesperado: " + e.getMessage());
//	    }
//	}
	
	
}
