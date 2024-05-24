package valuesite.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
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
		login.iniciarSesion("automrt@aquivoy.cl", "123456");

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

//	@Test(priority = 7)
//	public void creaSucursal() throws InterruptedException {
//		// Obtener el número de sucursal del archivo de propiedades
//	    int nroSucursal = Integer.parseInt(suc.obtieneNroSucursal());
//	    
//		String nombreSucursalCrear = "SUCURSAL " + nroSucursal;
//		String direccionCrear = "cerro aguja 0379";
//		
//		System.out.println("Sucursal a crear: " + nombreSucursalCrear + "\n");
//
//		suc.ingresarNombreFormulario(nombreSucursalCrear);
//		suc.seleccionaCiudad(1);
//		suc.seleccionaDireccion(direccionCrear, 1);
//		
//		// Crear un objeto para ejecutar JavaScript
//		JavascriptExecutor js = (JavascriptExecutor) getDriver();
//
//		// Obtener el texto del input usando JavaScript (Ya que en el html no guarda el texto del input)
//		String direccionEsperada = (String) js.executeScript("return arguments[0].value;", suc.campoDireccion);
//
//		suc.btnCrear.click();
//		
//		// Incrementar el número de sucursal para el próximo uso
//	    nroSucursal++;
//	    
//	    // Guardar el nuevo número de sucursal en el archivo de propiedades
//	    suc.guardaNroSucursal(String.valueOf(nroSucursal));
//
//	    // Esperar a que desaparezca modal de espera
//		cp.waitForInvisibilityOfElement(suc.waitingDialog);
//		
//		// Imprimir todos los textos de los elementos para depuración
//		//suc.obtenerListaNombres().forEach(elemento -> System.out.println("Elemento texto: '" + elemento.getText() + "'"));
//		
//		 // Buscar el nombre de la sucursal en las páginas de la lista
//		WebElement nombreSucursal = suc.buscarElementoEnPaginas(nombreSucursalCrear);;
//		
//		if (nombreSucursal != null) {
//	        Assert.assertTrue(true);
//	        System.out.println("Sucursal creada: " + nombreSucursal.getText() + "\n");
//	        
//	     // Verificar que la dirección de la sucursal creada es la correcta
//	        boolean direccionCorrecta = suc.verificaDireccionSucursal(nombreSucursalCrear, direccionEsperada.trim());
//	        Assert.assertTrue(direccionCorrecta, "La direccion de la sucursal creada es incorrecta.");
//	    } else {
//	        Assert.assertTrue(false);
//	        System.out.println("Sucursal no creada\n");
//	    }
//		
//		suc.btnCancelar.click();
//
//	}
	
//	@Test(priority = 8)
//	public void VisibilidadConvenio () throws InterruptedException {
//		// Obtener el número de sucursal del archivo de propiedades
//	    int nroSucursal = Integer.parseInt(suc.obtieneNroSucursal());
//	    
//		String nombreSucursalCrear = "SUCURSAL " + nroSucursal;
//		String direccionCrear = "cerro aguja 0379";
//		String convenio = "001";
//		
//		System.out.println("Sucursal a crear: " + nombreSucursalCrear + "\n");
//
//		suc.ingresarNombreFormulario(nombreSucursalCrear);
//		suc.seleccionaCiudad(1);
//		suc.seleccionaConvenio(convenio);
//		suc.seleccionaDireccion(direccionCrear, 1);
//		
//		String convenioSeleccionado = suc.selectConvenio.getAttribute("title");
//		
//		suc.btnCrear.click();
//		
//		// Incrementar el número de sucursal para el próximo uso
//	    nroSucursal++;
//	    
//	    // Guardar el nuevo número de sucursal en el archivo de propiedades
//	    suc.guardaNroSucursal(String.valueOf(nroSucursal));
//		
//		// Esperar a que desaparezca modal de espera
//		cp.waitForInvisibilityOfElement(suc.waitingDialog);
//		
//		suc.filtraPorNombre(nombreSucursalCrear);
//		suc.primeraSucursal.click();
//		
//		String convenioCreado = suc.selectConvenio.getAttribute("title");
//		
//		if (convenioSeleccionado.trim().equalsIgnoreCase(convenioCreado.trim())) {
//			Assert.assertTrue(true);
//		}else {
//			Assert.assertTrue(false);
//			System.out.println("El convenio seleccionado en formulario no corresponde con lo creado");
//		}
//		
//		suc.btnCancelar.click();
//	}

