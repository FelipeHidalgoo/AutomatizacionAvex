package valuesite.pageobjects;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import valuesite.componentesreusables.ComponentesReusables;

public class AreaCliente extends ComponentesReusables {

	WebDriver driver;

	private static final String PROPERTIES_FILE = "config.properties";
	private static final String NUMERO_AREA_KEY = "numeroAreaCli";
	private static final String NUMERO_AREA2_KEY = "numeroArea2Cli";
	private static final String NUMERO_MODIFICACION_KEY = "numeroModificacionCli";

	public AreaCliente(WebDriver driver) {

		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	// PAGE FACTORY

	// Pestaña de adminstracion en navBar
	@FindBy(xpath = "//a[normalize-space()='Administración']")
	WebElement tabAdministracion;

	// Pestaña de area cliente en navBar
	@FindBy(xpath = "//a[normalize-space()='Área']")
	WebElement tabAreaCliente;

	// Filtro nombre de area
	@FindBy(id = "txtNombreFind")
	WebElement filtroNombre;

	// Campo nombre area
	@FindBy(id = "txtNombreGrupoPasajero")
	public WebElement campoNombreArea;

	// Filtro estado (Select)
	@FindBy(xpath = "(//input[@data-activates='select-options-slcEstadoFind'])[1]")
	WebElement selectEstados;

	// Filtro estado (Opcion Todos)
	@FindBy(css = "div[class='bg-filtros mx-0 '] li:nth-child(1)")
	WebElement estadoTodos;

	// Filtro estado (Opcion Activo)
	@FindBy(css = "div[class='bg-filtros mx-0 '] li:nth-child(2)")
	WebElement estadoActivo;

	// Filtro estado (Opcion Activo)
	@FindBy(css = "div[class='bg-filtros mx-0 '] li:nth-child(3)")
	WebElement estadoInactivo;

	// Modal Por favor Espere
	// @FindBy(xpath = "//div[@class='modal fade right show']")
	@FindBy(css = "sideModalTR3")
	WebElement waitingDialog;

	// Boton buscar
	@FindBy(id = "btnBuscar")
	WebElement btnBuscar;

	// Boton limpiar
	@FindBy(id = "btnLimpiar")
	WebElement btnLimpiar;

	// Boton crear
	@FindBy(id = "btnCrear")
	WebElement btnCrear;

	// Boton cancelar
	@FindBy(id = "btnCancelar")
	WebElement btnCancelar;

	// Check estado area (Sirve para verificar el estado actual del check, marcado o
	// desmarcado)
	@FindBy(id = "rdoActivo1")
	public WebElement checkEstado;

	// Span estado area (Tambien hace alusion al check, pero sirve para clickearlo)
	@FindBy(css = ".checkmark-ds.ml-3")
	public WebElement marcarCheckEstado;

	// Select cliente (formulario)
	@FindBy(xpath = "//input[@data-activates='select-options-slcCliente']")
	WebElement selectCliente;

	// Select cliente / Opcion seleccione (formulario)
	@FindBy(xpath = "//ul[@id='select-options-slcCliente']/li[1]")
	WebElement selectClienteSeleccione;

	// Select cliente / Primer cliente de lista (formulario)
	@FindBy(xpath = "//ul[@id='select-options-slcCliente']/li[2]")
	WebElement selectClientePrimero;

	// Select cliente / Segundo cliente de lista (formulario)
	@FindBy(xpath = "//ul[@id='select-options-slcCliente']/li[3]")
	WebElement selectClienteSegundo;

	// Error nombre obligatorio
	@FindBy(css = ".alert-danger")
	WebElement errorCampoObligatorio;

	// Boton primera pagina (Paginacion)
	@FindBy(xpath = "//a[normalize-space()='Primera']")
	WebElement primeraPagina;

	// Boton anterior pagina (Paginacion)
	@FindBy(xpath = "//a[normalize-space()='Anterior']")
	WebElement anteriorPagina;

	// Boton anterior pagina (Paginacion)
	@FindBy(xpath = "//a[normalize-space()='Siguiente']")
	static WebElement siguientePagina;

	// Boton anterior pagina (Paginacion)
	@FindBy(xpath = "//a[normalize-space()='Ultima']")
	WebElement ultimaPagina;

	// Primer area de la lista
	// @FindBy(xpath = "//tbody[@class='tb-bss-pointer']/tr[1]/td[1]") (Mas seguro)
	@FindBy(css = "tbody.tb-bss-pointer > tr:first-of-type > td:first-of-type") // (Mas rapido)
	static WebElement primerArea;

	// Columna nombre (grilla)
	@FindBy(id = "//tbody[@class='tb-bss-pointer']/tr/td[1]")
	List<WebElement> columnaNombre;

	// Columna cliente (grilla)
	@FindBy(id = "//tbody[@class='tb-bss-pointer']/tr/td[2]")
	List<WebElement> columnaCliente;

	// Columna estado (grilla)
	@FindBy(id = "//tbody[@class='tb-bss-pointer']/tr/td[3]")
	List<WebElement> columnaEstado;

	// Lista de areas (Pagina actual)
	@FindBy(xpath = "//tbody/tr")
	List<WebElement> listaAreas;

	// Lista de areas (Pagina actual)
	By productosBy = By.xpath("//tbody/tr");

	// Error campo obligatorio
	By errorCampoObligatorioBy = By.cssSelector(".alert-danger");

	// Mensaje porfavor espere
	By waitingDialogBy = By.cssSelector("sideModalTR3");

	// PAGE FACTORY

	// ComponentesReusables componentesReusables = new ComponentesReusables(driver);

	// Metodo para entrar al mantenedor de area y buscar un cliente
	public void ingresoMantenedorArea() {
		tabAdministracion.click();
		tabAreaCliente.click();
		// Espera a que desaparezca el mensaje Por favor espere
		waitForInvisibilityOfElement(waitingDialog);
	}

	// Comprueba que el sistema no permite crear area sin ingresar un nombre a esta
	public void errorNombreObligatorio() throws InterruptedException {
		// waitForWebElementToBeClickable(btnCrear);
		
		// Comprueba si el select cliente esta presente, si lo esta, selecciona un cliente
		if(elementoPresente(selectCliente)){
			selectCliente.click();
			waitForWebElementToBeClickable(selectClientePrimero);
			selectClientePrimero.click();
		}
		
		btnCrear.click();

		// Declara el mensaje de error en la variable errorCampoObligatorio
//		errorCampoObligatorio = driver
//				.findElement(By.xpath("//div[@class='alert alert-danger animated fadeInDown']"));
		
		// Espera a que aparezca el mensaje de error
		try {
			//waitForWebElementToAppear(errorCampoObligatorio);
			Thread.sleep(500);
		} catch (TimeoutException e) {
			// Si no aparece en el tiempo de espera explicito establecido,
			// entonces manda mensaje para indicar el problema
			System.out.println("El mensaje de error de campo obligatorio no aparecio en el tiempo limite");
		}
		

		// Obtiene el texto del mensaje de error y valida si es el mensaje correspondiente
		String msjError = errorCampoObligatorio.getText();
		if (msjError.equals("Error\n· Campo Nombre Área Obligatorio.")) {
			System.out.println("El campo es obligatorio :)");
			Assert.assertTrue(true);
		} else {
			System.out.println("No toma en cuenta la obligatoriedad del campo");
			Assert.assertTrue(false);
		}

		// Limpia formulario antes de salir del metodo
		btnCancelar.click();
	}

	// Comprueba que el sistema no permite crear area sin seleccionar un cliente
	// Controlado para que solo se ejecute en caso de que se muestre el select (grupo de clientes)
public void errorClienteObligatorio() throws InterruptedException {
		
		// Comprueba si el select cliente esta presente, si no lo esta, sale del metodo
		if(!elementoPresente(selectCliente)){
			return;
		}
		
		errorCampoObligatorio=null;
		
		// Manda un nombre al campo nombre y presiona boton crear
		waitForWebElementToBeClickable(campoNombreArea);
		campoNombreArea.clear();
		campoNombreArea.sendKeys("Sin Informacion");
		// waitForWebElementToBeClickable(btnCrear);
		btnCrear.click();
		
		//Thread.sleep(500);
		// Declara el mensaje de error en la variable errorCampoObligatorio
		errorCampoObligatorio = driver
				.findElement(By.cssSelector(".alert-danger + .alert-danger"));
		
		// Espera a que aparezca el mensaje de error
		try {
			waitForWebElementToAppear(errorCampoObligatorio);
		} catch (TimeoutException e) {
			// Si no aparece en el tiempo de espera explicito establecido,
			// entonces manda mensaje para indicar el problema
			System.out.println("El mensaje de error de campo obligatorio no aparecio en el tiempo limite");
		}

		// Obtiene el texto del mensaje de error y valida si es el mensaje correspondiente
		String msjError = errorCampoObligatorio.getText();
		if (msjError.equals("Error\n· Campo Cliente Área Obligatorio.")) {

			Assert.assertTrue(true);
		} else {

			Assert.assertTrue(false);
		}

		// Limpia formulario antes de salir del metodo
		btnCancelar.click();
	}

	// Obtiene un numero de el archivo "config.properties" para luego ingresarlo en
	// el nombre del area, de esta manera no repetir nombres (Se utiliza para el
	// area ACTIVA)
	public static int obtenerNumeroArea() {
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
			properties.load(fis);
		} catch (IOException e) {
			return 1; // Si ocurre un error, inicializa en 1
		}
		return Integer.parseInt(properties.getProperty(NUMERO_AREA_KEY, "1"));
	}

