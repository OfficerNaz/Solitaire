import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

// This class represents a pile of cards in solitaire. Unlike the deck, a pile does not
 // necessarily contain 52 cards, it can hold anywhere from 0 to 52 cards. Piles can have cards
 // added and removed at runtime.
 //
 // Note: the Pile class does not care how or in what order cards are added. The CardListener class
 // is responsible for ensuring that cards are added to piles in red black red black order and that
 // the faces are descending.


//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// !!! THIS file is divided into THREE parts - Model, VIEW and Controller
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class Pile {

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // !!! M-MODEL PART - START
  // !!! CREATED BY JENNY
  private final ArrayList<Card> pile;
  
  // Foundation Piles are the 4 piles on the top right of the board.
  public static final int FOUNDATION_PILE = 0;
  
  // Tableau Piles are the 7 piles along the board.
  public static final int TABLEAU_PILE = 1;
  
  // A pile in movement.
  public static final int TEMP_PILE = 2;
  
  // The remnants of the deck after the game is dealt. The player may draw from this deck.
  public static final int STOCKPILE = 3;
  
  // The integer representation of the type of pile. (foundation, tableau, temp, stockpile)
  private final int type;
  
  // The x and y locations of the pile. Though these are not final, they should NEVER change
  private int xLoc, yLoc;
  
  // The width of the pile. It will always be the width of a card
  private final int width;
  
  // The height of a pile. It will change as cards are added to the pile
  private int height;
  
  // Vertical displacement between cards in a pile. This only applies for cards in a TABLEAU_PILE or TEMP_PILE
  public static final int VERT_DISPL = 20;
  
  // Horizontal displacement between cards. This only applies for cards in a STOCKPILE
  public static final int HORI_DISPL = 26;
  

  // The top 3 cards in a deck pile. This is necessary because a maximum of three cards can
  // be drawn in a deck pile. Also, if one card is removed, we must draw two, not three.
  private final Card[] top3;

  // No-arg constructor that sets the piles coordinates to (0,0) and sets the pile type to TABLEAU_PILE
  public Pile() {
   this(0, 0, TEMP_PILE);
  }

  // returns the size of the pile
  public int size() {
    return pile.size();
  }


  // returns the x location of the pile
  public int getX() {
    return xLoc;
  }

  // returns the y location of the pile
  public int getY() {
    return yLoc;
  }

  // returns whether a pile is empty (size == 0) or not
  public boolean isEmpty() {
    return this.size() == 0;
  }

  // returns the card on top of the pile or null if there are no cards in the pile
  public Card getCardOnTop() {
    if (pile.size() > 0)
      return pile.get(pile.size() - 1);

    return null;
  }


  // returns the card at the first position of the pile
  public Card getCardOnBottom() {
    if (pile.size() > 0) {
      return pile.get(0);
    }
    return null;
  }

  // Returns the card at index i or null if i is out of bounds
  public Card getCardAt(int i) {
    return withinBounds(i) ? pile.get(i) : null;
  }

  // Returns whether i is a valid index of the pile
  private boolean withinBounds(int i) {
    return (i >= 0 && i < pile.size());
  }

  // Constructs a new pile of cards with an x location and a y location of (x,y)
  public Pile(int x, int y, int t) {
    pile   = new ArrayList<>();
    xLoc   = x;
    yLoc   = y;
    width  = Card.WIDTH;
    height = Card.HEIGHT;
    if (t != FOUNDATION_PILE && t != TABLEAU_PILE && t != STOCKPILE) {
      type = TEMP_PILE;
    } else {
      type = t;
    }
    // the top 3 are only used by STOCKPILE's
    top3 = (type == STOCKPILE) ? new Card[3] : null;
  }

  // !!! M-MODEL PART - FINISH
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////

  // !!! V-VIEW PART - START
  // !!! CREATED BY DMYTRO

  // Draws the pile of cards
  public void draw(Graphics g) throws IOException {
    if (pile.size() == 0 && type != STOCKPILE) {
      g.setColor(Color.white);
      g.drawRect(xLoc, yLoc, Card.WIDTH, Card.HEIGHT);
      return;
    }
    
    if (type == STOCKPILE) {
      for (Card card : top3) {
        if (card != null) {
          card.draw(g);
        }
      }
    } else {
      for (Card card : pile) {
        card.draw(g);
      }
    }
  }
  public boolean hasTopCardTurned() {
    if (this.size() == 0) {
      return false;
    }
    return true;
  }
  // !!! V-VIEW PART - FINISH
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////


  /////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // !!! С-CONTROLLER PART - START
  // !!! CREATED BY NAZ


  // Adds a card to the pile and sets its location appropriately
  public void addToPile(Card c) {
    if (c != null) pile.add(c);
    if (type != FOUNDATION_PILE && type != STOCKPILE) {
      assert c != null;
      c.setLocation(xLoc, yLoc + pile.indexOf(c) * VERT_DISPL);
      if (pile.size() > 1) height += VERT_DISPL;
    } else if (type == FOUNDATION_PILE){
      assert c != null;
      c.setLocation(xLoc, yLoc);
    } else {
      assert c != null;
      c.setLocation(xLoc, yLoc);
      updateTop3();
    }
  }

  // Adds a pile to the pile and sets its location appropriately
  public void addToPile(Pile p) {
    if (this.type == TABLEAU_PILE) {
      for (int i = 0; i < p.size(); i++) {
        this.addToPile(p.getCardAt(i));
      }
    }
  }

  // Returns the pile that has been clicked, if any. If only one card has been clicked, the pile
  // will simply contain a single card
  public Pile pileHasBeenClicked(MouseEvent e) {
    if (type == STOCKPILE) {
      Card c = this.getCardOnTop();
      if (c != null) {
        if ((e.getX() >= c.getX() && e.getX() <= c.getX() + Card.WIDTH) && 
            (e.getY() >= c.getY() && e.getY() <= c.getY() + Card.HEIGHT))
          return this.getPileAt(this.size() - 1);
      }
      return null;
    }
    // no point in checking the y coordinates if the x isn't right
    if (e.getX() < this.xLoc || e.getX() > this.xLoc + this.width)
      return null;
    
    if (this.size() == 0 && (e.getY() >= this.yLoc && e.getY() <= this.yLoc + this.height)) {
      return this;
    }
    for (int i = 0; i < this.size() - 1; i++) {
      Card c = this.getCardAt(i);
      if (e.getY() >= c.getY() && e.getY() <= c.getY() + VERT_DISPL && !c.faceDown) {
        return this.getPileAt(i);
      }
    }
    
    if (this.size() > 0) {
      Card c = this.getCardOnTop();
      if (e.getY() >= c.getY() && e.getY() <= c.getBottomY() && !c.faceDown)
        return this.getPileAt(this.size() - 1);
    }
    return null;
  }
  

   // Returns a pile of cards from the passed in index to the end of this pile
   // @param i the index to start at
   // @return a pile of cards from the index i to the end of this pile

  public Pile getPileAt(int i) {
    Pile p = new Pile(this.getCardAt(i).getX(), this.getCardAt(i).getY(), TEMP_PILE);
    while (i < this.size()) {
      p.addToPile(this.getCardAt(i));
      this.removeCardAt(i);
    }
    return p;
  }
  

  // Removes the card at index i from the deck or does nothing if the index is out of bounds
  public void removeCardAt(int i) {
    if (withinBounds(i)) {
      pile.remove(i);
    }
  }


  // Returns whether a card has been dropped on the pile
  public boolean droppedOnPile(Card c) {
    // this checks to see if any of the cards corners is on a pile
    return (((c.getX() >= xLoc && c.getX() <= xLoc + width)           && (c.getY() >= yLoc && c.getY() <= yLoc + height))             ||
            ((c.getRightX() >= xLoc && c.getRightX() <= xLoc + width) && (c.getY() >= yLoc && c.getY() <= yLoc + height))             ||
            ((c.getX() >= xLoc && c.getX() <= xLoc + width)           && (c.getBottomY() >= yLoc && c.getBottomY() <= yLoc + height)) ||
            ((c.getRightX() >= xLoc && c.getRightX() <= xLoc + width) && (c.getBottomY() >= yLoc && c.getBottomY() <= yLoc + height)));
  }

  // Returns whether a pile has been dropped on this pile
  public boolean droppedOnPile(Pile p) {
    if (p.size() > 0) {
      // because dropping a pile is really like dropping the bottom card on that pile
      return droppedOnPile(p.getCardAt(0));
    }
    return false;
  }



  // Moves a TEMP_PILE. The method simply does nothing if the pile is not a TEMP_PILE.
  // It also moves the x and y location of every card in the pile
  public void setLocation(int x, int y) {
    if (type == TEMP_PILE) {
      xLoc = x;
      yLoc = y;
      // move every card in the pile
      for (int i = 0; i < this.size(); i++) {
        Card c = this.getCardAt(i);
        c.setLocation(x, y + (i * VERT_DISPL));
      }
    }
  }



  // Turns the top card of the pile up, or does nothing if the size is 0
  public void turnTopCardUp() {
    if (this.size() > 0) {
      this.getCardOnTop().faceDown = false;
    }
  }


  // Turns all cards in the pile face up
  public void turnAllCardsUp() {
    for (int i = 0; i < size(); i++) {
      pile.get(i).faceDown = false;
    }
  }

  // Updates the top 3 cards in the deck pile and adjusts their location
  public void updateTop3() {
    if (type == STOCKPILE) {
      // first clear the top 3
      Arrays.fill(top3, null);

      if (this.size() >= 3) {
        for (int i = this.size() - 3, j = 0; i < this.size(); i++, j++) {
          top3[j] = this.getCardAt(i);
          top3[j].setLocation(xLoc + (j * HORI_DISPL), yLoc);
        }
      } else {
        for (int i = 0; i < this.size(); i++) {
          top3[i] = this.getCardAt(i);
          top3[i].setLocation(xLoc + (i *  HORI_DISPL), yLoc);
        }
      }
    }
  }

}

// !!! С-CONTROLLER PART - FINISH
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
