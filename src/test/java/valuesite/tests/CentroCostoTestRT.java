package valuesite.tests;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import valuesite.componentesreusables.ComponentesReusables;
import valuesite.pageobjects.CentroCostoRT;
import valuesite.pageobjects.Login;
import valuesite.testcomponents.BaseTest;

public class CentroCostoTestRT extends BaseTest {

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

		// Pasa como parametro el cliente a buscar
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
//		// try {
//		getDriver().navigate().refresh();
//		cc.buscaCliente("001");
//		cc.ingresarCodigoFormulario("01");
//		cc.ingresarNombreFormulario("TEST RESPONSABLE CC OBLIGATORIO");
//		cc.notificarServicio("");
//		cc.tipoCorreo("todos");
//		cp.waitForWebElementToBeClickable(cc.btnCrear);
//		cc.btnCrear.click();
//
////		Thread.sleep(1000);
//		try {
//			cp.waitForVisibilityOfElement(cc.msjError);
//			boolean msj = cc.msjError.isDisplayed();
//
//			if (msj) {
//				if (cc.msjError.getText().contains("Campo Responsable del Centro Costo obligatorio")) {
//					Assert.assertTrue(true);
//				} else {
//					Assert.fail(
//							"Campo Responsable del Centro Costo no se pide obligatoriamente o no lo indica por mensaje de error");
//				}
//			} else {
//				Assert.fail("No se esta mostrando el mensaje de error o no es el mensaje correcto");
//			}
//		} catch (TimeoutException e) {
//			Assert.fail("El mensaje de error no se encontro en el tiempo esperado");
//		}
////	    } catch (Exception e) {
////	        Assert.fail("Ocurrio un error inesperado: " + e.getMessage());
////	    }
//	}

//	@Test(priority=5)
//	public void tipoCorreoObligatorio() throws InterruptedException {
//	    cp.waitForWebElementToBeClickable(cc.btnCancelar);
//	    cc.btnCancelar.click();
//	    cc.ingresarCodigoFormulario("01");
//	    cc.ingresarNombreFormulario("TEST TIPO CORREO OBLIGATORIO");
//	    cc.notificarServicio("Automatizacion cliente");
//	    cc.tipoCorreo("");
//	    cp.waitForWebElementToBeClickable(cc.btnCrear);
//	    cc.btnCrear.click();
//	    
//	    cp.waitForWebElementToAppear(cc.msjError);
//	    
//	    // Obtener todos los mensajes de error
//	    List<WebElement> mensajesError = cc.obtenerMensajesError();
//	    
//	    // Verificar si alguno de los mensajes de error contiene el texto esperado
//	    boolean mensajeEncontrado = mensajesError.stream()
//	        .anyMatch(mensaje -> mensaje.getText().contains("Por favor, selecciona el tipo de correo a notificar"));
//	    
//	    // Validar el resultado
//	    if (mensajeEncontrado) {
//	        Assert.assertTrue(true);
//	    } else {
//	        Assert.fail("Campo 'Tipo correo' no se pide obligatoriamente o no lo indica por mensaje de error.");
//	    }
//	}

//	@Test(priority = 6)
//	public void montoPresupuestoObligatorio() throws InterruptedException {
//		cp.waitForWebElementToBeClickable(cc.btnCancelar);
//		cc.btnCancelar.click();
//		cc.ingresarCodigoFormulario("01");
//		cc.ingresarNombreFormulario("TEST TIPO CORREO OBLIGATORIO");
//		cc.definirPresupuesto("");
//		cp.waitForWebElementToBeClickable(cc.btnCrear);
//		cc.btnCrear.click();
//
//		cp.waitForWebElementToAppear(cc.msjError);
//
//		// Obtener todos los mensajes de error
//		List<WebElement> mensajesError = cc.obtenerMensajesError();
//
//		// Verificar si alguno de los mensajes de error contiene el texto esperado
//		boolean mensajeEncontrado = mensajesError.stream()
//				.anyMatch(mensaje -> mensaje.getText().contains("Ingrese monto presupuesto"));
//
//		// Validar el resultado
//		if (mensajeEncontrado) {
//			Assert.assertTrue(true);
//		} else {
//			Assert.fail("Campo 'Monto presupuesto' no se pide obligatoriamente o no lo indica por mensaje de error.");
//		}
//	}
//
//	@Test(priority = 6)
//	public void porcentajePresupuestoObligatorio() throws InterruptedException {
//		cp.waitForWebElementToBeClickable(cc.btnCancelar);
//		cc.btnCancelar.click();
//		cc.ingresarCodigoFormulario("01");
//		cc.ingresarNombreFormulario("TEST TIPO CORREO OBLIGATORIO");
//		cc.notificarServicio("Automatizacion cliente");
//		cc.definirPresupuesto("100000");
//		cp.waitForWebElementToBeClickable(cc.btnCrear);
//		cc.btnCrear.click();
//
//		cp.waitForWebElementToAppear(cc.msjError);
//
//		// Obtener todos los mensajes de error
//		List<WebElement> mensajesError = cc.obtenerMensajesError();
//
//		// Verificar si alguno de los mensajes de error contiene el texto esperado
//		boolean mensajeEncontrado = mensajesError.stream()
//				.anyMatch(mensaje -> mensaje.getText().contains("Ingrese porcentaje"));
//
//		// Validar el resultado
//		if (mensajeEncontrado) {
//			Assert.assertTrue(true);
//		} else {
//			Assert.fail(
//					"Campo 'Porcentaje para notificacion de presupuesto' no se pide obligatoriamente o no lo indica por mensaje de error.");
//		}
//	}
//
	@Test(priority = 6)
	public void creacionDeCC() throws InterruptedException {

		String nombreCC = "CC AutoTest - Nro " + cc.identificadorUnico();
		String codigoCC = cc.identificadorUnico();
		String userNotificar = "Automatizacion cliente";
		String montoPresupuesto = "100000";
		String porcentajeNotificar1 = "57";
		String porcentajeNotificar2 = "67";
		String porcentajeNotificar3 = "77";
		String tipoCorreoNotificar = "Todos";

		cp.waitForWebElementToBeClickable(cc.btnCancelar);
		cc.btnCancelar.click();

		// Creacion de CC //

		cc.ingresarCodigoFormulario(codigoCC);
		cc.ingresarNombreFormulario(nombreCC);
		cc.notificarServicio(userNotificar);
		cc.seleccionaTipoCorreo(tipoCorreoNotificar);
		cc.definirPresupuesto(montoPresupuesto);
		cc.notificarPresupuesto(true);
		cc.definePrimerPorcentajePresupuesto(porcentajeNotificar1);
		cc.defineSegundoPorcentajePresupuesto(porcentajeNotificar2);
		cc.defineTercerPorcentajePresupuesto(porcentajeNotificar3);
		boolean estado = cc.obtieneEstadoFormulario();

		cp.waitForWebElementToBeClickable(cc.btnCrear);
		cc.btnCrear.click();

		// Esperar mensaje "Porfavor espere"

		cp.waitForInvisibilityOfElement(cc.waitingDialog);

		// Busqueda de CC //

		cc.buscarCC(nombreCC);

		Thread.sleep(1000);

		// Obtener la lista de nombres presentes en la grilla
		List<WebElement> nombres = cc.obtenerListaNombres();
		
		System.out.println(nombreCC);

		// Verificar si la grilla está vacía
		if (nombres.isEmpty()) {
			Assert.fail("La grilla no contiene resultados.");
		} else {
			// Usar un stream para verificar si alguno de los nombres coincide con el
			// buscado
			boolean nombreEncontrado = nombres.stream()
			        .map(elemento -> elemento.getText().trim()) // Convertir cada WebElement a su texto, eliminando espacios
			        .anyMatch(nombre -> nombre.equalsIgnoreCase(nombreCC.trim())); // Verificar si algún nombre coincide ignorando mayúsculas y minúsculas

			// Verificar el resultado
			if (!nombreEncontrado) {
				Assert.fail("El cc creado: '" + nombreCC + "' no se encontró en la grilla.");
			} else {
				System.out.println("El cc creado: '" + nombreCC + "' fue encontrado en la grilla.");
				cp.waitForWebElementToAppear(cc.primerCC);
				cc.primerCC.click();
			}
		}


			String nuevoNombreCC = cc.obtieneNombreFormulario(getDriver());
			String nuevoCodigoCC = cc.obtieneCodigoFormulario(getDriver());
			String nuevoUserNotificar = cc.obtieneResponsableCC();
			String nuevoTipoCorreo = cc.obtieneOpcionTipoCorreo();
			//boolean nuevoCheckPresupuesto = cc.obtieneCheckPresupuesto();
			String nuevoMontoPresupuesto = cc.obtieneMontoPresupuesto(getDriver());
			String nuevoPorcentajePresupuesto1 = cc.obtienePrimerPorcentajePresupuesto(getDriver());
			String nuevoPorcentajePresupuesto2 = cc.obtieneSegundoPorcentajePresupuesto(getDriver());
			String nuevoPorcentajePresupuesto3 = cc.obtieneTercerPorcentajePresupuesto(getDriver());
			boolean nuevoEstado = cc.obtieneEstadoFormulario();
			//boolean nuevoCheckDefinePresupuesto = cc.obtieneCheckNotificarPresupuesto();
			
		// Compara datos ingresados en la creacion con los datos obtenidos despues de crear
			
			// Nombre
			if (nombreCC.trim().equalsIgnoreCase(nuevoNombreCC.trim())) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("El cc creado: '" + nombreCC + "' no guardo correctamente el nombre ingresado");
			}
			
