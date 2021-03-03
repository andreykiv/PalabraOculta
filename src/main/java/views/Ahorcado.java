package views;

import java.awt.BorderLayout;
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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;

public class Ahorcado extends JFrame {
	// private Image icono1;
	private JPanel contentPane;

	/*protected JButton botones[] = new JButton[27];
    protected String[] abecedario= {"A","B","C","D","E","F","G","H","I","J",
    		"K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z"};{
   
    for( int i=0; i < 27; i++ ) {
        JButton botonera;
        botonera = new JButton(abecedario[i]);
        //botonera.setBorder("ALGO");
        botonera.setName( new Integer(i).toString() );
        contentPane.add( botonera );
      }
      setSize(200,200);
    }*/
    
    
	/*
	 * private void preInit() { icono1 = new
	 * ImageIcon("medidas_bombilla.png").getImage(); }
	 */

	/*
	 * private void paint(Graphics g) {
	 * g.drawImage(icono1,0,0,getWidth(),getHeight(),this); }
	 */

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */

	public Ahorcado() {
		// preInit();
		// initComponents();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 500);
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

		// JLabel bombi1 = new JLabel(new
		// ImageIcon("/src/main/java/imagenes/medidas_bombilla.PNG"));
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
		lblNewLabel.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/1.PNG")));
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
					.addGap(549)
					.addComponent(lblNewLabel)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
		JButton letraA = new JButton("A");
		
		JButton letraB = new JButton("B");
		
		JButton letraC = new JButton("C");
		
		JButton letraD = new JButton("D");
		
		JButton letraE = new JButton("E");
		
		JButton letraF = new JButton("F");
		letraF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton letraG = new JButton("G");
		
		JButton letraH = new JButton("H");
		
		JButton letraI = new JButton("I");
		
		JButton letraJ = new JButton("J");
		letraJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton letraL = new JButton("L");
		
		JButton letraK = new JButton("K");
		
		JButton letraM = new JButton("M");
		letraM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton letraN = new JButton("N");
		
		JButton letraÑ = new JButton("Ñ");
		
		JButton letraO = new JButton("O");
		
		JButton letraP = new JButton("P");
		
		JButton letraQ = new JButton("Q");
		
		JButton letraR = new JButton("R");
		
		JButton letraS = new JButton("S");
		
		JButton letraT = new JButton("T");
		
		JButton letraU = new JButton("U");
		
		JButton letraV = new JButton("V");
		
		JButton letraW = new JButton("W");
		
		JButton letraX = new JButton("X");
		
		JButton letraY = new JButton("Y");
		
		JButton letraZ = new JButton("Z");
		
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

		JLabel bombilla1 = new JLabel("New label");
		bombilla1.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/medidas_bombilla.PNG")));

		JLabel bombilla2 = new JLabel("New label");
		bombilla2.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/medidas_bombilla.PNG")));
		
		JLabel bombilla3 = new JLabel("New label");
		bombilla3.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/medidas_bombilla.PNG")));
		
		JLabel bombilla4 = new JLabel("New label");
		bombilla4.setIcon(new ImageIcon(Ahorcado.class.getResource("/imagenes/medidas_bombilla.PNG")));
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
							.addComponent(bombilla1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bombilla2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bombilla3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bombilla4, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(bombilla2)
						.addComponent(bombilla1)
						.addComponent(bombilla3)
						.addComponent(bombilla4))
					.addGap(18)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(101))
		);

		JTextPane palabraSecreta = new JTextPane();
		palabraSecreta.setBackground(Color.BLACK);// aquí, el fons del jtextpane de la paraula secreta esta en negre;
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

		JButton inicia = new JButton("Iniciar Juego");
		lblNewLabel.setLabelFor(inicia);
		inicia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JButton resuelve = new JButton("Resolver");
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

		/*
		 * contentPane.setLayout(new FormLayout(new ColumnSpec[] {
		 * FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
		 * FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,}, new RowSpec[] {
		 * FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
		 * FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
		 * FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,}));
		 * 
		 * JButton inicia = new JButton("Iniciar Juego"); contentPane.add(inicia,
		 * "4, 4");
		 * 
		 * JButton resuelve = new JButton("Resolver"); contentPane.add(resuelve,
		 * "4, 6");
		 */

	}
}
