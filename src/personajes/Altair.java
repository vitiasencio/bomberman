package personajes;

import graficos.personajes.GraficoAltair;
import juego.Celda;
import juego.Tablero;

/**
 * Clase que modela a un tipo efectivo de Enemigo
 * @author Asencio Victor, Gutierrez Gabriel, Iurchuk Joaqu�n
 *
 */
public class Altair extends Enemigo {

	private final static int VELOCIDAD_INICIAL = 1;
	private final static int PUNTAJE = 20;
	
	/**
	 * Constructor de enemigo Altair
	 * @param celda Celda inicial
	 * @param t referencia al tablero principal
	 */
	public Altair (Celda celda, Tablero t) {
		
		super(true, VELOCIDAD_INICIAL, celda, t, PUNTAJE);
		this.miGrafico = new GraficoAltair(1, celda.getX(), celda.getY());
	}
}
