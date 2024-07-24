package valuesite.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import valuesite.componentesreusables.ComponentesReusables;

public class ClienteRT extends ComponentesReusables {

	private static final String CONFIG_FILE = "config.properties";
	private static final String NRO_ClIENTE_KEY = "nroClienteRT";
	private static final String NRO_MODIF_KEY = "nroModifClienteRT";

	WebDriver driver;

	public ClienteRT(WebDriver driver) {
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
			@FindBy(xpath = "//a[normalize-space()='Cliente']")
			WebElement tabCentroCosto;
			
		// FILTROS
			
			// RUT
			@FindBy(id="txtRutFind")
			WebElement filtroRut;
			
			// Razon social
			@FindBy(id="txtRazonSocialFind")
			WebElement filtroRazonSocial;
			
			// Filtro Nombre
			@FindBy(id="txtNombreCliFind")
			WebElement filtroNombre;
			
			// Filtro Convenio
			@FindBy(id="txtConvenioFind")
			WebElement filtroConvenio;
			
			// Boton limpiar
			@FindBy(xpath="//button[@onclick='av.clienteEmpresa.limpiar();']")
			WebElement btnLimpiar;
			
			// Boton buscar
			@FindBy(xpath="//button[@onclick='av.clienteEmpresa.filtrar(0)']")
			WebElement btnBuscar;
			
		// GRILLA
			
			// Columna RUT
			@FindBy(xpath="//div[@id='ListaConvenio']//td[1]")
			WebElement columnaRut;
			
			By columnaRutBy = By.xpath("//div[@id='ListaConvenio']//td[1]");
			
			// Columna Razon
			@FindBy(xpath="//div[@id='ListaConvenio']//td[2]")
			WebElement columnaRazon;
			
			By columnaRazonBy = By.xpath("//div[@id='ListaConvenio']//td[2]");
						
			// Columna Nombre
			@FindBy(xpath="//div[@id='ListaConvenio']//td[3]")
			WebElement columnaNombre;
			
			By columnaNombreBy = By.xpath("//div[@id='ListaConvenio']//td[3]");
						
			// Columna Codigo
			@FindBy(xpath="//div[@id='ListaConvenio']//td[4]")
			WebElement columnaCodigo;
			
			By columnaCodigoBy = By.xpath("//div[@id='ListaConvenio']//td[4]");
			
		// FORMULARIO
			
			// Boton Cargar foto
			@FindBy(xpath="//label[normalize-space()='Cargar foto']")
			WebElement btnCargarFoto;
			
			// Boton RUT
			@FindBy(id="txtRut")
			WebElement campoRut;
						
			// Boton Razon social
			@FindBy(id="txtRazonSocial")
			WebElement campoRazonSocial;
						
			// Boton campoNombre
			@FindBy(id="txtNombre")
			WebElement campoNombre;
						
			// Boton select Giro
			@FindBy(xpath="//input[@data-activates='select-options-slcGiro']")
			WebElement selectGiro;
						
			// Primera opcion select Giro
			@FindBy(xpath="//ul[@id='select-options-slcGiro']//li[2]")
			WebElement primeraOpcionGiro;
						
			// Select Holding
			@FindBy(xpath="//input[contains(@data-activates,'select-options-slcPadre')]")
			WebElement selectHolding;
						
			// Primera opcion Holding
			@FindBy(xpath="//ul[@id='select-options-slcPadre']//li[2]")
			WebElement primeraOpcionHolding;
			
			// Check Seleccion multiple area
			@FindBy(id="chkMultiArea")
			WebElement checkMultipleArea;
			
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
			
			// Boton Cancelar
			@FindBy(css="input[value='Cancelar']")
			public
			WebElement btnCancelar;
			
			// Boton Crear
			@FindBy(id="btnCrear")
			public
			WebElement btnCrear;
			
		// PAGINACION
			
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
			
			
}
