package valuesite.tests;

import java.util.List;

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
import valuesite.pageobjects.SucursalClienteCli;
import valuesite.testcomponents.BaseTest;

public class SucursalClienteTestCli extends BaseTest {

	SucursalClienteCli suc;
	ComponentesReusables cp;

	@BeforeTest
	public void setUp() {
		suc = new SucursalClienteCli(getDriver());
		cp = new ComponentesReusables(getDriver());
	}

	@BeforeClass
	public void ingresaMantenedor() throws InterruptedException {
		Login login = new Login(getDriver());

		// Pasa como parametro correo y contraseña para ingresar a la web
		login.iniciarSesion("automcli@aquivoy.cl", "123456");

		// Ingresa al mantenedor una vez la sesion este iniciada
		suc.ingresoMantenedorSucursalesCliente();
	}

	// Testea si se esta limpiando el campo nombre con el boton cancelar //
	@Test(priority = 1)
	public void limpiaCampoNombre() throws InterruptedException {

		// Ingresa un nombre al campo y presiona el boton cancelar
		suc.ingresarNombreFormulario("Test");
		suc.btnCancelar.click();

		// Obtiene el nombre que tiene ingresando actualmente el campo
		String nombre = suc.campoNombre.getText();

		// Valida que "nombre" este vacio
		if (nombre.isEmpty()) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("El campo no se limpia correctamente");
		}
	}

	// Testea si se esta limpiando el campo direccion con el boton cancelar //
	@Test(priority = 2)
	public void limpiaCampoDireccion() throws InterruptedException {

		// Ingresa y selecciona una direccion, pasa como parametro la direccion a buscar
		// y la posicion a seleccionar en la lista
		suc.seleccionaDireccion("Cerro aguja 0376", 1);

		// Espera a que boton cancelar sea clickeable y lo clickea
		cp.waitForWebElementToBeClickable(suc.btnCancelar);
		suc.btnCancelar.click();

		// Obtiene el valor del campo direccion
		String direccion = suc.campoDireccion.getText();

		// Comprueba que el campo este vacio
		if (direccion.isEmpty()) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("Campo direccion no se limpia correctamente");
		}
	}

	// Comprueba que el campo nombre sea obligatorio //
	@Test(priority = 3)
	public void nombreObligatorio() throws InterruptedException {

		// Selecciona Direccion
		suc.seleccionaDireccion("cerro aguja 0376 quilicura", 1);

		// Presiona el boton crear
		suc.btnCrear.click();

		// Espera a que aparezca el mensaje de error
		cp.waitForWebElementToAppear(suc.msjError);

		// Obtiene el texto del mensaje de error
		String mensaje = suc.msjError.getText();

		// Comprueba que el mensaje indique que falta el campo nombre
		if (mensaje.contains("Campo Nombre Sucursal Obligatorio")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("No esta pidiendo nombre obligatoriamente para crear");
		}
	}

	// Comprueba que el campo nombre sea obligatorio //
	@Test(priority = 4)
	public void direccionObligatorio() throws InterruptedException {
		// Refresca la pagina (Para evitar colapso de mensajes de error)
		getDriver().navigate().refresh();

		// Ingresa un nombre
		suc.ingresarNombreFormulario("Test");

		// Espera a que boton crear este disponible y lo presiona
		cp.waitForWebElementToBeClickable(suc.btnCrear);
		suc.btnCrear.click();

		// Espera a que aparezca el mensaje de error y extrae el texto de este
		cp.waitForWebElementToAppear(suc.msjError);

		// Valida que el mensaje indique que falta direccion
		if (suc.msjError.isDisplayed()) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("No esta pidiendo direccion obligatoriamente para crear o no esta mostrando mensaje de error");
		}

		suc.btnCancelar.click();
	}

	// Valida la correcta creacion de sucursales //
	@Test(priority = 5)
	public void creaSucursal() throws InterruptedException {
		// Obtener el número de sucursal del archivo de propiedades
		int nroSucursal = Integer.parseInt(suc.obtieneNroSucursal());

		// Define el nombre de sucursal y la direccion a crear
		String nombreSucursalCrear = "SUCURSAL " + nroSucursal;
		String direccionCrear = "cerro aguja 0379";

		// Completa el formulario
		suc.ingresarNombreFormulario(nombreSucursalCrear);
		suc.seleccionaDireccion(direccionCrear, 1);

		// Crear un objeto para ejecutar JavaScript
		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		// Obtener el texto del input usando JavaScript (Ya que en el html no guarda el
		// texto del input, por lo tanto no se puede extraer con selenium)
		String direccionEsperada = (String) js.executeScript("return arguments[0].value;", suc.campoDireccion);

		suc.btnCrear.click();

		// Incrementar el número de sucursal para el próximo uso
		nroSucursal++;

		// Guardar el nuevo número de sucursal en el archivo de propiedades
		suc.guardaNroSucursal(String.valueOf(nroSucursal));

		// Esperar a que desaparezca modal de espera
		cp.waitForInvisibilityOfElement(suc.waitingDialog);

		// Imprimir todos los nombres de las sucursales listadas para depuracion
		// suc.obtenerListaNombres().forEach(elemento -> System.out.println("Elemento
		// texto: '" + elemento.getText() + "'"));

		// Buscar el nombre de la sucursal en las páginas de la lista
		WebElement nombreSucursal = suc.buscarElementoEnPaginas(nombreSucursalCrear);
		;

		if (nombreSucursal != null) {
			Assert.assertTrue(true);
			// System.out.println("Sucursal creada: " + nombreSucursal.getText() + "\n");

			// Verificar que la dirección de la sucursal creada es la correcta
			boolean direccionCorrecta = suc.verificaDireccionSucursal(nombreSucursalCrear, direccionEsperada.trim());
			Assert.assertTrue(direccionCorrecta, "La direccion de la sucursal creada es incorrecta.");
		} else {
			Assert.fail("La sucursal no fue creada o encontrada");
			// System.out.println("Sucursal no creada\n");
		}

		// Limpia el formulario antes de salir del metodo
		Thread.sleep(500);
		suc.btnCancelar.click();

	}

	// Testea la modificacion de los campos //
	@Test(priority = 6)
	public void modificarSucursal() throws InterruptedException {
		// Crear un objeto para ejecutar JavaScript
		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		// Obtener el número de modificacion del archivo de propiedades
		int nroModif = Integer.parseInt(suc.obtieneNroModif());

		// Obtener el número de sucursal del archivo de propiedades
		int nroSucursal = Integer.parseInt(suc.obtieneNroSucursal());

		// CREACION DE SUCURSAL (Se crea una sucursal exclusiva para la modificacion,
		// para mas fiabilidad en la prueba)

		String nombreSucursalCrear = "SUCURSAL " + nroSucursal;
		String direccionCrear = "cerro aguja 0379";

		// Completa los campos del formulario
		suc.ingresarNombreFormulario(nombreSucursalCrear);
		suc.seleccionaDireccion(direccionCrear, 1);

		// Crea la sucursal
		suc.btnCrear.click();

		// Incrementar el número de sucursal para el próximo uso
		nroSucursal++;

		// Guardar el nuevo número de sucursal en el archivo de propiedades
		suc.guardaNroSucursal(String.valueOf(nroSucursal));

		// TERMINO DE CREACION DE SUCURSAL

		// Esperar a que desaparezca modal de espera
		cp.waitForInvisibilityOfElement(suc.waitingDialog);

		// Busca la sucursal creada por el filtro y la clickea
		suc.filtraPorNombre(nombreSucursalCrear);
		Thread.sleep(700);
		suc.primeraSucursal.click();

		// Guarda los valores antes de modificar
		String nombreActual = (String) js.executeScript("return arguments[0].value;", suc.campoNombre);
		boolean estadoActual = suc.obtenerEstado();
		String direccionActual = (String) js.executeScript("return arguments[0].value;", suc.campoDireccion);

		// Define los valores a utilizar para modificar
		String nombreNuevo = "SUCURSAL MODIF " + nroModif;
		String direccionNueva;
		boolean estadoNuevo;

		// Cambia nombre
		suc.ingresarNombreFormulario(nombreNuevo);

		// Cambia al estado contrario del actual
		if (estadoActual) {
			suc.marcaCheckActivo.click();
		} else {
			suc.marcaCheckActivo.click();
		}

		// Obtiene el estado despues de cambiarlo y lo guarda en la variable
		estadoNuevo = suc.obtenerEstado();

		// Cambia direccion la guarda en la variable
		suc.seleccionaDireccion("camino real lo barnechea", 1);
		direccionNueva = (String) js.executeScript("return arguments[0].value;", suc.campoDireccion);

		// Confirma la modificacion
		suc.btnCrear.click();

		// Aumenta el nro de modificacion
		nroModif++;

		// Guarda el nro de modificacion para no repetirlo en su proximo uso
		suc.guardaNroModif(String.valueOf(nroModif));

		// Esperar a que desaparezca modal de espera
		cp.waitForInvisibilityOfElement(suc.waitingDialog);

		if (estadoNuevo) {
			suc.filtroEstado.click();
			suc.estadoActivo.click();
		} else {
			suc.filtroEstado.click();
			suc.estadoInactivo.click();
		}
		// Busca la sucursal en base al nuevo nombre que se le otorgo en la modificacion
		// (y controla el caso de que el filtro no trae resultado)
		try {
			suc.filtraPorNombre(nombreNuevo);
		} catch (NoSuchElementException e) {
			// Manejar el caso en que el elemento no se encuentra
			Assert.fail("Error: El elemento no fue encontrado.");
		} catch (StaleElementReferenceException e) {
			// Manejar el caso en que la referencia al elemento es obsoleta
			Assert.fail("Error: La referencia al elemento es obsoleta.");
		} catch (Exception e) {
			// Manejar cualquier otro tipo de error
			Assert.fail("Error: Ocurrió un error inesperado.");
		}

		// Hace click en la sucursal y espera 250 milisegundos para dar tiempo a cargar
		// los datos
		Thread.sleep(700);
		suc.primeraSucursal.click();
		Thread.sleep(250);

		// Validacion cambio de nombre
		if (nombreActual.trim() != nombreNuevo.trim()) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("El nombre de la sucursal no cambio correctamente");
		}

		// Validacion cambio de estado
		if (estadoActual != estadoNuevo) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("El estado de la sucursal no cambio correctamente");
		}

		// Validacion cambio de direccion
		if (direccionActual.trim() != direccionNueva.trim()) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("La ciudad de la sucursal no cambio correctamente");
		}

		// Presiona nuevamente la sucursal y obtiene el estado actual
		suc.primeraSucursal.click();
		estadoActual = suc.obtenerEstado();

		// Vuelve a cambiar al estado contrario
		if (estadoActual) {
			suc.marcaCheckActivo.click();
		} else {
			suc.marcaCheckActivo.click();
		}

		// Confirma modificacion
		suc.btnCrear.click();

		// Esperar a que desaparezca modal de espera
		cp.waitForInvisibilityOfElement(suc.waitingDialog);

		if (estadoActual) {
			suc.filtroEstado.click();
			suc.estadoInactivo.click();
		} else {
			suc.filtroEstado.click();
			suc.estadoActivo.click();
		}

		suc.btnBuscar.click();

		// Vuelve a seleccionar la sucursal y obtiene su estado
		Thread.sleep(700);
		suc.primeraSucursal.click();
		estadoNuevo = suc.obtenerEstado();

		// Validacion cambio de estado
		if (estadoActual != estadoNuevo) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("El estado de la sucursal no cambio correctamente");
		}

		suc.btnLimpiar.click();
		cp.waitForWebElementToBeClickable(suc.btnBuscar);
		suc.btnBuscar.click();
	}

	// Testea el correcto funcionamiento del filtro nombre
	@Test(priority = 7)
	public void filtroNombre() throws InterruptedException {

		// Crear un objeto para ejecutar JavaScript
		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		Thread.sleep(700);
		suc.primeraSucursal.click();
		Thread.sleep(250);

		// Define la sucursal a buscar
		String sucBuscar = (String) js.executeScript("return arguments[0].value;", suc.campoNombre);

		// Busca la sucursal
		suc.filtraPorNombre(sucBuscar);

		// Le da 300 milisegundos para obtener resultados
		Thread.sleep(300);

		// Validar si el filtro trajo resultados a la grilla
		List<WebElement> resultados = suc.obtenerListaNombres();

		// Guarda true si encontro la sucursal y false si no la encontro
		boolean encontrado = resultados.stream()
				.anyMatch(elemento -> elemento.getText().trim().equalsIgnoreCase(sucBuscar));

		// Si "encontrado" es true, pasa la prueba, si es false manda mensaje indicando
		// el problema
		Assert.assertTrue(encontrado,
				"No se encontraron resultados en la grilla para la sucursal buscada: " + sucBuscar);
		
		Thread.sleep(500);
		suc.btnLimpiar.click();
	}

	//Testea el correcto funcionamiento del filtro estado
	@Test(priority = 8)
	public void filtroEstado() throws InterruptedException {
		
	// Estado ACTIVO
		
		// Aplicar el filtro por estado "activo"
		suc.filtraPorEstado("activo");

		// Obtener la lista de estados de la grilla
		List<WebElement> resultados1 = suc.obtenerListaEstados();

		// Verificar que todos los elementos sean "activo"
		boolean estadosActivos = resultados1.stream()
				.allMatch(elemento -> elemento.getText().trim().equalsIgnoreCase("activo"));

		// Afirmar que todos los elementos son "activo"
		Assert.assertTrue(estadosActivos, "Se encontraron estados inactivos filtrando por activos");
		
	// Estado INACTIVO
		
		// Aplicar el filtro por estado "inactivo"
		
		// Tiempo para evitar topar con modal "porfavor espere"
		Thread.sleep(500);
		suc.filtraPorEstado("inactivo");

		// Tiempo para obtener resultados
		Thread.sleep(200);
				
		// Obtener la lista de estados de la grilla
		List<WebElement> resultados2 = suc.obtenerListaEstados();

		// Verificar que todos los elementos sean "activo"
		boolean estadosInactivos = resultados2.stream()
						.allMatch(elemento -> elemento.getText().trim().equalsIgnoreCase("inactivo"));

		// Afirmar que todos los elementos son "activo"
		Assert.assertTrue(estadosInactivos, "Se encontraron estados activos filtrando por inactivos");
	}

}
