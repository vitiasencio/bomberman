package juego;

import java.util.LinkedList;
import java.util.List;

import gui.Const;
import gui.SoundPlayer;

/**
 * Clase encargada de modelar el funcionamiento l�gico de una bomba. 
 * @author Asencio Victor, Gutierrez Gabriel, Iurchuk Joaqu�n
 */
public class Bomba {

	protected int alcance;
	protected Tablero miTablero;
	protected Celda miCelda;

	/**
	 * Constructor de clase.
	 * Crea una bomba en una celda indicada con su alcance correspondiente.
	 * @param c Celda en la cual esta contenida la bomba.
	 * @param alcance Alcance explosivo de la bomba.
	 * @param t Referencia al tablero de juego.
	 */
	public Bomba(Celda c, int alcance, Tablero t) {
		miCelda = c;
		this.alcance = alcance;
		miTablero = t;
	}
	
	/**
	 * Indicador de explosi�n de la bomba con un timer asignado.
	 */
	public void comenzarDetonacion() {
		
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				SoundPlayer.explosionBomba();
				
//				
				//TODO la explosion de la bomba se saltea las paredes noDestruibles cuando el alcance es grande #viti
				List<Celda> celdasAfectadas = calcularCeldasAfectadas();
				celdasAfectadas.add(miCelda);
				for (Celda c : celdasAfectadas){
					int puntaje = c.detonar();
					miTablero.getJuego().aumentarPuntaje(puntaje);
				}				
				miTablero.devolverBombaABomberman();
			}
		}, Const.TIEMPO_DETONACION);
	}
	
	/**
	 * Metodo encargado de calcular las celdas 
	 * que tienen que ser afectadas por la explosi�n de la bomba.
	 * @return Lista de celdas afectadas por la explosi�n.
	 */
	protected List<Celda> calcularCeldasAfectadas() {
		List<Celda> celdasAf = new LinkedList<Celda>();
		celdasAf.add(miCelda);
		for (int i = 1; i <= this.alcance; i++) {
			if(miCelda.getY()-i>=0){
				celdasAf.add(miTablero.getCelda(miCelda.getX(), miCelda.getY()-i));
			}
			if(miCelda.getY()+i<Const.CANT_CELDAS_ALTO){
				celdasAf.add(miTablero.getCelda(miCelda.getX(), miCelda.getY()+i));
			}
			if(miCelda.getX()-i>=0){
				celdasAf.add(miTablero.getCelda(miCelda.getX()-i, miCelda.getY()));
			}
			if(miCelda.getX()+i<Const.CANT_CELDAS_ANCHO){ 
				celdasAf.add(miTablero.getCelda(miCelda.getX()+i, miCelda.getY())); 
			}
		}
		
		return celdasAf;
	}
}
