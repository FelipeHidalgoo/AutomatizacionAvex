package valuesite.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import valuesite.componentesreusables.ComponentesReusables;
import valuesite.pageobjects.AreaCliente;
import valuesite.pageobjects.Login;
import valuesite.pageobjects.ModificacionPinCliente;
import valuesite.testcomponents.BaseTest;

public class MantenedorAreaTestCliente extends BaseTest{
	
	AreaCliente areacli;
	ComponentesReusables componentesReusables;

	@BeforeTest
	public void setUp() {
		areacli = new AreaCliente(driver);
		componentesReusables = new ComponentesReusables(driver);
    }
	
	@BeforeClass
	public void ingresaMantenedor() {
		Login login = new Login(driver);
		
		// Pasa como parametro correo y contraseña para ingresar a la web
		login.iniciarSesion("userautocliente@aquivoy.cl", "123456");
		
		// Pasa como parametro el nombre o codigo del cliente a buscar en el select cliente
		areacli.ingresoMantenedorArea();
	}
	
	@Test
	public void testsillo() {
		componentesReusables.waitForWebElementToAppear(areacli.checkEstado);
		boolean b1 = areacli.checkEstado.isSelected();
		areacli.campoNombreArea.sendKeys("aaaaaaaa");
		System.out.println(b1);
		
		
		componentesReusables.waitForWebElementToBeClickable(areacli.checkEstado);
		areacli.checkEstado.click();
		
		boolean b2 = areacli.checkEstado.isSelected();
		System.out.println(b2);
	}
}
