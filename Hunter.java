import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Hunter extends Player{

    private double x, y, size;
        private BufferedImage image;
    
    private BufferedImage hunterD1, hunterD2, hunterD3, hunterD4, hunterD5, hunterD6, hunterDI1, hunterDI2, hunterDI3, hunterDI4,
    hunterDI5, hunterDI6, hunterU1, hunterU2, hunterU3, hunterU4, hunterU5, hunterU6, hunterUI1, hunterUI2, hunterUI3, hunterUI4,
    hunterUI5, hunterUI6, hunterL1, hunterL2, hunterL3, hunterL4, hunterL5, hunterL6, hunterLI1, hunterLI2, hunterLI3, hunterLI4,
    hunterLI5, hunterLI6, hunterR1, hunterR2, hunterR3, hunterR4, hunterR5, hunterR6, hunterRI1, hunterRI2, hunterRI3, hunterRI4,
    hunterRI5, hunterRI6;
    private int spriteCounter, spriteNum, frames, scroll;
       

    public Hunter(double a, double b, double s, BufferedImage i) {
          x = a;
            y = b;
            image = i;
            size = s;
            spriteCounter = 0;
            spriteNum = 1;
            frames = 5;
            loadSprites();
           
    }

    @Override
    public void moveH(double n){
            x += n;
        }
    @Override
        public void moveV(double n){
            y += n;
        }
    @Override
        public void setX(double n){
            x = n;
        }
    @Override
        public void setY(double n){
            y = n;        
        }
    @Override
        public double getX(){
            return x;
        }
    @Override
        public double getY(){
            return y;
        }
    @Override
        public void setFrames(int n){
            frames = n;
        }
    @Override
        public BufferedImage getImage(){
            return image;
        }
    
    @Override
    public void drawSprite(Graphics2D  g2d){
            
            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            g2d.setColor(Color.RED);
            g2d.drawString("Hunter", (int)x, (int)y - 10);
            g2d.drawImage(image, (int)x, (int)y, (int)size, (int)size, null);
    }

    @Override
        public void leftCycle(){
            spriteCounter++;
            if (spriteCounter > frames) {
                spriteNum++;
                if (spriteNum > 6) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = hunterL1; break;
                    case 2: image = hunterL2; break;
                    case 3: image = hunterL3; break;
                    case 4: image = hunterL4; break;
                    case 5: image = hunterL5; break;
                    case 6: image = hunterL6; break;
                }
                spriteCounter = 0;
            }
        }

    @Override
        public void downCycle(){
             spriteCounter++;
            if (spriteCounter > frames) {
                spriteNum++;
                if (spriteNum > 6) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = hunterD1; break;
                    case 2: image = hunterD2; break;
                    case 3: image = hunterD3; break;
                    case 4: image = hunterD4; break;
                    case 5: image = hunterD5; break;
                    case 6: image = hunterD6; break;
                }
                spriteCounter = 0;
            }
        }

    @Override
        public void rightCycle(){
            spriteCounter++;
            if (spriteCounter > frames) {
                spriteNum++;
                if (spriteNum > 6) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = hunterR1; break;
                    case 2: image = hunterR2; break;
                    case 3: image = hunterR3; break;
                    case 4: image = hunterR4; break;
                    case 5: image = hunterR5; break;
                    case 6: image = hunterR6; break;
                }
                spriteCounter = 0;
            }
        }

    @Override
        public void upCycle(){
             spriteCounter++;
            if (spriteCounter > frames) {
                spriteNum++;
                if (spriteNum > 6) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = hunterU1; break;
                    case 2: image = hunterU2; break;
                    case 3: image = hunterU3; break;
                    case 4: image = hunterU4; break;
                    case 5: image = hunterU5; break;
                    case 6: image = hunterU6; break;
                }
                spriteCounter = 0;
            }
        }

    @Override
        public void leftIdleCycle(){
            spriteCounter++;
            if (spriteCounter > frames) {
                spriteNum++;
                if (spriteNum > 6) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = hunterLI1; break;
                    case 2: image = hunterLI2; break;
                    case 3: image = hunterLI3; break;
                    case 4: image = hunterLI4; break;
                    case 5: image = hunterLI5; break;
                    case 6: image = hunterLI6; break;
                }
                spriteCounter = 0;
            }
        }

    @Override
        public void rightIdleCycle(){
            spriteCounter++;
            if (spriteCounter > frames) {
                spriteNum++;
                if (spriteNum > 6) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = hunterRI1; break;
                    case 2: image = hunterRI2; break;
                    case 3: image = hunterRI3; break;
                    case 4: image = hunterRI4; break;
                    case 5: image = hunterRI5; break;
                    case 6: image = hunterRI6; break;
                }
                spriteCounter = 0;
            }
        }

    @Override
        public void upIdleCycle(){
            spriteCounter++;
            if (spriteCounter > frames) {
                spriteNum++;
                if (spriteNum > 6) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = hunterUI1; break;
                    case 2: image = hunterUI2; break;
                    case 3: image = hunterUI3; break;
                    case 4: image = hunterUI4; break;
                    case 5: image = hunterUI5; break;
                    case 6: image = hunterUI6; break;
                }
                spriteCounter = 0;
            }
        }

    @Override
        public void downIdleCycle(){
            spriteCounter++;
            if (spriteCounter > frames) {
                spriteNum++;
                if (spriteNum > 6) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = hunterDI1; break;
                    case 2: image = hunterDI2; break;
                    case 3: image = hunterDI3; break;
                    case 4: image = hunterDI4; break;
                    case 5: image = hunterDI5; break;
                    case 6: image = hunterDI6; break;
                }
                spriteCounter = 0;
            }

        }

        

    @Override
        public void deathAnimation(){
            spriteCounter++;
            if (spriteCounter > 30) {
                spriteNum++;
                if (spriteNum > 12) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = hunterDI1; break;
                    case 2: image = null; break;
                    case 3: image = hunterDI2; break;
                    case 4: image = null; break;
                    case 5: image = hunterDI3; break;
                    case 6: image = null; break;
                    case 7: image = hunterDI4; break;
                    case 8: image = null; break;
                    case 9: image = hunterDI5; break;
                    case 10: image = null; break;
                    case 11: image = hunterDI6; break;
                    case 12: image = null; break;
                }
                spriteCounter = 0;
            }
        }

        
        
    @Override
        public void loadSprites(){
             try {
                hunterD1 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Down/HunterDown14.png"));
                hunterD2 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Down/HunterDown15.png"));
                hunterD3 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Down/HunterDown16.png"));
                hunterD4 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Down/HunterDown17.png"));
                hunterD5 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Down/HunterDown18.png"));
                hunterD6 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Down/HunterDown19.png"));

                hunterDI1 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Down/HunterDown8.png"));
                hunterDI2 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Down/HunterDown9.png"));
                hunterDI3 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Down/HunterDown10.png"));
                hunterDI4 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Down/HunterDown11.png"));
                hunterDI5 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Down/HunterDown12.png"));
                hunterDI6 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Down/HunterDown13.png"));
                
                hunterR1 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Right/HunterLowerRight14.png"));
                hunterR2 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Right/HunterLowerRight15.png"));
                hunterR3 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Right/HunterLowerRight16.png"));
                hunterR4 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Right/HunterLowerRight17.png"));
                hunterR5 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Right/HunterLowerRight18.png"));
                hunterR6 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Right/HunterLowerRight19.png"));

                hunterRI1 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Right/HunterLowerRight8.png"));
                hunterRI2 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Right/HunterLowerRight9.png"));
                hunterRI3 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Right/HunterLowerRight10.png"));
                hunterRI4 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Right/HunterLowerRight11.png"));
                hunterRI5 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Right/HunterLowerRight12.png"));
                hunterRI6 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Right/HunterLowerRight13.png"));

                hunterU1 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Up/HunterUp14.png"));
                hunterU2 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Up/HunterUp15.png"));
                hunterU3 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Up/HunterUp16.png"));
                hunterU4 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Up/HunterUp17.png"));
                hunterU5 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Up/HunterUp18.png"));
                hunterU6 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Up/HunterUp19.png"));

                hunterUI1 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Up/HunterUp8.png"));
                hunterUI2 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Up/HunterUp9.png"));
                hunterUI3 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Up/HunterUp10.png"));
                hunterUI4 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Up/HunterUp11.png"));
                hunterUI5 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Up/HunterUp12.png"));
                hunterUI6 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Up/HunterUp13.png"));

                hunterL1 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Left/HunterLowerLeft14.png"));
                hunterL2 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Left/HunterLowerLeft15.png"));
                hunterL3 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Left/HunterLowerLeft16.png"));
                hunterL4 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Left/HunterLowerLeft17.png"));
                hunterL5 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Left/HunterLowerLeft18.png"));
                hunterL6 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Left/HunterLowerLeft19.png"));

                hunterLI1 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Left/HunterLowerLeft8.png"));
                hunterLI2 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Left/HunterLowerLeft9.png"));
                hunterLI3 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Left/HunterLowerLeft10.png"));
                hunterLI4 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Left/HunterLowerLeft11.png"));
                hunterLI5 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Left/HunterLowerLeft12.png"));
                hunterLI6 = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Lower Left/HunterLowerLeft13.png"));

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    @Override
    void scrollCycle() {
    }

    @Override
    void setScroll(int n) {
    }
}
