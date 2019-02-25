package gui;

/**
 * Clase pensada para administrar las constantes que se van a utilizar en todo el juego.  
 * @author Asencio Victor, Gutierrez Gabriel, Iurchuk Joaqu�n
 */
public class Const {

	public static final int MOVIMIENTO_ARRIBA    = 0;
	public static final int MOVIMIENTO_ABAJO     = 1;
	public static final int MOVIMIENTO_IZQUIERDA = 2;
	public static final int MOVIMIENTO_DERECHA   = 3;
	
	public static final int EMPTY_BORDER = 5;
	
	public static final int CANT_CELDAS_ANCHO = 31;
	public static final int CANT_CELDAS_ALTO = 13;	
	
	public static final int ANCHO_CELDA = 32;
	public static final int ALTO_CELDA = 32;
	
	public static final int ANCHO_GUI = ANCHO_CELDA * CANT_CELDAS_ANCHO+6;
	public static final int ALTO_GUI = ALTO_CELDA * (CANT_CELDAS_ALTO+1)-5;
	
	public static final int ALTO_ENCABEZADO = 50;
	
	public static final Double PORCENTAJE_DESTRUIBLES = 0.2;
	public static final int TIEMPO_DETONACION = 2500;
	
	public static final int VELOCIDAD_INICIAL_BMAN = 1;
	
	public static final int CANT_POWERUPS_FATALITY = 3;
	public static final int CANT_POWERUPS_BOMBALITY = 3;
	public static final int CANT_POWERUPS_SPEEDUP = 4;
	public static final int CANT_POWERUPS_MASACRALITY = 1;
	
	public static final int CANT_RUGULOS = 3;
	public static final int CANT_ALTAIR = 2;
	public static final int CANT_SIRIUS = 1;
	
	public static final int DURACION_MASACRALITY = 5000;
	public static final int COUNTDOWN_EXPLOSION = 1500;
}