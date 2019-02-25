package graficos.powerups;

import javax.swing.ImageIcon;

import graficos.estructuras.GraficoEstructuras;

/**
 * Clase que modela la gr�fica de la bomba.
 * @author Asencio Victor, Gutierrez Gabriel, Iurchuk Joaqu�n
 */
public class GraficoFatality extends GraficoEstructuras {
	
	/**
	 * Constructor de clase.
	 * Crea la entidad gr�fica de la bomba con una posici�n en el tablero.
	 * @param x Posicion l�gica horizontal de la bomba.
	 * @param y Posicion l�gica vertical de la bomba.
	 */
	public GraficoFatality() {
		super();
		
		imagen = new ImageIcon(this.getClass().getResource("/recursos/fatality.png"));	
		
	}	
}