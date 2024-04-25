package valuesite.testcomponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import valuesite.resources.ConfigReportes;
import org.openqa.selenium.WebDriver;

public class ListenersTestNG extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent = ConfigReportes.reporteSetUp();

	@Override
	public void onFinish(ITestContext result) {
		// TODO Auto-generated method stub
		extent.flush();
	}

	@Override
	public void onStart(ITestContext result) {
		System.out.println("\n----------------------------------------------------");
		System.out.println("COMENZANDO EL TEST: \n" + result.getName().toUpperCase() + "\n");
		
		//Reporte
		test = extent.createTest(result.getName());

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
	    Throwable throwable = result.getThrowable();
	    
	    try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    // Detecta si el error fue de codigo o de prueba fallida
	    if (throwable instanceof AssertionError) {
	        // La falla está relacionada con una aserción en la prueba
	    	System.out.println("\nPrueba " + result.getName() + ": \u001B[31mFALLIDA\u001B[0m \n");
	    } else {
	        // La falla está relacionada con un error de código
	        System.out.println("La prueba del metodo " + result.getName() + " falló debido a un error de código, limite de tiempo o cierre de ventana temprano \n");
	    }
	    
	    //test.fail("Prueba " +result.getName()+ ": Fallida\n");
	    test.fail(result.getName());
	    test.fail(throwable);
	    
	    // Toma screenshot del momento del error y adjuntarlo al reporte
	    String filePath = null;
		try {
			filePath = tomarCaptura(result.getName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    test.addScreenCaptureFromPath(filePath, result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		 System.out.println("\n ------------- Pruebas de " +result.getName()+ " ------------- \n");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//System.out.println("********************");
		System.out.println("\nPrueba "+ result.getName() +": EXITOSA \n");
		//test.log(Status.PASS, "Prueba " +result.getName()+ ": Exitosa");
		test.log(Status.PASS, result.getName());
		//System.out.println("********************");
	}
}
