package valuesite.pageobjects;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import valuesite.componentesreusables.ComponentesReusables;

public class AreaRT extends ComponentesReusables {

	WebDriver driver;

	private static final String PROPERTIES_FILE = "config.properties";
	private static final String NUMERO_AREA_KEY = "numeroArea";
	private static final String NUMERO_AREA2_KEY = "numeroArea2";
	private static final String NUMERO_MODIFICACION_KEY = "numeroModificacion";

	// Incializa driver y PageFactory
	public AreaRT(WebDriver driver) {

		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

// PAGE FACTORY

	// Pestaña de adminstracion en navBar
	@FindBy(xpath = "//a[normalize-space()='Administración']")
	WebElement tabAdministracion;

	// Pestaña de area cliente en navBar
	@FindBy(xpath = "//a[normalize-space()='Área Cliente']")
	WebElement tabAreaCliente;

	// Campo buscar cliente (Para abrir buscador)
	@FindBy(xpath = "//span[@class='filter-option pull-left']")
	WebElement campoBuscarCliente;

	// Input buscar cliente (Para realizar la busqueda)
	@FindBy(xpath = "//input[@placeholder='Buscar cliente']")
	WebElement inputBuscarCliente;

	// Para seleccionar el primer resultado de la lista en campo buscar cliente
	@FindBy(xpath = "//div[@class='bs-container btn-group bootstrap-select show-tick open']//li[2]//a[1]//span[1]")
	WebElement seleccionAreaSelect;

	// Boton administrar para realizar la busqueda
	@FindBy(css = "button[onclick=\"admDatosCliente.changeCliente('GrupoPasajero')\"]")
	WebElement botonAdministrar;

	// Campo nombre area
	@FindBy(id = "txtNombreGrupoPasajero")
	WebElement campoNombreArea;

	// Modal Por favor Espere
	@FindBy(css = ".modal-dialog.modal-m")
	WebElement waitingDialog;

	// Boton crear
	@FindBy(id = "btnCrear")
	WebElement btnCrear;
	
	// Boton buscar
	@FindBy(css = ".btn.celeste")
	WebElement btnBuscar;
	
	// Boton crear
	@FindBy(xpath = "//div[@class='body-content']//div[@class='col-sm-3']//div[1]/button[2]")
	WebElement btnLimpiar;

	// Mensaje de error campo nombre obligatorio
	@FindBy(css = "div[role='alert']")
	WebElement errorNombreObligatorio;

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

	// Boton activo
	@FindBy(css = "label[for='rdoActivo1']")
	static WebElement btnActivo;

	// Boton inactivo
	@FindBy(css = "label[for='rdoActivo2']")
	static WebElement btnInactivo;

	// Primer area de la lista
	@FindBy(xpath = "//tbody[@class='tb-bss-pointer']/tr[1]/td[1]")
	static WebElement primerArea;
	
	// Filtro nombre de area
	@FindBy(name = "txtNombreFind")
	WebElement filtroNombre;
	
	// Filtro estado (Select)
	@FindBy(xpath = "//div[@class='btn-group bootstrap-select show-tick all-width']/button[1]")
	WebElement selectEstados;
	
	// Filtro estado (Opcion Estados)
	@FindBy(xpath = "//div[@class='dropdown-menu open']/ul[@class='dropdown-menu inner']/li[1]")
	WebElement filtroEstados;
	
	// Filtro estado (Opcion Activo)
	@FindBy(xpath = "//div[@class='dropdown-menu open']/ul[@class='dropdown-menu inner']/li[2]")
	WebElement filtroActivo;
	
	// Filtro estado (Opcion Inactivo)
	@FindBy(xpath = "//div[@class='dropdown-menu open']/ul[@class='dropdown-menu inner']/li[3]")
	WebElement filtroInactivo;
	
	// Lista de areas (Pagina actual)
	@FindBy (xpath = "//tbody/tr")
	List<WebElement> listaAreas;
	
	// Lista de areas (Pagina actual)
	By productosBy = By.xpath("//tbody/tr");

// PAGE FACTORY	

// METODOS
	
	ComponentesReusables componentesReusables = new ComponentesReusables(driver);
	
	// Metodo para entrar al mantenedor de area y buscar un cliente
	public void ingresoMantenedorArea(String nombreCliente) {
		tabAdministracion.click();
		tabAreaCliente.click();
		campoBuscarCliente.click();
		inputBuscarCliente.sendKeys(nombreCliente);
		seleccionAreaSelect.click();
		botonAdministrar.click();
		waitForInvisibilityOfElement(waitingDialog);
	}

	// Comprueba que el sistema no permite crear area sin ingresar un nombre a esta
	public void errorNombreObligatorio() {
		btnCrear.click();
		componentesReusables.waitForWebElementToAppear(errorNombreObligatorio);
		String msjError = errorNombreObligatorio.getText();
		//System.out.println("------------ Comprobacion de campo Nombre obligatorio ------------ \n");

		if (msjError.contains("Error · Campo Nombre obligatorio .")) {
			
			Assert.assertTrue(true);
		} else {
			
			Assert.assertTrue(false);
		}
	}

	// Obtiene un numero de el archivo "config.properties" para luego ingresarlo en el nombre del area, de esta manera no repetir nombres (Se utiliza para el area ACTIVA)
	public static int obtenerNumeroArea() {
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
			properties.load(fis);
		} catch (IOException e) {
			return 1; // Si ocurre un error, inicializa en 1
		}
		return Integer.parseInt(properties.getProperty(NUMERO_AREA_KEY, "1"));
	}

