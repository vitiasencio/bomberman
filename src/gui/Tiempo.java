package gui;

/**
 * Objeto encargado de mantener actualizado el tiempo.
 * @author Asencio Victor, Gutierrez Gabriel, Iurchuk Joaqu�n
 */

public class Tiempo implements Runnable {

	private int segundos;
	private Thread t;
	private GUI gui;
	private boolean detenerReloj;
	
	/**
	 * Constructor de clase.
	 * Setea la variable de clase que setea el conteo y guarda referencia de la gui a modificar.
	 */
	public Tiempo(GUI gui) {
		segundos = 0;
		this.gui = gui;
		detenerReloj = true;
	}
	
	public int getSegundos() {
		return segundos;
	}
	
	public void iniciarReloj(){
		t = new Thread(this);
		detenerReloj = false;
		t.start();
	}
	
	@Override
	public void run() {
		try {
			while(!detenerReloj){
				Thread.sleep(1000);
				segundos++;
				
				int minutos = segundos/60;
				int seg = segundos - 60 * (segundos/60);
				gui.actualizarTiempo(minutos, seg);
			}
			
		} catch (InterruptedException e){
		}
	}
	
	public void detenerTiempo(){
		detenerReloj = true;
	}
}