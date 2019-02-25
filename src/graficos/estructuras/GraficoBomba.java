package graficos.estructuras;

import javax.swing.ImageIcon;

/**
 * Clase que modela la gr�fica de la bomba.
 * @author Asencio Victor, Gutierrez Gabriel, Iurchuk Joaqu�n
 */
public class GraficoBomba extends GraficoEstructuras {
	
	/**
	 * Constructor de clase.
	 * Crea la entidad gr�fica de la bomba con una posici�n en el tablero.
	 * @param x Posicion l�gica horizontal de la bomba.
	 * @param y Posicion l�gica vertical de la bomba.
	 */
	public GraficoBomba() {
		super();		
		
		imagen = new ImageIcon(this.getClass().getResource("/recursos/bomb.png"));	
		
	}	
}