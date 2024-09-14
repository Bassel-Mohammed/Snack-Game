import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class MenuPanel extends JPanel implements ActionListener , KeyListener {


    private int height;

    private int width;


    public MenuPanel(int height , int width){

        this.height = height;
        this.width = width;

        setPreferredSize(new Dimension(height , width));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        JButton startButton = new JButton();
        startButton.setPreferredSize(new Dimension(100 , 30));
        startButton.setBounds(250 , 200 , 100 , 30);
        startButton.setText("Start");
        startButton.setFocusable(false);
        startButton.setVisible(true);

        JButton infoButton = new JButton();
        infoButton.setPreferredSize(new Dimension(100 , 30));
        infoButton.setBounds(250 , 300 , 100 , 30);
        infoButton.setText("How to play");
        infoButton.setFocusable(false);
        infoButton.setVisible(true);


        setLayout(null);
        add(startButton);
        add(infoButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the game is over
                if (Application.game.gameOver) {
                    // Reset the game
                    Application.game.setVisible(true);
                    Application.game.resetGame();
                } else {
                    // Start the game immediately
                    Application.runGame();
                }

                // Hide the menu panel
                Application.menuPanel.setVisible(false);
            }
        });

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.gameInfo();
            }
        });

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
