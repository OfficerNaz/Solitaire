import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About implements ActionListener {

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
// !!! V-VIEW PART - START
// !!! CREATED BY DMYTRO

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public JMenu createMenu() {
        JMenu menu;
        JMenuItem rules;
        JMenuItem help;
        JMenuItem about;

        // Build menu
        menu = new JMenu("About");
        rules = new JMenuItem("Rules");
        menu.add(rules);
        menu.addSeparator();
        help = new JMenuItem("Help");
        menu.add(help);

        about = new JMenuItem("About");
        menu.add(about);
        rules.addActionListener(e -> {
            new TextWindow("rules.txt","RULES");
        });
        help.addActionListener(e -> {
            new TextWindow("contact.txt","HELP" );
        });
        about.addActionListener(e -> {
            new TextWindow("about.txt","ABOUT");
        });


        return menu;
    }


}


