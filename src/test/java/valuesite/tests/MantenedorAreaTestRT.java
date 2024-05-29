package valuesite.tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import valuesite.pageobjects.Login;
import valuesite.componentesreusables.ComponentesReusables;
import valuesite.pageobjects.AreaCliente;
import valuesite.pageobjects.AreaRT;
import valuesite.testcomponents.BaseTest;

public class MantenedorAreaTestRT extends BaseTest {

	AreaRT areart;
	
	@BeforeTest
	public void setUp() {
		areart = new AreaRT(getDriver());
	}

	@BeforeClass
	public void ingresaMantenedor() {
		Login login = new Login(getDriver());

		// Pasa como parametro correo y contraseña para ingresar a la web
		login.iniciarSesion("automrt@aquivoy.cl", "123456");

		// Pasa como parametro el nombre o codigo del cliente a buscar en el select
		// cliente
		areart.ingresoMantenedorArea("001");
	}

	// Comprobacion de nombre obligatorio
	@Test(priority = 1)
	public void nombreObligatorioTest() throws IOException, InterruptedException {
		areart.errorNombreObligatorio();
	}

	// Comprobacion de creacion de area activa
	@Test(priority = 2)
	public void creaAreaActivaTest() throws InterruptedException {
		areart.creaAreaActiva();
	}

	// Comprobacion de creacion de area inactiva
	@Test(priority = 3)
	public void creaAreaInactivaTest() throws InterruptedException {
		areart.creaAreaInactiva();
	}

	// Comprobacion de modificacion de nombre y estado de area
	@Test(priority = 4)
	public void modificaAreaTest() throws InterruptedException {
		areart.modificaArea();
	}

	// Comprobacion de busqueda de area por nombre
	@Test(priority = 5)
	public void filtroNombreAreaTest() throws InterruptedException {
		areart.filtroNombreArea();
	}

	// Comprobacion de busqueda de area por estado (Activo / Inactivo)
	@Test(priority = 6)
	public void filtroEstadoAreaTest() throws InterruptedException {
		areart.filtroEstadoArea();
	}
}
