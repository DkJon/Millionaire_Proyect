package proyect.com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JuegoGUI extends javax.swing.JFrame implements ActionListener, WindowListener {

	/**
	 * Para que pueda convertir la clase en bits para enviarlo por la red
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * La interfaz ActionListener sirve para crear programas dinÃ¡micos y
	 * permitir la interacciÃ³n del usuario. El window listener es un objeto que
	 * se encarga de escuchar los eventos que se ejecutan que tienen que ver con
	 * la ventana. Estos eventos son como si esta activa,minimizada,maximizada,
	 * abierta,se cierra.
	 */
	private JScrollPane pnlDes;
	private JTextArea txtPreguntas;
	private JLabel lblDineroG;
	private JLabel[] lblRespuestas;
	private JButton[] btnRespuestas;
	private JButton btnSiguientePreg;
	private JButton btnSalir;
	private JButton btn50;
	private JButton btnLlamada;
	private JButton btnPublico;

	private int numPreg = 0;
	private Container cont;
	private String nombre;
	private String imagenP = "src/files/";

	private JuegoM mg;

	private final int Num_Respuestas = 4;

	public JuegoGUI(JuegoM m) {
		mg = m;
		nombre = JOptionPane.showInputDialog("Ingrese su nombre");
		cont = getContentPane();

		cont.setLayout(new BorderLayout());

		JPanel pnlCentral = new JPanel();
		pnlCentral.setLayout(new GridLayout(2, 1));
		ImagePanel logo = new ImagePanel("src/files/millonario.jpg", 645, 285);
		pnlCentral.add(logo);
		pnlCentral.add(panelRespuesta());
		cont.add(iniPanelSuperior(), BorderLayout.NORTH);
		cont.add(pnlCentral, BorderLayout.CENTER);
		cont.add(iniPanelInferior(), BorderLayout.SOUTH);

		addWindowListener(this); // Funcion de realizar eventos en windows
		setTitle("Quien Quiere Ser Millonario ");
		setLocation(0, 300);
		setSize(645, 720);
		// Propiedades de dimensiones Generales
		setResizable(false);
		setVisible(true);
	}

	// Crea un panel que contiene las preguntas y respuestas.
	private JComponent panelRespuesta() {

		JPanel pnlRespuesta = new JPanel();

		ImagePanel pnlCentral = new ImagePanel("src/files/wiki.jpg", 645, 175);
		JPanel pnlSur = new JPanel();
		JPanel pnlNorte = new JPanel();

		pnlRespuesta.setLayout(new BorderLayout());
		// txtPreguntas = new JTextArea(4, 45); tamaÃ±o de dimensiÃ³n ejes x,y
		// (largo/ancho)
		txtPreguntas = new JTextArea(3, 45);
		txtPreguntas.setFont(new Font("Courier", Font.PLAIN, 18));
		// Estilo de Fuente
		txtPreguntas.setWrapStyleWord(true);
		// establece el estilo de envolver usado si el Ã¡rea de texto estÃ¡
		// terminando lÃ­neas.
		txtPreguntas.setForeground(Color.white);
		// Establece el color de los componentes
		txtPreguntas.setBackground(Color.DARK_GRAY);
		txtPreguntas.setText("                ¡¡¡BIENVENIDO A QUIEN QUIERE SER MILLONARIO!!!   "
				+ "\n  PARA INICIAR EL JUEGO ACCIONE EL BOTON SIGUIENTE PREGUNTA");
		// Establece el background(fondo) del componene.Es del textbox.
		txtPreguntas.setEditable(false);
		// Propiedad permite determinar si se puede o no manipular
		// informacion.Editar el contenido.

		// hace que la pregunta se desplace en caso de que la pregunta sea muy
		// larga.
		pnlDes = new JScrollPane(txtPreguntas);
		// La funcion de plnNorte esabarcar un margen al rededor del textbox con
		// la finalidad de dar mÃ¡s estilo y orden.
		pnlNorte.setBackground(Color.BLACK);
		pnlNorte.add(pnlDes);

		// pnlNorth.setPreferredSize (new Dimension (460,70)); hace referencia
		// al contenedor de la pregunta
		pnlNorte.setPreferredSize(new Dimension(460, 87));
		// pnlNorth.setPreferredSize (new Dimension (410,120));

		lblDineroG = new JLabel(

		"Jugador: " + nombre + "                                              Dinero Actual: "
				+ mg.obtenerDineroGanado());
		pnlSur.add(lblDineroG);
		pnlSur.setBackground(Color.BLACK);
		lblDineroG.setForeground(Color.white);

		iniBtnRespuestas();
		iniLblRespuestas();

		String[] s = { "", "", "", "" };
		ponerLblResp(s);

		for (int i = 0; i < Num_Respuestas; i++) {

			btnRespuestas[i].setPreferredSize(new Dimension(51, 30));// Edición
																		// tamaño
																		// del
																		// boton.
			lblRespuestas[i].setPreferredSize(new Dimension(255, 80));// Edición
																		// tamaño
																		// del
																		// boton.

			btnRespuestas[i].setBackground(Color.BLACK);// Color de los
														// botones de la
														// respuesta
			btnRespuestas[i].setForeground(Color.WHITE);// Color de las
														// respuestas
														// desplegadas
			lblRespuestas[i].setForeground(Color.WHITE);// Color de las
														// respuestas
														// desplegadas
			pnlCentral.add(btnRespuestas[i]);
			pnlCentral.add(lblRespuestas[i]);
		}

		activarBtnRespuesta(false);
		// BorderLayout: un contenedor, organizar y cambiar el tamaÃ±o de sus
		// componentes para caber en cinco regiones : norte, sur, este, oeste y
		// centro.
		pnlRespuesta.add(pnlNorte, BorderLayout.NORTH);
		pnlRespuesta.add(pnlCentral, BorderLayout.CENTER);
		pnlRespuesta.add(pnlSur, BorderLayout.SOUTH);

		return pnlRespuesta;
	}

	// Es un panel que contiene los botones "Salir" y "Proxima Pregunta"
	private JComponent iniPanelInferior() {

		JPanel pnlInferior = new JPanel();
		pnlInferior.setLayout(new GridLayout(1, 2));

		Icon pregunta = new ImageIcon(imagenP + "question.png");
		// Imagen en el boton del panel inferior: Siguiente Pregunta
		// Icon pregunta = new ImageIcon(imagenP + "question.gif");
		// btnSiguientePreg = new
		// JButton("<html><font size = -1><b><u>N</u>ext Question", ppregunta);
		btnSiguientePreg = new JButton("<html><font size = -1><b><u>S</u>iguiente Pregunta", pregunta);
		btnSiguientePreg.addActionListener(this);

		Icon parar = new ImageIcon(imagenP + "stop.png");

		btnSalir = new JButton("<html><b><u>Retirarse</u>", parar);
		// Imagen en el boton del panel inferior: Salir
		// Icon parar = new ImageIcon(imagenP + "stop.gif");
		// btnSalir = new JButton("<html><b><u>Q</u>uit", parar);
		btnSalir.addActionListener(this);

		pnlInferior.add(btnSiguientePreg);
		pnlInferior.add(btnSalir);

		return pnlInferior;
	}

	private JComponent iniPanelSuperior() {

		JPanel pnlSuperior = new JPanel();
		pnlSuperior.setLayout(new GridLayout(1, 2));

		Icon medio = new ImageIcon(imagenP + "50.png");
		btn50 = new JButton(medio);

		btn50.addActionListener(this);
		btn50.setPreferredSize(new Dimension(10, 60));

		Icon publico = new ImageIcon(imagenP + "publico.jpg");
		btnPublico = new JButton(publico);
		btnPublico.addActionListener(this);
		btnPublico.setPreferredSize(new Dimension(10, 60));

		Icon llamada = new ImageIcon(imagenP + "llamada.jpg");
		btnLlamada = new JButton(llamada);
		btnLlamada.addActionListener(this);
		btnLlamada.setPreferredSize(new Dimension(10, 60));

		pnlSuperior.add(btn50);
		pnlSuperior.add(btnPublico);
		pnlSuperior.add(btnLlamada);
		pnlSuperior.add(new TimerJFrame());

		return pnlSuperior;
	}

	// Crea la etiqueta usada para mostrar las respuestas.
	private void iniLblRespuestas() {

		lblRespuestas = new JLabel[Num_Respuestas];

		for (int i = 0; i < Num_Respuestas; i++) {
			lblRespuestas[i] = new JLabel();
		}
	}

	// Crea los botones usados para responder preguntas.
	private void iniBtnRespuestas() {

		String[] btnLabels = { "A", "B", "C", "D" };
		btnRespuestas = new JButton[Num_Respuestas];

		for (int i = 0; i < Num_Respuestas; i++) {
			btnRespuestas[i] = new JButton(btnLabels[i]);
			btnRespuestas[i].addActionListener(this);
		}
	}

	// Camnia el texto de las respuestas en las etiquetas
	public void ponerLblResp(String[] answers) {

		if (answers.length < Num_Respuestas) {
			return;
		}

		for (int i = 0; i < Num_Respuestas; i++) {
			lblRespuestas[i].setText(answers[i]);
		}
	}

	// Activa o desactiva todos los botones de respuesta
	public void activarBtnRespuesta(boolean activar) {

		for (int i = 0; i < Num_Respuestas; i++) {
			btnRespuestas[i].setEnabled(activar);
		}
	}

	// Metodo llamado cuando el boton es accionado
	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource() == btn50) {
			Random randomGenerator = new Random();
			int cont=0;
			int y=0;
			while(cont!=2){
				int x = randomGenerator.nextInt(3);
				if(!mg.esResCorrecta(btnRespuestas[x].getText())&& y!=x){
					btnRespuestas[x].setEnabled(false);
					y=y+x;
					cont++;
				}
			}
			btn50.setEnabled(false);
		}

		if (evento.getSource() == btnLlamada) {
			Random randomGenerator = new Random();
			JPanel panel = new JPanel(new GridLayout(0, 1));
			int y = randomGenerator.nextInt(4);
			if (y == 1) {
				panel.add(new JLabel("LLamando a: María Jara"));
			}
			if (y == 2) {
				panel.add(new JLabel("LLamando a: Juan Diego"));
			}
			if (y == 3) {
				panel.add(new JLabel("LLamando a: Andres Molina"));
			}
			if (y == 4) {
				panel.add(new JLabel("LLamando a: Marco José"));
			}
			if (y == 0) {
				panel.add(new JLabel("LLamando a: Jhon Villalobos"));
			}
			panel.add(new CallJframe());
			JOptionPane.showMessageDialog(null, panel);
			btnLlamada.setEnabled(false);
		}
		if (evento.getSource() == btnPublico) {

			Random randomGenerator = new Random();
			int a = randomGenerator.nextInt(100);
			int b = randomGenerator.nextInt(100 - a);
			int c = randomGenerator.nextInt(100 - a - b);
			int d = 100 - a - b - c;

			JPanel panel = new JPanel(new GridLayout(0, 1));
			panel.add(new JLabel("A) : "));
			panel.add(new JTextField(" " + a + "%"));
			panel.add(new JLabel("B) : "));
			panel.add(new JTextField(" " + b + "%"));
			panel.add(new JLabel("C) : "));
			panel.add(new JTextField(" " + c + "%"));
			panel.add(new JLabel("D) : "));
			panel.add(new JTextField(" " + d + "%"));
			JOptionPane.showMessageDialog(null, panel);

			btnPublico.setEnabled(false);
		}

		// Usuario pulsa para conseguir la proxima pregunta
		if (evento.getSource() == btnSiguientePreg) {
			numPreg++; // numero de pregunta actual
			cont.remove(1); // Borra del contenedor papa el hijo de la posicion
							// 1 (imagen mas la pregunta)
			JPanel pnlCentral = new JPanel();
			pnlCentral.setLayout(new GridLayout(2, 1));
			ImagePanel logo = new ImagePanel("src/files/millonario" + numPreg + ".jpg", 645, 285);
			pnlCentral.add(logo);
			pnlCentral.add(panelRespuesta());
			cont.add(pnlCentral, 1); // Anado el nuevo contenedor en la misma
										// posicion de la anterior

			btnSiguientePreg.setEnabled(false);
			activarBtnRespuesta(true);
			mg.siguientePreg();
			txtPreguntas.setText(mg.obtenerResp());

			// Crea una matriz de cadenas utiliza para establecer el texto
			// de la
			// etiqueta
			String[] answers = new String[Num_Respuestas];
			for (int y = 0; y < Num_Respuestas; y++) {
				answers[y] = mg.obtextodePreg(btnRespuestas[y].getText());
			}
			ponerLblResp(answers);
		}

		// Usuario pulsa para salir del juego
		if (evento.getSource() == btnSalir) {
			System.exit(0);
		}

		// Evento cuando un usuario preciona un botton de las respuestas
		for (int d = 0; d < Num_Respuestas; d++) {

			if (evento.getSource() == btnRespuestas[d]) {

				btnSiguientePreg.setEnabled(true);
				boolean correct = mg.esResCorrecta(btnRespuestas[d].getText());

				// El jugador tiene la respuesta correcta
				if (correct) {
					if (mg.haGanado()) {
						txtPreguntas.setText("                             ¡¡¡ FELICIDADES " + nombre + "!!!"
								+ "\n                                GANASTE ₡ 25,000,000 MILLONES");
						lblDineroG.setText((" Jugador: " + nombre
								+ "                                               GANASTE ₡ 25,000,000 MILLONES"));
						btnSiguientePreg.setEnabled(false);
					} else {
						lblDineroG.setText(("Jugador: " + nombre
								+ "                                                 Dinero Ganado: "
								+ mg.obtenerDineroGanado()));
					}
				} // El jugador tiene la respuesta incorrecta
				else {
					txtPreguntas.setText("                                           " + nombre
							+ "\n                                                 ¡¡¡HAS PERDIDO!!!");
					lblDineroG.setText(
							("Jugador: " + nombre + "                                                 Has Perdido"));
					btnSiguientePreg.setEnabled(false);
				}

				activarBtnRespuesta(false);
			}
		}

	}

	// Sale del programa cuando la ventana estÃ¡ cerrada
	public void windowClosing(WindowEvent we) {
		System.exit(0);
	}

	// Esta clase está implementando WindowListener
	// Es requerido proporcionar implementaciones de estos metodos.
	// No pasa nada especial pero cuando los eventos son "desencadenados"
	// trabaja de mejor manera cuando se compila el juego.
	public void windowClosed(WindowEvent we) {
	}

	public void windowActivated(WindowEvent we) {
	}

	public void windowOpened(WindowEvent we) {
	}

	public void windowDeactivated(WindowEvent we) {
	}

	public void windowDeiconified(WindowEvent we) {
	}

	public void windowIconified(WindowEvent we) {
	}

	/**
	 * Gets everything started
	 */
	public static void main(String[] args) {
		new JuegoGUI(new JuegoM());
	}
}
