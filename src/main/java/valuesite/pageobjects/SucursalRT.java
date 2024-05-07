package valuesite.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import valuesite.componentesreusables.Acciones;
import valuesite.componentesreusables.ComponentesReusables;

public class SucursalRT extends ComponentesReusables {

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
	WebElement selectCiudad;
	
	// Ciudad a seleccionar
	WebElement ciudad;

	// Check Activo (formulario)
	@FindBy(id = "rdoActivo1")
	WebElement checkActivo;

	// Check Inactivo (formulario)
	@FindBy(id = "rdoActivo2")
	WebElement checkInactivo;

	// Check Todos (Formulario / Visibilidad de convenios)
	@FindBy(id = "rdoConvenio1")
	WebElement checkTodos;

	// Check Definir (Formulario / Visibilidad de convenios)
	@FindBy(id = "rdoConvenio2")
	WebElement checkDefinir;
	
	// Select convenio (Formulario)
	@FindBy (xpath="//div[@id='dvConvenio']/div/div/button")
	WebElement selectConvenio;
	
	// Searchbox convenio
	@FindBy(xpath="//div[@class='bs-searchbox']/input")
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
	
	// Grilla de opciones direcciones
	@FindBy(id="dropDownListResultados-txtDireccion")
	WebElement opcionesDireccion;
	
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
	WebElement waitingDialog;

	// PAGE FACTORY

	public void ingresoMantenedorSucursales() throws InterruptedException {
		tabAdministracion.click();
		tabSucusales.click();
		waitForWebElementToDisappear(waitingDialog);
	}
	
	public void ingresarNombreFormulario(String nombreSucursal) {
		campoNombre.sendKeys(nombreSucursal);
	}
	
	public boolean obtenerEstado() {
		boolean activo = true;
		boolean inactivo = false;
		waitForWebElementToBeClickable(checkActivo);
		
		if (checkActivo.isSelected()) {
			return activo;
			
		}else if (checkInactivo.isSelected()) {
			return inactivo;
			
		}
		
		return false;
	}
	
	public void seleccionaCiudad(int posicion) {
		selectCiudad.click();
		ciudad = driver.findElement(By.xpath("//div[@class='btn-group bootstrap-select show-tick all-width open']//div[@role='combobox']/ul/li["+posicion+"]"));
		ciudad.click();
	}
	
	public void seleccionaDireccion(String direccion, int posicion) throws InterruptedException {
		campoDireccion.sendKeys(direccion);
		a.presionarEnter(campoDireccion);
		
		//Thread.sleep(1500);
		if (opcionesDireccion.isDisplayed()) {
			waitForWebElementToBeClickable(seleccionDireccion);
			posicion = posicion + 1;
			seleccionDireccion = driver.findElement(By.xpath("//div[@id='dropDownListResultados-txtDireccion']/div["+posicion+"]"));
			seleccionDireccion.click();
		}
	}
	
	public void seleccionaConvenio(String convenio) {
		selectConvenio.click();
		buscaConvenio.sendKeys(convenio);
		opcionConvenio.click();
	}
	
	public void filtraPorNombre(String nombre) {
		waitForWebElementToBeClickable(filtroNombre);
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
	
	
	

}
