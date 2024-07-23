package valuesite.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import valuesite.componentesreusables.ComponentesReusables;

public class CentroCostoRT extends ComponentesReusables {

	private static final String CONFIG_FILE = "config.properties";
	private static final String NRO_CC_KEY = "nroCentroCostoRT";
	private static final String NRO_MODIF_KEY = "nroModifCentroCostoRT";

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
			@FindBy(xpath = "//div[@class='bs-container btn-group bootstrap-select show-tick open']//li[2]//a[1]//span[1]")
			WebElement seleccionCliente;

		// Boton administrar para realizar la busqueda
			@FindBy(css = "button[onclick=\"admDatosCliente.changeCliente('SucursalCliente')\"]")
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
}