			// Codigo
			if (codigoCC.equals(nuevoCodigoCC)) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("El cc creado: '" + nombreCC + "' no guardo correctamente el codigo ingresado");
			}
			
			// Estado
			if (estado != nuevoEstado) {
				Assert.fail("El cc creado: '" + nombreCC + "' no guardo correctamente el estado ingresado");
			} else {
				Assert.assertTrue(true);
			}
			
			// ResponsableCC
			if (userNotificar.equals(nuevoUserNotificar)) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("El cc creado: '" + nombreCC + "' no guardo correctamente el usuario a notificar ingresado");
			}
			
			// Tipo correo
			if (nuevoTipoCorreo.equalsIgnoreCase(tipoCorreoNotificar)) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("El cc creado: '" + nombreCC + "' no guardo correctamente las opciones de Tipo correo ingresadas");
			}
			
			// Monto presupuesto
			if (montoPresupuesto != nuevoMontoPresupuesto) {
				Assert.fail("El cc creado: '" + nombreCC + "' no guardo correctamente el monto presupuesto ingresado");
			} else {
				Assert.assertTrue(true);
			}
			
			// Porcentaje presupuesto
			if (porcentajeNotificar1 != nuevoPorcentajePresupuesto1) {
				Assert.fail("El cc creado: '" + nombreCC + "' no guardo correctamente el primer porcentaje de presupuesto ingresado");
			} else {
				Assert.assertTrue(true);
			}
			
			if (porcentajeNotificar2 != nuevoPorcentajePresupuesto2) {
				Assert.fail("El cc creado: '" + nombreCC + "' no guardo correctamente el segundo porcentaje de presupuesto ingresado");
			} else {
				Assert.assertTrue(true);
			}
			
			if (porcentajeNotificar3 != nuevoPorcentajePresupuesto3) {
				Assert.fail("El cc creado: '" + nombreCC + "' no guardo correctamente el tercer porcentaje de presupuesto ingresado");
			} else {
				Assert.assertTrue(true);
			}
		}
	
