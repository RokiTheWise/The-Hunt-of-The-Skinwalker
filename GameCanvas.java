import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class GameCanvas extends JComponent{
    private int hidingTime, huntingTime, revengeTime, frameCounter;
    private BufferedImage tra, darkForest, skinWalkerAttack;
    //screen settings
    final int originalTileSize = 16; //16x16
    final int scale  = 4;

    final int tileSize = originalTileSize * scale; //64x64
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize* maxScreenCol; // 1024 px
    final int screenHeight = tileSize* maxScreenRow; //768 px
    private String PlayerType;
    private Boolean skinWalkerAttacked;
    private double skinwalkerX, skinwalkerY;
    TileManager tileM = new TileManager(this);

     // World Settings
    public final int maxWorldCol = 48;
    public final int maxWorldRow = 36;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // Coordinates
    private int playerX,playerY;
    private double cameraX, cameraY;


    /**
     * Constructor intializes the variables for the class. It also attempts to retrieve images to be drawn
     */
    public GameCanvas(){
        hidingTime = 100;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        try {
            darkForest = ImageIO.read(getClass().getResourceAsStream("/MISC/DarkForest.png"));
            skinWalkerAttack = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/skinwalkerattack.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PlayerType = "none";
        skinWalkerAttacked = false;
        frameCounter = 10001;
        playerX = 0;
        playerY = 0;
        
    }

    /**
     * Overrides the paintcomponent method of the JComponent class in order to draw the sprites. Additionally it also
     * contains conditions for camera movement.
     */
    @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            GameFrame.getOther().drawSprite(g2d);
            GameFrame.getMe().drawSprite(g2d);


            
            AffineTransform camera = new AffineTransform();
            int centerX = screenWidth/2;
            int centerY = screenHeight/2;

            double playerCenterX = playerX + 32; // 32 is player width / 2
            double playerCenterY = playerY + 32; // 32 is player height / 2

            cameraX = playerCenterX - centerX;
            cameraY = playerCenterY - centerY;

            AffineTransform overlay = new AffineTransform();
            cameraX = Math.max(0, Math.min(worldWidth - screenWidth, cameraX));
            cameraY = Math.max(0, Math.min(worldHeight - screenHeight, cameraY));

            
            
             
            
            camera.translate (-cameraX, -cameraY);


           

            g2d.setTransform(camera);

            tileM.drawTiles(g2d);
            GameFrame.getOther().drawSprite(g2d);
            GameFrame.getMe().drawSprite(g2d);

            g2d.setTransform(overlay);
            if (PlayerType.equals("Hunter")){
               if (hidingTime > 0){
                    g2d.drawImage(darkForest, 0, 0, getWidth(), getHeight(), null);
                    g2d.setColor(new Color(165, 0, 0));

                    Font titleFont = new Font("Papyrus", Font.BOLD, 70);
                    g2d.setFont(titleFont);
                    String title = "You are the Hunter";
                    int titleX = (getWidth() - g2d.getFontMetrics().stringWidth(title)) / 2;
                    g2d.drawString(title, titleX, 120);

                    Font subFont = new Font("Papyrus", Font.BOLD, 40);
                    g2d.setFont(subFont);
                    String sub = "The Skinwalker is hiding. " + hidingTime + " seconds left...";
                    int subX = (getWidth() - g2d.getFontMetrics().stringWidth(sub)) / 2;
                    g2d.drawString(sub, subX, getHeight() / 2);
               }

           } else if (PlayerType.equals("Skinwalker")){
                if (hidingTime > 0){
                    g2d.setColor(new Color(165, 0, 0));
                    Font f = new Font("Papyrus", Font.BOLD, 30);
                    g2d.setFont(f);
                    String msg = "You are the Skinwalker, you have " + hidingTime + " seconds to hide. Good luck!";
                    int msgX = (getWidth() - g2d.getFontMetrics().stringWidth(msg)) / 2;
                    g2d.drawString(msg, msgX, 63);
               }
           }

           if (hidingTime <= 0){
                String hudLabel;
                int hudTime;
                if (huntingTime > 0){
                    hudLabel = "Hunt: ";
                    hudTime = huntingTime;
                } else {
                    hudLabel = "Revenge: ";
                    hudTime = Math.max(revengeTime, 0);
                }
                Font hudFont = new Font("Papyrus", Font.BOLD, 36);
                g2d.setFont(hudFont);
                String hudText = hudLabel + hudTime + "s";
                int textW = g2d.getFontMetrics().stringWidth(hudText);
                int padX = 16, padY = 10;
                int boxX = (getWidth() - textW) / 2 - padX;
                int boxY = 12;
                int boxW = textW + padX * 2;
                int boxH = g2d.getFontMetrics().getHeight() + padY;
                g2d.setColor(new Color(0, 0, 0, 160));
                g2d.fillRect(boxX, boxY, boxW, boxH);
                g2d.setColor(new Color(220, 30, 30));
                g2d.drawString(hudText, boxX + padX, boxY + g2d.getFontMetrics().getAscent() + padY / 2);
           }
           g2d.setTransform(camera);

            if (GameFrame.getIsFired()){
                GameFrame.setBullet(new BulletSprite(GameFrame.getBulletX(),GameFrame.getBulletY(),32));
                GameFrame.setIsFired(false);
            }
            if (GameFrame.getBullet() != null){
                GameFrame.getBullet().drawSprite(g2d);
            }
            if (skinWalkerAttacked){
                frameCounter = 0;
            }
            if (frameCounter <= 25){
                g2d.drawImage(skinWalkerAttack, (int)skinwalkerX-64, (int) skinwalkerY-64, 192, 192, null);
                frameCounter++;
            }
            if (frameCounter<25 & !(frameCounter >1000)){
                frameCounter++;
            }

        }

    /**
     * sets the x value of the player
     * @param x the desired value
     */
    public void setPlayerX(int x){
        playerX = x;
    }
    /**
     * sets the y value of the player
     * @param y the desired value
     */
    public void setPlayerY(int y){
        playerY = y;
    }

    /**
     * sets the type of the player
     * @param s the desired value
     */
    public void setPlayerType(String s){
        PlayerType = s;
    }

    /**
     * sets the hiding time of the player specifically used for the overlay of the hunter
     * @param i the desired value
     */
    public void setHidingTime(int i){
        hidingTime = i;
    }

    public void setHuntingTime(int i){
        huntingTime = i;
    }

    public void setRevengeTime(int i){
        revengeTime = i;
    }

    /**
     * sets the x of the skinwalker
     * @param x the desired value
     */
    public void setSkinWalkerX(double d){
        skinwalkerX = d;
    } 

    /**
     * sets the y of the skinwalker
     * @param y the desired value
     */
    public void setSkinWalkerY(double d){
        skinwalkerY = d;
    }


    /**
     * sets the boolean if the skinwalker has attacked for animations
     * @param b the desired  truth value
     */
    public void setSkinWalkerAttacked(boolean b){
        skinWalkerAttacked = b;
    }

    /**
     * sets the x of the camera
     * @param x the desired value
     */
    public double getCameraX() {
        return cameraX;
    }

    /**
     * sets the y of the camera
     * @param y the desired value
     */
    public double getCameraY(){
        return cameraY;
    }

    

    

        

}

