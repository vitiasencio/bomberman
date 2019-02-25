package graficos.estructuras;

import javax.swing.ImageIcon;

/**
* Clase que modela la gr�fica de la pared no destruible.
* @author Asencio Victor, Gutierrez Gabriel, Iurchuk Joaqu�n
*/

public class GraficoParedDestruible extends GraficoEstructuras {

	/**
	 * Constructor de clase.
	 * Crea la entidad gr�fica de una pared no destruible en una posici�n en el tablero.
	 * @param x Posicion l�gica horizontal de la pared.
	 * @param y Posicion l�gica vertical de la pared.
	 */
	public GraficoParedDestruible() {
		super();
		
		imagen = new ImageIcon(this.getClass().getResource("/recursos/murodestruible.png"));			
	}	
}