import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * The class <b>GameController</b> is the controller of the game. It has a
 * method <b>selectColor</b> which is called by the view when the player selects
 * the next color. It then computesthe next step of the game, and updates model
 * and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameController implements ActionListener {

	private static GameModel game;
	private static GameView view;

	private static Stack<DotInfo> s;

	private static int newcolor;
	private int size;

	/**
	 * Constructor used for initializing the controller. It creates the game's
	 * view and the game's model instances
	 * 
	 * @param size
	 *            the size of the board on which the game will be played
	 */
	public GameController(int size) {
		this.size = size;
		game = new GameModel(size);
		view = new GameView(game, this);

	}

	/**
	 * resets the game
	 */
	public void reset() {
		GameModel.reset();
		game = new GameModel(size);
		view.update();
	}

	/**
	 * Callback used when the user clicks a button (reset or quit)
	 *
	 * @param e
	 *            the ActionEvent
	 */

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == view.btnReset) {
			System.out.println("resetting");
			reset();

		} else if (e.getSource() == view.btnQuit) {
			System.exit(0);
		} else if (e.getSource() == view.col0) {

			selectColor(0);
		} else if (e.getSource() == view.col1) {
			selectColor(1);
		} else if (e.getSource() == view.col2) {
			selectColor(2);
		} else if (e.getSource() == view.col3) {
			selectColor(3);
		} else if (e.getSource() == view.col4) {
			selectColor(4);
		} else if (e.getSource() == view.col5) {
			selectColor(5);
		}

	}

	/**
	 * <b>selectColor</b> is the method called when the user selects a new
	 * color. If that color is not the currently selected one, then it applies
	 * the logic of the game to capture possible locations. It then checks if
	 * the game is finished, and if so, congratulates the player, showing the
	 * number of moves, and gives two options: start a new game, or exit
	 * 
	 * @param color
	 *            the newly selected color
	 */
	public void selectColor(int color) {
		game.setCurrentSelectedColor(color);
		newcolor = color;
		s = new Stack<DotInfo>();
		for (int i = 0; i < game.getSize(); i++) {
			for (int j = 0; j < game.getSize(); j++) {
				if (game.dots[i][j].isCaptured() == true) {
					s.push(game.dots[i][j]);
					// System.out.println(game.dots[i][j].getX() + ", " +
					// game.dots[i][j].getY());
				}
			}
		}

		while (!s.isEmpty()) {
			DotInfo dot = s.pop();
			if (dot.getX() == 0 && dot.getY() == 0) {
				edot(dot);
				sdot(dot);

			} else if (dot.getX() == 0 && dot.getY() < size - 1) {
				edot(dot);
				sdot(dot);
				wdot(dot);
			} else if (dot.getX() == 0 && dot.getY() == size - 1) {
				sdot(dot);
				wdot(dot);
			} else if (dot.getX() == size - 1 && dot.getY() == 0) {
				edot(dot);
				ndot(dot);
			} else if (dot.getX() == size - 1 && dot.getY() < size - 1) {
				ndot(dot);
				edot(dot);
				wdot(dot);
			} else if (dot.getX() == size - 1 && dot.getY() == size - 1) {
				ndot(dot);
				wdot(dot);
			} else if (dot.getX() < size - 1 && dot.getY() == 0) {
				ndot(dot);
				sdot(dot);
				edot(dot);
			} else if (dot.getX() < size - 1 && dot.getY() == size - 1) {
				ndot(dot);
				sdot(dot);
				wdot(dot);
			} else {
				ndot(dot);
				sdot(dot);
				edot(dot);
				wdot(dot);
			}
			dot.setCol(newcolor);
			game.setCurrentSelectedColor(newcolor);
		}

		game.step();

		view.update();
		if (winCheck()) {
			Object[] options = { "Quit", "Play Again"};
			int n = JOptionPane.showOptionDialog(null, "Congrats! You won in " + game.getNumberOfSteps() +  " steps! Would you like to play again?",
					"Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
					options[0]);
			if(n == 0){
				System.exit(0);
			}else if(n == 1){
				reset();
			}
		}
	}

	private boolean winCheck() {
		int winCol = game.dots[0][0].getColor();
		for (int i = 0; i < game.getSize(); i++) {
			for (int j = 0; j < game.getSize(); j++) {
				if (game.dots[i][j].getColor() != winCol) {
					return false;
				}
			}
		}
		return true;
	}

	private static void edot(DotInfo dot) {
		DotInfo edot = game.get(dot.getX(), dot.getY() + 1);
		if ((!edot.isCaptured()) && (edot.getColor() == newcolor || dot.getColor() == game.getCurrentSelectedColor()
				&& edot.getColor() == game.getCurrentSelectedColor())) {
			edot.setCaptured(true);
			System.out.println("capped");
			System.out.println(edot.getColor());
			s.push(edot);

		}

	}

	private static void wdot(DotInfo dot) {
		DotInfo wdot = game.get(dot.getX(), dot.getY() - 1);
		if ((!wdot.isCaptured()) && (wdot.getColor() == newcolor || dot.getColor() == game.getCurrentSelectedColor()
				&& wdot.getColor() == game.getCurrentSelectedColor())) {
			wdot.setCaptured(true);
			s.push(wdot);

		}
	}

	private static void ndot(DotInfo dot) {
		DotInfo ndot = game.get(dot.getX() - 1, dot.getY());
		if ((!ndot.isCaptured()) && (ndot.getColor() == newcolor || dot.getColor() == game.getCurrentSelectedColor()
				&& ndot.getColor() == game.getCurrentSelectedColor())) {
			ndot.setCaptured(true);
			s.push(ndot);

		}
	}

	private static void sdot(DotInfo dot) {
		DotInfo sdot = game.get(dot.getX() + 1, dot.getY());
		if ((!sdot.isCaptured()) && (sdot.getColor() == newcolor || dot.getColor() == game.getCurrentSelectedColor()
				&& sdot.getColor() == game.getCurrentSelectedColor())) {
			sdot.setCaptured(true);
			s.push(sdot);

		}
	}

}