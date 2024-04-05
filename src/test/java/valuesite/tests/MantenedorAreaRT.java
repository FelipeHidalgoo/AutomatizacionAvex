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
import org.testng.annotations.Test;

import valuesite.pageobjects.Login;
import valuesite.pageobjects.AreaRT;
import valuesite.testcomponents.BaseTest;

public class MantenedorAreaRT extends BaseTest{

	//public static void main(String[] args) throws InterruptedException, IOException {
	
//	public void AreaRT() throws IOException, InterruptedException {
//		
//		// Inicializar Driver
//		BaseTest basetest = new BaseTest();
//		
//		// Lanza navegador y entra al ambiente (URL de ambiente y navegador a ocupar se configuran en GlobalData.properties)
//		basetest.lanzarNavegador("URLQA");
//		
//
//		// Crear objeto de Login
//		Login login = new Login(driver);
//
//		// Iniciar sesion con las credenciales deseadas
//		login.iniciarSesion("userauto@aquivoy.cl", "123456");
//		
//		// Crear objeto de Area
//		AreaRT areart = new AreaRT(driver);
//		
//		// Ingresa a mantenedor de area y busca el cliente entregado en el parametro 
//		areart.ingresoMantenedorArea("8215");
//	}
		//AreaRT areart = new AreaRT(driver);
	
		@BeforeClass
		public void ingresaMantenedor() {
			Login login = new Login(driver);
			login.iniciarSesion("userauto@aquivoy.cl", "123456");
			
			AreaRT areart = new AreaRT(driver);
			areart.ingresoMantenedorArea("8215");
		}
		
		//Comprobacion de nombre obligatorio
		@Test(priority = 1)
		public void nombreObligatorioTest() throws IOException, InterruptedException {	
			AreaRT areart = new AreaRT(driver);
			areart.errorNombreObligatorio();
		}

		// Comprobacion de creacion de area activa
		@Test(priority = 2)
		public void creaAreaActivaTest() throws InterruptedException {
			AreaRT areart = new AreaRT(driver);
			areart.creaAreaActiva();
		}
		
		// Comprobacion de creacion de area inactiva
		@Test(priority = 3)
		public void creaAreaInactivaTest() throws InterruptedException {
			AreaRT areart = new AreaRT(driver);
			areart.creaAreaInactiva();
		}
		
		// Comprobacion de modificacion de nombre y estado de area
		@Test(priority = 4)
		public void modificaAreaTest() throws InterruptedException {
			AreaRT areart = new AreaRT(driver);
			areart.modificaArea();
		}
		
		// Comprobacion de busqueda de area por nombre
		@Test(priority = 5)
		public void filtroNombreAreaTest() throws InterruptedException {
			AreaRT areart = new AreaRT(driver);
			areart.filtroNombreArea();
		}
		
		// Comprobacion de busqueda de area por estado (Activo / Inactivo)
		@Test(priority = 6)
		public void filtroEstadoAreaTest() throws InterruptedException {
			AreaRT areart = new AreaRT(driver);
			areart.filtroEstadoArea();
		}
}
