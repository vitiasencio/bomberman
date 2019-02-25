package graficos.personajes;

import javax.swing.ImageIcon;

import gui.Const;

/**
 * Clase que modela la gr�fica del enemigo Altair.
 * @author Asencio Victor, Gutierrez Gabriel, Iurchuk Joaqu�n
 */
public class GraficoRugulos extends GraficoPersonaje {

	/**
	 * Constructor de clase.
	 * Crea la entidad gr�fica del enemigo con una posici�n en el tablero
	 * y una velocidad determinada.
	 * @param velocidad Velocidad del enemigo.
	 * @param x Posicion l�gica horizontal del enemigo.
	 * @param y Posicion l�gica vertical del enemigo.
	 */
	public GraficoRugulos(int velocidad, int x, int y) {
		super(velocidad, x, y);
		
		getLabel().setBounds(x * Const.ANCHO_CELDA, y * Const.ALTO_CELDA+Const.ALTO_ENCABEZADO, Const.ANCHO_CELDA, Const.ALTO_CELDA);
		
		imagenes[Const.MOVIMIENTO_ARRIBA] = new ImageIcon(this.getClass().getResource("/recursos/RleftGif.gif"));		
		imagenes[Const.MOVIMIENTO_ABAJO] = new ImageIcon(this.getClass().getResource("/recursos/RrightGif.gif"));
		imagenes[Const.MOVIMIENTO_IZQUIERDA] = new ImageIcon(this.getClass().getResource("/recursos/RleftGif.gif"));
		imagenes[Const.MOVIMIENTO_DERECHA] = new ImageIcon(this.getClass().getResource("/recursos/RrightGif.gif"));
		imagenes[4] = new ImageIcon(this.getClass().getResource("/recursos/RleftGif.gif"));		
		imagenes[5] = new ImageIcon(this.getClass().getResource("/recursos/RrightGif.gif"));
		imagenes[6] = new ImageIcon(this.getClass().getResource("/recursos/RleftGif.gif"));
		imagenes[7] = new ImageIcon(this.getClass().getResource("/recursos/RrightGif.gif"));
		grafico.setIcon(imagenes[7]);
	}	
}