package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import juego.Juego;

/**
 * Clase encargada de dibujar los paneles y administrar las entradas por teclado
 * para movilizar al Bomberman.
 * 
 * @author Asencio Victor, Gutierrez Gabriel, Iurchuk Joaqu�n
 */

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private static GUI g;
	private Juego miJuego;
	private JLabel labelPuntaje;
	private JLabel labelTiempo;
	private JPanel panelControl;
	private JLabel titulo;
	private JButton botonIniciar;
	private JButton botonControles;
	private JButton botonSalir;


	/**
	 * Metodo que da el puntapie inicial al juego.
	 * 
	 * @param args
	 *            String del metodo main.
	 */
	public static void main(String[] args) {
		g = new GUI("Bomberman");
		g.pantallaBienvenida();
		//		g.iniciarJuego();
		g.repaint();

	}

	/**
	 * Constructor que inicializa la GUI del juego.
	 * @param nombre Titulo que llevar� el frame que contenga a la GUI
	 */
	private GUI(String nombre) {

		super(nombre);

		panelControl = new JPanel();
		panelControl.setLayout(null);
		panelControl.setSize(Const.ANCHO_GUI, Const.ALTO_ENCABEZADO);
		panelControl.setBackground(new Color(200, 200, 200, 200));

		titulo = new JLabel("BOMBERMAN");
		labelPuntaje = new JLabel("SCORE: 0");
		labelTiempo = new JLabel("TIME: " + "00:00");

		this.setVisible(true);
	}

	private void configurarJPanel(JPanel panelControl) {
		setContentPane(panelControl);

		panelControl.setLocation(0, 50);
		panelControl.setLayout(null);
		panelControl.setBackground(new Color(50, 175, 50, 255));

		this.pack();
		this.setLocationRelativeTo(null);


	}

	private void configurarLabels(JPanel panelControl) {


		titulo.setForeground(Color.WHITE);
		labelPuntaje.setForeground(Color.WHITE);
		labelTiempo.setForeground(Color.WHITE);

		titulo.setLocation(0, 0);
		labelPuntaje.setLocation((Const.ANCHO_GUI / 2), 0);
		labelTiempo.setLocation((Const.ANCHO_GUI / 4) * 3, 0);

		titulo.setSize(Const.ANCHO_GUI / 2, Const.ALTO_ENCABEZADO);
		labelPuntaje.setSize(Const.ANCHO_GUI / 4, Const.ALTO_ENCABEZADO);
		labelTiempo.setSize(Const.ANCHO_GUI / 4, Const.ALTO_ENCABEZADO);

		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		labelTiempo.setHorizontalAlignment(SwingConstants.CENTER);

		Font customFont = loadArcadeFont();
		labelTiempo.setFont(customFont);
		titulo.setFont(customFont);
		labelPuntaje.setFont(customFont);

		titulo.setBackground(new Color(180, 180, 180));
		titulo.setOpaque(true);
		labelPuntaje.setBackground(new Color(180, 180, 180));
		labelPuntaje.setOpaque(true);
		labelTiempo.setBackground(new Color(180, 180, 180));
		labelTiempo.setOpaque(true);


		panelControl.add(titulo);
		panelControl.add(labelPuntaje);
		panelControl.add(labelTiempo);
	}

	private Font loadArcadeFont() {
		Font customFont = null;
		try {
			URL url = getClass().getResource("/recursos/fonts/arcade.ttf");
			customFont = Font.createFont(Font.TRUETYPE_FONT, url.openStream()).deriveFont(20f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}

		return customFont;
	}

	public void actualizarPuntaje(int puntaje) {
		labelPuntaje.setText("SCORE: " + puntaje);
		repaint();
	}

	public void actualizarTiempo(int minutos, int seg) {
		labelTiempo.setText("TIME: " + String.format("%02d", minutos) + ":" + String.format("%02d", seg));
		repaint();
	}

	/**
	 * Recibe un evento de teclado, identifica a que direcci�n corresponde y le
	 * avisa al juego que mueva a Bomberman apropiadamente.
	 * 
	 * @param keyEvent Evento de teclado recibido.
	 */
	private void mover(KeyEvent keyEvent) {

		if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE) {
			miJuego.getBomberman().colocarBomba();

		} else {
			int direccion;
			switch (keyEvent.getKeyCode()) {
			case KeyEvent.VK_UP:
				direccion = Const.MOVIMIENTO_ARRIBA;
				break;
			case KeyEvent.VK_DOWN:
				direccion = Const.MOVIMIENTO_ABAJO;
				break;
			case KeyEvent.VK_LEFT:
				direccion = Const.MOVIMIENTO_IZQUIERDA;
				break;
			case KeyEvent.VK_RIGHT:
				direccion = Const.MOVIMIENTO_DERECHA;
				break;
			default:
				direccion = Const.MOVIMIENTO_ARRIBA;
				break;
			}

			miJuego.moverBomberman(direccion);
		}
	}

	public void pantallaBienvenida(){

		setPreferredSize(new Dimension(Const.ANCHO_GUI, Const.ALTO_GUI + Const.ALTO_ENCABEZADO));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null); 
		setBounds(0, 0, Const.ANCHO_GUI, Const.ALTO_GUI);
		panelControl.setBorder(new EmptyBorder(Const.EMPTY_BORDER, Const.EMPTY_BORDER, Const.EMPTY_BORDER, Const.EMPTY_BORDER));
		
		JLabel fondo = new JLabel(new ImageIcon(getClass().getResource("/recursos/fondo2.png")));
		fondo.setLayout(null);
		fondo.setSize(Const.ANCHO_GUI, Const.ALTO_GUI);
		
		loadArcadeFont();
		botonIniciar = new JButton();
		botonIniciar.setText("<html><font face='Press Start 2P'>Iniciar</font></html>");
		makeButtonFlat(botonIniciar);
		botonControles = new JButton();
		botonControles.setText("<html><font face='Press Start 2P'>Controles</font></html>");
		makeButtonFlat(botonControles);
		botonSalir = new JButton();
		botonSalir.setText("<html><font face='Press Start 2P'>Salir</font></html>");
		makeButtonFlat(botonSalir);
		
		
		botonIniciar.setSize(150, 30);
		botonControles.setSize(150, 30);
		botonSalir.setSize(150, 30);
		
		botonIniciar.setLocation( Const.ANCHO_GUI/4-75 , Const.ALTO_GUI-100);
		botonControles.setLocation( (Const.ANCHO_GUI/4)*2-75 , Const.ALTO_GUI-100);
		botonSalir.setLocation( (Const.ANCHO_GUI/4)*3-75 , Const.ALTO_GUI-100);

		fondo.add(botonIniciar);
		fondo.add(botonControles);
		fondo.add(botonSalir);
		
		getContentPane().add(fondo);

		this.setLocationRelativeTo(null);
		repaint();

		botonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundPlayer.click();
				g.iniciarJuego();

			}
		});
		
		botonControles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundPlayer.click();
				mostrarControles();

			}
		});

		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundPlayer.click();
				System.exit(0);

			}
		});
	}
	
	public void mostrarControles(){
		
		JFrame controles = new JFrame("Controles");
		controles.setSize(700, 400);
		controles.setLayout(null);
		controles.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		controles.setVisible(true);
		
		JLabel fondo = new JLabel(new ImageIcon(this.getClass().getResource("/recursos/fondoControl.png")));
		fondo.setLayout(null);
		fondo.setSize(700, 400);
		
		controles.setLocationRelativeTo(null);
		controles.setResizable(false);
		
		JButton botonAtras = new JButton();
		botonAtras.setText("<html><font face='Press Start 2P'>Atr�s</font></html>");
		botonAtras.setSize(150, 30); 
		botonAtras.setLocation(50,300);
		makeButtonFlat(botonAtras);
		
		JLabel indicacionesUp = new JLabel("FLECHA ARRIBA: MOVIMIENTO HACIA ARRIBA");
		JLabel indicacionesDown = new JLabel("FLECHA ABAJO: MOVIMIENTO HACIA ABAJO");
		JLabel indicacionesLeft = new JLabel("FLECHA IZQUIERDA: MOVIMIENTO HACIA LA IZQUIERDA");
		JLabel indicacionesRight = new JLabel("FLECHA DERECHA: MOVIMIENTO HACIA LA DERECHA");
		JLabel indicacionesSpace = new JLabel("ESPACIO: COLOCAR BOMBA");
		
		indicacionesUp.setLocation(300, 250);
		indicacionesUp.setSize(300, 10);
		indicacionesDown.setLocation(300, 265);
		indicacionesDown.setSize(300, 10);
		indicacionesLeft.setLocation(300, 280);
		indicacionesLeft.setSize(300, 10);
		indicacionesRight.setLocation(300, 295);
		indicacionesRight.setSize(300, 10);
		indicacionesSpace.setLocation(300, 310);
		indicacionesSpace.setSize(300, 10);
//		indicaciones.setOpaque(true);
		
		fondo.add(indicacionesUp);
		fondo.add(indicacionesDown);
		fondo.add(indicacionesLeft);
		fondo.add(indicacionesRight);
		fondo.add(indicacionesSpace);
		
		fondo.add(botonAtras);
		
		controles.getContentPane().add(fondo);
		
		botonAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SoundPlayer.click();
				controles.dispose();
			}
		});
		
		
		
		
		
		
	}



	private void makeButtonFlat(JButton boton) {
		boton.setBorderPainted(false);
		boton.setFocusPainted(false);
		boton.setContentAreaFilled(false);
	}

	public void iniciarJuego(){
		panelControl = new JPanel();
		panelControl.setLayout(null);
		panelControl.setSize(Const.ANCHO_GUI, Const.ALTO_ENCABEZADO);
		panelControl.setBackground(new Color(200, 200, 200, 200));

		configurarLabels(panelControl);

		configurarJPanel(panelControl);

		actualizarTiempo(0, 0);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent keyEvent) {
				mover(keyEvent);
			}
		});
		
		this.requestFocusInWindow();
		miJuego = new Juego(this);
		miJuego.iniciarJuego();
		repaint();



	}
}