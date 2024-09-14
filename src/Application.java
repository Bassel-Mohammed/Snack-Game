import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Application {

   private static int height = 600;
    private static int width = 600;

    static MenuPanel menuPanel = new MenuPanel(height , width);
    static JFrame frame = new JFrame("Snake game");

    static SnakeGame game = new SnakeGame(height, width);

    static InfoPanel panelInfo;

    static {
        try {
            panelInfo = new InfoPanel(600 , 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public Application() throws IOException, FontFormatException {



    }

    public static void backMethod(){
        panelInfo.setVisible(false);
        menuPanel.setVisible(true);

    }

    public static void gameInfo()  {




        menuPanel.setVisible(false);
        panelInfo.setVisible(true);

        frame.add(panelInfo);
        frame.pack();


    }


    public static void runGame() {




        frame.add(game);
        frame.pack();

        game.requestFocus();
    }

    public static void main(String[] args) {


        frame.setVisible(true);
        frame.setSize(height , width);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        frame.add(menuPanel);
        frame.pack();
        menuPanel.requestFocus();


    }


}