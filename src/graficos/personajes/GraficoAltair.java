package graficos.personajes;

import javax.swing.ImageIcon;

import gui.Const;

/**
 * Clase que modela la gr�fica del enemigo Altair.
 * @author Asencio Victor, Gutierrez Gabriel, Iurchuk Joaqu�n
 */
public class GraficoAltair extends GraficoPersonaje {

	/**
	 * Constructor de clase.
	 * Crea la entidad gr�fica del enemigo con una posici�n en el tablero
	 * y una velocidad determinada.
	 * @param velocidad Velocidad del enemigo.
	 * @param x Posicion l�gica horizontal del enemigo.
	 * @param y Posicion l�gica vertical del enemigo.
	 */
	public GraficoAltair(int velocidad, int x, int y) {
		super(velocidad, x, y);
		
		getLabel().setBounds(x * Const.ANCHO_CELDA, y * Const.ALTO_CELDA+Const.ALTO_ENCABEZADO, Const.ANCHO_CELDA, Const.ALTO_CELDA);
		
		imagenes[Const.MOVIMIENTO_ARRIBA] = imagenes[4] = imagenes[5] = imagenes[6] = imagenes[7] = imagenes[Const.MOVIMIENTO_DERECHA] = imagenes[Const.MOVIMIENTO_IZQUIERDA] = imagenes[Const.MOVIMIENTO_ABAJO] = new ImageIcon(this.getClass().getResource("/recursos/AGif.gif"));		
		grafico.setIcon(imagenes[0]);
	}	
}