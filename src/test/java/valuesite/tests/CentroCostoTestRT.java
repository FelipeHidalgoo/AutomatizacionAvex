package valuesite.tests;

import org.testng.annotations.BeforeTest;

import valuesite.componentesreusables.ComponentesReusables;
import valuesite.pageobjects.CentroCostoRT;
import valuesite.pageobjects.SucursalClienteCli;
import valuesite.testcomponents.BaseTest;

public class CentroCostoTestRT extends BaseTest{

	CentroCostoRT ct;
	ComponentesReusables cp;

	@BeforeTest
	public void setUp() {
		ct = new CentroCostoRT(getDriver());
		cp = new ComponentesReusables(getDriver());
	}
}
