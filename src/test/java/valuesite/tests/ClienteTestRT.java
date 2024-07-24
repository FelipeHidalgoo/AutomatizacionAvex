package valuesite.tests;

import org.testng.annotations.BeforeTest;

import valuesite.componentesreusables.ComponentesReusables;
import valuesite.pageobjects.CentroCostoRT;
import valuesite.pageobjects.ClienteRT;
import valuesite.testcomponents.BaseTest;

public class ClienteTestRT extends BaseTest{

	ClienteRT cli;
	ComponentesReusables cp;

	@BeforeTest
	public void setUp() {
		cli = new ClienteRT(getDriver());
		cp = new ComponentesReusables(getDriver());
	}
	
	

}
