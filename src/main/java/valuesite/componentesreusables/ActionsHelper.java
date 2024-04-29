package valuesite.componentesreusables;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsHelper {

	WebDriver driver;

	public ActionsHelper(WebDriver driver) {
		this.driver = driver;
	}

	// Declara la clase de Actions
	public Actions action = new Actions(driver);

	// Método para hacer clic derecho en un elemento
	public void clickDerecho(WebElement element) {
		action.contextClick(element).build().perform();
	}

	// Método para mover el mouse a un elemento
	public void MoverMouseHacia(WebElement element) {
		action.moveToElement(element).build().perform();
	}

	// Método para arrastrar y soltar un elemento
	public void arrastraElemento(WebElement source, WebElement target) {
		action.dragAndDrop(source, target).build().perform();
	}

	// Método para hacer doble clic en un elemento
	public void dobleClick(WebElement element) {
		action.doubleClick(element).build().perform();
	}

	// Método para hacer clic y mantener presionado un elemento
	public void mantenerPresionado(WebElement element) {
		action.clickAndHold(element).build().perform();
	}

	// Método para liberar un clic mantenido
	public void dejarDePresionar() {
		action.release().build().perform();
	}

	// Método para escribir texto en un elemento después de hacer clic
	public void clickEscribe(WebElement element, String text) {
		action.click(element).sendKeys(text).build().perform();
	}

	// Método para hacer clic con el botón izquierdo del mouse
	public void clickIzquierdo(WebElement element) {
		action.click(element).build().perform();
	}

	// Método para desplazar el mouse desde un elemento hasta otro
	public void MouseDeUnElementoAOtro(int xOffset, int yOffset) {
		action.moveByOffset(xOffset, yOffset).build().perform();
	}

	// Método para eliminar el texto en un campo presionando la tecla "Borrar" según
	// la longitud del texto actual
	public void borraTexto(WebElement element, String texto) {

		// Calcular la longitud del texto
		int length = texto.length();

		// Presionar la tecla "Borrar" tantas veces como la longitud del texto
		for (int i = 0; i < length; i++) {
			action.sendKeys(Keys.BACK_SPACE);
		}

		// Realizar las acciones
		action.build().perform();
	}

	// Método para presionar la tecla "Enter" en un elemento
	public void presionarEnter(WebElement element) {
		action.sendKeys(element, Keys.ENTER).build().perform();
	}
}
