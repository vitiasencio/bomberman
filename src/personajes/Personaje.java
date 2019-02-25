package personajes;

import java.awt.Point;

import javax.swing.JLabel;

import graficos.personajes.GraficoPersonaje;
import gui.Const;
import juego.Celda;
import juego.Tablero;

/**
 * Clase abstracta que maneja y determina las habilidades esenciales de cada Personaje
 * @author Asencio Victor, Gutierrez Gabriel, Iurchuk Joaqu�n
 */

public abstract class Personaje {

	protected boolean modoAtravesar;
	protected int velocidad;
	protected Celda miCelda;
	protected Tablero miTablero;
	protected boolean estoyVivo;
	protected GraficoPersonaje miGrafico;
	protected boolean lock;

	/**
	 * Constructor de Personaje
	 * @param modoAtravesar True si este personaje puede atravesar paredes, false en caso contrario
	 * @param vel velocidad inicial
	 * @param c celda inicial
	 * @param t referencia al tablero principal
	 */
	protected Personaje(boolean modoAtravesar, int vel, Celda c, Tablero t) {
		this.modoAtravesar = modoAtravesar;
		velocidad = vel;
		miCelda = c;
		miTablero = t;
		estoyVivo = true;
		miGrafico = null;
		lock = false;
	}

	/**
	 * M�todo que se ejecuta cuando a este personaje le ha llegado la hora de morir 
	 */
	public abstract void matar();
	
	/**
	 * Determina si este personaje puede atravesar paredes o no
	 * @return true si puede atravesar paredes, false en caso contrario
	 */
	public boolean enModoAtravesar() {
		return modoAtravesar;
	}

	/**
	 * Le asigna una celda actual a este personaje
	 * @param c celda actual para este personaje
	 */
	public void setCelda(Celda c) {
		miCelda = c;
	}

	/**
	 * Devuelve la celda actual donde se encuentra este personaje
	 * @return la celda actual donde se encuentra este personaje
	 */
	public Celda getCelda() {
		return miCelda;
	}

	/**
	 * Devuelve el label asociado a este personaje
	 * @return JLabel asociado al personaje.
	 */
	public JLabel getLabel() {
		return miGrafico.getLabel();
	}

	/**
	 * Devuelve el Grafico asociado a este personaje
	 * @return Grafica asociada al personaje.
	 */
	public GraficoPersonaje getGrafico() {
		return miGrafico;
	}

	/**
	 * Devuelve la posicion de la celda dentro de la matriz donde esta parado este personaje
	 * @return Un punto que contiene las coordenadas enteras x, y que corresponden a la posicion en la matriz de este personaje 
	 */
	public Point getPos() {
		return miGrafico.getPos();
	}

	/**
	 * Basandose en la celda/posicion actual se calcula la que deberia ser la
	 * siguiente segun la direccion recibida
	 * 
	 * @param dir
	 *            direccion hacia donde se desea avanzar a este personaje
	 * @return la celda que deberia ser la siguiente segun la direccion recibida
	 */
	protected Celda calcularCeldaSiguiente(int dir) {
		int xActual = miCelda.getX();
		int yActual = miCelda.getY();

		switch (dir) {
		case 0: { // arriba
			yActual--;
			if (yActual < 0)
				yActual = 0;
			break;
		}
		case 1: { // abajo
			yActual++;
			if (yActual >= Const.CANT_CELDAS_ALTO)
				yActual = Const.CANT_CELDAS_ALTO - 1;
			break;
		}
		case 2: { // izquierda
			xActual--;
			if (xActual <= 0)
				xActual = 0;
			break;
		}
		case 3: { // derecha
			xActual++;
			if (xActual >= Const.CANT_CELDAS_ANCHO)
				xActual = Const.CANT_CELDAS_ANCHO - 1;
			break;
		}
		}

		return miTablero.getCelda(xActual, yActual);
	}

	/**
	 * M�todo que es invocado cuando se quiere mover la gr�fica asociada a este personaje
	 * @param dir direcci�n a donde debe ser movido este personaje
	 */
	public abstract void moverGrafica(int dir);
	
	/**
	 * Determina si este personaje est� bloqueado para realizar movimientos
	 * @return true si este personaje est� bloqueado para realizar movimientos, false en caso contrario
	 */
	public boolean getLock() {
		return lock;
	}

	/**
	 * Bloquea los movimientos de este personaje
	 */
	public void lock() {
		lock = true;
	}
	
	/**
	 * Determina si este personaje est� vivo
	 * @return true si este personaje est� vivo, false en caso contrario
	 */
	public boolean estoyVivo() {
		return estoyVivo;
	}
}