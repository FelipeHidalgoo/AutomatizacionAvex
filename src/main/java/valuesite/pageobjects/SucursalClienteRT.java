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

public class SucursalClienteRT extends ComponentesReusables{
	
	private static final String CONFIG_FILE = "config.properties";
    private static final String NRO_SUCURSAL_KEY = "nroSucursalClienteRT";
    private static final String NRO_MODIF_KEY = "nroModifSucClienteRT";
	
	WebDriver driver;
	
	Acciones a;
	
	public SucursalClienteRT(WebDriver driver) {
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
			@FindBy(css = "a[href='/Administrar/SucursalClientesEmpresa']")
			WebElement tabSucusales;

	// BUSCAR CLIENTE
			
		// Campo buscar cliente (Para abrir buscador)
			@FindBy(xpath = "//span[@class='filter-option pull-left']")
			WebElement campoBuscarCliente;

		// Input buscar cliente (Para realizar la busqueda)
			@FindBy(xpath = "//input[@placeholder='Buscar cliente']")
			WebElement inputBuscarCliente;

		// Para seleccionar el primer resultado de la lista en campo buscar cliente
			@FindBy(xpath = "//div[@class='bs-container btn-group bootstrap-select show-tick open']//li[2]//a[1]//span[1]")
			WebElement seleccionCliente;

		// Boton administrar para realizar la busqueda
			@FindBy(css = "button[onclick=\"admDatosCliente.changeCliente('SucursalCliente')\"]")
			WebElement botonAdministrar;
			
	// VISTA
		// Filtro nombre sucursal
			@FindBy(name = "txtNombreFind")
			public
			WebElement filtroNombre;

		// Boton buscar filtros
			@FindBy(xpath = "//button[@onclick='av.sucursalCliente.filtrar(0)']")
			public
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
			public
			WebElement buscaConvenio;
			
		// Opcion lista convenios
			@FindBy (xpath="//ul[@aria-expanded='true']/li")
			public
			WebElement opcionConvenio;
			
		// Opcion convenio LIST
			@FindBy (xpath="//ul[@aria-expanded='true']/li")
			List<WebElement> opcionConvenioList;
			
		// Opcion lista convenios By
			By opcionConvenioBy = By.xpath("//ul[@aria-expanded='true']/li");

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
			@FindBy(css = "input[onclick='av.sucursalCliente.nuevo();']")
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

	// PAGE FACTORY //
				
				
				
				public void ingresoMantenedorSucursalesCliente() throws InterruptedException {
					tabAdministracion.click();
					tabSucusales.click();
					Thread.sleep(500);
				}
				
				public void buscaCliente(String cliente) {
					waitForWebElementToBeClickable(campoBuscarCliente);
					campoBuscarCliente.click();
					inputBuscarCliente.sendKeys(cliente);
					seleccionCliente.click();
					botonAdministrar.click();
					
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
				
				public void seleccionaConvenio(String convenio) throws InterruptedException{
					Thread.sleep(250);
					checkDefinir.click();
					waitForWebElementToAppear(selectConvenio);
					selectConvenio.click();
					//waitForWebElementToAppear(buscaConvenio);
					Thread.sleep(500);
					buscaConvenio.sendKeys(convenio);
					// Control de errores en caso de no haber nada que seleccionar
					try {
						waitForWebElementToBeClickable(opcionConvenio);
						Thread.sleep(500);
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
				
				public List<WebElement> obtenerOpcionesConvenio() throws InterruptedException {

					// Retorna la lista de areas presentes en la vista
					Thread.sleep(300);
					waitForWebElementToBeClickable(selectConvenio);
					selectConvenio.click();
					Thread.sleep(500);
					waitForElementToAppear(opcionConvenioBy);
					return opcionConvenioList;
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


