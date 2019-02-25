package graficos.estructuras;

import javax.swing.ImageIcon;

/**
 * Clase que modela la gr�fica del estado de una celda transitable.
 * @author Asencio Victor, Gutierrez Gabriel, Iurchuk Joaqu�n
 */
public class GraficoCeldaTransitable extends GraficoEstructuras {

	/**
	 * Constructor de clase.
	 * Crea la entidad gr�fica de una celda transitable con una posici�n en el tablero.
	 * @param x Posicion l�gica horizontal de la celda.
	 * @param y Posicion l�gica vertical de la celda.
	 */
	public GraficoCeldaTransitable() {
		super();

		imagen = new ImageIcon();		
	}
}