	// Obtiene un numero de el archivo "config.properties" para luego ingresarlo en
	// el nombre del area, de esta manera no repetir nombres (Se utiliza para el
	// area INACTIVA)
	public static int obtenerNumeroArea2() {
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
			properties.load(fis);
		} catch (IOException e) {
			return 1; // Si ocurre un error, inicializa en 1
		}

		return Integer.parseInt(properties.getProperty(NUMERO_AREA2_KEY, "1"));
	}

	// Obtiene un numero de el archivo "config.properties" para luego ingresarlo en
	// el nombre del area, de esta manera no repetir nombres (Se utiliza para la
	// MODIFICACION)
	public static int obtenerNumeroModificacion() {
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
			properties.load(fis);
		} catch (IOException e) {
			return 1; // Si ocurre un error, inicializa en 1
		}

		return Integer.parseInt(properties.getProperty(NUMERO_MODIFICACION_KEY, "1"));
	}

	// Incrementa el numero de area y lo guarda para la proxima iteracion (Se
	// utiliza para el area ACTIVA)
	public static void incrementarNumeroArea() {
		int numeroArea = obtenerNumeroArea();
		numeroArea++;
		guardarNumeroArea(numeroArea);
	}

	// Incrementa el numero de area y lo guarda para la proxima iteracion (Se
	// utiliza para el area INACTIVA)
	public static void incrementarNumeroArea2() {
		int numeroArea2 = obtenerNumeroArea2();
		numeroArea2++;
		guardarNumeroArea2(numeroArea2);
	}

	public static void incrementarNumeroModificacion() {
		int numeroModificacion = obtenerNumeroModificacion() + 1;
		guardarNumeroModificacion(numeroModificacion);
	}

	// Hace un guardado de los numeros utilizados para la creacion de areas (Se
	// utiliza para el area ACTIVA)
	public static void guardarNumeroArea(int numeroArea) {
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		properties.setProperty(NUMERO_AREA_KEY, String.valueOf(numeroArea));
		properties.setProperty(NUMERO_AREA2_KEY, obtenerNumeroArea2() + ""); // Mantener el valor de numeroArea2
		properties.setProperty(NUMERO_MODIFICACION_KEY, obtenerNumeroModificacion() + ""); // Mantener el valor de
																							// numeroModificacion

		try (FileOutputStream fos = new FileOutputStream(PROPERTIES_FILE)) {
			properties.store(fos, "Configuración de números de área");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Hace un guardado de los numeros utilizados para la creacion de areas (Se
	// utiliza para el area INACTIVA)
	public static void guardarNumeroArea2(int numeroArea2) {
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		properties.setProperty(NUMERO_AREA2_KEY, String.valueOf(numeroArea2));
		properties.setProperty(NUMERO_AREA_KEY, obtenerNumeroArea() + ""); // Mantener el valor de numeroArea
		properties.setProperty(NUMERO_MODIFICACION_KEY, obtenerNumeroModificacion() + ""); // Mantener el valor de
																							// numeroModificacion

		try (FileOutputStream fos = new FileOutputStream(PROPERTIES_FILE)) {
			properties.store(fos, "Configuración de números de área");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Hace un guardado de los numeros utilizados para la modificacion de areas
	public static void guardarNumeroModificacion(int numeroModificacion) {
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		properties.setProperty(NUMERO_MODIFICACION_KEY, String.valueOf(numeroModificacion));
		properties.setProperty(NUMERO_AREA_KEY, obtenerNumeroArea() + ""); // Mantener el valor de numeroArea
		properties.setProperty(NUMERO_AREA2_KEY, obtenerNumeroArea2() + ""); // Mantener el valor de numeroArea2

		try (FileOutputStream fos = new FileOutputStream(PROPERTIES_FILE)) {
			properties.store(fos, "Configuración de números de modificación");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Busca el area ACTIVA creada en todas las paginas de la paginacion
	public boolean buscarAreaEnTodasLasPaginas(WebDriver driver, String nombreArea) {

		boolean hayMasPaginas = true;

		while (hayMasPaginas) {
			
			// Buscar el área en la página actual
			try {
				WebElement areaCreada = driver.findElement(By.xpath("//td[normalize-space()='" + nombreArea + "']"));
				if (areaCreada.isDisplayed()) {
					return true; // Área encontrada en la página actual
				}
			} catch (NoSuchElementException e) {
				// Área no encontrada en la página actual
			}

			// Intentar ir a la siguiente página
			try {
				WebElement botonUltima = null;

				// Intentar encontrar el botón "Última" (si existe)
				try {
					botonUltima = driver.findElement(By.xpath("//a[normalize-space()='Última']"));
				} catch (NoSuchElementException ignored) {
					// Si no se encuentra el botón "Última", continuar sin hacer nada
				}

				if (botonUltima != null && botonUltima.isDisplayed()) {
					// Si encuentra boton ultima, lo clickea
					waitForWebElementToBeClickable(botonUltima);
					botonUltima.click();
				} else {
					// Si no encuentra boton ultima, clickea siguiente
					waitForWebElementToBeClickable(siguientePagina);
					siguientePagina.click();
				}
			} catch (NoSuchElementException e) {
				
				// Si no hay mas paginas, devuelve false
				System.out.println("No hay más páginas en las que buscar");
				hayMasPaginas = false;
			}
		}

		return false; // Área no encontrada en ninguna página
	}

	// Busca el area INACTIVA creada en todas las paginas de la paginacion
	public boolean buscarAreaEnTodasLasPaginas2(WebDriver driver, String nombreArea2) {

		boolean hayMasPaginas = true;

		while (hayMasPaginas) {
			
			// Buscar el área en la página actual
			try {
				WebElement areaCreada = driver.findElement(By.xpath("//td[normalize-space()='" + nombreArea2 + "']"));
				waitForWebElementToAppear(areaCreada);
				if (areaCreada.isDisplayed()) {
					return true; // Área encontrada en la página actual
				}
			} catch (NoSuchElementException e) {
				// Área no encontrada en la página actual
			}

			// Intentar ir a la siguiente página
			try {

				WebElement botonUltima = null;

				// Intentar encontrar el botón "Última" (si existe)
				try {
					botonUltima = driver.findElement(By.xpath("//a[normalize-space()='Última']"));
				} catch (NoSuchElementException ignored) {
					// Si no se encuentra el botón "Última", continuar sin hacer nada
				}

				if (botonUltima != null && botonUltima.isDisplayed()) {
					// Si encuentra boton ultima, lo clickea
					waitForWebElementToBeClickable(botonUltima);
					botonUltima.click();
				} else {
					// Si no encuentra boton ultima, clickea siguiente
					waitForWebElementToBeClickable(siguientePagina);
					siguientePagina.click();
				}
			} catch (NoSuchElementException e) {
				System.out.println("No hay más páginas en las que buscar");
				hayMasPaginas = false;
			}
		}

		return false; // Área no encontrada en ninguna página
	}

	// Crea un area activa en el mantenedor y la busca para comprobar si fue creada
	public void creaAreaActiva() throws InterruptedException {

		// Guarda el "nombreArea" a ocupar y se lo manda al campo Nombre del formulario
		int numeroArea = obtenerNumeroArea();
		String nombreArea = "AREA ACTIVA " + String.format("%03d", numeroArea);
		waitForWebElementToBeClickable(campoNombreArea);
		campoNombreArea.sendKeys(nombreArea);

		// Verifica si el select cliente esta presente, antes de crear el area
		if (elementoPresente(selectCliente)) {
			selectCliente.click();
			waitForWebElementToBeClickable(selectClientePrimero);
			selectClientePrimero.click();
			btnCrear.click();
		} else {
			btnCrear.click();
		}

		// Thread.sleep(500);
		
		// Espera a que desaparezca el mensaje por favor espere
		waitForInvisibilityOfElement(waitingDialog);
		// Incrementa el número del área para la siguiente iteración
		
		// Incrementa el numero de area adjunto al nombre para no repetirlo en la siguiente iteracion
		incrementarNumeroArea();

		// Guarda el resultado del metodo "buscarAreaEnTodasLasPaginas"
		boolean areaEncontrada = buscarAreaEnTodasLasPaginas(driver, nombreArea);

		// Si la encuentra en la pagina actual, devuelve un Assert(true) y se devuelve a la primera pagina en caso de no estar en ella
		// Si no encuentra el area, devuelve un Assert(false)
		if (areaEncontrada) {
			// System.out.println("Éxito!! El área activa se creó correctamente \n");
			Assert.assertTrue(true);

			// Volver a primera pagina o anterior si no hay "Primera"
			try {
				if (primeraPagina.isDisplayed()) {
					Thread.sleep(700);
					// waitForWebElementToBeClickable(primeraPagina);
					primeraPagina.click();
				} else if (anteriorPagina.isDisplayed()) {
					Thread.sleep(700);
					// waitForWebElementToBeClickable(anteriorPagina);
					anteriorPagina.click();
				} else {
					// Continua con el proceso
				}
			} catch (NoSuchElementException e) {
				// Continua con el proceso si no encuentra "Primera" ni "Anterior"
			}
		} else {
			// System.out.println("Incorrecto!! El área activa no se encontro en ninguna
			// pagina, por lo que no fue creada \n");
			Assert.assertTrue(false);
		}
	}

	// Crea un area inactiva en el mantenedor y la busca para comprobar si fue creada
	public void creaAreaInactiva() throws InterruptedException {

		// Crear area INACTIVA
		int numeroArea2 = obtenerNumeroArea2();
		String nombreArea2 = "AREA INACTIVA " + String.format("%03d", numeroArea2);
		waitForInvisibilityOfElement(waitingDialog);
		campoNombreArea.sendKeys(nombreArea2);
		marcarCheckEstado.click();
		
		if (elementoPresente(selectCliente)) {
			selectCliente.click();
			waitForWebElementToBeClickable(selectClientePrimero);
			selectClientePrimero.click();
			btnCrear.click();
		} else {
			btnCrear.click();
		}
		
		waitForInvisibilityOfElement(waitingDialog);

		// Incrementa el número del área para la siguiente iteración
		incrementarNumeroArea2();

		selectEstados.click();
		estadoTodos.click();
		btnBuscar.click();

		// waitForInvisibilityOfElement(waitingDialog);

		boolean areaEncontrada2 = buscarAreaEnTodasLasPaginas2(driver, nombreArea2);

		// System.out.println("------------ Creacion de Área inactiva ------------ \n");

		if (areaEncontrada2) {
			// System.out.println("Éxito!! El área inactiva se creó correctamente \n");
			Assert.assertTrue(true);

			// Volver a primera pagina o anterior si no hay "Primera"
			try {
				if (primeraPagina.isDisplayed()) {
					Thread.sleep(700);
					primeraPagina.click();
				} else if (anteriorPagina.isDisplayed()) {
					Thread.sleep(700);
					anteriorPagina.click();
				} else {
					// Continua con el proceso
				}
			} catch (NoSuchElementException e) {
				// Continua con el proceso si no encuentra "Primera" ni "Anterior"
			}
		} else {
			// System.out.println("Incorrecto!! El área inactiva no se encontro en ninguna
			// pagina, por lo que no fue creada \n");
			Assert.assertTrue(false);
		}
	}

	// Modifica el nombre y el estado de un area, y hace la verificacion de que el
	// cambio de ambas cosas fue exitoso
	public void modificaArea() throws InterruptedException {

		Thread.sleep(700);
		// waitForWebElementToBeClickable(primerArea);
		
		// Declara el nombre con el que se va a modificar al area
		String nuevoNombre = "MODIFICACION AUTOMATICA " + obtenerNumeroModificacion();

		try {
			
			// Espera a que la primer area de la lista sea clickeable y la clickea
			waitForWebElementToBeClickable(primerArea);
			primerArea.click();
		} catch (StaleElementReferenceException e) {
			
			// Elemento obsoleto, intentar ubicarlo nuevamente
			primerArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[1]/td[1]"));
			waitForWebElementToBeClickable(primerArea);
			primerArea.click(); // Intentar hacer clic nuevamente
		}
		Thread.sleep(350);

		// Luego de seleccionar, limpia el nombre e ingresa el "nuevoNombre"
		campoNombreArea.clear();
		campoNombreArea.sendKeys(nuevoNombre);
		btnCrear.click();
		waitForInvisibilityOfElement(waitingDialog);

		// Obtiene el nombre que modificamos al primer area de la lista
		String nombreareaNuevo = "";

		// Obtiene el texto del primer area despues de modificar y lo guarda en
		// "nombreareaNuevo"
		try {
			// waitForWebElementToBeClickable(primerArea);
			waitForWebElementToAppear(primerArea);
			nombreareaNuevo = primerArea.getText();
		} catch (StaleElementReferenceException e) {
			// Elemento obsoleto, intentar ubicarlo nuevamente
			primerArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[1]/td[1]"));
			waitForWebElementToAppear(primerArea);
			nombreareaNuevo = primerArea.getText();
		}

		// Incrementar el número de modificación
		incrementarNumeroModificacion();

		// Verifica si el nombre se modifico correctamente comparando "nuevoNombre" con
		// "nombreareaNuevo"
		if (nombreareaNuevo.equals(nuevoNombre)) {
			// System.out.println("Exito!! El nombre del area se modifico correctamente
			// \n");
			Assert.assertTrue(true);

		} else {
			// System.out.println("Incorrecto!! El nombre del area no se modifico
			// correctamente \n");
			Assert.assertTrue(false);
		}

		// Cambia el estado del area 2 veces, de activo a inactivo y de inactivo a
		// activo o viceversa, y comprueba que el cambio fue exitoso
		for (int i = 0; i < 2; i++) {

			try {
				waitForWebElementToBeClickable(primerArea);
				primerArea.click();
			} catch (StaleElementReferenceException e) {
				// Elemento obsoleto, intentar ubicarlo nuevamente
				primerArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[1]/td[1]"));
				waitForWebElementToAppear(primerArea);
				primerArea.click(); // Intentar hacer clic nuevamente
			}

			Thread.sleep(350);

			// Obtener el estado actual del área (activo o inactivo)
			// WebElement inputActivo = driver.findElement(By.id("rdoActivo1"));
			boolean estadoActualActivo = checkEstado.isSelected();

			if (estadoActualActivo) {
				// Si el estado actual es activo, entonces cambiar a inactivo
				marcarCheckEstado.click();
			} else {
				// Si el estado actual es inactivo, entonces cambiar a activo
				marcarCheckEstado.click();
			}

			btnCrear.click();
			// Thread.sleep(500);
			waitForInvisibilityOfElement(waitingDialog);

			int nroModif = obtenerNumeroModificacion() - 1;
			filtroNombre.clear();
			// waitForWebElementToBeClickable(filtroNombre);
			filtroNombre.sendKeys("MODIFICACION AUTOMATICA " + nroModif);
			selectEstados.click();
			waitForWebElementToBeClickable(estadoTodos);
			estadoTodos.click();
			btnBuscar.click();
			waitForInvisibilityOfElement(waitingDialog);

			// Obtiene el nuevo estado del área después de cambiarlo
			try {
				waitForWebElementToBeClickable(primerArea);
				primerArea.click();
			} catch (StaleElementReferenceException e) {
				// Elemento obsoleto, intentar ubicarlo nuevamente
				primerArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[1]/td[1]"));
				waitForWebElementToBeClickable(primerArea);
				primerArea.click(); // Intentar hacer clic nuevamente
			}
			Thread.sleep(350);

			boolean nuevoEstadoActivo = checkEstado.isSelected();

			// Mensaje de depuración
			// System.out.println("------------ Cambio de estado de Área ------------ \n");
			System.out.println("Antiguo estado: " + estadoActualActivo);
			System.out.println("Nuevo estado: " + nuevoEstadoActivo + "\n");

			// Verifica si el estado actual es diferente al estado anterior
			if (estadoActualActivo != nuevoEstadoActivo) {
				// System.out.println("\nÉxito!! El estado del área se modificó correctamente
				// \n");
				Assert.assertTrue(true);
			} else {
				// System.out.println("\nError!! El estado del área no se modificó correctamente
				// \n");
				Assert.assertTrue(false);
			}

		}
		campoNombreArea.clear();

	}

	// Obtiene lista de areas presentes en la grilla actual
	public List<WebElement> obtenerListaAreas() {

		// Retorna la lista de areas presentes en la vista
		waitForElementToAppear(productosBy);
		return listaAreas;
	}

	// Prueba del filtro nombre area
	public void filtroNombreArea() throws InterruptedException {

		Actions a = new Actions(driver);

		// Proporciona un nombre de area para la prueba
		String nombreAreaFiltro = "BUSCA AREA " + obtenerNumeroModificacion();

		// Crea el area con el nombre definido
		campoNombreArea.clear();
		campoNombreArea.sendKeys(nombreAreaFiltro);
		
		// Verifica si el select cliente esta presente, para considerarlo o no en la creacion del area
		if (elementoPresente(selectCliente)) {
			selectCliente.click();
			waitForWebElementToBeClickable(selectClientePrimero);
			selectClientePrimero.click();
			btnCrear.click();
		} else {
			btnCrear.click();
		}
		
		waitForInvisibilityOfElement(waitingDialog);

		incrementarNumeroModificacion();

		// Manda variable nombreArea al filtro de nombre
		filtroNombre.clear();
		filtroNombre.sendKeys(nombreAreaFiltro);

		// Realiza la busqueda mediante tecla enter
		a.moveToElement(filtroNombre).click().keyDown(Keys.ENTER).build().perform();

		// Thread.sleep(850);
		// waitForInvisibilityOfElement(waitingDialog);
		WebElement area = obtenerListaAreas().stream()
				.filter(elemento -> elemento.findElement(By.cssSelector("td")).getText().contains(nombreAreaFiltro))
				.findFirst().orElse(null);

		// Verifica si obtuvo resultados con la busqueda mediante tecla ENTER
		if (area != null) {
			Assert.assertTrue(true);
		} else {
			System.out.println("Tecla enter no esta buscando");
			Assert.assertTrue(false);
		}

		btnBuscar.click();
		// waitForInvisibilityOfElement(waitingDialog);
		WebElement area2 = obtenerListaAreas().stream()
				.filter(elemento -> elemento.findElement(By.cssSelector("td")).getText().contains(nombreAreaFiltro))
				.findFirst().orElse(null);

		// Verifica si obtuvo resultados con la busqueda mediante boton Buscar
		if (area2 != null) {
			Assert.assertTrue(true);
		} else {
			System.out.println("Boton buscar no esta buscando");
			Assert.assertTrue(false);
		}
	}

	// Prueba del filtro estado area
	public void filtroEstadoArea() throws InterruptedException {

		// waitForWebElementToAppear(btnLimpiar);
		// waitForWebElementToBeClickable(btnLimpiar);
		
		Thread.sleep(1000);
		
		// Limpia los filtros y filtra por el estado activo
		btnLimpiar.click();
		selectEstados.click();
		estadoActivo.click();
		btnBuscar.click();
		
		// waitForInvisibilityOfElement(waitingDialog);
		Thread.sleep(1000);

		int encontroArea = 0;
		List<WebElement> listaAreas;
		
		// Guarda la lista de areas en la variable 
		listaAreas = obtenerListaAreas();

		// Por cada iteracion, clickea un area y verifica si su estado es activo, lo hace con las 5 primeras
		for (int i = 1; i < Math.min(listaAreas.size(), 6); i++) {

			WebElement areaLista = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[" + i + "]/td[1]"));

			// Comprobacion de areas activas
			try {
				waitForWebElementToBeClickable(areaLista);
				areaLista.click();
			} catch (StaleElementReferenceException e) {
				// Elemento obsoleto, intentar ubicarlo nuevamente
				areaLista = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[" + i + "]/td[1]"));
				waitForWebElementToBeClickable(areaLista);
				areaLista.click(); // Intentar hacer clic nuevamente
			}

			Thread.sleep(300);
//				waitForInvisibilityOfElement(waitingDialog);
//				waitForWebElementToBeClickable(inputActivo);

			if (checkEstado.isSelected()) {
				continue;
			} else {
				encontroArea++;
				System.out.println("Se encontro un area inactiva al buscar areas activas");
			}

		}

		if (encontroArea == 0) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

		// Comprobacion de areas Inactivas
		selectEstados.click();
		estadoInactivo.click();
		btnBuscar.click();
		
		// waitForInvisibilityOfElement(waitingDialog);
		Thread.sleep(1000);

		encontroArea = 0;
		listaAreas = obtenerListaAreas();
		
		// Por cada iteracion, clickea un area y verifica si su estado es inactivo, lo hace con las 5 primeras
		for (int i = 1; i < Math.min(listaAreas.size(), 6); i++) {

			WebElement areaLista = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[" + i + "]/td[1]"));

			// Comprobacion de areas inactivas
			try {
				waitForWebElementToBeClickable(areaLista);
				areaLista.click();
			} catch (StaleElementReferenceException e) {
				// Elemento obsoleto, intentar ubicarlo nuevamente
				areaLista = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[" + i + "]/td[1]"));
				waitForWebElementToBeClickable(areaLista);
				areaLista.click(); // Intentar hacer clic nuevamente
			}

			Thread.sleep(300);
//				waitForInvisibilityOfElement(waitingDialog);
//				waitForWebElementToBeClickable(inputActivo);

			if (checkEstado.isSelected()) {
				// validaEstado = false;
				encontroArea++;
				System.out.println("Se encontro un area activa al buscar areas inactivas");
			}

		}

		if (encontroArea == 0) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

	// Prueba del select cliente (Solo se ejecuta en caso de que este el select)
	public void selectCliente() throws InterruptedException {
		
		// Comprueba si el select cliente esta presente, si no lo esta, no ejecuta la prueba
		if (!elementoPresente(selectCliente)) {
	        return;
	    }
		
		// Refresca la pagina
		driver.navigate().refresh();

		waitForWebElementToBeClickable(campoNombreArea);
		
	// Ingresa un nombre al area, selecciona un cliente, y la crea
		int numeroArea = obtenerNumeroArea();
		String nombreArea = "SELECT CLIENTE " + String.format("%03d", numeroArea);
		waitForInvisibilityOfElement(waitingDialog);
		campoNombreArea.sendKeys(nombreArea);
		selectCliente.click();
		waitForWebElementToBeClickable(selectClientePrimero);
		
		// Extrae el texto del primer area que arroja el select cliente
		String areaSeleccionada = selectClientePrimero.getText();
		selectClientePrimero.click();
		btnCrear.click();

		waitForInvisibilityOfElement(waitingDialog);
		
	// Incrementa el número del área para la siguiente iteración
		incrementarNumeroArea();

		// Limpia el filtro nombre, le pasa el nombre del area creada anteriormente, y busca
		filtroNombre.clear();
		filtroNombre.sendKeys(nombreArea);
		btnBuscar.click();
		
		// Encuentra el primer resultado de la grilla
		WebElement clienteArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[1]/td[2]"));
		
		// Comprueba que la columna cliente sea el mismo cliente que se selecciono en la creacion del area
		if (areaSeleccionada.equals(clienteArea.getText())) {
			System.out.println("Cliente se asigno correctamente");
			Assert.assertTrue(true);
		}else {
			System.out.println("Cliente no fue correctamente asignado");
			Assert.assertTrue(false);
		}
	}
	
	
	// Comprueba si un elemento esta presente en el DOM
	private boolean elementoPresente(WebElement element) {
		
	    // Comprueba si el elemento esta presente, si no lo esta devuelve false
		try {
	        if (element.isDisplayed()) {
		    	return true;
	        }else {
	        	return false;
	        	}
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}

}
