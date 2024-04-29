package valuesite.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

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
		areacli = new AreaCliente(getDriver());
		componentesReusables = new ComponentesReusables(getDriver());
    }
	
	@BeforeClass
	public void ingresaMantenedor() {
		Login login = new Login(getDriver());
		
		// Pasa como parametro correo y contraseña para ingresar a la web
		login.iniciarSesion("userautocliente@aquivoy.cl", "123456"); //(Grupo cliente)
		//login.iniciarSesion("pasajero@aquivoy.cl", "123456"); //(Sin grupo cliente)

		
		// Pasa como parametro el nombre o codigo del cliente a buscar en el select cliente
		areacli.ingresoMantenedorArea();
	}
	
			//Comprobacion de nombre obligatorio
			@Test(priority = 1)
			public void nombreObligatorioTest() throws IOException, InterruptedException {	
				areacli.errorNombreObligatorio();
			}
			
			//Comprobacion de campo cliente obligatorio
			@Test(priority = 2, retryAnalyzer=valuesite.testcomponents.Retry.class)
			public void clienteObligatorioTest() throws IOException, InterruptedException {	
				areacli.errorClienteObligatorio();
			}

			// Comprobacion de creacion de area activa
			@Test(priority = 3)
			public void creaAreaActivaTest() throws InterruptedException {
				areacli.creaAreaActiva();
			}
		
			// Comprobacion de creacion de area inactiva
			@Test(priority = 4)
			public void creaAreaInactivaTest() throws InterruptedException {
				areacli.creaAreaInactiva();
			}

			// Comprobacion de modificacion de nombre y estado de area
			@Test(priority = 5)
			public void modificaAreaTest() throws InterruptedException {
				areacli.modificaArea();
			}

			// Comprobacion de busqueda de area por nombre
			@Test(priority = 6)
			public void filtroNombreAreaTest() throws InterruptedException {
				areacli.filtroNombreArea();
			}
			
			// Comprobacion de busqueda de area por estado (Activo / Inactivo)
			@Test(priority = 7)
			public void filtroEstadoAreaTest() throws InterruptedException {
				areacli.filtroEstadoArea();
			}
			
			// Comprobacion de busqueda de area por estado (Activo / Inactivo)
			@Test(priority = 8)
			public void selectClienteTest() throws InterruptedException {
				areacli.selectCliente();
			}
}
