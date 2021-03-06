package views;



import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import javax.swing.ButtonGroup;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.border.LineBorder;


public class Ahorcado extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	//creamos un array de strings que pasaremos al campo textField
	private String[] passArr = new String [] {"NOTMYPASS", "MYPASS", "TOOEASY", "SUPERMAN", "BATMAN", "HACKERMAN", 
			"HELLOTHERE", "GENERAL", "KENOBI", "THEEND"};
	
	private ArrayList<String> passArrStrings = new ArrayList<String>();
	
	//pass
	private String pass;
	//array de caracteres de la palabra pass
	private ArrayList<Character> caracteresPass = new ArrayList<Character>();
	//array de botones del teclado
	private ArrayList<JButton> botonesAbecedario = new ArrayList<JButton>();
	
	//array de JLabel 
	private ArrayList<JLabel> labelsPorPass = new ArrayList<JLabel>();
	//palabraSecreta
	private JLabel palabraSecreta = new JLabel("_");
	//label del ahorcado el icono se asigna al clickar alguno de los botones del teclado
	private JLabel lblNewLabel = new JLabel("");
	//botones resolver e iniciar el juego
	private JButton iniciar, resolver;
	//array de letras que obtenemos del ASCII
	private JButton[] letras = new JButton[27];
	
	//array de bombillas (JLabel)
	private JButton[] bombillas = new JButton[5];
	
	//atributos de la partida
	private int fallos = 0;
	
	//METODOS
	//random index del String de pass
	private int randomIndex() {
		Random rnd = new Random();
		int num = rnd.nextInt(passArrStrings.size());
		return num;
	}
	
	//poblamos el array de pass con palabras
	private void poblarPassArray() {
		for(int i = 0; i < passArr.length; i++) {
			passArrStrings.add(passArr[i]);	
		}
	}
	
	private void randomPass() {
		String myPass = "";
		myPass = passArrStrings.get(randomIndex());	
		pass = myPass;
	}
	//metodo que construye un array de chars que compone la palabra pass 
	private void splitPass() {
		for(int i = 0; i < pass.length(); i++) {
			caracteresPass.add(pass.charAt(i));
		}
	}
	// **PENDIENTE** creamos los botones del teclado y los pasamos a la array de botonesAbecedario utilizando los valores ASCII
	private void crearBotones() {
		int numLetrasAbecedario = 27;
		//int del ASCII que equivale a la letra "A"
		int startChartAt = 65;
		//bucle para recorrer el array de letras 
		for(int i = 0; i < numLetrasAbecedario; i++) {
			String letra = "";
			String newChar = String.valueOf((char) startChartAt);
			letra = newChar;
			//en la posicion 14 del abecedario añadimos la letra Ñ que no coincide con el rango recorrido de caracteres dentro de ASCII
			if(i == 14) {
				letra = "Ñ";
			}
			//asignamos el texto de la letra obtenida al boton
			letras[i] = new JButton(letra);
			//asignamos los botones al array de botones
			botonesAbecedario.add(letras[i]);
			//aumentamos el numero de ASCII por 1
			startChartAt++;
		}
	}
	
	//crear bombillas
	private void crearBombillas() {
		for (int i = 0; i < 5; i++) {
			bombillas[i] = new JButton("");
			//asignamos el icono a cada bombilla creada
			bombillas[i].setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/medidas_bombilla.PNG")));
		}
	}

	//Recorremos la array de botones y ejecutamos los metodos pertinentes al hacer el click sobre cada boton
	private void alClickarBotonTeclado() {
			for(JButton boton : botonesAbecedario){
				//añadimos el action listener al boton
				boton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//si la letra clickada está entre las letras de la palabra secreta
					if(siExiste(boton)) {
						//modificamos string palabraSecreta con los valores que nos devuelve el metodo palabraSecretaDisplay
						palabraSecreta.setText(palabraSecretaDisplay());
						//comprobamos si se ha ganado
						siGana();
					} else {
						//si la letra clickada no está entre las letras de la palabra sumamos fallos, mostramos siguiente img y verificamos si ha perdido
						fallos++;	
						lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
						siPierde();
					}
				}
			});
		}
	}
	
	//metodo que muestra la primera letra no visible de la palabra secreta
	private void activarPista() {
		//recorremos labelsPorPass para ver la posicion del primer caracter '_'. 
		for(int z = 0; z < labelsPorPass.size(); z++) {
			//cuando encontremos la posicion:
			if(labelsPorPass.get(z).getText().equals("_")) {
				//cambiamos el primer carater "_" por el caracter de la misma posicion del caracter del array de caracteres 
				labelsPorPass.get(z).setText(caracteresPass.get(z).toString());
				//mostramos el campo de palabrasecreta con la primera letra visible
				palabraSecreta.setText(palabraSecretaDisplay());
				//sumamos un fallo
				fallos++;
				//mostramos imagen actualizada
				lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				//ejecutamos metodos que arrancan si se pierde o gana
				siGana();
				siPierde();
				//salimos del bucle cuando se cumple una vez
				break;
			}
		}
	}

	//metodo que verifica si la letra pulsada está dentro del array de chars que compone la palabra pass
	private boolean siExiste(JButton letra) {
		boolean existe = false;
		//recorremos la array de chars 
		for(int i = 0; i < caracteresPass.size(); i++) {
				if(letra.getText().equals(caracteresPass.get(i).toString())) {
					//si la letra existe, cambiamos el texto de JLabel de la misma posicion donde hay coincidencia
					for(int z = 0; z < labelsPorPass.size(); z++) {
						if(z == i) {
							//si el index del label es igual al index del caracteres donde coincide cambiar el texto del label
							labelsPorPass.get(z).setText(letra.getText());
						}
					}
					existe = true;
				}
		}
		//inhabilitamos el botón
		letra.setEnabled(false);
		return existe;
	}
	//deshabilitar cada boton del teclado
	private void deshabilitarBotones() {
		for(int i = 0; i < botonesAbecedario.size(); i++) {
			botonesAbecedario.get(i).setEnabled(false);
		}
	}
	
	//habilitar cada boton del teclado
	private void habilitarBotones() {
		for(int i = 0; i < botonesAbecedario.size(); i++) {
			botonesAbecedario.get(i).setEnabled(true);
		}
		
	}
	
	//añadir los labels al arraylist de labelsPorPass
	private void poblarLabelsPorPass() {
		int x = 25;
		for(int i = 0; i < caracteresPass.size(); i++) {
			JLabel newLabel = new JLabel("_");
			newLabel.setBounds(x, 45, 50, 50);
			labelsPorPass.add(newLabel);
			//para cada label la posicion x se aumentará en 25
			x+=25;
		}
	}
	
	//palabra secreta que se muestra a medida que se va adivinando
	private String palabraSecretaDisplay() {
		
		String palabraSecretaDisplay = "";
		for(int i = 0; i < labelsPorPass.size(); i++) {
			palabraSecretaDisplay+= labelsPorPass.get(i).getText();
		}
		return palabraSecretaDisplay;
	}
	
	public void iniciarPartida(int fallosAlEmpezarElJuego) {
		//cada nueva partida reiniciamos los atributos
		fallos = fallosAlEmpezarElJuego;
		caracteresPass.clear();
		labelsPorPass.clear();
		pass = "";
		palabraSecreta.setText("_");
		lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
		//volvemos a mostrar las bombillas
		for(int i = 0; i < 5; i++ ) {
			bombillas[i].setVisible(true);
		}
		//poblamos el array con palabras
		poblarPassArray();
		//asignamos el valor del pass a la variable pass con este metodo
		randomPass();
		// dividimos la palabra secreta en un array de chars
		splitPass();
		//habilitamos los botones del teclado
		habilitarBotones();
		//añadimos los labels 
		poblarLabelsPorPass();
		//asignamos la palabra escogiendo aleatoriamente del array de strings y transformamos al uppercase
		palabraSecreta.setText(palabraSecretaDisplay());
		//desactivamos el boton
		iniciar.setEnabled(false);
		
	}
	//mostramos la ventana con opciones de volver a jugar o salir cuando user pierde
	public void siPierde() {
		if(fallos == 10) {
			String[] options = {"Jugar de nuevo", "Salir"};
			
			int opciones = JOptionPane.showOptionDialog(contentPane, "Has perdido, indica que quieres hacer:", "Fin Partida", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			if(opciones == 1) {
				System.exit(1);
			} else if(opciones == 0) {
				//si se pierde se inicia la partida como principiante (10 intentos)
				iniciarPartida(0);
			}
		}
	}
	//mostramos la ventana con opciones de volver a jugar o salir cuando user gana
	public void siGana() {
		if(palabraSecreta.getText().equals(pass) && fallos < 10) {
			String[] options = {"Jugar de nuevo", "Salir"};
			
			int opciones = JOptionPane.showOptionDialog(contentPane, "Has ganado, indica que quieres hacer:", "Fin Partida", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			if(opciones == 1) {
				System.exit(1);
			} else if(opciones == 0) {
				//si se gana se inicia la partida como principiante (10 intentos)
				iniciarPartida(0);
			}
		}
	}
	
	public Ahorcado() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		setTitle("Ahorcado");
		setBounds(600, 160, 700, 800);
		contentPane.setBorder(BorderFactory.createTitledBorder(""));
		
		//PANEL AJUSTES JUEGO(POP-UP)
		//creamos el panel de popUp al hacer el click sobre el boton de iniciar partida
		JPanel ajustesJuego = new JPanel();
		ajustesJuego.setBounds(380, 20, 262, 200);
		ajustesJuego.setVisible(false);
		ajustesJuego.setLayout(null);
		
		//COMPONENTES panel AjustesJuego
		JButton empezar = new JButton("Empezar");
		empezar.setBounds(75, 155, 120, 30);
		ajustesJuego.add(empezar);
		
		JLabel elegirOpciones = new JLabel("Elige el nivel:");
		elegirOpciones.setBounds(75, 15, 120, 30);
		ajustesJuego.add(elegirOpciones);
		
		JRadioButton opcionPrincipante = new JRadioButton("Principiante", false);
		opcionPrincipante.setBounds(75, 45, 120, 30);
		ajustesJuego.add(opcionPrincipante);
		
		JRadioButton opcionIntermedio = new JRadioButton("Intermedio", false);
		opcionIntermedio.setBounds(75, 75, 120, 30);
		ajustesJuego.add(opcionIntermedio);
		
		JRadioButton opcionAvanzado = new JRadioButton("Avanzado", false);
		opcionAvanzado.setBounds(75, 105, 120, 30);
		ajustesJuego.add(opcionAvanzado);
		//juntamos los JRadioButtons
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(opcionPrincipante);
		bgroup.add(opcionIntermedio);
		bgroup.add(opcionAvanzado);
		
		empezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//comprobamos que ha seleccionado el usuario y pasamos el numero de fallos segun lo pulsado a la nueva partida. 
				if(opcionPrincipante.isSelected()) {
					//desactivamos el panel ajustesJuego
					ajustesJuego.setVisible(false);
					iniciarPartida(0);
				} else if(opcionIntermedio.isSelected()) {
					ajustesJuego.setVisible(false);
					iniciarPartida(2);
				} else if(opcionAvanzado.isSelected()) {
					//hacemos el panel ajustesJuego invisible
					ajustesJuego.setVisible(false);
					iniciarPartida(4);
				} else {
					//si ninguna opcion se ha pulsado le mostramos un aviso al usuario
					JOptionPane.showMessageDialog(null, "Tienes que elegir una opción");
				}
			}
		});
		
		//añadimos el componente POP-UP al contentPane
		contentPane.add(ajustesJuego);
		
		//BARRA MENU Y SUS LISTENERS
		JMenuBar barraMenu = new JMenuBar();
		
		JMenu archivo = new JMenu("Archivo");
		JMenu comoJugar = new JMenu("Como Jugar");
		JMenu acercaDe = new JMenu("Acerca de");
		JMenu diccionario = new JMenu("Diccionario");
		
		JMenuItem comoJugarItem = new JMenuItem("Como Jugar");
		JMenuItem acercaDeItem = new JMenuItem("Acerca de");
		JMenuItem palabraNueva = new JMenuItem("Palabra nueva");
		
		JMenuItem nuevoJuego = new JMenuItem("Nuevo juego");
		JMenuItem salir = new JMenuItem("Salir");
		
		nuevoJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//al hacer el click llamamos a la funcion iniciarPartida();
				ajustesJuego.setVisible(true);
				// y desactivamos los botones
				deshabilitarBotones();
			}
		});
		
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//al hacer el click llamamos a la funcion iniciarPartida();
				System.exit(1);
			}
		});
		
		comoJugarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//al hacer el click llamamos a la funcion iniciarPartida();
				JOptionPane.showMessageDialog(contentPane, "Acierta la palabra seleccionando una de las letras del teclado.");
			}
		});
		
		acercaDeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//al hacer el click llamamos a la funcion iniciarPartida();
				JOptionPane.showMessageDialog(contentPane, "Nombre de alumnos: \n \nMyroslav Andreykiv \nNoelia Barrera \nGerard Bonet");
			}
		});
		
		palabraNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nuevaPalabra = JOptionPane.showInputDialog(null, "Introduce una nueva palabra");
				passArrStrings.add(nuevaPalabra.toUpperCase().replace(" ", ""));
			}
		});
		
		archivo.add(nuevoJuego);
		archivo.add(salir);
		
		comoJugar.add(comoJugarItem);
		acercaDe.add(acercaDeItem);
		diccionario.add(palabraNueva);
		
		barraMenu.add(archivo);
		barraMenu.add(diccionario);
		barraMenu.add(comoJugar);
		barraMenu.add(acercaDe);
		
		//aplicamos la barramenu a la ventana principal
		setJMenuBar(barraMenu);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Men\u00FA",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setForeground(Color.WHITE);

		panel_1.setBorder(new LineBorder(UIManager.getColor("CheckBox.foreground")));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Teclado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBackground(Color.WHITE);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.BLACK));
		panel_4.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
							.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)))
					.addGap(38)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 638, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(107, Short.MAX_VALUE))
		);
		
		
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 237, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(155)
					.addComponent(lblNewLabel)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
		JButton letraA = new JButton("A");
		botonesAbecedario.add(letraA);

		
		JButton letraB = new JButton("B");
		botonesAbecedario.add(letraB);

		
		JButton letraC = new JButton("C");
		botonesAbecedario.add(letraC);

		
		JButton letraD = new JButton("D");
		botonesAbecedario.add(letraD);

		
		JButton letraE = new JButton("E");
		botonesAbecedario.add(letraE);

		
		JButton letraF = new JButton("F");
		botonesAbecedario.add(letraF);
		
		JButton letraG = new JButton("G");
		botonesAbecedario.add(letraG);

		JButton letraH = new JButton("H");
		botonesAbecedario.add(letraH);

		JButton letraI = new JButton("I");
		botonesAbecedario.add(letraI);

		
		JButton letraJ = new JButton("J");
		botonesAbecedario.add(letraJ);

		
		JButton letraL = new JButton("L");
		botonesAbecedario.add(letraL);

		
		JButton letraK = new JButton("K");
		botonesAbecedario.add(letraK);

		
		JButton letraM = new JButton("M");
		botonesAbecedario.add(letraM);

		
		JButton letraN = new JButton("N");
		botonesAbecedario.add(letraN);

		
		JButton letraÑ = new JButton("Ñ");
		botonesAbecedario.add(letraÑ);

		JButton letraO = new JButton("O");
		botonesAbecedario.add(letraO);

		
		JButton letraP = new JButton("P");
		botonesAbecedario.add(letraP);

		
		JButton letraQ = new JButton("Q");
		botonesAbecedario.add(letraQ);

		
		JButton letraR = new JButton("R");
		botonesAbecedario.add(letraR);

		
		JButton letraS = new JButton("S");
		botonesAbecedario.add(letraS);

		
		JButton letraT = new JButton("T");
		botonesAbecedario.add(letraT);

		
		JButton letraU = new JButton("U");
		botonesAbecedario.add(letraU);

		
		JButton letraV = new JButton("V");
		botonesAbecedario.add(letraV);

		
		JButton letraW = new JButton("W");
		botonesAbecedario.add(letraW);

		
		JButton letraX = new JButton("X");
		botonesAbecedario.add(letraX);

		
		JButton letraY = new JButton("Y");
		botonesAbecedario.add(letraY);

		
		JButton letraZ = new JButton("Z");
		botonesAbecedario.add(letraZ);
		
		
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addComponent(letraO, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addComponent(letraK, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
							.addComponent(letraF, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
							.addComponent(letraA, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
								.addComponent(letraY, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(letraT, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_3.createSequentialGroup()
											.addComponent(letraB, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(letraC, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_3.createSequentialGroup()
											.addComponent(letraG, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(letraH, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
										.addComponent(letraI, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(letraD, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(letraL, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(letraM, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(letraN, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(letraÑ, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(letraE, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
								.addComponent(letraJ, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(letraP, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(letraQ, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(letraR, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(letraS, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(letraZ, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(letraU, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(letraV, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(letraW, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(letraX, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(letraC, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(letraD, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
								.addComponent(letraE, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
							.addComponent(letraA, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
							.addComponent(letraB, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
							.addComponent(letraF, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(letraG, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
							.addComponent(letraH, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(letraI, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(letraJ, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(letraL, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(letraK, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(letraM, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(letraN, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(letraÑ, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(letraO, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(letraP, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(letraQ, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(letraR, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(letraS, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(letraT, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(letraU, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(letraV, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(letraW, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(letraX, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
						.addComponent(letraZ, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(letraY, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(
				new TitledBorder(null, "Palabra secreta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		//creamos las bombillas
		crearBombillas();

		
		//LISTENERS PISTAS
		
		bombillas[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//activamos la pista
				activarPista();
				//hacemos desaparecer el boton
				bombillas[0].setVisible(false);
			}
		});
		
		bombillas[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//activamos la pista
				activarPista();
				//hacemos desaparecer el boton
				bombillas[1].setVisible(false);
			}
		});
		
		bombillas[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//activamos la pista
				activarPista();
				//hacemos desaparecer el boton
				bombillas[2].setVisible(false);
			}
		});
		
		bombillas[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//activamos la pista
				activarPista();
				//hacemos desaparecer el boton
				bombillas[3].setVisible(false);
			}
		});
		
		bombillas[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//activamos la pista
				activarPista();
				//hacemos desaparecer el boton
				bombillas[4].setVisible(false);
			}
		});
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addGap(25)
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(bombillas[0], GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bombillas[1], GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bombillas[2], GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bombillas[3], GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bombillas[4], GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		)));
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(bombillas[1])
						.addComponent(bombillas[0])
						.addComponent(bombillas[2])
						.addComponent(bombillas[3])
						.addComponent(bombillas[4]))
					.addGap(18)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(101))
		);

		
		palabraSecreta.setBackground(Color.WHITE);
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
						.addComponent(palabraSecreta, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
						.addContainerGap()));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2
						.createSequentialGroup().addContainerGap().addComponent(palabraSecreta,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(15, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);
		panel_1.setLayout(gl_panel_1);
		
		//deshabilitamos los botones al ejecutar el programa
		deshabilitarBotones();

		resolver = new JButton("Resolver");
		resolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//cambiamos el fondo de background para mostrar la palabra
				palabraSecreta.setText(pass);
			}
		});
		
		//al ejecutar el programa el boton 'Resolver' está desactivado
		resolver.setEnabled(true);
		
		iniciar = new JButton("Iniciar Juego");
		lblNewLabel.setLabelFor(iniciar);
		iniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//iniciar juego como principiante por defecto (10 intentos)
				iniciarPartida(0);
				//desactivamos el boton
				iniciar.setEnabled(false);
			}
		});
		
		//eventos que se ejectutan al clickar los botones del teclado
		alClickarBotonTeclado();
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(35)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(resolver, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
						.addComponent(iniciar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
				.addGap(40)));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup().addGap(19)
						.addComponent(iniciar).addGap(18).addComponent(resolver).addContainerGap(26, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
