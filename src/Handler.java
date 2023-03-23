import javax.swing.*;
import java.awt.*;

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// !!! V - VIEW This entire file is about VIEW.
// !!! CREATED BY DMYTRO
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class Handler {

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // !!! V-VIEW PART - START
  // !!! CREATED BY DMYTRO

  private static JPanel formatPanel;
  private static GameBoard gamePanel;
  private static ScoreBoard scoreBoard;


  private static final int FRAME_WIDTH  = 750;
  private static final int FRAME_HEIGHT = 750;

  public static void main(String[] args) {
    loadGame();
  }

  /**
   * Runs the game. There is currently no point in having a game loop, since the only
   * time the game needs to be re-painted is when the user moves a card.
   */

  public static void reloadGame(int mode) {
    formatPanel.remove(gamePanel);
    formatPanel.remove(scoreBoard);
    gamePanel = new GameBoard(mode);
    scoreBoard = gamePanel.getScoreBoard();
    formatPanel.add(gamePanel);
    formatPanel.add(scoreBoard, BorderLayout.SOUTH);
    formatPanel.revalidate();
  }

  /**
   * Initializes game objects
   */
  public static void loadGame() {
    JFrame gameFrame = new JFrame("Solitaire Game");

    formatPanel = new JPanel();
    gamePanel = new GameBoard();
    scoreBoard = gamePanel.getScoreBoard();
    JMenuBar menuBar = new JMenuBar();
    Menu gameMenu = new Menu();
    About aboutMenu = new About();
    menuBar.add(gameMenu.createMenu());
    menuBar.add(aboutMenu.createMenu());

    gameFrame.setJMenuBar(menuBar);

    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

    formatPanel.setLayout(new BorderLayout());
    formatPanel.add(gamePanel);
    formatPanel.add(scoreBoard, BorderLayout.SOUTH);
    gameFrame.add(formatPanel);
    gameFrame.setVisible(true);
    gameFrame.setResizable(false);
  }
}

// !!! V-VIEW PART - FINISH
/////////////////////////////////////////////////////////////////////////////////////////////////////////////