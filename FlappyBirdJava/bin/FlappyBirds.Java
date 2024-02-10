import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FlappyBird extends JFrame {
    JLabel Player;
    JLabel scoreArea;
    JLabel displayScore;
    JLabel ground;
    JLabel background;
    int sizeViewport_x = 480;
    int sizeViewport_Y = 854;

    ImageIcon groundTexture = new ImageIcon("ground.png");
    ImageIcon bgTexture = new ImageIcon("background.png");
    ImageIcon playerCenter = new ImageIcon("bird.png");
    ImageIcon playerRight = new ImageIcon("birdRight.png");
    ImageIcon playerLeft = new ImageIcon("birdLeft.png");

    

    int velocityY = 0;
    int gravity = 0;
    Boolean alreadyClicked = false;
    boolean alive = true;
    Integer score = 0;
    boolean done = false;
    Random random;
    int randomInt;
    FlappyBird(){
       random = new Random();
        randomInt =random.nextInt(854 - 168);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        //-------------Make Player---------------
        Player = new JLabel();
        Player.setBounds(0,0, 51,36);
        Player.setLocation(50,sizeViewport_Y/2 -100);
        Player.setIcon(playerCenter);
        //-------------Make Player---------------

        //-------------Make Score Area-----------
        scoreArea = new JLabel();
        scoreArea.setBackground(Color.RED);
        scoreArea.setBounds(0, 0, 20, 100);
        scoreArea.setOpaque(true);
        scoreArea.setLocation(50, randomInt);
        //-------------Make Score Area-----------

        //-----------Make Display Score----------
        displayScore = new JLabel();
        displayScore.setText(String.valueOf(score));
        displayScore.setFont(new Font("MV Boli", Font.BOLD, 50));
        displayScore.setBounds(0,0,100,100);
        //-----------Make Display Score----------

        ground = new JLabel();
        ground.setBounds(0,sizeViewport_Y - 168,504,168);
        ground.setIcon(groundTexture);

        background = new JLabel(bgTexture);
        background.setBounds(0, 0, sizeViewport_x, sizeViewport_Y);
        
        if (alreadyClicked){System.out.println("true");}
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // ----------Jump-----------
                if (alive == true){
                    if(velocityY >= 0){
                        velocityY -=25;}
                    else{ 
                        velocityY -= 10;}}
                // ----------Jump-----------
                if (alreadyClicked == true){
                    return;
                }
                
                alreadyClicked = true;
               
                // If clicked, make gravity to 1
                gravity = 1;
                // If clicked, make gravity to 1
            }
            @Override
            public void mouseReleased(MouseEvent e){
              

                  // ----------Jump-----------
                  if (alive == true){
                    if(velocityY >= 0){
                        velocityY -=30;}
                    else{ 
                        velocityY -= 10;}}
                // ----------Jump-----------
                        
                if (alreadyClicked == true){
                    return;
                }

                alreadyClicked = true;
               
                // If clicked, make gravity to 1
                gravity = 1;
                // If clicked, make gravity to 1
            }
        });
        this.add(Player);
        this.add(scoreArea);
        this.add(displayScore);
        this.add(ground);
        this.add(background);
        this.setSize(sizeViewport_x,sizeViewport_Y);
        this.setVisible(true);
        
        if (alreadyClicked){System.out.println("true");}
         physicPlayer();
    }
    void physicPlayer() {
       if (alreadyClicked){System.out.println("what the");}
     while(Player.getY() > -999999999){
   
        velocityY += gravity;
        Player.setLocation(Player.getX(), Player.getY()+velocityY);
        // -----------clamp gravity Player-------------
        if(velocityY >= 20){
            velocityY = 20;}
            else if(velocityY <= -10){
                velocityY = -10;}

        // -----------clamp gravity Player-------------
        try{
            Thread.sleep(30);
        }
        catch(InterruptedException e){
        }
        // If Player touch ground then player die
        if (Player.getY() >= sizeViewport_Y - 168 - 36){
            player_die();
            return;
        } else if(Player.getY() <= 0){
            Player.setLocation(Player.getX(), 0);
        }
       addScore();
    
        }
     }
     
    void player_die(){
        alive = false;
    }
    public static void main(String[] args) {
        new FlappyBird();
     }

    public void addScore(){
        if (Player.getBounds().intersects(scoreArea.getBounds())) {
            if (done == true){
                return;
            }
            score++;
            displayScore.setText(String.valueOf(score)); 
            done = true;
            } else{
                done = false;
            }
    }
    public void playAnimation(){
        while(alreadyClicked == true){
            playerCenter = playerRight;
        }
    }

    public void obstacle(){
        JLabel obb = new JLabel();
        obb.setBackground(Color.RED);
        obb.setBounds(0, 0, 20, 100);
        obb.setOpaque(true);
        obb.setLocation(50, randomInt);
        add(obb);
        repaint();
        
    }
}
