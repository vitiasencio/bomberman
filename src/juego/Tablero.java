package juego;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import gui.Const;
import gui.GUI;
import juego.estadosCelda.EstadoBomba;
import juego.estadosCelda.EstadoBombality;
import juego.estadosCelda.EstadoDestruible;
import juego.estadosCelda.EstadoFatality;
import juego.estadosCelda.EstadoMasacrality;
import juego.estadosCelda.EstadoNoDestruible;
import juego.estadosCelda.EstadoSpeedUp;
import juego.estadosCelda.EstadoTransitable;

/**
 * Clase que se encarga de modelar el tablero de juego, con todos sus atributos
 * @author Asencio Victor, Gutierrez Gabriel, Iurchuk Joaqu�n
 *
 */
public class Tablero {

	protected Juego miJuego;
	protected Celda [][] misCeldas;	
	protected GUI gui;
	private HashMap<Celda, Boolean> mapeoControl;
	private List<Celda> misParedesDestruibles;

	/**
	 * Constructor que inicializa un tablero de juego
	 * @param juego referencia al juego principal
	 * @param gui referencia a la clase que maneja la interfaz grafica
	 */
	public Tablero (Juego juego, GUI gui) {
		miJuego = juego;
		mapeoControl = new HashMap<Celda, Boolean>();
		misParedesDestruibles = new LinkedList<Celda>();

		misCeldas = new Celda[Const.CANT_CELDAS_ANCHO][Const.CANT_CELDAS_ALTO];
		this.gui = gui;

		for (int i = 0; i < Const.CANT_CELDAS_ANCHO; i++) {
			for (int j = 0; j < Const.CANT_CELDAS_ALTO	; j++) {
				misCeldas[i][j] = new Celda(i, j, this);				
				misCeldas[i][j].setEstado(new EstadoTransitable());	//Al crear todas las celdas asumo que todas son transitables				
			}
		}
		
		mapeoControl.put(misCeldas[1][1], true);
		mapeoControl.put(misCeldas[2][1], true);
		mapeoControl.put(misCeldas[1][2], true);
		mapeoControl.put(misCeldas[Const.CANT_CELDAS_ANCHO-2][Const.CANT_CELDAS_ALTO-2], true);
		mapeoControl.put(misCeldas[Const.CANT_CELDAS_ANCHO-2][Const.CANT_CELDAS_ALTO-3], true);
		mapeoControl.put(misCeldas[Const.CANT_CELDAS_ANCHO-3][Const.CANT_CELDAS_ALTO-2], true);

		crearNoDestruibles();

		distribuirDestruibles();
		

		//luego de setear todos los estados a la celda, agrego el JLabel de cada celda a la gui
		for (int i = 0; i < Const.CANT_CELDAS_ANCHO; i++)
			for (int j = 0; j < Const.CANT_CELDAS_ALTO; j++){			 
				gui.add(misCeldas[i][j].getLabel());		
			}

	}

	/**
	 * Devuelve la celda que est� ubicada en las coordenadas recibidas 
	 * @param x coordenada x de la celda a devolver
	 * @param y coordenada y de la celda a devolver
	 * @return la celda que est� ubicada en las coordenadas recibidas
	 */
	public Celda getCelda (int x, int y) {
		return misCeldas[x][y];
	}
	
	public HashMap<Celda, Boolean> getMapeoControl() {
		return mapeoControl;
	}
	
	

	/**
	 * Se encarga de recorrer la matriz de celdas y configurarles en ellas el estado no destruible segun corresponda
	 */
	private void crearNoDestruibles () {

		for(int i=0 ; i<Const.CANT_CELDAS_ANCHO;i++){
			for(int j=0 ; j<Const.CANT_CELDAS_ALTO;j++){
				if(i==0) { misCeldas[i][j].setEstado(new EstadoNoDestruible(i, j)); mapeoControl.put(misCeldas[i][j], true); }
				if(j==0) { misCeldas[i][j].setEstado(new EstadoNoDestruible(i, j)); mapeoControl.put(misCeldas[i][j], true); }
				if(i==Const.CANT_CELDAS_ANCHO-1) { misCeldas[i][j].setEstado(new EstadoNoDestruible(i, j)); mapeoControl.put(misCeldas[i][j], true); }
				if(j==Const.CANT_CELDAS_ALTO-1) { misCeldas[i][j].setEstado(new EstadoNoDestruible(i, j)); mapeoControl.put(misCeldas[i][j], true); }
				if((i%2==0)&&(j%2==0)) { misCeldas[i][j].setEstado(new EstadoNoDestruible(i, j)); mapeoControl.put(misCeldas[i][j], true); }
			}
		}
	}

