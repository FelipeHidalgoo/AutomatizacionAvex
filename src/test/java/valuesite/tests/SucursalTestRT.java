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

		// Ingresa al mantenedor una vez la sesion este iniciada
		suc.ingresoMantenedorSucursales();
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

	// Testea si se esta limpiando el select ciudad con el boton cancelar //
	@Test(priority = 2)
	public void limpiaSelectCiudad() throws InterruptedException {
		
		// Selecciona una ciudad y presiona boton cancelar
		suc.seleccionaCiudad(1);
		suc.btnCancelar.click();
		
		// Guarda el valor actual del select ciudad
		String ciudad = suc.selectCiudad.getAttribute("title");
		
		// Valida que el valor del select este en "Seleccione.."
		if (ciudad.equalsIgnoreCase("Seleccione..")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("El campo no se vacia correctamente");
		}
	}

	// Testea si se esta limpiando el campo direccion con el boton cancelar //
	@Test(priority = 3)
	public void limpiaCampoDireccion() throws InterruptedException {
		
		// Ingresa y selecciona una direccion, pasa como parametro la direccion a buscar y la posicion a seleccionar en la lista
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
	@Test(priority = 4)
	public void nombreObligatorio() throws InterruptedException {
		
		// Selecciona ciudad y direccion
		suc.seleccionaCiudad(1); // parametro posicion de la ciudad a seleccionar en la lista
		suc.seleccionaDireccion("cerro aguja 0376 quilicura", 1);
		
		// Presiona el boton crear
		suc.btnCrear.click();
		
		// Espera a que aparezca el mensaje de error
		cp.waitForWebElementToAppear(suc.msjError);
		
		// Obtiene el texto del mensaje de error
		String mensaje = suc.msjError.getText();
		
		// Comprueba que el mensaje indique que falta el campo nombre
		if (mensaje.contains("Campo Sucursal: Nombre")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("No esta pidiendo nombre obligatoriamente para crear");
		}
	}

	// Comprueba que el select ciudad sea obligatorio //
	@Test(priority = 5)
	public void ciudadObligatorio() throws InterruptedException {
		
		// Refresca la pagina (Para evitar colapso de mensajes de error)
		getDriver().navigate().refresh();
		
		// Ingresa nombre y direccion
		suc.ingresarNombreFormulario("Test");
		suc.seleccionaDireccion("cerro aguja 0376 quilicura", 1);
		
		// Espera a que boton crear sea clickeable y lo clickea
		cp.waitForWebElementToBeClickable(suc.btnCrear);
		suc.btnCrear.click();
		
		// Espera a que aparezca mensaje de error y extrae el texto de este
		cp.waitForWebElementToAppear(suc.msjError);
		String mensaje = suc.msjError.getText();
		
		// Valida que el mensaje indique que falta ciudad
		if (mensaje.contains("Campo Ciudad obligatorio")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("No esta pidiendo ciudad obligatoriamente para crear");
		}
	}

	// Comprueba que el campo nombre sea obligatorio //
	@Test(priority = 6)
	public void direccionObligatorio() throws InterruptedException {
		// Refresca la pagina (Para evitar colapso de mensajes de error)
		getDriver().navigate().refresh();
		
		// Selecciona ciudad e ingresa un nombre
		suc.seleccionaCiudad(1);
		suc.ingresarNombreFormulario("Test");
		
		// Espera a que boton crear este disponible y lo presiona
		cp.waitForWebElementToBeClickable(suc.btnCrear);
		suc.btnCrear.click();
		
		// Espera a que aparezca el mensaje de error y extrae el texto de este
		cp.waitForWebElementToAppear(suc.msjError);
		String mensaje = suc.msjError.getText();
		
		// Valida que el mensaje indique que falta direccion
		if (mensaje.contains("Campo Sucursal: Dirección obligatorio")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("No esta pidiendo direccion obligatoriamente para crear");
		}

		suc.btnCancelar.click();
	}

	// Valida la correcta creacion de sucursales //
	@Test(priority = 7)
	public void creaSucursal() throws InterruptedException {
		// Obtener el número de sucursal del archivo de propiedades
	    int nroSucursal = Integer.parseInt(suc.obtieneNroSucursal());
	    
	    // Define el nombre de sucursal y la direccion a crear
		String nombreSucursalCrear = "SUCURSAL " + nroSucursal;
		String direccionCrear = "cerro aguja 0379";
		
		// Completa el formulario
		suc.ingresarNombreFormulario(nombreSucursalCrear);
		suc.seleccionaCiudad(1);
		suc.seleccionaDireccion(direccionCrear, 1);
		
		// Crear un objeto para ejecutar JavaScript
		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		// Obtener el texto del input usando JavaScript (Ya que en el html no guarda el texto del input, por lo tanto no se puede extraer con selenium)
		String direccionEsperada = (String) js.executeScript("return arguments[0].value;", suc.campoDireccion);

		suc.btnCrear.click();
		
		// Incrementar el número de sucursal para el próximo uso
	    nroSucursal++;
	    
	    // Guardar el nuevo número de sucursal en el archivo de propiedades
	    suc.guardaNroSucursal(String.valueOf(nroSucursal));

	    // Esperar a que desaparezca modal de espera
		cp.waitForInvisibilityOfElement(suc.waitingDialog);
		
		// Imprimir todos los nombres de las sucursales listadas para depuracion
		//suc.obtenerListaNombres().forEach(elemento -> System.out.println("Elemento texto: '" + elemento.getText() + "'"));
		
		// Buscar el nombre de la sucursal en las páginas de la lista
		WebElement nombreSucursal = suc.buscarElementoEnPaginas(nombreSucursalCrear);;
		
		if (nombreSucursal != null) {
	        Assert.assertTrue(true);
	        //System.out.println("Sucursal creada: " + nombreSucursal.getText() + "\n");
	        
	     // Verificar que la dirección de la sucursal creada es la correcta
	        boolean direccionCorrecta = suc.verificaDireccionSucursal(nombreSucursalCrear, direccionEsperada.trim());
	        Assert.assertTrue(direccionCorrecta, "La direccion de la sucursal creada es incorrecta.");
	    } else {
	        Assert.fail("La sucursal no fue creada o encontrada");
	        //System.out.println("Sucursal no creada\n");
	    }
		
		// Limpia el formulario antes de salir del metodo
		suc.btnCancelar.click();

	}
	
	// Valida la correcta creacion y modificacion del campo Visibilidad convenio // 
	@Test(priority = 8)
	public void visibilidadConvenio () throws InterruptedException {
		// Obtener el número de sucursal del archivo de propiedades
	    int nroSucursal = Integer.parseInt(suc.obtieneNroSucursal());
	    
		// Define los valores a utilizar para la creacion de la sucursal
	    String nombreSucursalCrear = "SUCURSAL " + nroSucursal;
		String direccionCrear = "cerro aguja 0379";
		String convenio = "001";
		
		//System.out.println("Sucursal a crear: " + nombreSucursalCrear + "\n");

		// Completa el formulario
		suc.ingresarNombreFormulario(nombreSucursalCrear);
		suc.seleccionaCiudad(1);
		suc.seleccionaConvenio(convenio);
		suc.seleccionaDireccion(direccionCrear, 1);
		
		// Obtiene el valor del campo convenio antes de crear
		String convenioSeleccionado = suc.selectConvenio.getAttribute("title");
		
		// Crea
		suc.btnCrear.click();
		
		// Incrementar el número de sucursal para el próximo uso
	    nroSucursal++;
	    
	    // Guardar el nuevo número de sucursal en el archivo de propiedades
	    suc.guardaNroSucursal(String.valueOf(nroSucursal));
		
		// Esperar a que desaparezca modal de espera
		cp.waitForInvisibilityOfElement(suc.waitingDialog);
		
		// Busca la sucursal creada filtrando por el nombre y la clickea
		suc.filtraPorNombre(nombreSucursalCrear);
		suc.primeraSucursal.click();
		
		// Vuelve a obtener el valor del campo convenio
		String convenioCreado = suc.selectConvenio.getAttribute("title");
		
		// Comprueba que el valor del campo antes y despues de crear sea el mismo
		if (convenioSeleccionado.trim().equalsIgnoreCase(convenioCreado.trim())) {
			Assert.assertTrue(true);
		}else {
			Assert.fail("El convenio seleccionado en formulario no corresponde con lo creado");
			//System.out.println("El convenio seleccionado en formulario no corresponde con lo creado");
		}
		
		// Comienzo de prueba de modificacion // 
		
		// Limpia el formulario y vuelve a seleccionar la sucursal que se creo 
		Thread.sleep(250);
		suc.btnCancelar.click();
		suc.primeraSucursal.click();
		
		// Define el nuevo convenio a utilizar, luego lo busca y lo selecciona
		convenio = "003";
		suc.seleccionaConvenio(convenio);
		
		// Actualiza la sucursal luego de seleccionar el nuevo convenio
		suc.btnCrear.click();
		
		// Esperar a que desaparezca modal de espera
		cp.waitForInvisibilityOfElement(suc.waitingDialog);
		
		// Vuelve a seleccionar la sucursal modificada
		suc.primeraSucursal.click();
		
		Thread.sleep(300);
		
		// Genera una lista de las opciones seleccionadas en el campo convenio y la guarda en la variable resultados
		List<WebElement> resultados = suc.obtenerOpcionesConvenio();
		
		// Comprueba que este en la lista el convenio utilizado en la modificacion y guarda el resultado en el booleano encontrado
		boolean encontrado = resultados.stream()
                .anyMatch(elemento -> elemento.getText().trim().contains("003"));
		
		// Limpia el formulario antes de salir del metodo
		suc.btnCancelar.click();

		// Comprueba "encontrado" es true, si es false devuelve un mensaje de error
		Assert.assertTrue(encontrado, "No se encontraron resultados en la grilla para la sucursal buscada: " + convenio);
		
	}
	
	// Testea la modificacion de los campos // 
	@Test(priority = 9)
	public void modificarSucursal() throws InterruptedException {
		// Crear un objeto para ejecutar JavaScript
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		
		// Obtener el número de modificacion del archivo de propiedades
	    int nroModif = Integer.parseInt(suc.obtieneNroModif());
	    
	    // Obtener el número de sucursal del archivo de propiedades
	    int nroSucursal = Integer.parseInt(suc.obtieneNroSucursal());
	    
	// CREACION DE SUCURSAL (Se crea una sucursal exclusiva para la modificacion, para mas fiabilidad en la prueba)
	    
	    String nombreSucursalCrear = "SUCURSAL " + nroSucursal;
		String direccionCrear = "cerro aguja 0379";
		
		// Completa los campos del formulario
		suc.ingresarNombreFormulario(nombreSucursalCrear);
		suc.seleccionaCiudad(1);
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
		suc.primeraSucursal.click();
		
		// Guarda los valores antes de modificar
		String nombreActual = (String) js.executeScript("return arguments[0].value;", suc.campoNombre);
		boolean estadoActual = suc.obtenerEstado();
		String ciudadActual = suc.selectCiudad.getAttribute("title");
		String direccionActual = (String) js.executeScript("return arguments[0].value;", suc.campoDireccion);
		
		// Define los valores a utilizar para modificar
		String nombreNuevo = "SUCURSAL MODIF " + nroModif;
		String direccionNueva;
		String ciudadNueva;
		boolean estadoNuevo;

		// Cambia nombre
		suc.ingresarNombreFormulario(nombreNuevo);
		
		// Cambia al estado contrario del actual
		if (estadoActual) {
			suc.marcaCheckInactivo.click();
		}else {
			suc.marcaCheckActivo.click();
		}
		
		// Obtiene el estado despues de cambiarlo y lo guarda en la variable
		estadoNuevo = suc.obtenerEstado();
		
		// Cambia direccion  la guarda en la variable
		suc.seleccionaDireccion("camino real lo barnechea", 1);
		direccionNueva = (String) js.executeScript("return arguments[0].value;", suc.campoDireccion);
		
		// Cambia Ciudad y la guarda en la variable
		suc.seleccionaCiudad(2);
		ciudadNueva = suc.selectCiudad.getAttribute("title");
		
		// Confirma la modificacion
		suc.btnCrear.click();
		
		// Aumenta el nro de modificacion
		nroModif++;
		
		// Guarda el nro de modificacion para no repetirlo en su proximo uso
		suc.guardaNroModif(String.valueOf(nroModif));
		
		// Esperar a que desaparezca modal de espera
		cp.waitForInvisibilityOfElement(suc.waitingDialog);
		
		// Busca la sucursal en base al nuevo nombre que se le otorgo en la modificacion (y controla el caso de que el filtro no trae resultado)
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
		
		// Hace click en la sucursal y espera 250 milisegundos para dar tiempo a cargar los datos
		suc.primeraSucursal.click();
		Thread.sleep(250);
		
		// Validacion cambio de nombre
		if (nombreActual.trim() != nombreNuevo.trim()) {
			Assert.assertTrue(true);
		}else {
			Assert.fail("El nombre de la sucursal no cambio correctamente");
		}
		
		// Validacion cambio de estado
		if (estadoActual != estadoNuevo) {
			Assert.assertTrue(true);
		}else {
			Assert.fail("El estado de la sucursal no cambio correctamente");
		}
		
		// Validacion cambio de ciudad
		if (ciudadActual.trim() != ciudadNueva.trim()) {
			Assert.assertTrue(true);
		}else {
			Assert.fail("La ciudad de la sucursal no cambio correctamente");
		}
		
		// Validacion cambio de direccion
		if (direccionActual.trim() != direccionNueva.trim()) {
			Assert.assertTrue(true);
		}else {
			Assert.fail("La ciudad de la sucursal no cambio correctamente");
		}
		
		// Presiona nuevamente la sucursal y obtiene el estado actual
		suc.primeraSucursal.click();
		estadoActual = suc.obtenerEstado();
		
		// Vuelve a cambiar al estado contrario
		if (estadoActual) {
			suc.marcaCheckInactivo.click();
		}else {
			suc.marcaCheckActivo.click();
		}
		
		// Confirma modificacion
		suc.btnCrear.click();
		
		// Esperar a que desaparezca modal de espera
		cp.waitForInvisibilityOfElement(suc.waitingDialog);
		
		// Vuelve a seleccionar la sucursal y obtiene su estado
		suc.primeraSucursal.click();
		estadoNuevo = suc.obtenerEstado();
		
		// Validacion cambio de estado
		if (estadoActual != estadoNuevo) {
			Assert.assertTrue(true);
		}else {
			Assert.fail("El estado de la sucursal no cambio correctamente");
		}
	}
	
	// Testea el correcto funcionamiento del filtro nombre
	@Test(priority = 10)
	public void filtroNombre() throws InterruptedException {
	    // Obtener el número de sucursal del archivo de propiedades
	    int nroSucursal = Integer.parseInt(suc.obtieneNroSucursal());
	    
	    // Si el nro sucursal es mayor o igual a 3, le resta 1, esto con el fin estar seguro que la busqueda traera resultado, ya que el ultimo numero de sucursal antes de la prueba se reemplaza por el de modificacion
	    if (nroSucursal>=3) {
		    nroSucursal = nroSucursal - 1;
	    }
	    
	    // Define la sucursal a buscar
	    String sucBuscar = "SUCURSAL " + nroSucursal;
	    
	    // Busca la sucursal
	    suc.filtraPorNombre(sucBuscar);

	    // Le da 300 milisegundos para obtener resultados
	    Thread.sleep(300);
	    
	    // Validar si el filtro trajo resultados a la grilla
	    List<WebElement> resultados = suc.obtenerListaNombres();
	    
	    // Guarda true si encontro la sucursal y false si no la encontro
	    boolean encontrado = resultados.stream()
	                                   .anyMatch(elemento -> elemento.getText().trim().equalsIgnoreCase(sucBuscar));

	    // Si "encontrado" es true, pasa la prueba, si es false manda mensaje indicando el problema
	    Assert.assertTrue(encontrado, "No se encontraron resultados en la grilla para la sucursal buscada: " + sucBuscar);
	    
	    // Limpia el campo filtro y busca antes de salir del metodo
	    suc.filtroNombre.clear();
	    suc.btnBuscar.click();
	}
	

	
}