//	@Test(priority = 9)
//	public void modificarSucursal() throws InterruptedException {
//		// Crear un objeto para ejecutar JavaScript
//		JavascriptExecutor js = (JavascriptExecutor) getDriver();
//		
//		// Obtener el número de modificacion del archivo de propiedades
//	    int nroModif = Integer.parseInt(suc.obtieneNroModif());
//	    
//	    // Obtener el número de sucursal del archivo de propiedades
//	    int nroSucursal = Integer.parseInt(suc.obtieneNroSucursal());
//	    
//	// CREACION DE SUCURSAL (Se crea una sucursal exclusiva para la modificacion, para mas fiabilidad en la prueba)
//	    
//	    String nombreSucursalCrear = "SUCURSAL " + nroSucursal;
//		String direccionCrear = "cerro aguja 0379";
//		
//		System.out.println("Sucursal a crear: " + nombreSucursalCrear + "\n");
//
//		suc.ingresarNombreFormulario(nombreSucursalCrear);
//		suc.seleccionaCiudad(1);
//		suc.seleccionaDireccion(direccionCrear, 1);
//		
//		suc.btnCrear.click();
//		
//		// Incrementar el número de sucursal para el próximo uso
//	    nroSucursal++;
//	    
//	    // Guardar el nuevo número de sucursal en el archivo de propiedades
//	    suc.guardaNroSucursal(String.valueOf(nroSucursal));
//	    
//	 // TERMINO DE CREACION DE SUCURSAL
//
//	    // Esperar a que desaparezca modal de espera
//		cp.waitForInvisibilityOfElement(suc.waitingDialog);
//		
//		suc.filtraPorNombre(nombreSucursalCrear);
//		suc.primeraSucursal.click();
//		
//		// Valores antes de modificar
//		String nombreActual = (String) js.executeScript("return arguments[0].value;", suc.campoNombre);
//		boolean estadoActual = suc.obtenerEstado();
//		String ciudadActual = suc.selectCiudad.getAttribute("title");
//		String direccionActual = (String) js.executeScript("return arguments[0].value;", suc.campoDireccion);
//		
//		// Valores despues de modificar
//		String nombreNuevo = "SUCURSAL MODIF " + nroModif;
//		String direccionNueva;
//		String ciudadNueva;
//		boolean estadoNuevo;
//
//		// Cambia nombre
//		suc.ingresarNombreFormulario(nombreNuevo);
//		
//		// Cambia al estado contrario del actual
//		if (estadoActual) {
//			suc.marcaCheckInactivo.click();
//		}else {
//			suc.marcaCheckActivo.click();
//		}
//		
//		estadoNuevo = suc.obtenerEstado();
//		
//		// Cambia direccion
//		suc.seleccionaDireccion("camino real lo barnechea", 1);
//		direccionNueva = (String) js.executeScript("return arguments[0].value;", suc.campoDireccion);
//		
//		// Cambia Ciudad
//		suc.seleccionaCiudad(2);
//		ciudadNueva = suc.selectCiudad.getAttribute("title");
//		
//		suc.btnCrear.click();
//		
//		nroModif++;
//		
//		suc.guardaNroModif(String.valueOf(nroModif));
//		
//		// Esperar a que desaparezca modal de espera
//		cp.waitForInvisibilityOfElement(suc.waitingDialog);
//		
//		// Busca la sucursal en base al nuevo nombre que se le otorgo en la modificacion (y controla el caso de que el filtro no trae resultado)
//		try {
//		    suc.filtraPorNombre(nombreNuevo);
//		} catch (NoSuchElementException e) {
//		    // Manejar el caso en que el elemento no se encuentra
//		    Assert.fail("Error: El elemento no fue encontrado.");
//		} catch (StaleElementReferenceException e) {
//		    // Manejar el caso en que la referencia al elemento es obsoleta
//		    Assert.fail("Error: La referencia al elemento es obsoleta.");
//		} catch (Exception e) {
//		    // Manejar cualquier otro tipo de error
//		    Assert.fail("Error: Ocurrió un error inesperado.");
//		}
//		
//		suc.primeraSucursal.click();
//		Thread.sleep(250);
//		
//		// Validacion cambio de nombre
//		if (nombreActual.trim() != nombreNuevo.trim()) {
//			Assert.assertTrue(true);
//		}else {
//			Assert.fail("El nombre de la sucursal no cambio correctamente");
//		}
//		
//		// Validacion cambio de estado
//		if (estadoActual != estadoNuevo) {
//			Assert.assertTrue(true);
//		}else {
//			Assert.fail("El estado de la sucursal no cambio correctamente");
//		}
//		
//		// Validacion cambio de ciudad
//		if (ciudadActual.trim() != ciudadNueva.trim()) {
//			Assert.assertTrue(true);
//		}else {
//			Assert.fail("La ciudad de la sucursal no cambio correctamente");
//		}
//		
//		// Validacion cambio de direccion
//		if (direccionActual.trim() != direccionNueva.trim()) {
//			Assert.assertTrue(true);
//		}else {
//			Assert.fail("La ciudad de la sucursal no cambio correctamente");
//		}
//		
//		// Vuelve a cambiar al estado contrario
//		suc.primeraSucursal.click();
//		estadoActual = suc.obtenerEstado();
//		
//		if (estadoActual) {
//			suc.marcaCheckInactivo.click();
//		}else {
//			suc.marcaCheckActivo.click();
//		}
//		
//		suc.btnCrear.click();
//		// Esperar a que desaparezca modal de espera
//		cp.waitForInvisibilityOfElement(suc.waitingDialog);
//		
//		suc.primeraSucursal.click();
//		estadoNuevo = suc.obtenerEstado();
//		
//		// Validacion cambio de estado
//		if (estadoActual != estadoNuevo) {
//			Assert.assertTrue(true);
//		}else {
//			Assert.fail("El estado de la sucursal no cambio correctamente");
//		}
//	}
	
	@Test(priority = 10)
	public void filtroNombre() {
	    // Obtener el número de sucursal del archivo de propiedades
	    int nroSucursal = Integer.parseInt(suc.obtieneNroSucursal());
	    
	    if (nroSucursal>=3) {
		    nroSucursal = nroSucursal - 2;
	    }
	    
	    String sucBuscar = "SUCURSAL " + nroSucursal;
	    
	    suc.filtraPorNombre(sucBuscar);

	    // Validar si el filtro trajo resultados a la grilla
	    List<WebElement> resultados = suc.obtenerListaNombres();
	    
	    boolean encontrado = resultados.stream()
	                                   .anyMatch(elemento -> elemento.getText().trim().equalsIgnoreCase(sucBuscar));

	    Assert.assertTrue(encontrado, "No se encontraron resultados en la grilla para la sucursal buscada: " + sucBuscar);
	}
}
