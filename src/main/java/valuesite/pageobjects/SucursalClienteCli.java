package valuesite.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import valuesite.componentesreusables.Acciones;
import valuesite.componentesreusables.ComponentesReusables;

public class SucursalClienteCli extends ComponentesReusables{
	
	private static final String CONFIG_FILE = "config.properties";
    private static final String NRO_SUCURSAL_KEY = "nroSucursalClienteCli";
    private static final String NRO_MODIF_KEY = "nroModifSucClienteCli";
	
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

		// Boton buscar filtros
			@FindBy(id = "btnBuscar")
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
	}
	

}
