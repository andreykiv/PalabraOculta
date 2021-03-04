package views;


import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Component;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;

public class Ahorcado extends JFrame {

	private JPanel contentPane;
	
	//creamos un array de strings que pasaremos al campo textField
	private String[] passArr = new String [] {"UNO", "DOS", "ADIVINAPASS", "CUARTOPASS", "MUYFACIL", "SUPERDOOPER", 
			"HELLOTHERE", "GENERAL", "KENOBI", "THEEND"};
	
	//pass
	private String pass;
	//array de caracteres de la paraula pass
	private ArrayList<Character> caracteresPass = new ArrayList<Character>();
	//array de botones del teclado
	private ArrayList<JButton> botonesAbecedario = new ArrayList<JButton>();
	//array de JLabel del mismo tamaño que caracteresPass
	private ArrayList<JLabel> labelsPorPass = new ArrayList<JLabel>();
	
	//atributos del juego
	private int fallos = 0;
	
	
	//METODOS
	//random index del String de pass
	private int randomIndex() {
		Random rnd = new Random();
		int num = rnd.nextInt(10);
		return num;
	}
	
	private void randomPass() {
		String myPass = "";
		myPass = passArr[randomIndex()];	
		pass = myPass;
	}
	//metodo que construye un array de chars que compone la palabra pass 
	private void splitPass() {
		for(int i = 0; i < pass.length(); i++) {
			caracteresPass.add(pass.charAt(i));
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
	private void popularLabelsPorPass() {
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
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ahorcado frame = new Ahorcado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ahorcado() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		setTitle("Ahorcado");
		setBounds(200, 200, 700, 800);
		contentPane.setBorder(BorderFactory.createTitledBorder(""));

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
		
		JLabel lblNewLabel = new JLabel("");
		
		
		
		
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

		JLabel bombilla1 = new JLabel("");
		bombilla1.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/medidas_bombilla.PNG")));
		
		
		bombilla1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Click sobre bombilla!");
			}
		});

		JLabel bombilla2 = new JLabel("");
		bombilla2.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/medidas_bombilla.PNG")));
		
		JLabel bombilla3 = new JLabel("");
		bombilla3.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/medidas_bombilla.PNG")));
		
		JLabel bombilla4 = new JLabel("");
		bombilla4.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/medidas_bombilla.PNG")));
		
		JLabel bombilla5 = new JLabel("");
		bombilla5.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/medidas_bombilla.PNG")));
		
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
							.addComponent(bombilla1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bombilla2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bombilla3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bombilla4, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bombilla5, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		)));
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(bombilla2)
						.addComponent(bombilla1)
						.addComponent(bombilla3)
						.addComponent(bombilla4)
						.addComponent(bombilla5))
					.addGap(18)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(101))
		);

		JLabel palabraSecreta = new JLabel("_");
		palabraSecreta.setBackground(Color.WHITE);// aquí, el fons del jtextpane de la paraula secreta esta en negre;
													// fer funció que cambie de BLACK a WHITE per a que revele la
													// paraula (en lo botó resolver)
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

		JButton inicia = new JButton("Iniciar Juego");
		lblNewLabel.setLabelFor(inicia);
		inicia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//asignamos el valor del pass a la variable pass con este metodo
				randomPass();
				// dividimos la palabra secreta en un array de chars
				splitPass();
				//habilitamos los botones del teclado
				habilitarBotones();
				//añadimos los labels 
				popularLabelsPorPass();
				//asignamos la palabra escogiendo aleatoriamente del array de strings y transformamos al uppercase
				palabraSecreta.setText(palabraSecretaDisplay());
				//desactivamos el boton
				inicia.setEnabled(false);
			}
		});

		JButton resuelve = new JButton("Resolver");
		resuelve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//cambiamos el fondo de background para mostrar la palabra
				palabraSecreta.setText(pass);
			}
		});
		
		letraA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(siExiste(letraA)) {
					//modificamos los elementos de la palabra secreta que se muestra al user
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(siExiste(letraB)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(siExiste(letraC)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraD)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraE)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
				
			}
		});
		letraF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraF)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraG)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraH)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraI)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraJ)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraL)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraK)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraM)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraN)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraÑ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraÑ)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraO)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraP)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraQ)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraR)){
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraS)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraT)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraU)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraV)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraW)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraX)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraY)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		letraZ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(siExiste(letraZ)) {
					palabraSecreta.setText(palabraSecretaDisplay());
				} else {
					fallos++;	
					lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/" + fallos +".PNG")));
				}
			}
		});
		

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(35)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(resuelve, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
						.addComponent(inicia, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
				.addGap(40)));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup().addGap(19)
						.addComponent(inicia).addGap(18).addComponent(resuelve).addContainerGap(26, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

	}
	
}
