package juego.estadosCelda;

import javax.swing.ImageIcon;

import graficos.estructuras.GraficoCeldaTransitable;
import juego.Celda;
import personajes.Bomberman;
import personajes.Enemigo;

/**
 * Clase que modela un tipo de EstadoCelda efectivo: No transitable
 * @author Asencio Victor, Gutierrez Gabriel, Iurchuk Joaqu�n
 *
 */
public class EstadoTransitable extends EstadoCelda {

	/**
	 * Constructor de un EstadoTransitable
	 * Se utiliza para modelar el estado de una celda que es libremente transitable
	 * @param x posicion x dentro de la matriz de celdas 
	 * @param y posicion y dentro de la matriz de celdas
	 */
	public EstadoTransitable() {
		super(new GraficoCeldaTransitable());
	}

	@Override
	public int destruir(Celda c) {
		
		c.getLabel().setIcon(new ImageIcon(this.getClass().getResource("/recursos/ExplosionGif2.gif")));
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				c.getLabel().setIcon(null);
			}
		}, 1000);
		
		c.matarBomberman();
		return c.destruirEnemigos();
	}

	@Override
	public void avanzar(Bomberman bomberman, Celda celdaSiguiente, int dir) {
		
		
		if (celdaSiguiente.hayEnemigos()) {
			bomberman.matar();
			System.out.println("El bomberman colisiona con un enemigo");
		} else {
			if (bomberman.getLock() == false) {
				bomberman.lock();
				bomberman.getCelda().eliminarBomberman();
				bomberman.setCelda(celdaSiguiente);
				celdaSiguiente.agregarBomberman(bomberman);

				bomberman.moverGrafica(dir);
			} 
		}
	}

	@Override
	public void avanzar(Enemigo enemigo, Celda celdaSiguiente, int dir) {

		if (celdaSiguiente.hayBomberman()) {
			if (enemigo.getLock() == false) {
				enemigo.lock();
				System.out.println("El enemigo colisiona con bomberman");
				celdaSiguiente.matarBomberman();
				enemigo.getCelda().eliminarEnemigo(enemigo);
				enemigo.setCelda(celdaSiguiente);
				celdaSiguiente.agregarEnemigo(enemigo);

				enemigo.moverGrafica(dir);
			}
		} else {
			if (enemigo.getLock() == false) {
				enemigo.lock();
				enemigo.getCelda().eliminarEnemigo(enemigo);
				enemigo.setCelda(celdaSiguiente);
				celdaSiguiente.agregarEnemigo(enemigo);

				enemigo.moverGrafica(dir);
			}
		}
	}
}
