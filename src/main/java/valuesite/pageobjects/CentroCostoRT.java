package valuesite.pageobjects;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import valuesite.componentesreusables.ComponentesReusables;

public class CentroCostoRT extends ComponentesReusables {

	private static final String CONFIG_FILE = "config.properties";
	private static final String NRO_CC_KEY = "nroCentroCostoRT";
	private static final String NRO_MODIF_KEY = "nroModifCentroCostoRT";
	private static final String NRO_CODIGO_KEY = "nroModifCentroCostoRT";

	WebDriver driver;

	public CentroCostoRT(WebDriver driver) {
		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

// PAGE FACTORY //

	// NAVBAR

		// Pestaña de adminstracion en navBar
		@FindBy(xpath = "//a[normalize-space()='Administración']")
		WebElement tabAdministracion;

		// Pestaña de sucursales en navBar
		@FindBy(xpath = "//a[normalize-space()='Centros de Costo Cliente']")
		WebElement tabCentroCosto;
		
	// BUSCAR CLIENTE
		
		// Campo buscar cliente (Para abrir buscador)
			@FindBy(xpath = "//span[@class='filter-option pull-left']")
			WebElement campoBuscarCliente;

		// Input buscar cliente (Para realizar la busqueda)
			@FindBy(xpath = "//input[@placeholder='Buscar cliente']")
			WebElement inputBuscarCliente;

		// Para seleccionar el primer resultado de la lista en campo buscar cliente
			@FindBy(xpath = "//li[2]//a[1]//span[1]")
			WebElement seleccionCliente;

		// Boton administrar para realizar la busqueda
			@FindBy(xpath = "//button[normalize-space()='Administrar']")
			WebElement botonAdministrar;

	// VISTA
			
		// Codigo
		@FindBy(name = "txtNumeroFind")
		public WebElement filtroCodigo;	
		
		// Filtro Nombre
		@FindBy(name = "txtNombreFind")
		public WebElement filtroNombre;
		
		// Filtro estado
		@FindBy(xpath = "//input[@data-activates='select-options-slcEstadoFind']")
		public
		WebElement filtroEstado;
					
		// Filtro estado / opcion TODOS
		@FindBy(xpath = "//ul[@id='select-options-slcEstadoFind']/li[1]")
		public
		WebElement estadoTodos;
					
		// Filtro estado / opcion ACTIVO
		@FindBy(xpath = "//ul[@id='select-options-slcEstadoFind']/li[2]")
		public
		WebElement estadoActivo; 
					
		// Filtro estado / opcion INACTIVO
		@FindBy(xpath = "//ul[@id='select-options-slcEstadoFind']/li[3]")
		public
		WebElement estadoInactivo; 			

		// Boton buscar filtros
		@FindBy(xpath = "//button[normalize-space()='Buscar']")
		public
		WebElement btnBuscar;
					
		// Boton limpiar filtros
		@FindBy(xpath = "//button[normalize-space()='Limpiar']")
		public
		WebElement btnLimpiar;
		
		// Boton crear 
		@FindBy(id = "btnCrear")
		public
		WebElement btnCrear;
		
		// Boton cancelar 
		@FindBy(xpath = "//input[@value='Cancelar']")
		public
		WebElement btnCancelar;
		
		// Select Notiifcar servicio (Filtros)
		@FindBy(xpath="//input[@data-activates='select-options-slcNotificarServFinalizadoFind']")
		WebElement filtroNotificar;
				
		// Select Notiifcar servicio / Opcion Todos (Filtros)
		@FindBy(xpath="//ul[@id='select-options-slcNotificarServFinalizadoFind']/li[1]")
		WebElement filtroNotificarTodos;
				
		// Select Notiifcar servicio / Opcion Si (Filtros)
		@FindBy(xpath="//ul[@id='select-options-slcNotificarServFinalizadoFind']/li[2]")
		WebElement filtroNotificarSi;
				
		// Select Notiifcar servicio / Opcion No (Filtros)
		@FindBy(xpath="//ul[@id='select-options-slcNotificarServFinalizadoFind']/li[3]")
		WebElement filtroNotificarNo;
				
		// Input Responsable CC
		@FindBy(name="txtNombreJefeCCFind")
		WebElement filtroResponsableCC;
				
		// Columna Codigo
		@FindBy(xpath="//tbody[@class='tb-bss-pointer']/tr/td[1]")
		List<WebElement> columnaCodigo;
				
		// Columna Codigo By
		By columnaCodigoBy = By.xpath("//tbody[@class='tb-bss-pointer']/tr/td[1]");
				
		// Columna Nombre
		@FindBy(xpath="//tbody[@class='tb-bss-pointer']/tr/td[2]")
		List<WebElement> columnaNombre;
						
		// Columna Nombre By
		By columnaNombreBy = By.xpath("//tbody[@class='tb-bss-pointer']/tr/td[2]");
				
		// Columna Responsable CC
		@FindBy(xpath="//tbody[@class='tb-bss-pointer']/tr/td[3]")
		List<WebElement> columnaResponsableCC;
						
		// Columna ResponsableCC By
		By columnaResponsableBy = By.xpath("//tbody[@class='tb-bss-pointer']/tr/td[3]");
				
		// Columna Correo ResponsableCC
		@FindBy(xpath="//tbody[@class='tb-bss-pointer']/tr/td[4]")
		List<WebElement> columnaCorreoCC;
						
		// Columna Correo ResponsableCC By
		By columnaCorreoCCBy = By.xpath("//tbody[@class='tb-bss-pointer']/tr/td[4]");
				
		// Columna Notificar Servicio
		@FindBy(xpath="//tbody[@class='tb-bss-pointer']/tr/td[5]")
		List<WebElement> columnaNotificarServicio;
						
		// Columna Notificar servicio By
		By columnaNotificarServicioBy = By.xpath("//tbody[@class='tb-bss-pointer']/tr/td[5]");
				
		// Columna Estado
		@FindBy(xpath="//tbody[@class='tb-bss-pointer']/tr/td[6]")
		List<WebElement> columnaEstado;
						
		// Columna Estado By
		By columnaEstadoBy = By.xpath("//tbody[@class='tb-bss-pointer']/tr/td[6]");
		
		// Input Codigo (Formulario)
		@FindBy(id="txtNumero")
		WebElement inputCodigo;
		
		// Input Nombre (Formulario)
		@FindBy(id="txtNombre")
		WebElement inputNombre;
		
		// Check Definir Presupuesto
		@FindBy(xpath="//div[@class='col-1']//span[@class='checkmark-ds ml-0']")
		public
		WebElement checkDefinir;
		
		// Input Monto presupuesto
		@FindBy(id="tbMontoPresupuesto")
		WebElement inputPresupuesto;
		
		// Check Notificar servicio NO
		@FindBy(xpath="//body[1]/main[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[4]/div[1]/div[3]/div[1]/div[1]/label[1]/span[1]")
		WebElement checkNotificarNo;
		
		// Check Notificar servicio SI
		@FindBy(xpath="//body[1]/main[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]/label[1]/span[1]")
		WebElement checkNotificarSi;
		
		// Input Notificar servicio SI
		@FindBy(css="label[for='rdoNotificarserviciosfinalizados1']")
		WebElement inputNotificarSi;
		
		// Select Responsable CC (Formulario)
		@FindBy(xpath="//button[@data-id='slcJefeCentroDeCosto']")
		public
		WebElement selectResponsableCC;
		
		// Select Responsable CC / Input (Formulario)
		@FindBy(css="input[placeholder='Buscar responsable']")
		WebElement inputResponsableCC;
		
		// Select Responsable CC / Primera opcion (Formulario)
		@FindBy(xpath="//ul[@aria-expanded='true']/li[1]")
		WebElement primerResponsableCC;
		
		// Select Tipo correo (Formulario)
		@FindBy(xpath="//input[@data-activates='select-options-slcTipoCorreo']")
		public
		WebElement selectTipoCorreo;
		
		// Select Tipo correo / Opcion Todos (Formulario)
		//@FindBy(css=".disabled")
		@FindBy(xpath="//ul[@id='select-options-slcTipoCorreo']/li[2]")
		WebElement selectTipoCorreoTodos;
		
		// Select Tipo correo / Opcion Creacion (Formulario)
		@FindBy(xpath="//ul[@id='select-options-slcTipoCorreo']/li[3]")
		WebElement selectTipoCorreoCreacion;
				
		// Select Tipo correo / Opcion Termino (Formulario)
		@FindBy(xpath="//ul[@id='select-options-slcTipoCorreo']/li[4]")
		WebElement selectTipoCorreoTermino;
				
		// Select Tipo correo / Opcion Cancelacion (Formulario)
		@FindBy(xpath="//ul[@id='select-options-slcTipoCorreo']/li[5]")
		WebElement selectTipoCorreoCancelacion;
		
		// Check Notificar por correo (Formulario)
		@FindBy(css="div[class='col-2 col-lg-1'] span[class='checkmark-ds ml-0']")
		WebElement checkNotificarPresupuesto;
		
		// Input Porcentaje 1 (Formulario)
		@FindBy(id="txtPorcentajeFormulario01")
		WebElement porcentaje1;
		
		// Input Porcentaje 2 (Formulario)
		@FindBy(id="txtPorcentajeFormulario02")
		WebElement porcentaje2;
		
		// Input Porcentaje 3 (Formulario)
		@FindBy(id="txtPorcentajeFormulario03")
		WebElement porcentaje3;
		
		// Check Estado
		@FindBy(xpath="//div[@class='col-2']//span[@class='checkmark-ds ml-0']")
		WebElement checkEstado;
		
		// Check Estado (Obtener estado)
		@FindBy(id="rdoActivo1")
		WebElement checkEstadoObtener;
		
		// Modal Por favor Espere
		@FindBy(css = ".modal-dialog.modal-m")
		public
		WebElement waitingDialog;
		
		// Mensaje error
		 @FindBy (xpath="//div[@class='alert alert-danger']")
		 public
		 WebElement msjError;
		 
		// Primer CC de la lista
		@FindBy (xpath="//tbody[@class='tb-bss-pointer']/tr[1]")
		public
		WebElement primerCC;
		
	// Paginacion
		
		// Boton primera pagina (Paginacion)
		@FindBy(xpath = "//a[normalize-space()='Primera']")
		WebElement primeraPagina;

		// Boton anterior pagina (Paginacion)
		@FindBy(xpath = "//a[normalize-space()='Anterior']")
		WebElement anteriorPagina;
		// Boton siguiente pagina (Paginacion)
		@FindBy(xpath = "//a[normalize-space()='Siguiente']")
		static WebElement siguientePagina;

		// Boton ultima pagina (Paginacion)
		@FindBy(xpath = "//a[normalize-space()='Ultima']")
		WebElement ultimaPagina;

// PAGE FACTORY //
		
		
		public void ingresoMantenedorCentroCostoCli() throws InterruptedException {
			tabAdministracion.click();
			tabCentroCosto.click();
		}
		
		public void buscarCC(String nombreCC) {
			filtroNombre.sendKeys(nombreCC);
			btnBuscar.click();
		}
		
		public void buscaCliente(String cliente) {
			waitForWebElementToBeClickable(campoBuscarCliente);
			campoBuscarCliente.click();
			inputBuscarCliente.sendKeys(cliente);
			seleccionCliente.click();
			botonAdministrar.click();
			
			waitForInvisibilityOfElement(waitingDialog);
		}
		
		public void ingresarNombreFormulario(String nombreSucursal) throws InterruptedException {
			waitForWebElementToBeClickable(inputNombre);
			//Thread.sleep(500);
			inputNombre.clear();
			inputNombre.sendKeys(nombreSucursal);
		}
		
		public void ingresarCodigoFormulario(String codigo) throws InterruptedException {
			waitForWebElementToBeClickable(inputCodigo);
			//Thread.sleep(500);
			inputCodigo.clear();
			inputCodigo.sendKeys(codigo);
		}
		
		public void definirPresupuesto(String montoPresupuesto) {
			checkDefinir.click();
			inputPresupuesto.sendKeys(montoPresupuesto);
		}
		
		public void notificarPresupuesto(boolean activo) {
			 try {
				 checkNotificarPresupuesto.isDisplayed();
		     } catch (NoSuchElementException e) {
		          // Si no se encuentra el elemento, se lanza una excepción
		          System.out.println("Error: El check 'Notificar por correo presupuesto utilizado al' no está presente en la página.");
		        } 
			if (activo = true) {
			waitForWebElementToBeClickable(checkNotificarPresupuesto);
			checkNotificarPresupuesto.click();
			}
		}
		
		public void definePrimerPorcentajePresupuesto(String porcentaje) {
			porcentaje1.sendKeys(porcentaje);
		}
		
		public void defineSegundoPorcentajePresupuesto(String porcentaje) {
			porcentaje2.sendKeys(porcentaje);
		}
		
		public void defineTercerPorcentajePresupuesto(String porcentaje) {
			porcentaje3.sendKeys(porcentaje);
		}
		
		public void notificarServicio(String Responsable) throws InterruptedException {
			waitForWebElementToBeClickable(inputNotificarSi);;
			inputNotificarSi.click();
			selectResponsableCC.click();
			inputResponsableCC.sendKeys(Responsable);
			Thread.sleep(600);
			primerResponsableCC.click();
		}
		
		public void seleccionaTipoCorreo(String opcion) {
			waitForWebElementToBeClickable(selectTipoCorreo);
			inputNotificarSi.click();
			selectTipoCorreo.click();
			if (opcion.equalsIgnoreCase("todos")) {
				selectTipoCorreoTodos.click();
			}else if (opcion.equalsIgnoreCase("creacion")) {
				selectTipoCorreoCreacion.click();
			}else if (opcion.equalsIgnoreCase("termino")) {
				selectTipoCorreoTermino.click();
			}else if (opcion.equalsIgnoreCase("cancelacion")) {
				selectTipoCorreoCancelacion.click();
			}else if (opcion.equalsIgnoreCase("")) {
				selectTipoCorreo.click();
			}else if (opcion.equalsIgnoreCase("Creacion, Termino") || opcion.equalsIgnoreCase("Termino, Creacion")) {
				selectTipoCorreoCreacion.click();
				selectTipoCorreoTermino.click();
			}else if (opcion.equalsIgnoreCase("Termino, Cancelacion	") || opcion.equalsIgnoreCase("Cancelacion, Termino")) {
				selectTipoCorreoCancelacion.click();
				selectTipoCorreoTermino.click();
			}
			selectTipoCorreo.click();
		}
		
		public List<WebElement> obtenerMensajesError() {
		    return driver.findElements(By.xpath("//div[@class='alert alert-danger']"));
		}
		
		public String identificadorUnico() {
			String identificador = UUID.randomUUID().toString().substring(0, 7);
			return identificador;
		}
		
		public List<WebElement> obtenerListaNombres() {

			// Retorna la lista de nombres presentes en la grilla
			waitForElementToAppear(columnaNombreBy);
			return columnaNombre;
		}
		
		public String obtieneNombreFormulario(WebDriver driver) {
			waitForWebElementToAppear(inputNombre);
			
			// Usamos JavascriptExecutor para obtener el value directamente del elemento
		    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		    String nombre = (String) jsExecutor.executeScript("return arguments[0].value;", inputNombre);
		    
			return nombre;
		}
		
		public String obtieneCodigoFormulario(WebDriver driver) {
			waitForWebElementToAppear(inputCodigo);

			// Usamos JavascriptExecutor para obtener el value directamente del elemento
		    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		    String codigo = (String) jsExecutor.executeScript("return arguments[0].value;", inputCodigo);
		    
			return codigo;
		}
		
		public boolean obtieneEstadoFormulario() {
			waitForWebElementToAppear(checkEstado);
			return checkEstadoObtener.isSelected();
		}
		
		public boolean obtieneCheckNotificar() {
			
			waitForWebElementToAppear(checkNotificarSi); 
			
			if (checkNotificarSi.isSelected()) {
				return true;
			}else {
				return false;
			}
		}
		
		public String obtieneResponsableCC() {
			waitForWebElementToAppear(selectResponsableCC);
			return selectResponsableCC.getAttribute("title");
		}
		
		public String obtieneOpcionTipoCorreo() {
			String opcionMarcada = null;
			waitForWebElementToAppear(selectTipoCorreo);
			selectTipoCorreo.click();
			
			//Verifica que opciones hay marcadas en el select (Unica opcion)
			if (selectTipoCorreoCreacion.getAttribute("aria-selected") == "true" 
				&& selectTipoCorreoTermino.getAttribute("aria-selected") == "false"
				&& selectTipoCorreoCancelacion.getAttribute("aria-selected") == "false") {
				opcionMarcada = "Creacion";
			}else if (selectTipoCorreoCreacion.getAttribute("aria-selected") == "false" 
					&& selectTipoCorreoTermino.getAttribute("aria-selected") == "true"
					&& selectTipoCorreoCancelacion.getAttribute("aria-selected") == "false") {
				opcionMarcada = "Termino";
			}else if (selectTipoCorreoCreacion.getAttribute("aria-selected") == "false" 
					&& selectTipoCorreoTermino.getAttribute("aria-selected") == "false"
					&& selectTipoCorreoCancelacion.getAttribute("aria-selected") == "true") {
				opcionMarcada = "Cancelacion";
			}
			
			//Verifica que opciones hay marcadas en el select (Multiples opciones)
			if (selectTipoCorreoCreacion.getAttribute("aria-selected") == "true" 
				&& selectTipoCorreoTermino.getAttribute("aria-selected") == "true"
				&& selectTipoCorreoCancelacion.getAttribute("aria-selected") == "false") {
				opcionMarcada = "Creacion, Termino";
			}else if (selectTipoCorreoCreacion.getAttribute("aria-selected") == "true" 
					&& selectTipoCorreoTermino.getAttribute("aria-selected") == "false"
					&& selectTipoCorreoCancelacion.getAttribute("aria-selected") == "true") {
				opcionMarcada = "Creacion, Cancelacion";
			}else if (selectTipoCorreoCreacion.getAttribute("aria-selected") == "false" 
					&& selectTipoCorreoTermino.getAttribute("aria-selected") == "true"
					&& selectTipoCorreoCancelacion.getAttribute("aria-selected") == "true") {
				opcionMarcada = "Termino, Cancelacion";
			}else if (selectTipoCorreoCreacion.getAttribute("aria-selected") == "true" 
					&& selectTipoCorreoTermino.getAttribute("aria-selected") == "true"
					&& selectTipoCorreoCancelacion.getAttribute("aria-selected") == "true") {
				opcionMarcada = "Todos";
			}
			
			selectTipoCorreo.click();
			return opcionMarcada;
		}
		
		public boolean obtieneCheckPresupuesto() {
			//waitForWebElementToBeClickable(checkDefinir);
			
			if (checkDefinir.isSelected()) {
				return true;
			}else {
				return false;
			}
		}
		
		public String obtieneMontoPresupuesto(WebDriver driver) {
		    waitForWebElementToAppear(inputPresupuesto);
		    
		    // Usamos JavascriptExecutor para obtener el value directamente del elemento
		    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		    String monto = (String) jsExecutor.executeScript("return arguments[0].value;", inputPresupuesto);
		    
		    return monto;
		}
		
		public boolean obtieneCheckNotificarPresupuesto() {
			waitForWebElementToAppear(checkNotificarPresupuesto);
			
			if (checkNotificarPresupuesto.isSelected()) {
				return true;
			}else {
				return false;
			}
		}
		
		public String obtienePrimerPorcentajePresupuesto(WebDriver driver) {
			waitForWebElementToAppear(porcentaje1);
			
			// Usamos JavascriptExecutor para obtener el value directamente del elemento
		    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		    String p1 = (String) jsExecutor.executeScript("return arguments[0].value;", porcentaje1);
		    
		    return p1;
			}
		
		public String obtieneSegundoPorcentajePresupuesto(WebDriver driver) {
			waitForWebElementToAppear(porcentaje2);
			
			// Usamos JavascriptExecutor para obtener el value directamente del elemento
		    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		    String p2 = (String) jsExecutor.executeScript("return arguments[0].value;", porcentaje2);
		    
		    return p2;
			}
		
		public String obtieneTercerPorcentajePresupuesto(WebDriver driver) {
			waitForWebElementToAppear(porcentaje3);
			
			// Usamos JavascriptExecutor para obtener el value directamente del elemento
		    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		    String p3 = (String) jsExecutor.executeScript("return arguments[0].value;", porcentaje3);
		    
		    return p3;
			}
		
		
	// IDENTIFICADORES PARA LOS TEST DE CREACION Y MODIFICACION //
		
//		public String obtieneNroCC() {
//	        Properties prop = new Properties();
//	        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
//	            prop.load(input);
//	            return prop.getProperty(NRO_CC_KEY, "1");
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	            return "1"; // Valor predeterminado
//	        }
//	    }
//
//		public void guardaNroCC(String nroCC) {
//		    Properties prop = new Properties();
//		    try (InputStream input = new FileInputStream(CONFIG_FILE)) {
//		        // Cargar todas las propiedades existentes
//		        prop.load(input);
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }
//
//		    try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
//		        // Actualizar la propiedad específica
//		        prop.setProperty(NRO_CC_KEY, nroCC);
//		        // Guardar todas las propiedades nuevamente
//		        prop.store(output, null);
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }
//		}
//		
//		public String obtieneNroCodigo() {
//	        Properties prop = new Properties();
//	        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
//	            prop.load(input);
//	            return prop.getProperty(NRO_CODIGO_KEY, "1");
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	            return "1"; // Valor predeterminado
//	        }
//	    }
//
//		public void guardaNroCodigo(String nroCodigo) {
//		    Properties prop = new Properties();
//		    try (InputStream input = new FileInputStream(CONFIG_FILE)) {
//		        // Cargar todas las propiedades existentes
//		        prop.load(input);
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }
//
//		    try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
//		        // Actualizar la propiedad específica
//		        prop.setProperty(NRO_CC_KEY, nroCodigo);
//		        // Guardar todas las propiedades nuevamente
//		        prop.store(output, null);
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }
//		}
//	    
//		public String obtieneNroModif() {
//	        Properties prop = new Properties();
//	        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
//	            prop.load(input);
//	            return prop.getProperty(NRO_MODIF_KEY, "0");
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	            return "1"; // Valor predeterminado
//	        }
//	    }
//
//		public void guardaNroModif(String nroModifCC) {
//		    Properties prop = new Properties();
//		    try (InputStream input = new FileInputStream(CONFIG_FILE)) {
//		        // Cargar todas las propiedades existentes
//		        prop.load(input);
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }
//
//		    try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
//		        // Actualizar la propiedad específica
//		        prop.setProperty(NRO_MODIF_KEY, nroModifCC);
//		        // Guardar todas las propiedades nuevamente
//		        prop.store(output, null);
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }
//		}
}
