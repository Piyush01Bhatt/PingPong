import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Game{
    
    JFrame frame;
    int x,y,height,width,xPl,yPl,r,xPl2,yPl2;
    Ball whiteBall;
    Player bluePl;
    int ballSpeed;
    Player greenPl;
    DrawPanel drawPanel;
    boolean animationDone;
    GameEvents gameEvent = new GameEvents();
    JPanel gameStats;
    String gameState;
    JLabel gameLabel;
    JLabel scoreLabel;
    JLabel score;
    JPanel scorePanel;
    JPanel highScorePanel;
    JLabel highScoreLabel;
    JLabel highScore;
    JPanel gamePanel;
    
    Game(){

        whiteBall = new Ball(60,150);
        ballSpeed = 15;
        x = whiteBall.getBallX();
        y = whiteBall.getBallY();
        whiteBall.setSpeed(ballSpeed);
        bluePl = new Player(40,150);
        xPl = bluePl.getPlX();
        yPl = bluePl.getPlY();
        greenPl = new Player(640,150);
        xPl2 = greenPl.getPlX();
        yPl2 = greenPl.getPlY();
        height = 430;
        width = 730;

        gamePanel = new JPanel();

        gameState = new String("stop");

        gameLabel = new JLabel("Ping Pong");
        gamePanel.add(gameLabel);

        frame = new JFrame();
        drawPanel = new DrawPanel();
        //drawPanel.addKeyListener(gameEvent);
        drawPanel.requestFocusInWindow();
        frame.addKeyListener(gameEvent);
        drawPanel.addKeyListener(gameEvent);
        frame.setFocusable(true);
        drawPanel.setFocusable(true);
       // frame.getContentPane().add(btn,BorderLayout.SOUTH);
        frame.getContentPane().add(drawPanel,BorderLayout.CENTER);

        gameStats = new JPanel();
        scorePanel = new JPanel();
        highScore = new JLabel("0");
        highScoreLabel = new JLabel("High Score::");
        highScorePanel = new JPanel();
        highScorePanel.add(highScoreLabel);
        highScorePanel.add(highScore);
        gameStats.setLayout(new GridLayout(1, 3));
        scoreLabel = new JLabel("SCORE::");
        score = new JLabel("0");
        scorePanel.add(scoreLabel);
        scorePanel.add(score);
        gameStats.add(scorePanel);
        gameStats.add(gamePanel);
        gameStats.add(highScorePanel);

        frame.getContentPane().add(gameStats,BorderLayout.NORTH);

        frame.setSize(710,450);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocation(350,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    //set Game state
    public void setGameState(String gameState){
        this.gameState = gameState;
    }

    //get Game state

    public String getGameState(){
        return gameState;
    }

    // Main function

    public static void main(String[] ar){
        new Game();
    }


       // Updating Animation

       public void updateAnimation(int k){

        if(bluePl.getScore()%80==0){
            ballSpeed += 2;
            whiteBall.setSpeed(ballSpeed);
        }

        r = whiteBall.ballMove(k,bluePl,greenPl);
        x = whiteBall.getBallX();
        y = whiteBall.getBallY();

        if(((x<=15) && (y>=5 && y<=420)) || (x<=15 && y<=5) || (x<=15 && y>=380)){
          //  frame.setFocusable(false);
          //  drawPanel.setFocusable(false);
            JOptionPane.showMessageDialog(null,"GAME OVER");
            setGameState("stop");
            animationDone = true;
            if(bluePl.getScore() > Integer.parseInt(highScore.getText())){
                highScore.setText(Integer.toString(bluePl.getScore()));
            }
        }


        xPl2 = greenPl.getPlX();
        yPl2 = greenPl.getPlY();
        xPl = bluePl.getPlX();
        yPl = bluePl.getPlY();
        drawPanel.repaint();
    }

    // Delaying Animation

    public void delayAnimation(){
        try{
            Thread.sleep(80);
        }catch(Exception e){}
    }


    private class GameEvents implements KeyListener{
        
                public void keyPressed(KeyEvent e) {

                    if(e.getKeyCode()==KeyEvent.VK_ENTER){
                        if(getGameState().equals("start")){
                            animationDone=true;
                        }
                        frame.setFocusable(true);
                        drawPanel.setFocusable(true);
                        frame.requestFocus();
                        drawPanel.requestFocus();
                        setGameState("start");
                        r=1;
                        animationDone = false;  
                        ballSpeed = 20;
                        whiteBall = new Ball(60,150);
                        bluePl = new Player(40,150);
                        greenPl = new Player(640,150);
                        whiteBall.setSpeed(ballSpeed);
        
                        new Thread(){
                            @Override
                            public void run(){
                                while(!animationDone){
                                    updateAnimation(r);
                                    delayAnimation();
                                    drawPanel.repaint();
                                }
                            }
                        }.start();
                    }


                    if( e.getKeyCode()==KeyEvent.VK_SPACE ){

                        if(getGameState().equals("start")){
                           // System.out.println(getGameState());
                            animationDone = true;
                            setGameState("pause");
                        }else{
                     
                       if(getGameState().equals("pause")){
                            setGameState("start");
                           // frame.setFocusable(true);
                           // drawPanel.setFocusable(true);
                           // frame.requestFocus();
                           // drawPanel.requestFocus();
                            animationDone = false;
        
                            new Thread(){
                                @Override
                                public void run(){
                                    while(!animationDone){
                                        updateAnimation(r);
                                        delayAnimation();
                                        drawPanel.repaint();
                                    }
                                }
                            }.start();
        
                        } 
                    }

                }


                    if( e.getKeyCode()==KeyEvent.VK_UP && getGameState().equals("start") ){
                        // System.out.println(getGameState());
                        if(yPl>0){
                            yPl -= 15;
                            bluePl.updatePl(xPl,yPl);
                            drawPanel.repaint();
                        }
                    }
               
                    if( e.getKeyCode()==KeyEvent.VK_DOWN && getGameState().equals("start") ){
                               
                        if(yPl<315){
                            yPl+=15;
                            bluePl.updatePl(xPl,yPl);
                            drawPanel.repaint();
                        }
                    }
                }
        
                public void keyReleased(KeyEvent e){
        
                }
        
                public void keyTyped(KeyEvent e){
        
                }
        
    }

    // Inner class for draw panel
    class DrawPanel extends JPanel{
        // Paint() Method

        public void paintComponent(Graphics g){
            g.setColor(new Color(51,153,102)); //field color
            g.fillRect(0,0,700,400);  //field
            g.setColor(Color.BLUE);  //player color
            g.fillRect(xPl,yPl,20,80);  // player blue
            g.setColor(new Color(181,230,89));  //player color
            g.fillRect(xPl2,yPl2,20,80);  // player green
            g.setColor(new Color(255,255,0)); // ball color
            g.fillOval(x,y,20,20); // ball
            g.setColor(Color.WHITE);
            g.fillRect(350,0,5,400); // Center Line
            g.setColor(new Color(194,194,163));
            g.fillRect(0,0,8,400); // left Goal Post
            g.setColor(new Color(194,194,163));
            g.fillRect(695,0,8,400); // Right Goal Post
            score.setText(Integer.toString(bluePl.getScore()));

        }
    }
}