	// Obtiene un numero de el archivo "config.properties" para luego ingresarlo en el nombre del area, de esta manera no repetir nombres (Se utiliza para el area INACTIVA)
	public static int obtenerNumeroArea2() {
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
			properties.load(fis);
		} catch (IOException e) {
			return 1; // Si ocurre un error, inicializa en 1
		}

		return Integer.parseInt(properties.getProperty(NUMERO_AREA2_KEY, "1"));
	}

	// Obtiene un numero de el archivo "config.properties" para luego ingresarlo en el nombre del area, de esta manera no repetir nombres (Se utiliza para la MODIFICACION)
	public static int obtenerNumeroModificacion() {
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
			properties.load(fis);
		} catch (IOException e) {
			return 1; // Si ocurre un error, inicializa en 1
		}

		return Integer.parseInt(properties.getProperty(NUMERO_MODIFICACION_KEY, "1"));
	}

	// Incrementa el numero de area y lo guarda para la proxima iteracion (Se utiliza para el area ACTIVA)
	public static void incrementarNumeroArea() {
		int numeroArea = obtenerNumeroArea();
		numeroArea++;
		guardarNumeroArea(numeroArea);
	}

	// Incrementa el numero de area y lo guarda para la proxima iteracion (Se utiliza para el area INACTIVA)
	public static void incrementarNumeroArea2() {
		int numeroArea2 = obtenerNumeroArea2();
		numeroArea2++;
		guardarNumeroArea2(numeroArea2);
	}
	
	
	public static void incrementarNumeroModificacion() {
	       int numeroModificacion = obtenerNumeroModificacion() + 1;
	       guardarNumeroModificacion(numeroModificacion);
	    }

	// Hace un guardado de los numeros utilizados para la creacion de areas (Se utiliza para el area ACTIVA)
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

	// Hace un guardado de los numeros utilizados para la creacion de areas (Se utiliza para el area INACTIVA)
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
				// WebElement botonSiguiente =
				// driver.findElement(By.xpath("//a[normalize-space()='Siguiente']"));
				WebElement botonUltima = null;

				// Intentar encontrar el botón "Última" (si existe)
				try {
					botonUltima = driver.findElement(By.xpath("//a[normalize-space()='Última']"));
				} catch (NoSuchElementException ignored) {
					// Si no se encuentra el botón "Última", continuar sin hacer nada
				}

				if (botonUltima != null && botonUltima.isDisplayed()) {
					waitForWebElementToBeClickable(botonUltima);
					botonUltima.click();
				} else {
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

	// Busca el area INACTIVA creada en todas las paginas de la paginacion
	public boolean buscarAreaEnTodasLasPaginas2(WebDriver driver, String nombreArea2) {

		boolean hayMasPaginas = true;

		while (hayMasPaginas) {
			// Buscar el área en la página actual
			try {
				WebElement areaCreada = driver.findElement(By.xpath("//td[normalize-space()='" + nombreArea2 + "']"));
				if (areaCreada.isDisplayed()) {
					return true; // Área encontrada en la página actual
				}
			} catch (NoSuchElementException e) {
				// Área no encontrada en la página actual
			}

			// Intentar ir a la siguiente página
			try {
				// WebElement botonSiguiente =
				// driver.findElement(By.xpath("//a[normalize-space()='Siguiente']"));
				WebElement botonUltima = null;

				// Intentar encontrar el botón "Última" (si existe)
				try {
					botonUltima = driver.findElement(By.xpath("//a[normalize-space()='Última']"));
				} catch (NoSuchElementException ignored) {
					// Si no se encuentra el botón "Última", continuar sin hacer nada
				}

				if (botonUltima != null && botonUltima.isDisplayed()) {
					waitForWebElementToBeClickable(botonUltima);
					botonUltima.click();
				} else {
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

		int numeroArea = obtenerNumeroArea();
		String nombreArea = "AREA ACTIVA " + String.format("%03d", numeroArea);
		campoNombreArea.sendKeys(nombreArea);
		btnCrear.click();
		Thread.sleep(500);

		// Incrementa el número del área para la siguiente iteración
		incrementarNumeroArea();

		boolean areaEncontrada = buscarAreaEnTodasLasPaginas(driver, nombreArea);

		//System.out.println("------------ Creacion de Área activa ------------ \n");

		if (areaEncontrada) {
			//System.out.println("Éxito!! El área activa se creó correctamente \n");
			Assert.assertTrue(true);

			// Volver a primera pagina o anterior si no hay "Primera"
			try {
				if (primeraPagina.isDisplayed()) {
					primeraPagina.click();
				} else if (anteriorPagina.isDisplayed()) {
					anteriorPagina.click();
				} else {
					// Continua con el proceso
				}
			} catch (NoSuchElementException e) {
				// Continua con el proceso si no encuentra "Primera" ni "Anterior"
			}
		} else {
			//System.out.println("Incorrecto!! El área activa no se encontro en ninguna pagina, por lo que no fue creada \n");
			Assert.assertTrue(false);
		}
	}

	public void creaAreaInactiva() throws InterruptedException {
		
		// Crear area INACTIVA
		int numeroArea2 = obtenerNumeroArea2();
		//Thread.sleep(1500);
		String nombreArea2 = "AREA INACTIVA " + String.format("%03d", numeroArea2);
		campoNombreArea.sendKeys(nombreArea2);
		//Thread.sleep(1000);
		btnInactivo.click();
		btnCrear.click();
		Thread.sleep(500);

		// Incrementa el número del área para la siguiente iteración
		incrementarNumeroArea2();

		boolean areaEncontrada2 = buscarAreaEnTodasLasPaginas2(driver, nombreArea2);

		//System.out.println("------------ Creacion de Área inactiva ------------ \n");

		if (areaEncontrada2) {
			//System.out.println("Éxito!! El área inactiva se creó correctamente \n");
			Assert.assertTrue(true);

			// Volver a primera pagina o anterior si no hay "Primera"
			try {
				if (primeraPagina.isDisplayed()) {
					primeraPagina.click();
				} else if (anteriorPagina.isDisplayed()) {
					anteriorPagina.click();
				} else {
					// Continua con el proceso
				}
			} catch (NoSuchElementException e) {
				// Continua con el proceso si no encuentra "Primera" ni "Anterior"
			}
		} else {
			//System.out.println("Incorrecto!! El área inactiva no se encontro en ninguna pagina, por lo que no fue creada \n");
			Assert.assertTrue(false);
		}
	}

	// Modifica el nombre y el estado de un area, y hace la verificacion de que el cambio fue exitoso
	public void modificaArea() throws InterruptedException {

		Thread.sleep(1000);
		String nuevoNombre = "MODIFICACION AUTOMATICA " + obtenerNumeroModificacion();
		//ComponentesReusables componentesReusables = new ComponentesReusables(driver);
		
		// Selecciona el primer area de la lista
		//componentesReusables.waitForWebElementToBeClickable(primerArea);
		//primerArea.click();
		try {
		    componentesReusables.waitForWebElementToBeClickable(primerArea);
		    primerArea.click();
		} catch (StaleElementReferenceException e) {
		    // Elemento obsoleto, intentar ubicarlo nuevamente
		    primerArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[1]/td[1]"));
		    primerArea.click(); // Intentar hacer clic nuevamente
		}
		Thread.sleep(500);	
		
		// Luego de seleccionar, limpia el nombre e ingresa el "nuevoNombre"
		campoNombreArea.clear();
		campoNombreArea.sendKeys(nuevoNombre);
		btnCrear.click();
		Thread.sleep(1000);
		
		// Obtiene el nombre que modificamos al primer area de la lista
		String nombreareaNuevo = "";
		try {
		    componentesReusables.waitForWebElementToBeClickable(primerArea);
		    nombreareaNuevo = primerArea.getText();
		} catch (StaleElementReferenceException e) {
		    // Elemento obsoleto, intentar ubicarlo nuevamente
		    primerArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[1]/td[1]"));
		    nombreareaNuevo = primerArea.getText();
		}

		// Incrementar el número de modificación
		incrementarNumeroModificacion();

		// Verifica si el nombre se modifico correctamente

		//System.out.println("------------ Cambio de nombre de Área ------------ \n");

		if (nombreareaNuevo.equals(nuevoNombre)) {
			//System.out.println("Exito!! El nombre del area se modifico correctamente \n");
			Assert.assertTrue(true);

		} else {
			//System.out.println("Incorrecto!! El nombre del area no se modifico correctamente \n");
			Assert.assertTrue(false);
		}

		// Cambia el estado del area 2 veces, de activo a inactivo y de inactivo a activo o viceversa, y comprueba que el cambio fue exitoso
		for (int i = 0; i < 2; i++) {
			try {
			    componentesReusables.waitForWebElementToBeClickable(primerArea);
			    primerArea.click();
			} catch (StaleElementReferenceException e) {
			    // Elemento obsoleto, intentar ubicarlo nuevamente
			    primerArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[1]/td[1]"));
			    primerArea.click(); // Intentar hacer clic nuevamente
			}

			Thread.sleep(500);
			// Obtener el estado actual del área (activo o inactivo)
			WebElement inputActivo = driver.findElement(By.id("rdoActivo1"));
			boolean estadoActualActivo = inputActivo.isSelected();

//			// Cambiar al estado contrario usando JavaScript
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			if (estadoActualActivo) {
//				// Si el estado actual es activo, entonces cambiar a inactivo
//				js.executeScript("arguments[0].click();", btnInactivo);
//			} else {
//				// Si el estado actual es inactivo, entonces cambiar a activo
//				js.executeScript("arguments[0].click();", btnActivo);
//			}
			
			if (estadoActualActivo) {
				// Si el estado actual es activo, entonces cambiar a inactivo
				btnInactivo.click();
			} else {
				// Si el estado actual es inactivo, entonces cambiar a activo
				btnActivo.click();
			}

			btnCrear.click();
			Thread.sleep(500);

			// Obtiene el nuevo estado del área después de cambiarlo
			try {
			    componentesReusables.waitForWebElementToBeClickable(primerArea);
			    primerArea.click();
			} catch (StaleElementReferenceException e) {
			    // Elemento obsoleto, intentar ubicarlo nuevamente
			    primerArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[1]/td[1]"));
			    primerArea.click(); // Intentar hacer clic nuevamente
			}
			Thread.sleep(500);

			boolean nuevoEstadoActivo = inputActivo.isSelected();

			// Mensaje de depuración
			//System.out.println("------------ Cambio de estado de Área ------------ \n");
			System.out.println("Antiguo estado: " + estadoActualActivo);
			System.out.println("Nuevo estado: " + nuevoEstadoActivo + "\n");

			// Verifica si el estado actual es diferente al estado anterior
			if (estadoActualActivo != nuevoEstadoActivo) {
				//System.out.println("\nÉxito!! El estado del área se modificó correctamente \n");
				Assert.assertTrue(true);
			} else {
				//System.out.println("\nError!! El estado del área no se modificó correctamente \n");
				Assert.assertTrue(false);
			}

		}
		campoNombreArea.clear();
		
	}
	
	public List<WebElement> obtenerListaAreas() {

		waitForElementToAppear(productosBy);
		return listaAreas;
	}
	
	public void filtroNombreArea() throws InterruptedException {
		Thread.sleep(700);
		Actions a = new Actions(driver);
		a.scrollToElement(filtroNombre).build().perform();
		// Proporciona un nombre de area para la prueba
		String nombreAreaFiltro = "BUSCA AREA " + obtenerNumeroModificacion();
		
		// Crea el area con el nombre definido
		campoNombreArea.sendKeys(nombreAreaFiltro);
		btnCrear.click();
		
		incrementarNumeroModificacion();
		
		// Manda variable nombreArea al filtro de nombre
		componentesReusables.waitForWebElementToBeClickable(filtroNombre);
		filtroNombre.sendKeys(nombreAreaFiltro);
	
		// Realiza la busqueda mediante tecla enter
		a.moveToElement(filtroNombre).click().keyDown(Keys.ENTER).build().perform();;
		
		// Busca en la lista si existe un resultado que coincida con variable nombreArea
//		WebElement area = obtenerListaAreas().stream()
//                .filter(elemento -> elemento.getText().startsWith(nombreArea))
//                .findFirst()
//                .orElse(null);
		
		Thread.sleep(500);
		WebElement area = obtenerListaAreas().stream()
				.filter(elemento -> elemento.findElement(By.cssSelector("td")).getText().contains(nombreAreaFiltro))
				.findFirst().orElse(null);
		
		// Verifica si obtuvo resultados con la busqueda
		if (area!=null) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}
	
	public void filtroEstadoArea() throws InterruptedException {

		int contadorA = 0;
		int contadorI = 0;
		
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		js.executeScript("window.scrollBy(0, 0)");
		
		waitForWebElementToAppear(btnLimpiar);
		waitForWebElementToBeClickable(btnLimpiar);
		//botonAdministrar.click();
		btnLimpiar.click();
		btnBuscar.click();
		WebElement inputActivo = driver.findElement(By.id("rdoActivo1"));
		WebElement inputInactivo = driver.findElement(By.id("rdoActivo2"));
		WebElement segundaArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[2]/td[1]"));
		WebElement terceraArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[3]/td[1]"));
		WebElement cuartaArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[4]/td[1]"));
		WebElement quintaArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[5]/td[1]"));
		waitForInvisibilityOfElement(waitingDialog);
		
		selectEstados.click();
		filtroActivo.click();
		btnBuscar.click();
		
		// Comprobacion de areas activas
		try {
		    componentesReusables.waitForWebElementToBeClickable(primerArea);
		    primerArea.click();
		} catch (StaleElementReferenceException e) {
		    // Elemento obsoleto, intentar ubicarlo nuevamente
		    primerArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[1]/td[1]"));
		    primerArea.click(); // Intentar hacer clic nuevamente
		}
		
		Thread.sleep(200);
//		waitForInvisibilityOfElement(waitingDialog);
//		waitForWebElementToBeClickable(inputActivo);
		if (inputActivo.isSelected()) {
			contadorA++;
		}else {
			System.out.println("Se encontro un area inactiva al buscar areas activas");
		}
		
		try {
			componentesReusables.waitForWebElementToBeClickable(segundaArea);
		    segundaArea.click();
		} catch (StaleElementReferenceException e) {
		    // Elemento obsoleto, intentar ubicarlo nuevamente
		   segundaArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[2]/td[1]"));
		   segundaArea.click(); // Intentar hacer clic nuevamente
		}
		
		Thread.sleep(200);
//		waitForInvisibilityOfElement(waitingDialog);
//		waitForWebElementToBeClickable(inputActivo);
		if (inputActivo.isSelected()) {
			contadorA++;
		}else {
			System.out.println("Se encontro un area inactiva al buscar areas activas");
		}
		
		try {
			componentesReusables.waitForWebElementToBeClickable(terceraArea);
		    terceraArea.click();
		} catch (StaleElementReferenceException e) {
		    // Elemento obsoleto, intentar ubicarlo nuevamente
			terceraArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[3]/td[1]"));
			terceraArea.click(); // Intentar hacer clic nuevamente
		}
		
		Thread.sleep(200);
//		waitForInvisibilityOfElement(waitingDialog);
//		waitForWebElementToBeClickable(inputActivo);
		if (inputActivo.isSelected()) {
			contadorA++;
		}else {
			System.out.println("Se encontro un area inactiva al buscar areas activas");
		}
		
		try {
			componentesReusables.waitForWebElementToBeClickable(cuartaArea);
		    cuartaArea.click();
		} catch (StaleElementReferenceException e) {
		    // Elemento obsoleto, intentar ubicarlo nuevamente
			cuartaArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[4]/td[1]"));
			cuartaArea.click(); // Intentar hacer clic nuevamente
		}
		
		Thread.sleep(200);
//		waitForInvisibilityOfElement(waitingDialog);
//		waitForWebElementToBeClickable(inputActivo);
		if (inputActivo.isSelected()) {
			contadorA++;
		}else {
			System.out.println("Se encontro un area inactiva al buscar areas activas");
		}
		
		try {
			componentesReusables.waitForWebElementToBeClickable(quintaArea);
		    quintaArea.click();
		} catch (StaleElementReferenceException e) {
		    // Elemento obsoleto, intentar ubicarlo nuevamente
			quintaArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[5]/td[1]"));
			quintaArea.click(); // Intentar hacer clic nuevamente
		}
		
		Thread.sleep(200);
//		waitForInvisibilityOfElement(waitingDialog);
//		waitForWebElementToBeClickable(inputActivo);
		if (inputActivo.isSelected()) {
			contadorA++;
		}else {
			System.out.println("Se encontro un area inactiva al buscar areas activas");
		}
		
		if (contadorA == 5) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
		
		
		// Comprobacion de areas Inactivas	
		selectEstados.click();
		filtroInactivo.click();
		btnBuscar.click();
		
		try {
			componentesReusables.waitForWebElementToBeClickable(primerArea);
		    primerArea.click();
		} catch (StaleElementReferenceException e) {
		    // Elemento obsoleto, intentar ubicarlo nuevamente
		    primerArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[1]/td[1]"));
		    primerArea.click(); // Intentar hacer clic nuevamente
		}
		
		Thread.sleep(200);
		if (inputInactivo.isSelected()) {
			contadorI++;
		}else {
			System.out.println("Se encontro un area inactiva al buscar areas activas");
		}
		
		try {
			componentesReusables.waitForWebElementToBeClickable(segundaArea);
		    segundaArea.click();
		} catch (StaleElementReferenceException e) {
		    // Elemento obsoleto, intentar ubicarlo nuevamente
		   segundaArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[2]/td[1]"));
		   segundaArea.click(); // Intentar hacer clic nuevamente
		}
		
		Thread.sleep(200);
		if (inputInactivo.isSelected()) {
			contadorI++;
		}else {
			System.out.println("Se encontro un area inactiva al buscar areas activas");
		}
		
		try {
			componentesReusables.waitForWebElementToBeClickable(terceraArea);
		    terceraArea.click();
		} catch (StaleElementReferenceException e) {
		    // Elemento obsoleto, intentar ubicarlo nuevamente
			terceraArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[3]/td[1]"));
			terceraArea.click(); // Intentar hacer clic nuevamente
		}
		
		Thread.sleep(200);
		if (inputInactivo.isSelected()) {
			contadorI++;
		}else {
			System.out.println("Se encontro un area inactiva al buscar areas activas");
		}
		
		try {
			componentesReusables.waitForWebElementToBeClickable(cuartaArea);
		    cuartaArea.click();
		} catch (StaleElementReferenceException e) {
		    // Elemento obsoleto, intentar ubicarlo nuevamente
			cuartaArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[4]/td[1]"));
			cuartaArea.click(); // Intentar hacer clic nuevamente
		}
		
		Thread.sleep(200);
		if (inputInactivo.isSelected()) {
			contadorI++;
		}else {
			System.out.println("Se encontro un area inactiva al buscar areas activas");
		}
		
		try {
			componentesReusables.waitForWebElementToBeClickable(quintaArea);
		    quintaArea.click();
		} catch (StaleElementReferenceException e) {
		    // Elemento obsoleto, intentar ubicarlo nuevamente
			quintaArea = driver.findElement(By.xpath("//tbody[@class='tb-bss-pointer']/tr[5]/td[1]"));
			quintaArea.click(); // Intentar hacer clic nuevamente
		}
		
		Thread.sleep(200);
		if (inputInactivo.isSelected()) {
			contadorI++;
		}else {
			System.out.println("Se encontro un area inactiva al buscar areas activas");
		}
		
		if (contadorI == 5) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}
}