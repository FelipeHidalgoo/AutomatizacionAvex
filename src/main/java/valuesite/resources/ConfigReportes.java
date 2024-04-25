package valuesite.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ConfigReportes {

	public static ExtentReports reporteSetUp() {
		
		String path = System.getProperty("user.dir")+"\\reportes\\reporte.html";
		ExtentSparkReporter reporte = new ExtentSparkReporter(path);
		
		reporte.config().setReportName("Resultados de pruebas automatizadas");
		reporte.config().setDocumentTitle("Resultados de pruebas");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporte);
		extent.setSystemInfo("Tester", "ASUS (Valuesite19) OFICINA");
		return extent;
	}
}
