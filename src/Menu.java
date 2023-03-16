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
    public JMenuBar createMenu() {
        JMenuBar menuBar;
        JMenu menu;
        ButtonGroup gameTypeOptions;
        JRadioButtonMenuItem gameTypeRegular, gameTypeVegas;
        JMenuItem newGame;

        // Create menu bar
        menuBar = new JMenuBar();

        // Build menu
        menu = new JMenu("Options");
        menuBar.add(menu);

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

        });
        return menuBar;
        // !!! M-MODEL PART - FINISH
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }


}
