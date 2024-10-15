package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    
    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 Tile size
    final int scale = 5;
    
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //1280 pixels
    final int screenHeight = tileSize * maxScreenRow; //960 pixels
    
    //FPS
    int FPS = 60;
    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyH);
    
    
    //set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    
    
    
    public GamePanel(){
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
    }
    
    public void startGameThread() {
        
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        
        double drawInterval = 1000000000/FPS; //0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
    
        while(gameThread != null){
                // 1 Update: update information such as character position
                update();
                // 2 Draw: draw the screen with the uploaded information
                repaint();
                
                //Game loop 'sleep' 
            try { //ensures update is visible to user, prevents the system from rendering the game too fast.
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000000;
                
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);
                
                nextDrawTime += drawInterval;
                        
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
        
    }
    public void update(){
     
        player.update();
        
    }
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        player.draw(g2);
        
        g2.dispose(); //save memory        
    }
}
