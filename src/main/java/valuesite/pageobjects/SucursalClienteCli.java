package valuesite.pageobjects;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import valuesite.componentesreusables.Acciones;
import valuesite.componentesreusables.ComponentesReusables;

public class SucursalClienteCli extends ComponentesReusables{
	
	private static final String CONFIG_FILE = "config.properties";
    private static final String NRO_SUCURSAL_KEY = "nroSucursalClienteRT";
    private static final String NRO_MODIF_KEY = "nroModifSucClienteRT";
	
	WebDriver driver;
	
	Acciones a;
	
	public SucursalClienteCli(WebDriver driver) {
		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);
		
		a = new Acciones(driver);
	}
	
// PAGE FACTORY //
	
	// NAVBAR
	
		// Pestaña de adminstracion en navBar
			@FindBy(xpath = "//a[normalize-space()='Administración']")
			WebElement tabAdministracion;

		// Pestaña de sucursales en navBar
			@FindBy(xpath = "//a[@href='/Administrar/SucursalClienteMd']")
			WebElement tabSucusales;
			
	// VISTA
		// Filtro nombre sucursal
			@FindBy(name = "txtNombreFind")
			public
			WebElement filtroNombre;
			
		// Filtro estado
			@FindBy(xpath = "//input[@role='listbox']")
			public
			WebElement filtroEstado;
			
		// Filtro estado / opcion TODOS
			@FindBy(xpath = "//div[@class='col-12 col-lg-12 px-0']//li[1]")
			public
			WebElement estadoTodos;
			
		// Filtro estado / opcion ACTIVO
			@FindBy(xpath = "//div[@class='col-12 col-lg-12 px-0']//li[2]")
			public
			WebElement estadoActivo; 
			
		// Filtro estado / opcion INACTIVO
			@FindBy(xpath = "//div[@class='col-12 col-lg-12 px-0']//li[3]")
			public
			WebElement estadoInactivo; 			

		// Boton buscar filtros
			@FindBy(id = "btnBuscar")
			public
			WebElement btnBuscar;
			
		// Boton limpiar filtros
			@FindBy(xpath = "//button[normalize-space()='Limpiar']")
			public
			WebElement btnLimpiar;

		// Columna sucursal
			@FindBy(xpath = "//tbody[@class='tb-bss-pointer']/tr/td[1]")
			List<WebElement> columnaSucursal;
			
		// Columna sucursal By
			By nombresBy = By.xpath("//tbody[@class='tb-bss-pointer']/tr/td[1]");

		// Columna direccion
			@FindBy(xpath = "//tbody[@class='tb-bss-pointer']/tr/td[2]")
			List<WebElement> columnaDireccion;
			
		// Columna direccion By
			By direccionesBy = By.xpath("//tbody[@class='tb-bss-pointer']/tr/td[2]");
			
		// Columna estado
			@FindBy(xpath = "//tbody[@class='tb-bss-pointer']/tr/td[3]")
			List<WebElement> columnaEstado;
						
		// Columna estado By
			By estadosBy = By.xpath("//tbody[@class='tb-bss-pointer']/tr/td[3]");

		// Campo nombre (formulario)
			@FindBy(id = "txtNombreSucursal")
			public
			WebElement campoNombre;

		// Estado de Check Activo (formulario)
			@FindBy(id = "chkActivo")
			public
			WebElement estadoCheckActivo;
			
		// Marcar Check Activo
			@FindBy(css = "label[for='chkActivo']")
			public
			WebElement marcaCheckActivo;

		// Campo direccion (Formulario)
			@FindBy(id = "txtDireccion")
			public
			WebElement campoDireccion;

		// Direccion a seleccionar
			@FindBy(id="//div[@id='dropDownListResultados-txtDireccion']/div[2]")
			WebElement seleccionDireccion;
			
		// Grilla de opciones direcciones
			@FindBy(css="#dropDownListResultados-txtDireccion")
			public
			WebElement opcionesDireccion;
			
		// Cuadro de carga campo direccion
			@FindBy(css=".col-12.text-center.w-100")
			public
			WebElement direccionWaiting;
			
		// Boton crear (Formulario)
			@FindBy(id = "btnCrear")
			public
			WebElement btnCrear;

		// Boton cancelar (Formulario)
			@FindBy(xpath = "//input[@value='Cancelar']")
			public
			WebElement btnCancelar;
		
		// Mensaje de error
			@FindBy(xpath = "//div[@class='alert alert-danger']")
			public
			WebElement msjError;
			
		// Modal Por favor Espere
			@FindBy(css = ".modal-dialog.modal-m")
			public
			WebElement waitingDialog;
			
		// Primera sucursal de la lista
			@FindBy (xpath="//tbody[@class='tb-bss-pointer']/tr[1]")
			public
			WebElement primeraSucursal;
			
			// Paginacion
			
				// Boton primera pagina (Paginacion)
				@FindBy(xpath = "//a[normalize-space()='Primera']")
				WebElement primeraPagina;

				// Boton anterior pagina (Paginacion)
				@FindBy(xpath = "//a[normalize-space()='Anterior']")
				WebElement anteriorPagina;
				
				// Boton Siguiente pagina (Paginacion)
				@FindBy(xpath = "//a[normalize-space()='Siguiente']")
				static WebElement siguientePagina;

				// Boton Ultima pagina (Paginacion)
				@FindBy(xpath = "//a[normalize-space()='Ultima']")
				WebElement ultimaPagina;

	// PAGE FACTORY //
				
	
	public void ingresoMantenedorSucursalesCliente() throws InterruptedException {
		tabAdministracion.click();
		tabSucusales.click();
	}
	
	public void ingresarNombreFormulario(String nombreSucursal) throws InterruptedException {
//		waitForWebElementToAppear(campoNombre);
		Thread.sleep(1000);
		campoNombre.clear();
		campoNombre.sendKeys(nombreSucursal);
	}
	
	public boolean obtenerEstado() throws InterruptedException {
		boolean activo;
		boolean inactivo;
		Thread.sleep(250);
		
		if (estadoCheckActivo.isSelected()) {
			activo = true;
			return activo;
		}else{
			inactivo = false;
			return inactivo;
		}
		
	}
	
	public void seleccionaDireccion(String direccion, int posicion) throws InterruptedException {
		campoDireccion.clear();
		campoDireccion.sendKeys(direccion);
		a.presionarEnter(campoDireccion);
		
		//Thread.sleep(1500);
		if (opcionesDireccion.isDisplayed()) {
			waitForWebElementToBeClickable(seleccionDireccion);
			posicion = posicion + 1;
			seleccionDireccion = driver.findElement(By.xpath("//div[@id='dropDownListResultados-txtDireccion']/div["+posicion+"]"));
			seleccionDireccion.click();
		}
		
		waitForInvisibilityOfElement(direccionWaiting);
	}
	
	
	
	public void filtraPorNombre(String nombre) {
		waitForWebElementToBeClickable(filtroNombre);
		filtroNombre.clear();
		filtroNombre.sendKeys(nombre);
		btnBuscar.click();
	}
	
	public void filtraPorEstado(String estado) throws InterruptedException {
		waitForWebElementToBeClickable(btnLimpiar);
		btnLimpiar.click();
		filtroEstado.click();
		if (estado.equalsIgnoreCase("activo")) {
			estadoActivo.click();
		}else if (estado.equalsIgnoreCase("inactivo")) {
			estadoInactivo.click();
		}else if (estado.equalsIgnoreCase("todos")) {
			estadoTodos.click();
		}
		btnBuscar.click();
	}
	
	public List<WebElement> obtenerListaNombres() {

		// Retorna la lista de nombres presentes en la grilla
		waitForElementToAppear(nombresBy);
		return columnaSucursal;
	}
	
	public List<WebElement> obtenerListaDirecciones() {

		// Retorna la lista de direcciones presentes en la grilla
		waitForElementToAppear(direccionesBy);
		return columnaDireccion;
	}
	
	public List<WebElement> obtenerListaEstados() {

		// Retorna la lista de estados presentes en la grilla
		waitForElementToAppear(estadosBy);
		return columnaEstado;
	}
	
	
	
	public boolean verificaDireccionSucursal(String nombreSucursalCrear, String direccionEsperada) throws InterruptedException {
	    WebElement filaSucursal = buscarElementoEnPaginas(nombreSucursalCrear);

	    if (filaSucursal != null) {
	        // Se busca la celda de direccion de la sucursal que queremos
	        WebElement celdaDireccion = filaSucursal.findElement(By.xpath("../td[2]"));

	        String direccionActual = celdaDireccion.getText().trim();

	        //System.out.println("\nDireccion esperada: " + direccionEsperada);
	        //System.out.println("Direccion actual: " + direccionActual + "\n");

	        return direccionEsperada.equalsIgnoreCase(direccionActual);
	    } else {
	        System.out.println("Sucursal no encontrada, no se pudo verificar la direccion.");
	        return false;
	    }
	}

	
	public WebElement buscarElementoEnPaginas(String nombreSucursalCrear) throws InterruptedException {
		Thread.sleep(500);
	    // Verifica si el elemento está en la página actual usando streams
	    WebElement nombreSucursal = obtenerListaNombres().stream()
	        .filter(elemento -> elemento.getText().trim().equalsIgnoreCase(nombreSucursalCrear.trim()))
	        .findFirst()
	        .orElse(null);

	    // Si encuentra el elemento en la página actual, retorna
	    if (nombreSucursal != null) {
	        //System.out.println("Elemento encontrado en la página actual: " + nombreSucursal.getText());
	        return nombreSucursal;
	    }

	    // Si no se encontró, continúa con la paginación
	    boolean encontrado = false;

	    while (existeElemento(siguientePagina)) {
	    	Thread.sleep(300);
	        siguientePagina.click();

	        // Esperar un tiempo breve para que la página cargue
	        try {
	            Thread.sleep(500); 
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        nombreSucursal = obtenerListaNombres().stream()
	            .filter(elemento -> elemento.getText().trim().equalsIgnoreCase(nombreSucursalCrear.trim()))
	            .findFirst()
	            .orElse(null);

	        if (nombreSucursal != null) {
	            encontrado = true;
	            //System.out.println("Elemento encontrado en una página siguiente: " + nombreSucursal.getText());
	            break;
	        }
	    }

	    // Al finalizar la búsqueda, regresa a la primera página o a la anterior si no existe la primera página
	    if (existeElemento(primeraPagina)) {
	        primeraPagina.click();
	    } else if (existeElemento(anteriorPagina)) {
	        anteriorPagina.click();
	    }

	    if (encontrado) {
	        return nombreSucursal;
	    } else {
	        System.out.println("Elemento no encontrado en ninguna página.");
	        return null;
	    }
	}


	private boolean existeElemento(WebElement elemento)  {
	    try {
	        // Verificar si el elemento está presente y visible en la página
	        return elemento.isDisplayed();
	    } catch (NoSuchElementException | StaleElementReferenceException e) {
	        // Si hay una excepción, el elemento no existe o no es visible
	    	e.printStackTrace();
	        return false;
	    }
	}

	// IDENTIFICADORES PARA LOS TEST DE CREACION Y MODIFICACION //
	
	public String obtieneNroSucursal() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            prop.load(input);
            return prop.getProperty(NRO_SUCURSAL_KEY, "1");
        } catch (IOException e) {
            e.printStackTrace();
            return "1"; // Valor predeterminado
        }
    }

	public void guardaNroSucursal(String nroSucursal) {
	    Properties prop = new Properties();
	    try (InputStream input = new FileInputStream(CONFIG_FILE)) {
	        // Cargar todas las propiedades existentes
	        prop.load(input);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
	        // Actualizar la propiedad específica
	        prop.setProperty(NRO_SUCURSAL_KEY, nroSucursal);
	        // Guardar todas las propiedades nuevamente
	        prop.store(output, null);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    
	public String obtieneNroModif() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            prop.load(input);
            return prop.getProperty(NRO_MODIF_KEY, "0");
        } catch (IOException e) {
            e.printStackTrace();
            return "1"; // Valor predeterminado
        }
    }

	public void guardaNroModif(String nroModifSucursal) {
	    Properties prop = new Properties();
	    try (InputStream input = new FileInputStream(CONFIG_FILE)) {
	        // Cargar todas las propiedades existentes
	        prop.load(input);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
	        // Actualizar la propiedad específica
	        prop.setProperty(NRO_MODIF_KEY, nroModifSucursal);
	        // Guardar todas las propiedades nuevamente
	        prop.store(output, null);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	

}
