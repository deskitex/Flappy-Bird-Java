import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FlappyBird extends JFrame {
    JLabel Player;
    JLabel displayScore;
    JLabel ground;
    JLabel background;

    JLabel pipeDownLabel;
    JLabel pipeUpLabel;
    JLabel scoreArea;

    JLabel pipeDown2;
    JLabel pipeUp2;
    JLabel scoreArea2;

    int sizeViewport_x = 480;
    int sizeViewport_Y = 854;

    ImageIcon groundTexture = new ImageIcon("ground.png");
    ImageIcon bgTexture = new ImageIcon("background.png");
    ImageIcon playerCenter = new ImageIcon("bird.png");
    ImageIcon pipeUp = new ImageIcon("pipe.png");
    ImageIcon pipeDown = new ImageIcon("pipeDown.png");


    int velocityY = 0;
    int velocityObs;
    int gravity = 0;
    Boolean alreadyClicked = false;
    boolean alive = true;
    Integer score = 0;
    boolean done = false;
    Random random;
    int randomInt;
    Random randomY = new Random();
    int intY ;
    int intY2;
    Clip clip;
    AudioInputStream hit;

    int posXObs= 500;
    FlappyBird() throws LineUnavailableException, UnsupportedAudioFileException, IOException{
        File hit_wav = new File("hit.wav");
        hit = AudioSystem.getAudioInputStream(hit_wav);
        clip = AudioSystem.getClip();



        File wing_wav = new File("wing.wav");
        AudioInputStream wing = AudioSystem.getAudioInputStream(wing_wav);

        File point_wav = new File("point.wav");
        AudioInputStream point = AudioSystem.getAudioInputStream(point_wav);

        clip.open(wing);  

       random = new Random();  
        randomInt =random.nextInt(854 - 168);

        intY = randomY.nextInt(400);
        intY2 = randomY.nextInt(400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        System.out.println(intY);
        System.out.println(intY2);
     // ---------------------Obastacle-----------------------
        // ---------------Pipe Down------------
        pipeDownLabel = new JLabel();
        pipeDownLabel.setBounds(posXObs,-intY,78,480);
        pipeDownLabel.setIcon(pipeDown);
        // ---------------Pipe Down------------

        //-------------Make Score Area-----------
        scoreArea = new JLabel();
        scoreArea.setBounds(posXObs, -intY + 480, 20, 200);
        // scoreArea.setBackground(Color.RED);
        // scoreArea.setOpaque(true);
        //-------------Make Score Area-----------

        // ---------------Pipe Up------------
        pipeUpLabel = new JLabel();
        pipeUpLabel.setBounds(posXObs,-intY + 680,78,480);
        pipeUpLabel.setIcon(pipeUp);
        // ---------------Pipe Up------------

    // -------------------Obstacle2-----------------------

        // ---------------Pipe Down------------
        pipeDown2 = new JLabel();
        pipeDown2.setBounds(posXObs + 350,-intY2,78,480);
        pipeDown2.setIcon(pipeDown);
        // ---------------Pipe Down------------

        //-------------Make Score Area-----------
        scoreArea2 = new JLabel();
        scoreArea2.setBounds(posXObs + 350, -intY2 + 480, 20, 200);
        // scoreArea.setBackground(Color.RED);
        // scoreArea.setOpaque(true);
        //-------------Make Score Area-----------

        // ---------------Pipe Up------------
        pipeUp2 = new JLabel();
        pipeUp2.setBounds(posXObs + 350,-intY2 + 680,78,480);
        pipeUp2.setIcon(pipeUp);
        // ---------------Pipe Up------------
        
        //-------------Make Player---------------
        Player = new JLabel();
        Player.setBounds(0,0, 51,36);
        Player.setLocation(50,sizeViewport_Y/2 -100);
        Player.setIcon(playerCenter);
        //-------------Make Player---------------

        //-----------Make Display Score----------
        displayScore = new JLabel();
        displayScore.setText(String.valueOf(score));
        displayScore.setForeground(Color.WHITE);
        displayScore.setFont(new Font("flappyfont", Font.BOLD, 50));
         displayScore.setBounds(220,0,100,100);
        //-----------Make Display Score----------
        
        //--------------Make Ground and Background------------
        ground = new JLabel();
        ground.setBounds(0,sizeViewport_Y - 168,504,168);
        ground.setIcon(groundTexture);

        background = new JLabel(bgTexture);
        background.setBounds(0, 0, sizeViewport_x, sizeViewport_Y);
        //--------------Make Ground and Background------------

        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // ----------Jump-----------
                if (alive == true){
                  
                    clip.start();
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
                    clip.setMicrosecondPosition(0);
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
        this.add(displayScore);
        this.add(ground);

        this.add(pipeUpLabel);
        this.add(pipeDownLabel);
        this.add(scoreArea);

        this.add(pipeUp2);
        this.add(pipeDown2);
        this.add(scoreArea2);
        
        this.add(background);
        this.setSize(sizeViewport_x,sizeViewport_Y);
        this.setVisible(true);
         physicPlayer();
    }
    void physicPlayer() throws LineUnavailableException, IOException {
     while(Player.getY() > -999999999){
        intY = randomY.nextInt(400);
        intY2 = randomY.nextInt(400);

        // --------------Animation Pipe----------------------
        velocityY += gravity;
        velocityObs += gravity;
        if (alive == true){
            pipeDownLabel.setLocation(pipeDownLabel.getX() - velocityObs, pipeDownLabel.getY());
            pipeUpLabel.setLocation(pipeUpLabel.getX() - velocityObs, pipeUpLabel.getY());
            scoreArea.setLocation(scoreArea.getX() - velocityObs, scoreArea.getY());}
        if (alive == true){
        pipeDown2.setLocation(pipeDown2.getX() - velocityObs, pipeDown2.getY());
        pipeUp2.setLocation(pipeUp2.getX() - velocityObs, pipeUp2.getY());
        scoreArea2.setLocation(scoreArea2.getX() - velocityObs, scoreArea2.getY());}
        // --------------Animation Pipe----------------------

        // -------------Collision Player and Pipe----------------
        if(Player.getBounds().intersects(pipeDownLabel.getBounds()) || 
         Player.getBounds().intersects(pipeUpLabel.getBounds()) ||
         Player.getBounds().intersects(pipeDown2.getBounds()) || 
         Player.getBounds().intersects(pipeUp2.getBounds())){
            alive = false;
            }
        // -------------Collision Player and Pipe----------------


        // -------------Return Obstacle Position --------------
        if (pipeDownLabel.getX() <= -100){
            pipeDownLabel.setLocation(pipeDownLabel.getX() + 700,-intY + 0);
        }
        if (scoreArea.getX() <= -100){
            scoreArea.setLocation(scoreArea.getX() + 700, -intY + 480);
        }
        if (pipeUpLabel.getX() <= -100){
            pipeUpLabel.setLocation(pipeUpLabel.getX() + 700, -intY + 680);
        }
        if (pipeDown2.getX() <= -100){
            pipeDown2.setLocation(pipeDown2.getX() + 700,-intY + 0);
        }
        if (scoreArea2.getX() <= -100){
            scoreArea2.setLocation(scoreArea2.getX() + 700, -intY + 480);
        }
        if (pipeUp2.getX() <= -100){
            pipeUp2.setLocation(pipeUp2.getX() + 700, -intY + 680);
        }
        // -------------Return Obstacle Position --------------


        // --------------Animation Player----------------------
        Player.setLocation(Player.getX(), Player.getY()+velocityY);
        // --------------Animation Player----------------------

        // -----------clamp gravity Player-------------
        if(velocityY >= 20){
            velocityY = 20;}
            else if(velocityY <= -10){
                velocityY = -10;}
        // -----------clamp gravity Player-------------

        // ----------clamp velocity Obstacle-----------
        if(velocityObs >= 10){
            velocityObs = 10;}
        // ----------clamp velocity Obstacle-----------


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

    public void addScore(){
        if (Player.getBounds().intersects(scoreArea.getBounds()) || Player.getBounds().intersects(scoreArea2.getBounds())) {
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

    public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        new FlappyBird();
     }
}



