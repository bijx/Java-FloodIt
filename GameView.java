import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It
 * extends <b>JFrame</b> and lays out the actual game and two instances of
 * JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView {

	Random rand = new Random();

	private JFrame frmFlooditIti;

	private GameController gameController;
	private GameModel game;

	public DotButton col0;
	public DotButton col1;
	public DotButton col2;
	public DotButton col3;
	public DotButton col4;
	public DotButton col5;
	
	public static JButton btnReset;
	public static JButton btnQuit;
	public JLabel lblNumberOfSteps;
	
	public DotButton dotButtons[][];
	
	
	/**
	 * Constructor used for initializing the Frame
	 * 
	 * @param model
	 *            the model of the game (already initialized)
	 * @param gameController
	 *            the controller
	 */

	public GameView(GameModel model, GameController gameController) {
		dotButtons = new DotButton[model.getSize()][model.getSize()];
		this.gameController = gameController;
		this.game = model;
		initialize();
		frmFlooditIti.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFlooditIti = new JFrame();
		frmFlooditIti.setTitle("FloodIt | ITI 1121 Version by Bijan Samiee and Trevor Siu");
		frmFlooditIti.setBounds(100, 100, 666, 508);
		frmFlooditIti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFlooditIti.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 630, 343);
		frmFlooditIti.getContentPane().add(panel);
		
		for(int i=0;i<game.getSize();i++){
			for(int j = 0; j<game.getSize();j++){
				DotInfo n = game.dots[i][j];
				DotButton newButton = new DotButton(n.getX(), n.getY(), n.getColor(), game.getSize());
				dotButtons[i][j] = newButton;
				panel.add(newButton);
			}
			
		}
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 365, 630, 55);
		frmFlooditIti.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		col0 = new DotButton(0,20);
		col1 = new DotButton(1,20);
		col2 = new DotButton(2,20);
		col3 = new DotButton(3,20);
		col4 = new DotButton(4,20);
		col5 = new DotButton(5,20);
		
		panel_1.add(col0);
		panel_1.add(col1);
		panel_1.add(col2);
		panel_1.add(col3);
		panel_1.add(col4);
		panel_1.add(col5);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 431, 630, 27);
		frmFlooditIti.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		lblNumberOfSteps = new JLabel("Number of steps: 0");
		panel_2.add(lblNumberOfSteps);
		
		btnReset = new JButton("Reset");
		panel_2.add(btnReset);
		
		btnQuit = new JButton("Quit");
		panel_2.add(btnQuit);
		
		col1.addActionListener(gameController);
		col2.addActionListener(gameController);
		col3.addActionListener(gameController);
		col4.addActionListener(gameController);
		col5.addActionListener(gameController);
		col0.addActionListener(gameController);
		this.btnQuit.addActionListener(gameController);
		this.btnReset.addActionListener(gameController);
	}
	
	/**
     * update the status of the board's DotButton instances based on the current game model
     */

    public void update(){
    	for(int i=0;i<game.getSize();i++){
			for(int j = 0; j<game.getSize();j++){
				
				dotButtons[i][j].setColor(game.getColor(i, j));
				
				
			}
			
		}
    	lblNumberOfSteps.setText("Number of steps: " + game.getNumberOfSteps());
    }
    
}