//	@Test //DEBUG
//	public void busquedaCC() throws InterruptedException{
//		String nombreCC = "CC AUTOTEST - NRO 107CB7B";
//		cc.buscarCC(nombreCC);
//
//		Thread.sleep(1000);
//
//		// Obtener la lista de nombres presentes en la grilla
//		List<WebElement> nombres = cc.obtenerListaNombres();
//		
//		System.out.println(nombres);
//
//		// Verificar si la grilla está vacía
//		if (nombres.isEmpty()) {
//			Assert.fail("La grilla no contiene resultados.");
//		} else {
//			// Usar un stream para verificar si alguno de los nombres coincide con el
//			// buscado
//			boolean nombreEncontrado = nombres.stream().map(WebElement::getText) // Convertir cada WebElement a su texto
//					.anyMatch(nombre -> nombre.equals(nombreCC)); // Verificar si algún nombre coincide
//
//			// Verificar el resultado
//			if (!nombreEncontrado) {
//				Assert.fail("El cc creado: '" + nombreCC + "' no se encontró en la grilla.");
//			} else {
//				System.out.println("El cc creado: '" + nombreCC + "' fue encontrado en la grilla.");
//				cp.waitForWebElementToAppear(cc.primerCC);
//				cc.primerCC.click();
//			}
//		}
//	}
	
//    @Test //DEBUG
//	public void testObtencionDeCampos() throws InterruptedException {
//    	
//    	cp.waitForWebElementToAppear(cc.primerCC);
//		cc.primerCC.click();
//		
//		cp.waitForInvisibilityOfElement(cc.waitingDialog);
//    	
//    	String nuevoNombreCC = cc.obtieneNombreFormulario(getDriver());
//		String nuevoCodigoCC = cc.obtieneCodigoFormulario(getDriver());
//		String nuevoUserNotificar = cc.obtieneResponsableCC();
//		boolean nuevoTipoCorreo = cc.obtieneOpcionTipoCorreo();
//		cc.selectTipoCorreo.click();
//		boolean nuevoCheckPresupuesto = cc.obtieneCheckPresupuesto();
//		String nuevoMontoPresupuesto = cc.obtieneMontoPresupuesto(getDriver());
//		String nuevoPorcentajePresupuesto = cc.obtienePrimerPorcentajePresupuesto(getDriver());
//		boolean estado = cc.obtieneEstadoFormulario();
//		boolean nuevoCheckDefinePresupuesto = cc.obtieneCheckNotificarPresupuesto();
//		
//		System.out.println("Nombre = "+nuevoNombreCC);
//		System.out.println("Codigo = "+nuevoCodigoCC);
//		System.out.println("User resp = "+nuevoUserNotificar);
//		System.out.println("Tipo correo = "+nuevoTipoCorreo);
//		System.out.println("Check presupuesto = "+nuevoCheckPresupuesto);
//		System.out.println("Monto presupuesto = "+nuevoMontoPresupuesto);
//		System.out.println("Check notificar presupuesto = "+nuevoCheckDefinePresupuesto);
//		System.out.println("Porcentaje presupuesto = "+nuevoPorcentajePresupuesto);
//		System.out.println("Estado = "+estado);
//    }
}
