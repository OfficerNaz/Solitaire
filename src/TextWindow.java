import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TextWindow extends JFrame {
    private static final int FRAME_WIDTH  = 725;
    private static final int FRAME_HEIGHT = 750;
    private JTextArea jTextArea;
    public TextWindow(String filename, String title){
        getRootPane().setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        int width = 800, height = 600;
        //TO DO: make a panel in TITLE MODE
        ///////////////////////////////////
        //panel in GAME MODE.
        File file = new File("resources/"+filename);

        jTextArea = new JTextArea();
        jTextArea.setBackground(Color.GRAY);
        // jTextArea.setMaximumSize(new Dimension(FRAME_WIDTH-50,FRAME_HEIGHT-50));
        jTextArea.setEditable(false);
        JScrollPane sp = new JScrollPane(jTextArea);
        add(sp);
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file)));
            jTextArea.read(input, title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //add(jTextArea);

        //set default close
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        //centers window
        //setLocationRelativeTo(null);
        setTitle(title);
        setResizable(false);
        setVisible(true);
    }


}

