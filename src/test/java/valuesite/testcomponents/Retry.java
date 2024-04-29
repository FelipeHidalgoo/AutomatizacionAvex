package valuesite.testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	
	int contador = 0;
	int maxIntento = 1;

	@Override
	public boolean retry(ITestResult result) {
		
		if (contador<maxIntento) {
			contador++;
			return true;
		}
		
		return false;
	}
	
	

}
