import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import java.io.IOException;
import java.nio.file.Paths;

public class InfoPanel extends JPanel implements ActionListener , KeyListener {

    private int height ;
    private int width;

    private JTextArea textArea = new JTextArea();
    private JButton backButton = new JButton();

    File fontFile = new File("C:\\Users\\User\\Desktop\\Java Projects\\Snake game with Swing GUI\\fonts\\Kode_Mono\\static\\KodeMono-Bold.ttf");


    Font kodoMonoBoldFont;

    {
        try {
            kodoMonoBoldFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.BOLD, 20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Font BackButtonFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.BOLD, 15);



    public InfoPanel(int height, int width) throws IOException, FontFormatException {
        this.height = height;
        this.width = width;
        setPreferredSize(new Dimension(this.height, width));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        setLayout(null);




        textArea.setFont(kodoMonoBoldFont);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);

        String text = "Welcome to the Snake Game!\n\n\n"
                + "Instructions:\n \n"
                + "- Use arrow keys to control the snake.\n \n"
                + "- Eat the food to grow longer.\n \n"
                + "- Avoid hitting the walls or yourself.\n \n";



        textArea.setMargin(new Insets(50, 50, 0, 0));

        textArea.setText(text);
        textArea.setPreferredSize(new Dimension(600, 400));
        textArea.setBounds(40, 100, 500, 350);

        textArea.setFocusable(false);
        textArea.setEditable(false);


        backButton.setText("Back");

        backButton.setFont(BackButtonFont);
        backButton.setPreferredSize(new Dimension(150 , 70));
        backButton.setBounds(20 , 550 , 70 , 30);
        backButton.setFocusable(false);
        backButton.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.backMethod();
            }
        });








        add(textArea);
        add(backButton);



    }








    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
