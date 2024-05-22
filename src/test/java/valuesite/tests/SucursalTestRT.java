package valuesite.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import valuesite.componentesreusables.ComponentesReusables;
import valuesite.pageobjects.Login;
import valuesite.pageobjects.SucursalRT;
import valuesite.testcomponents.BaseTest;

public class SucursalTestRT extends BaseTest {

	SucursalRT suc;
	ComponentesReusables cp;

	@BeforeTest
	public void setUp() {
		suc = new SucursalRT(getDriver());
		cp = new ComponentesReusables(getDriver());
	}

	@BeforeClass
	public void ingresaMantenedor() throws InterruptedException {
		Login login = new Login(getDriver());

		// Pasa como parametro correo y contraseña para ingresar a la web
		login.iniciarSesion("userauto@aquivoy.cl", "123456");

		suc.ingresoMantenedorSucursales();
	}

//	@Test(priority = 1)
//	public void limpiaCampoNombre() throws InterruptedException {
//		suc.ingresarNombreFormulario("Test");
//		suc.btnCancelar.click();
//		String nombre = suc.campoNombre.getText();
//		if (nombre.isEmpty()) {
//			Assert.assertTrue(true);
//		} else {
//			Assert.assertTrue(false);
//		}
//	}
//
//	@Test(priority = 2)
//	public void limpiaSelectCiudad() throws InterruptedException {
//		suc.ingresarNombreFormulario("Test");
//		suc.btnCancelar.click();
//		String nombre = suc.campoNombre.getText();
//		if (nombre.isEmpty()) {
//			Assert.assertTrue(true);
//		} else {
//			Assert.assertTrue(false);
//		}
//	}
//
//	@Test(priority = 3)
//	public void limpiaCampoDireccion() throws InterruptedException {
//		suc.seleccionaDireccion("Cerro aguja 0376", 1);
//		cp.waitForWebElementToBeClickable(suc.btnCancelar);
//		suc.btnCancelar.click();
//		String direccion = suc.campoDireccion.getText();
//		if (direccion.isEmpty()) {
//			Assert.assertTrue(true);
//		} else {
//			Assert.assertTrue(false);
//		}
//	}
//
//	@Test(priority = 4)
//	public void nombreObligatorio() throws InterruptedException {
//		suc.seleccionaCiudad(1);
//		suc.seleccionaDireccion("cerro aguja 0376 quilicura", 1);
//		suc.btnCrear.click();
//		cp.waitForWebElementToAppear(suc.msjError);
//		String mensaje = suc.msjError.getText();
//		if (mensaje.contains("Campo Sucursal: Nombre")) {
//			Assert.assertTrue(true);
//		} else {
//			Assert.assertTrue(false);
//		}
//	}
//
//	@Test(priority = 5)
//	public void ciudadObligatorio() throws InterruptedException {
//		// Refresca la pagina (Para evitar colapso de mensajes de error)
//		getDriver().navigate().refresh();
//		suc.ingresarNombreFormulario("Test");
//		suc.seleccionaDireccion("cerro aguja 0376 quilicura", 1);
//		cp.waitForWebElementToBeClickable(suc.btnCrear);
//		suc.btnCrear.click();
//		cp.waitForWebElementToAppear(suc.msjError);
//		String mensaje = suc.msjError.getText();
//		if (mensaje.contains("Campo Ciudad obligatorio")) {
//			Assert.assertTrue(true);
//		} else {
//			Assert.assertTrue(false);
//		}
//	}
//
//	@Test(priority = 6)
//	public void direccionObligatorio() throws InterruptedException {
//		// Refresca la pagina (Para evitar colapso de mensajes de error)
//		getDriver().navigate().refresh();
//		
//		suc.seleccionaCiudad(1);
//		suc.ingresarNombreFormulario("Test");
//		cp.waitForWebElementToBeClickable(suc.btnCrear);
//		suc.btnCrear.click();
//		
//		cp.waitForWebElementToAppear(suc.msjError);
//		String mensaje = suc.msjError.getText();
//		
//		if (mensaje.contains("Campo Sucursal: Dirección obligatorio")) {
//			Assert.assertTrue(true);
//		} else {
//			Assert.assertTrue(false);
//		}
//
//		suc.btnCancelar.click();
//	}

	@Test(priority = 7)
	public void creaSucursal() throws InterruptedException {
		// Obtener el número de sucursal del archivo de propiedades
	    int nroSucursal = Integer.parseInt(suc.obtieneNroSucursal());
	    
		String nombreSucursalCrear = "SUCURSAL " + nroSucursal;
		String direccionCrear = "cerro aguja 0379";
		
		System.out.println("Sucursal a crear: " + nombreSucursalCrear + "\n");

		suc.ingresarNombreFormulario(nombreSucursalCrear);
		suc.seleccionaCiudad(1);
		suc.seleccionaDireccion(direccionCrear, 1);
		
		// Crear un objeto para ejecutar JavaScript
		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		// Obtener el texto del input usando JavaScript (Ya que en el html no guarda el texto del input)
		String direccionEsperada = (String) js.executeScript("return arguments[0].value;", suc.campoDireccion);

		suc.btnCrear.click();
		
		// Incrementar el número de sucursal para el próximo uso
	    nroSucursal++;
	    
	    // Guardar el nuevo número de sucursal en el archivo de propiedades
	    suc.guardaNroSucursal(String.valueOf(nroSucursal));

		// Esperar a que la nueva sucursal aparezca en la lista
		cp.waitForInvisibilityOfElement(suc.waitingDialog);
		
		// Imprimir todos los textos de los elementos para depuración
		//suc.obtenerListaNombres().forEach(elemento -> System.out.println("Elemento texto: '" + elemento.getText() + "'"));
		
		 // Buscar el nombre de la sucursal en las páginas de la lista
		WebElement nombreSucursal = suc.buscarElementoEnPaginas(nombreSucursalCrear);;
		
		if (nombreSucursal != null) {
	        Assert.assertTrue(true);
	        System.out.println("Sucursal creada: " + nombreSucursal.getText() + "\n");
	        
	     // Verificar que la dirección de la sucursal creada es la correcta
	        boolean direccionCorrecta = suc.verificaDireccionSucursal(nombreSucursalCrear, direccionEsperada.trim());
	        Assert.assertTrue(direccionCorrecta, "La direccion de la sucursal creada es incorrecta.");
	    } else {
	        Assert.assertTrue(false);
	        System.out.println("Sucursal no creada\n");
	    }

	}

	
}
