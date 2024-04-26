package valuesite.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ConfigReportes {

	public static ExtentReports reporteSetUp() {

		String path = System.getProperty("user.dir") + "\\reportes\\reporte.html";
		ExtentSparkReporter reporte = new ExtentSparkReporter(path);

		reporte.config().setReportName("Resultados de pruebas automatizadas");
		reporte.config().setDocumentTitle("Resultados de pruebas");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporte);
		extent.setSystemInfo("Equipo de test", obtenerHostPrueba());
		try {
			extent.setSystemInfo("Ambiente", obtieneUrlAmbiente());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return extent;
	}

	private static String obtenerHostPrueba() {
		try {
			InetAddress localHost = InetAddress.getLocalHost();
			return localHost.getHostAddress() + " - " + localHost.getHostName();
		} catch (Exception e) {
			e.printStackTrace();
			return "No se pudo obtener la información del host";
		}

	}

	public static String obtieneUrlAmbiente() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\valuesite\\Resources\\GlobalData.properties");
		prop.load(fis);
		return prop.getProperty("URLQA");
	}
	
	

}
