package valuesite.pageobjects;

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

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SucursalRT extends ComponentesReusables {
	
	private static final String CONFIG_FILE = "config.properties";
    private static final String NRO_SUCURSAL_KEY = "nroSucursal";
    private static final String NRO_MODIF_KEY = "nroModif";

	WebDriver driver;
	
	Acciones a;

	public SucursalRT(WebDriver driver) {
		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);
		
		a = new Acciones(driver);
		
	}
	
//	public void setUp() {
//		Acciones a = new Acciones();
//	}
	

	// PAGE FACTORY

	// Pestaña de adminstracion en navBar
	@FindBy(xpath = "//a[normalize-space()='Administración']")
	WebElement tabAdministracion;

	// Pestaña de sucursales en navBar
	@FindBy(css = "a[href='/Administrar/Sucursal']")
	WebElement tabSucusales;

	// Filtro nombre sucursal
	@FindBy(name = "txtNombreFind")
	WebElement filtroNombre;

	// Boton buscar filtros
	@FindBy(css = ".btn.celeste")
	WebElement btnBuscar;

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

	// Campo nombre (formulario)
	@FindBy(id = "txtNombreSucursal")
	public
	WebElement campoNombre;

	// Select ciudad (formulario)
	@FindBy(xpath = "//button[@data-id='slcCiudad']")
	public
	WebElement selectCiudad;
	
	// Ciudad a seleccionar
	WebElement ciudad;

	// Estado de Check Activo (formulario)
	@FindBy(id = "rdoActivo1")
	WebElement estadoCheckActivo;
	
	// Marcar Check Activo
	@FindBy(css = "label[for='rdoActivo1']")
	public
	WebElement marcaCheckActivo;

	// Estao de Check Inactivo (formulario)
	@FindBy(id = "rdoActivo2")
	WebElement estadoCheckInactivo;
	
	// Marcar Check Inactivo
	@FindBy(css = "label[for='rdoActivo2']")
	public
	WebElement marcaCheckInactivo;

	// Check Todos (Formulario / Visibilidad de convenios)
	@FindBy(id = "rdoConvenio1")
	WebElement checkTodos;

	// Check Definir (Formulario / Visibilidad de convenios)
	@FindBy(css = "label[for='rdoConvenio2']")
	WebElement checkDefinir;
	
	// Select convenio (Formulario)
	@FindBy (xpath="//div[@id='dvConvenio']/div/div/button")
	public
	WebElement selectConvenio;
	
	// Searchbox convenio
	@FindBy(xpath="(//div[@class='bs-searchbox'])[2]/input")
	WebElement buscaConvenio;
	
	// Opcion lista convenios
	@FindBy (xpath="//ul[@aria-expanded='true']/li")
	WebElement opcionConvenio;

	// Campo direccion (Formulario)
	@FindBy(id = "txtDireccion")
	public
	WebElement campoDireccion;

	// Direccion a seleccionar
	@FindBy(id="//div[@id='dropDownListResultados-txtDireccion']/div[2]")
	WebElement seleccionDireccion;
	
