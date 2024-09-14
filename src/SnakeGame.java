import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class SnakeGame extends JPanel implements ActionListener , KeyListener {

    boolean gameReset = false;

//    File fontFile = new File("fonts\\Kode_Mono\\static\\KodeMono-Bold.ttf");






//    Font textFont;
//
//    {
//        try {
//            try {
//                textFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.BOLD, 20);
//            } catch (FontFormatException e) {
//                throw new RuntimeException(e);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    Font buttonFont;
//
//    {
//        try {
//            try {
//                buttonFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.BOLD, 20);
//            } catch (FontFormatException e) {
//                throw new RuntimeException(e);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private JTextArea textArea = new JTextArea();
    JDialog alert = new JDialog();
    JButton playAgainButton = new JButton();

    JButton menuButton = new JButton();

    private class Tile{

        int x;
        int y;


        Tile(int x , int y){
            this.x = x;
            this.y = y;
        }
    }


    int height;
    int width;

    int tileSize = 25;
    Tile snakeHead;

    ArrayList<Tile> snakeBody;



    Tile food;

    Random random;

    Timer gameLoop;

    int velocityX;
    int velocitY;

    boolean gameOver = false;

    public SnakeGame(int height , int width){
        this.height = height;
        this.width = width;
        setPreferredSize(new Dimension(this.height , this.width));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(5 , 5 );

        food = new Tile(10 , 10);

        random = new Random();

        snakeBody =  new ArrayList<>();


        foodPlacer();

        gameLoop = new Timer(100 , this);
        gameLoop.start();

        velocityX = 0;
        velocitY = 0;
        

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

    }

    public void draw(Graphics g){

//        for (int i = 0 ; i<width/tileSize ; i++ ){
//
//            g.drawLine(i*tileSize , 0, i*tileSize , height);
//            g.drawLine(0 , i*tileSize , width , i*tileSize );
//
//        }


        g.setColor(Color.red);
        g.fill3DRect(food.x*tileSize , food.y*tileSize , tileSize , tileSize ,true);

        g.setColor(Color.green);
        g.fill3DRect(snakeHead.x*tileSize , snakeHead.y*tileSize , tileSize , tileSize , true);

        for (int i =0 ; i <snakeBody.size() ; i++){
            Tile snakePart = snakeBody.get(i);
            g.fill3DRect(snakePart.x*tileSize , snakePart.y*tileSize , tileSize , tileSize , true);

        }


        if (gameOver){

            String gameOverMessage = "Game over your score is ";


            alert.setSize(400 , 400);
            alert.setVisible(true);


//            textArea.setFont(textFont);
            textArea.setBackground(Color.WHITE);
            textArea.setForeground(Color.BLACK);
            textArea.setText(gameOverMessage + snakeBody.size());
            textArea.setMargin(new Insets(50, 50, 0, 0));
            textArea.setBounds(40, 100, 600, 200);
            textArea.setFocusable(false);
            textArea.setEditable(false);



//            playAgainButton.setFont(buttonFont);
            playAgainButton.setSize(100 ,40);
            playAgainButton.setLocation(  230 , alert.getHeight()/2 - playAgainButton.getHeight()  ) ;
            playAgainButton.setVisible(true);
            playAgainButton.setText("Play again");

//            menuButton.setFont(buttonFont);
            menuButton.setSize(100 ,40);
            menuButton.setLocation(  50 , alert.getHeight()/2 - playAgainButton.getHeight()  ) ;
            menuButton.setVisible(true);
            menuButton.setText("Menu");



            menuButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    alert.dispose();

                    Application.game.setVisible(false);
                    Application.menuPanel.setVisible(true);
                }
            });


            playAgainButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                    resetGame();

                    // Close the dialog
                    alert.dispose();

                }
            });




            alert.add(playAgainButton, BorderLayout.CENTER);
            alert.add(menuButton);
            alert.add(textArea);

        }

        else {
            g.setFont(new Font("Arial" , Font.PLAIN , 16));
            g.drawString("Score" + String.valueOf(snakeBody.size()) , tileSize - 16 , tileSize);
        }

    }



    public void move(){

        if (collision(snakeHead , food)){
            snakeBody.add(new Tile(food.x, food.y));
            foodPlacer();
        }

        for (int i = snakeBody.size()-1 ; i>=0 ; i--){
            Tile snakePart = snakeBody.get(i);
            if (i ==0){
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }
            else {
                Tile prevSnakePart =  snakeBody.get(i-1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        snakeHead.x += velocityX;
        snakeHead.y += velocitY;

        for (int i = 0 ; i <snakeBody.size(); i++){
            Tile snakePart = snakeBody.get(i);

            if (collision(snakeHead , snakePart)){
                gameOver = true;
            }
        }
    }

    public void foodPlacer(){
        food.x = random.nextInt(width/tileSize);
        food.y = random.nextInt(height/tileSize);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

        if (gameOver){
            gameLoop.stop();
        }

        if (snakeHead.x*tileSize < 0 || snakeHead.x*tileSize > width || snakeHead.y*tileSize < 0 || snakeHead.y * tileSize > height){
            gameOver = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode()== KeyEvent.VK_UP && velocitY != 1){

            velocityX = 0;
            velocitY = -1;
        }

        else if (e.getKeyCode()== KeyEvent.VK_DOWN && velocitY != -1){

            velocityX = 0;
            velocitY = 1;
        }

        else if (e.getKeyCode()== KeyEvent.VK_LEFT && velocityX != 1){

            velocityX = -1;
            velocitY = 0;
        }

        else if (e.getKeyCode()== KeyEvent.VK_RIGHT && velocityX != -1){

            velocityX = 1;
            velocitY = 0;
        }

    }

    public boolean collision (Tile tile1 , Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }



    @Override
    public void keyReleased(KeyEvent e) {

    }


    public void resetGame() {


            gameOver = false;
            snakeBody.clear();
            snakeHead = new Tile(5, 5);
            foodPlacer();
            velocityX = 0;
            velocitY = 0;
            gameLoop.start();
            repaint();

    }


}
