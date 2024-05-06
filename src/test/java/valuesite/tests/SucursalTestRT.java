package valuesite.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import valuesite.componentesreusables.ComponentesReusables;
import valuesite.pageobjects.Login;
import valuesite.pageobjects.ModificacionPinRT;
import valuesite.pageobjects.SucursalRT;
import valuesite.testcomponents.BaseTest;

public class SucursalTestRT extends BaseTest{
	
	SucursalRT sucursal;
	ComponentesReusables componentesReusables;
	
	@BeforeTest
	public void setUp() {
		sucursal = new SucursalRT(getDriver());
		componentesReusables = new ComponentesReusables(getDriver());
    }
    
    @BeforeClass
    public void ingresaMantenedor() {
		Login login = new Login(getDriver());
		
		// Pasa como parametro correo y contraseña para ingresar a la web
		login.iniciarSesion("userauto@aquivoy.cl", "123456");

		sucursal.ingresoMantenedorSucursales();
	}
    
    @Test
    public void nombreObligatorio() {
    	sucursal.seleccionaCiudad(1);
    	sucursal.seleccionaDireccion("cerro aguja", 1);
    }

}
