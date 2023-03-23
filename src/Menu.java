import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// !!! THIS file is divided into TWO parts - MODEL AND VIEW
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class Menu implements ActionListener {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // !!! V-VIEW PART - START
    // !!! CREATED BY DMYTRO

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public JMenu createMenu() {
        JMenu menu;
        ButtonGroup gameTypeOptions;
        JRadioButtonMenuItem gameTypeRegular, gameTypeVegas, gameTypeDrawThreeCard, gameTypeDrawOneCard;
        JMenuItem newGame;


        // Create menu
        menu = new JMenu();

        // Build menu
        menu = new JMenu("Menu");
        //menu.add(menu);

        // Build menu options
        newGame = new JMenuItem("New Game");
        menu.add(newGame);

        menu.addSeparator();

        gameTypeOptions = new ButtonGroup();

        gameTypeRegular = new JRadioButtonMenuItem("Regular Rules");
        gameTypeRegular.setSelected(true);
        gameTypeRegular.addActionListener(e -> Handler.reloadGame(1));
        gameTypeOptions.add(gameTypeRegular);
        menu.add(gameTypeRegular);

        gameTypeVegas = new JRadioButtonMenuItem("Vegas Rules");
        gameTypeVegas.addActionListener(e -> Handler.reloadGame(2));
        gameTypeOptions.add(gameTypeVegas);
        menu.add(gameTypeVegas);
        menu.addSeparator();

        gameTypeDrawOneCard = new JRadioButtonMenuItem("Draw 1 Card");
        gameTypeDrawOneCard.addActionListener(e -> {
            Handler.reloadGame(4);
        });
        gameTypeOptions.add(gameTypeDrawOneCard );
        menu.add(gameTypeDrawOneCard );


        gameTypeDrawThreeCard = new JRadioButtonMenuItem("Draw 3 Cards");
        gameTypeDrawThreeCard.addActionListener(e -> {
            Handler.reloadGame(3);
        });
        gameTypeOptions.add(gameTypeDrawThreeCard);
        menu.add(gameTypeDrawThreeCard);




        // !!! V-VIEW PART - FINISH
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // !!! M-MODEL PART - START
        // !!! CREATED BY JENNY

        newGame.addActionListener(e -> {
            if (gameTypeRegular.isSelected()) {
                Handler.reloadGame(1);
            }
            if (gameTypeVegas.isSelected()) {
                Handler.reloadGame(2);
            }
            if (gameTypeDrawThreeCard.isSelected()) {
                Handler.reloadGame(3);
            }
            if (gameTypeDrawOneCard.isSelected()) {
                Handler.reloadGame(4);
            }

        });

        return menu;
        // !!! M-MODEL PART - FINISH
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }


}
