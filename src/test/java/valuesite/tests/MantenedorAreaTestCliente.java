package valuesite.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import valuesite.componentesreusables.ComponentesReusables;
import valuesite.pageobjects.AreaCliente;
import valuesite.pageobjects.AreaRT;
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
	
	//Comprobacion de nombre obligatorio
			@Test(priority = 1)
			public void nombreObligatorioTest() throws IOException, InterruptedException {	
				areacli.errorNombreObligatorio();
			}

			// Comprobacion de creacion de area activa
			@Test(priority = 2)
			public void creaAreaActivaTest() throws InterruptedException {
				areacli.creaAreaActiva();
			}
		
			// Comprobacion de creacion de area inactiva
			@Test(priority = 3)
			public void creaAreaInactivaTest() throws InterruptedException {
				areacli.creaAreaInactiva();
			}

			// Comprobacion de modificacion de nombre y estado de area
			@Test(priority = 4)
			public void modificaAreaTest() throws InterruptedException {
				areacli.modificaArea();
			}

			// Comprobacion de busqueda de area por nombre
			@Test(priority = 5)
			public void filtroNombreAreaTest() throws InterruptedException {
				areacli.filtroNombreArea();
			}
			
			// Comprobacion de busqueda de area por estado (Activo / Inactivo)
			@Test(priority = 6)
			public void filtroEstadoAreaTest() throws InterruptedException {
				areacli.filtroEstadoArea();
			}
}
