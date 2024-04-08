package valuesite.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AreaCliente extends AreaRT{
	
	WebDriver driver;

	private static final String PROPERTIES_FILE = "config.properties";
	private static final String NUMERO_AREA_KEY = "numeroArea";
	private static final String NUMERO_AREA2_KEY = "numeroArea2";
	private static final String NUMERO_MODIFICACION_KEY = "numeroModificacion";

	public AreaCliente(WebDriver driver) {
		
		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}
	
	// PAGE FACTORY
	
	// Filtro nombre de area
		@FindBy(id = "txtNombreFind")
		WebElement filtroNombre;
		
	// Filtro estado (Select)
		@FindBy(xpath = "(//input[@data-activates='select-options-slcEstadoFind'])[1]")
		WebElement selectEstados;
		
	
	// PAGE FACTORY

}
