import javax.swing.*;
import java.awt.*;

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// !!! V - VIEW This entire file is about VIEW.
// !!! CREATED BY DMYTRO
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class ScoreBoard extends JPanel {
    private int score;
    private final JLabel scoreLabel;

    public ScoreBoard() {
        setBackground(new Color(43, 155, 49));
        scoreLabel = new JLabel();
        updateLabel();
        scoreLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
        this.add(scoreLabel);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updateVictoryLabel() {
        scoreLabel.setText("Winner! Score: " + this.score);
    }

    public void updateVegasFinalLabel() {
        scoreLabel.setText("Final score: $" + this.score);
    }

    public void updateVegasLabel() {
        scoreLabel.setText("Score: $" + this.score);
    }

    public void updateLabel() {
        scoreLabel.setText("Score: " + this.score);
    }
}
