package valuesite.testcomponents;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenersTestNG implements ITestListener {

	@Override
	public void onFinish(ITestContext result) {
		// TODO Auto-generated method stub

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
	    if (throwable instanceof AssertionError) {
	        // La falla está relacionada con una aserción en la prueba
	    	System.out.println("\nPrueba " + result.getName() + ": \u001B[31mFALLIDA\u001B[0m \n");
	    } else {
	        // La falla está relacionada con un error de código
	        System.out.println("La prueba del metodo " + result.getName() + " falló debido a un error de código, limite de tiempo o cierre de ventana temprano \n");
	    }
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
		//System.out.println("********************");
	}
}