	/**
	 * Se encarga de recorrer la matriz de celdas y configurarles en ellas el estado destruible segun corresponda
	 */
	private void distribuirDestruibles () {

		int cantSpeedUp = Const.CANT_POWERUPS_SPEEDUP;
		int cantFatality = Const.CANT_POWERUPS_FATALITY;
		int cantBombality = Const.CANT_POWERUPS_BOMBALITY;
		int cantMasacrality = Const.CANT_POWERUPS_MASACRALITY;
		
		Double d =  (Double) (Const.CANT_CELDAS_ALTO * Const.CANT_CELDAS_ANCHO*Const.PORCENTAJE_DESTRUIBLES);
		int cantCeldas = d.intValue();

		Random rnd = new Random();

		while(cantCeldas>0){
			Celda nueva = misCeldas[rnd.nextInt(Const.CANT_CELDAS_ANCHO)][rnd.nextInt(Const.CANT_CELDAS_ALTO)];

			if(mapeoControl.get(nueva)==null){
				
				if(cantSpeedUp>0){
					nueva.setEstado(new EstadoDestruible(new EstadoSpeedUp()));
					cantSpeedUp--;
				}else
					if(cantFatality>0){
						nueva.setEstado(new EstadoDestruible(new EstadoFatality()));
						cantFatality--;
					}else
						if(cantBombality>0){
							nueva.setEstado(new EstadoDestruible(new EstadoBombality()));
							cantBombality--;
						}else
							if(cantMasacrality>0){
								nueva.setEstado(new EstadoDestruible(new EstadoMasacrality()));
								cantMasacrality--;
							}else{
								nueva.setEstado(new EstadoDestruible());

							}
				cantCeldas--;
				mapeoControl.put(nueva, true);
				misParedesDestruibles.add(nueva);
			}
		}
	}



	
	/**
	 * Devuelve una lista con las paredes destruibles actualmente en pie
	 * @return una lista con las paredes destruibles actualmente en pie
	 */
	public List<Celda> getMisParedesDestruibles(){
		controlarCantidadDeParedesDestruibles();
		return misParedesDestruibles;
	}

	/**
	 * Se encarga de fijarse si la cantidad de paredes destruibles es cero.
	 * En tal caso, declara fin de juego y el usuario gan�. 
	 */
	private void controlarCantidadDeParedesDestruibles() {
		if (misParedesDestruibles.size() == 0)
			miJuego.paredesDestruiblesAgotadas();
	}

	/**
	 * Devuelve una referencia al juego principal
	 * @return referencia al juego principal
	 */
	public Juego getJuego() {
		return miJuego;
	}

	/**
	 * Metodo llamado al finalizar una explosion por la bomba.
	 * Le devuelve la posibilidad de colocar una bomba mas a bomberman
	 */
	public void devolverBombaABomberman() {
		miJuego.getBomberman().aumentarBombasDisponibles();
	}

	/**
	 * M�todo invocado por un personaje para colocar una Bomba en el tablero
	 * @param x coordenada x de la celda donde debe ser colocada la bomba
	 * @param y coordenada x de la celda donde debe ser colocada la bomba
	 * @param alcance alcance de la bomba que sera colocada en esta celda
	 */
	public void colocarBomba (int x, int y, int alcance) {
		Celda celda = getCelda(x, y);
		Bomba bomba = new Bomba(celda, alcance, this);
		celda.setEstado(new EstadoBomba());		

		bomba.comenzarDetonacion();
	}

	public void notificarMuerteBomberman() {
		miJuego.notificarMuerteBomberman();
	}
}