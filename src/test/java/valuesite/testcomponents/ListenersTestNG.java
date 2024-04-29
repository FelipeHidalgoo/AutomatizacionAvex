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
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // Manejo de hilos
	
	WebDriver driver = getDriver();

	@Override
	public void onFinish(ITestContext result) {
		// TODO Auto-generated method stub
		extent.flush();
	}

	@Override
	public void onStart(ITestContext result) {
		System.out.println("\n----------------------------------------------------");
		System.out.println("COMENZANDO EL TEST: \n" + result.getName().toUpperCase() + "\n");

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
	    extentTest.get().fail(result.getName());
	    extentTest.get().fail(throwable);
	    
	    // Toma screenshot del momento del error y adjuntarlo al reporte
	    String filePath = null;
		try {
			filePath = tomarCaptura(result.getName(), getDriver());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 extentTest.get().addScreenCaptureFromPath(filePath, result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		 System.out.println("\n ------------- Pruebas de " +result.getName()+ " ------------- \n");
		
		 //Reporte
		 test = extent.createTest(result.getName());
		 extentTest.set(test); // id unico de hilo
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//System.out.println("********************");
		System.out.println("\nPrueba "+ result.getName() +": EXITOSA \n");
		//test.log(Status.PASS, "Prueba " +result.getName()+ ": Exitosa");
		 extentTest.get().log(Status.PASS, result.getName());
		//System.out.println("********************");
	}
}
