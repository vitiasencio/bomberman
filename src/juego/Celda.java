package juego;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;

import graficos.estructuras.GraficoEstructuras;
import gui.Const;
import juego.estadosCelda.EstadoCelda;
import personajes.Bomberman;
import personajes.Enemigo;

/**
 * Clase encargada de modelar la celda correspondiente a cada posicion l�gica.
 * @author Asencio Victor, Gutierrez Gabriel, Iurchuk Joaqu�n
 */
public class Celda {

	protected int x;
	protected int y;
	protected Tablero miTablero;
	protected EstadoCelda miEstado;
	protected List<Enemigo> misEnemigos;
	protected Bomberman miBomberman;
	protected GraficoEstructuras miGrafico;
	protected JLabel miLabel;
	

	/**
	 * Constructor de clase.
	 * Crea una celda en una posici�n l�gica indicada.
	 * @param x Posicion l�gica horizontal de la celda.
	 * @param y Posicion l�gica vertical de la celda.
	 * @param t Referencia al tablero de juego.
	 */
	public Celda(int x, int y, Tablero t) {

		this.x = x;
		this.y = y;
		miTablero = t;
		miEstado = null;
		misEnemigos = new LinkedList<Enemigo>();
		miBomberman = null;
//		miGrafico = new GraficoCeldaTransitable(x, y);
		miLabel = new JLabel();
		miLabel.setBounds(x * Const.ANCHO_CELDA, y * Const.ALTO_CELDA+Const.ALTO_ENCABEZADO, Const.ANCHO_CELDA, Const.ALTO_CELDA);

	}
	
	/**
	 * Metodo que le indica al estado actual de la celda que mueva
	 * al bomberman en una direcci�n indicada.
	 * @param bomberman Referencia a Bomberman.
	 * @param dir Direcci�n de movimiento.
	 */
	public void avanzar(Bomberman bomberman, int dir) {
		miEstado.avanzar(bomberman, this, dir);
	}

	/**
	 * Metodo que le indica al estado actual de la celda que mueva
	 * al enemigo en una direcci�n indicada.
	 * @param enemigo Referencia al enemigo.
	 * @param dir Direcci�n de movimiento.
	 */
	public void avanzar(Enemigo enemigo, int dir) {
		miEstado.avanzar(enemigo, this, dir);
	}
	
	
	
	/**
	 * Setter para cambiar el estado actual de la celda.
	 * @param e Estado al cual pasar� la celda.
	 */
	public void setEstado(EstadoCelda e) {
		miEstado = e;
		miLabel.setIcon(e.getGrafico().getIcon());
	}
	
	/**
	 * Elimina todos los enemigos ubicados en �sta celda.
	 */
	public int destruirEnemigos() {
		
		int puntaje = 0;
		List<Enemigo> aMatar = new LinkedList<Enemigo>();
		
		for (Enemigo e : misEnemigos){
			aMatar.add(e);
			e.matar();
			puntaje+=e.getPuntaje();
		}
		
		for (Enemigo e : aMatar)
			misEnemigos.remove(e);
		
		miTablero.getJuego().matarEnemigo(this);
		
		return puntaje;
	}
	

	/**
	 * Agrega un enemigo a la lista de enemigos perteneciente a la celda.
	 * @param enemigo Enemigo a agregar a la lista.
	 */
	public void agregarEnemigo(Enemigo enemigo) {
		misEnemigos.add(enemigo);
	}

	/**
	 * Elimina un enemigo de la lista de enemigos perteneciente a la celda.
	 * @param enemigo Enemigo a eliminar de la lista.
	 */
	public void eliminarEnemigo(Enemigo enemigo) {
		misEnemigos.remove(enemigo);
	}

	/**
	 * Agrega a Bomberman a �sta celda.
	 * @param b Referencia a Bomberman.
	 */
	public void agregarBomberman(Bomberman b) {
		miBomberman = b;
	}

	/**
	 * Quita a Bomberman de �sta celda.
	 */
	public void eliminarBomberman() {
		miBomberman = null;
	}

	/**
	 * Retorna la posici�n l�gica horizontal de la celda.
	 * @return Posici�n horizontal.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Retorna la posici�n l�gica vertical de la celda.
	 * @return Posici�n vertical.
	 */
	public int getY() {

		return y;
	}

	/**
	 * Chequea si hay enemigos actualmente en �sta celda.
	 * @return true si hay enemigos, false en caso contrario.
	 */
	public boolean hayEnemigos() {

		return !misEnemigos.isEmpty();
	}

	/**
	 * Chequea si el Bomberman se encuentra en �sta celda.
	 * @return true si el Bomberman est� en �sta celda, false en caso contrario.
	 */
	public boolean hayBomberman() {

		return miBomberman != null;
	}

	/**
	 * M�todo que indica que Bomberman estuvo poniendo su vida en riesgo para salvar al mundo
	 * pero sin �xito, por lo tanto debemos de informar su muerte a trav�s de este comunicado.
	 */
	public void matarBomberman() {

		if (miBomberman != null && miBomberman.estoyVivo()){
			miBomberman.matar();
		}
	}
	
	/**
	 * M�todo que retorna el estado actual de la celda.
	 * @return Estado actual de la celda.
	 */
	public EstadoCelda getEstado() {
		return miEstado;
	}

	/**
	 * Metodo que le indica al estado actual de la celda que 
	 * una bomba afect� a �sta celda y hay que modificar su contenido.
	 */
	public int detonar() {
		return miEstado.destruir(this);
	}
	
	public JLabel getLabel() {
		
		return miLabel;
	}
	
	public Tablero getMiTablero() {
		return miTablero;
	}
	
}