package valuesite.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import valuesite.componentesreusables.ComponentesReusables;
import valuesite.pageobjects.Login;
import valuesite.pageobjects.SucursalClienteCli;
import valuesite.testcomponents.BaseTest;

public class SucursalClienteTestCli extends BaseTest{
	
	SucursalClienteCli suc;
	ComponentesReusables cp;
	
	@BeforeTest
	public void setUp() {
		suc = new SucursalClienteCli(getDriver());
		cp = new ComponentesReusables(getDriver());
	}
	
	@BeforeClass
	public void ingresaMantenedor() throws InterruptedException {
		Login login = new Login(getDriver());

		// Pasa como parametro correo y contraseña para ingresar a la web
		login.iniciarSesion("automcli@aquivoy.cl", "123456");

		// Ingresa al mantenedor una vez la sesion este iniciada, pasa como parametro el cliente a buscar
		suc.ingresoMantenedorSucursalesCliente();
	}
	
	@Test
	public void estadoCheck() {
		System.out.println(suc.estadoCheckActivo.isSelected());
		suc.marcaCheckActivo.click();
		System.out.println(suc.estadoCheckActivo.isSelected());
		
	}

}