//	// Grilla de opciones direcciones
//	@FindBy(id="dropDownListResultados-txtDireccion")
//	public
//	WebElement opcionesDireccion;
	
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
	@FindBy(css = "input[onclick='av.sucursal.nuevo();']")
	public
	WebElement btnCancelar;
	
	// Mensaje de error
	@FindBy(css = "div[role='alert']")
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
		// Boton anterior pagina (Paginacion)
		@FindBy(xpath = "//a[normalize-space()='Siguiente']")
		static WebElement siguientePagina;

		// Boton anterior pagina (Paginacion)
		@FindBy(xpath = "//a[normalize-space()='Ultima']")
		WebElement ultimaPagina;

	// PAGE FACTORY

	public void ingresoMantenedorSucursales() throws InterruptedException {
		tabAdministracion.click();
		tabSucusales.click();
		waitForInvisibilityOfElement(waitingDialog);
	}
	
	public void ingresarNombreFormulario(String nombreSucursal) {
		waitForWebElementToBeClickable(campoNombre);
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
		}else if (estadoCheckInactivo.isSelected()) {
			inactivo = false;
			return inactivo;
		}
		
		return false;
	}
	
	public void seleccionaCiudad(int posicion) {
		waitForWebElementToBeClickable(selectCiudad);
		selectCiudad.click();
		ciudad = driver.findElement(By.xpath("//div[@class='btn-group bootstrap-select show-tick all-width open']//div[@role='combobox']/ul/li["+posicion+"]"));
		// Control de errores en caso de no haber nada que seleccionar
				try {
				    ciudad.click();
				} catch (NoSuchElementException e) {
					Assert.fail("Error: El elemento ciudad no fue encontrado. Puede deberse a que no existe la ciudad, o la clase del html sufrio cambios.");
				} catch (StaleElementReferenceException e) {
					Assert.fail("Error: La referencia al elemento ciudad es obsoleta.");
				} catch (Exception e) {
					Assert.fail("Error: Ocurrió un error inesperado al intentar hacer clic en ciudad.");
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
	
	public void seleccionaConvenio(String convenio) {
		checkDefinir.click();
		waitForWebElementToBeClickable(selectConvenio);
		selectConvenio.click();
		buscaConvenio.sendKeys(convenio);
		// Control de errores en caso de no haber nada que seleccionar
		try {
		    opcionConvenio.click();
		} catch (NoSuchElementException e) {
			Assert.fail("Error: El elemento opcionConvenio no fue encontrado. Puede deberse a que no existe el convenio buscado, o la clase del html sufrio cambios.");
		} catch (StaleElementReferenceException e) {
			Assert.fail("Error: La referencia al elemento opcionConvenio es obsoleta.");
		} catch (Exception e) {
			Assert.fail("Error: Ocurrió un error inesperado al intentar hacer clic en opcionConvenio.");
		}
		
		selectConvenio.click();
	}
	
	public void filtraPorNombre(String nombre) {
		waitForWebElementToBeClickable(filtroNombre);
		filtroNombre.clear();
		filtroNombre.sendKeys(nombre);
		btnBuscar.click();
	}
	
	public List<WebElement> obtenerListaNombres() {

		// Retorna la lista de areas presentes en la vista
		waitForElementToAppear(nombresBy);
		return columnaSucursal;
	}
	
	public List<WebElement> obtenerListaDirecciones() {

		// Retorna la lista de areas presentes en la vista
		waitForElementToAppear(direccionesBy);
		return columnaDireccion;
	}
	
	public boolean verificaDireccionSucursal(String nombreSucursalCrear, String direccionEsperada) {
	    WebElement filaSucursal = buscarElementoEnPaginas(nombreSucursalCrear);

	    if (filaSucursal != null) {
	        // Se busca la celda de direccion de la sucursal que queremos
	        WebElement celdaDireccion = filaSucursal.findElement(By.xpath("../td[2]"));

	        String direccionActual = celdaDireccion.getText().trim();

	        System.out.println("\nDireccion esperada: " + direccionEsperada);
	        System.out.println("Direccion actual: " + direccionActual + "\n");

	        return direccionEsperada.equalsIgnoreCase(direccionActual);
	    } else {
	        System.out.println("Sucursal no encontrada, no se pudo verificar la direccion.");
	        return false;
	    }
	}

	
	public WebElement buscarElementoEnPaginas(String nombreSucursalCrear) {
	    // Verifica si el elemento está en la página actual usando streams
	    WebElement nombreSucursal = obtenerListaNombres().stream()
	        .filter(elemento -> elemento.getText().trim().equalsIgnoreCase(nombreSucursalCrear.trim()))
	        .findFirst()
	        .orElse(null);

	    // Si encuentra el elemento en la página actual, retorna
	    if (nombreSucursal != null) {
	        System.out.println("Elemento encontrado en la página actual: " + nombreSucursal.getText());
	        return nombreSucursal;
	    }

	    // Si no se encontró, continúa con la paginación
	    boolean encontrado = false;

	    while (existeElemento(siguientePagina)) {
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
	            System.out.println("Elemento encontrado en una página siguiente: " + nombreSucursal.getText());
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
