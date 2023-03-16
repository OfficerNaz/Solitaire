import javax.swing.*;
import java.awt.*;
import java.io.IOException;


//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// !!! M - MODEL This entire file is about MODEL.
// !!! COMMIT #3 ON GITHUB
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class GameBoard extends JPanel {

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // !!! M-MODEL PART - START
  // !!! COMMIT #3 ON GITHUB
  private final Deck deck;
  private final CardListener listener;
  
  /**
   * Horizontal displacement between rows of cards
   */
  public static final int HORI_DISPL = 35;
  
  /**
   * X locations of every solitaire pile
   */
  public static final int[] TABLEAU_PILE_X_LOCS = {(HORI_DISPL),
                                                (HORI_DISPL*2) + (Card.WIDTH),
                                                (HORI_DISPL*3) + (Card.WIDTH * 2),
                                                (HORI_DISPL*4) + (Card.WIDTH * 3),
                                                (HORI_DISPL*5) + (Card.WIDTH * 4),
                                                (HORI_DISPL*6) + (Card.WIDTH * 5),
                                                (HORI_DISPL*7) + (Card.WIDTH * 6)
  };
  
  /**
   * The y location of each main pile
   */
  public static final int TABLEAU_PILE_Y_LOC = 150;
  
  /**
   * X locations of each suit pile
   */
  public static final int[] FOUNDATION_PILE_X_LOCS = {TABLEAU_PILE_X_LOCS[3],
                                                TABLEAU_PILE_X_LOCS[4],
                                                TABLEAU_PILE_X_LOCS[5],
                                                TABLEAU_PILE_X_LOCS[6]};

  /**
   * Y locations of each suit pile
   */
  public static final int FOUNDATION_PILE_Y_LOC = 20;
  
  private final Pile[] tableauPiles;
  private final Pile[] foundationPiles;
  private final Pile   stockpile;
  private int mode = 1;
  private final ScoreBoard scoreBoard;
  
  /**
   * The selected Pile is always drawn last (so it is on top of everything else)
   */
  public Pile selectedPile;
  
  /**
   * No-arg constructor for a game panel. Adds mouse listeners and initializes deck and piles
   */
  public GameBoard() {
    setBackground(new Color(36, 105, 41));
    deck = new Deck();
    tableauPiles = new Pile[7];
    foundationPiles = new Pile[4];
    scoreBoard = new ScoreBoard();
    setInitialLayout(deck);
    stockpile = new Pile(deck.getX() + Card.WIDTH + GameBoard.HORI_DISPL, deck.getY(), Pile.STOCKPILE);
    selectedPile = null;
    listener = new CardListener(this);
    this.addMouseListener(listener);
    this.addMouseMotionListener(listener);
    this.setFocusable(true);
  }
  public GameBoard(int mode) {
    setBackground(new Color(36, 105, 41));
    deck = new Deck();
    this.mode = mode;
    tableauPiles = new Pile[7];
    foundationPiles = new Pile[4];
    scoreBoard = new ScoreBoard();
    if (mode == 2) {
      scoreBoard.setScore(-52);
      scoreBoard.updateVegasLabel();
    } else {
      scoreBoard.setScore(0);
      scoreBoard.updateLabel();
    }
    setInitialLayout(deck);
    stockpile = new Pile(deck.getX() + Card.WIDTH + GameBoard.HORI_DISPL, deck.getY(), Pile.STOCKPILE);
    selectedPile = null;
    listener = new CardListener(this);
    this.addMouseListener(listener);
    this.addMouseMotionListener(listener);
    this.setFocusable(true);
  }
  
  /**
   * Paints the screen on a graphics context
   * @param g the graphics context to paint on
   */
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // draw all piles and the remaining cards left in the deck
    for (Pile tableauPile : tableauPiles) {
      try {
        tableauPile.draw(g);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    for (Pile foundationPile : foundationPiles) {
      try {
        foundationPile.draw(g);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      stockpile.draw(g);
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      deck.draw(g);
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (selectedPile != null) {
      try {
        selectedPile.draw(g);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  /**
   * @return the deck of cards
   */
  public Deck getDeck() {
    return deck;
  }
  
  /**
   * Sets the location of all cards to their starting points
   */
  public void setInitialLayout(Deck d) {
    int cardNum = 0;
    for (int i = 0; i < tableauPiles.length; i++) {
      tableauPiles[i] = new Pile(TABLEAU_PILE_X_LOCS[i], TABLEAU_PILE_Y_LOC, Pile.TABLEAU_PILE);
      for (int j = 0; j <= i; j++) {
        tableauPiles[i].addToPile(d.getCardAt(cardNum));
        if (j == i) d.getCardAt(cardNum).faceDown = false;
        d.removeCardAt(cardNum);
      }
    }
    
    for (int i = 0; i < Card.SUITS.length; i++) {
      foundationPiles[i] = new Pile(FOUNDATION_PILE_X_LOCS[i], FOUNDATION_PILE_Y_LOC, Pile.FOUNDATION_PILE);
    }
    
    deck.setLocation(HORI_DISPL, FOUNDATION_PILE_Y_LOC);
  }

  public ScoreBoard getScoreBoard() {
    return scoreBoard;
  }

  public Pile[] getTableauPiles() {
    return tableauPiles;
  }

  public Pile[] getFoundationPiles() {
    return foundationPiles;
  }
  
  /**
   * Returns the deck pile
   * @return the deck pile
   */
  public Pile getStockpile() {
    return stockpile;
  }

  public int getMode() {
    return mode;
  }


}


// !!! M-MODEL PART - FINISH
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
