import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class SkinWalker extends Player{

    private int spriteCounter, spriteNum, frames, scroll;
    private double x, y, size;
    private BufferedImage image;
    private BufferedImage skinWalkR1, skinWalkR2, skinWalkR3, skinWalkR4, skinWalkR5, skinWalkR6, skinWalkR7, 
    skinWalkR8, skinWalkRI1, skinWalkRI2, skinWalkRI3, skinWalkRI4, skinWalkRI5, skinWalkRI6, skinWalkRI7, skinWalkL1,
    skinWalkL2, skinWalkL3, skinWalkL4, skinWalkL5, skinWalkL6, skinWalkL7, skinWalkL8, skinWalkLI1, skinWalkLI2, skinWalkLI3,
    skinWalkLI4, skinWalkLI5, skinWalkLI6, skinWalkLI7, skinWalkD1, skinWalkD2, skinWalkD3, skinWalkD4, skinWalkD5, skinWalkD6,
    skinWalkD7, skinWalkD8, skinWalkDI1, skinWalkDI2, skinWalkDI3, skinWalkDI4, skinWalkDI5, skinWalkDI6, skinWalkDI7, skinWalkU1,
    skinWalkU2, skinWalkU3, skinWalkU4, skinWalkU5, skinWalkU6, skinWalkU7, skinWalkU8, skinWalkUI1, skinWalkUI2, skinWalkUI3,
    skinWalkUI4, skinWalkUI5, skinWalkUI6, skinWalkUI7;
    private BufferedImage prop1, prop2, prop3, prop4, prop5, prop6, prop7, prop8, prop9, prop10, prop11, prop12, prop13, prop14,
    prop15,prop16, prop17, prop18;

    public SkinWalker(double a, double b, double s, BufferedImage i) {
        x = a;
        y = b;
        image = i;
        size = s;
        spriteCounter = 0;
        spriteNum = 1;
        frames = 5;
        loadSprites();
        scroll = 0;
    }
    
    @Override
    public void drawSprite(Graphics2D  g2d){
        
            g2d.drawImage(image, (int)x, (int)y, (int)size, (int)size, null);
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
        public void setScroll(int n){
            scroll = n;
        }
        
    @Override
        public void rightCycle(){
            spriteCounter++;
            if (spriteCounter > frames) {
                spriteNum++;
                if (spriteNum > 8) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = skinWalkR1; break;
                    case 2: image = skinWalkR2; break;
                    case 3: image = skinWalkR3; break;
                    case 4: image = skinWalkR4; break;
                    case 5: image = skinWalkR5; break;
                    case 6: image = skinWalkR6; break;
                    case 7: image = skinWalkR7; break;
                    case 8: image = skinWalkR8; break;
                }
                spriteCounter = 0;
            }
        }

    @Override
        public void rightIdleCycle(){
        spriteCounter++;
            if (spriteCounter > frames) {
                spriteNum++;
                if (spriteNum > 7) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = skinWalkRI1; break;
                    case 2: image = skinWalkRI2; break;
                    case 3: image = skinWalkRI3; break;
                    case 4: image = skinWalkRI4; break;
                    case 5: image = skinWalkRI5; break;
                    case 6: image = skinWalkRI6; break;
                    case 7: image = skinWalkRI7; break;
                }
                spriteCounter = 0;
            }
        }

    @Override
        public void leftCycle(){
            spriteCounter++;
            if (spriteCounter > frames) {
                spriteNum++;
                if (spriteNum > 8) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = skinWalkL1; break;
                    case 2: image = skinWalkL2; break;
                    case 3: image = skinWalkL3; break;
                    case 4: image = skinWalkL4; break;
                    case 5: image = skinWalkL5; break;
                    case 6: image = skinWalkL6; break;
                    case 7: image = skinWalkL7; break;
                    case 8: image = skinWalkL8; break;
                }
                spriteCounter = 0;
            }
        }

    @Override
        public void leftIdleCycle(){
            spriteCounter++;
            if (spriteCounter > frames) {
                spriteNum++;
                if (spriteNum > 7) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = skinWalkLI1; break;
                    case 2: image = skinWalkLI2; break;
                    case 3: image = skinWalkLI3; break;
                    case 4: image = skinWalkLI4; break;
                    case 5: image = skinWalkLI5; break;
                    case 6: image = skinWalkLI6; break;
                    case 7: image = skinWalkLI7; break;
                }
                spriteCounter = 0;
            }
        }

    @Override
        public void downCycle(){
            spriteCounter++;
            if (spriteCounter > frames) {
                spriteNum++;
                if (spriteNum > 8) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = skinWalkD1; break;
                    case 2: image = skinWalkD2; break;
                    case 3: image = skinWalkD3; break;
                    case 4: image = skinWalkD4; break;
                    case 5: image = skinWalkD5; break;
                    case 6: image = skinWalkD6; break;
                    case 7: image = skinWalkD7; break;
                    case 8: image = skinWalkD8; break;
                }
                spriteCounter = 0;
            }

        }

    @Override
        public void downIdleCycle(){
            spriteCounter++;
            if (spriteCounter > frames) {
                spriteNum++;
                if (spriteNum > 7) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = skinWalkDI1; break;
                    case 2: image = skinWalkDI2; break;
                    case 3: image = skinWalkDI3; break;
                    case 4: image = skinWalkDI4; break;
                    case 5: image = skinWalkDI5; break;
                    case 6: image = skinWalkDI6; break;
                    case 7: image = skinWalkDI7; break;
                }
                spriteCounter = 0;
            }
        }

    @Override
        public void upCycle(){
            spriteCounter++;
            if (spriteCounter > frames) {
                spriteNum++;
                if (spriteNum > 8) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = skinWalkU1; break;
                    case 2: image = skinWalkU2; break;
                    case 3: image = skinWalkU3; break;
                    case 4: image = skinWalkU4; break;
                    case 5: image = skinWalkU5; break;
                    case 6: image = skinWalkU6; break;
                    case 7: image = skinWalkU7; break;
                    case 8: image = skinWalkU8; break;
                }
                spriteCounter = 0;
            }

        }

    @Override
         public void upIdleCycle(){
            spriteCounter++;
            if (spriteCounter > frames) {
                spriteNum++;
                if (spriteNum > 7) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = skinWalkUI1; break;
                    case 2: image = skinWalkUI2; break;
                    case 3: image = skinWalkUI3; break;
                    case 4: image = skinWalkUI4; break;
                    case 5: image = skinWalkUI5; break;
                    case 6: image = skinWalkUI6; break;
                    case 7: image = skinWalkUI7; break;
                }
                spriteCounter = 0;
            }
        }

    @Override
         public void scrollCycle(){
            switch(scroll){
                case -8: image = prop1; break;
                case -7: image = prop2; break;
                case -6: image = prop3; break;
                case -5: image = prop4; break;
                case -4: image = prop5; break;
                case -3: image = prop6; break;
                case -2: image = prop7; break;
                case -1: image = prop8; break;
                case 1: image = prop9; break;
                case 2: image = prop10; break;
                case 3: image = prop11; break;
                case 4: image = prop12; break;
                case 5: image = prop13; break;
                case 6: image = prop14; break;
                case 7: image = prop15; break;
                case 8: image = prop16; break;
                case 9: image = prop17; break;
                case 10: image = prop18; break;

            }
        }

    @Override
    public void deathAnimation(){
        spriteCounter++;
            if (spriteCounter > 30) {
                spriteNum++;
                if (spriteNum > 14) {
                    spriteNum = 1;
                }
                switch (spriteNum) {
                    case 1: image = skinWalkDI1; break;
                    case 2: image = null; break;
                    case 3: image = skinWalkDI2; break;
                    case 4: image = null; break;
                    case 5: image = skinWalkDI3; break;
                    case 6: image = null; break;
                    case 7: image = skinWalkDI4; break;
                    case 8: image = null; break;
                    case 9: image = skinWalkDI5; break;
                    case 10: image = null; break;
                    case 11: image = skinWalkDI6; break;
                    case 12: image = null; break;
                    case 13: image = skinWalkDI7; break;
                    case 14: image = null; break;
                }
                spriteCounter = 0;
            }
    }

    @Override
         public void loadSprites(){
             try {
                skinWalkR1 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Right/SKIN_RWALK1.png"));
                skinWalkR2 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Right/SKIN_RWALK2.png"));
                skinWalkR3 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Right/SKIN_RWALK3.png"));
                skinWalkR4 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Right/SKIN_RWALK4.png"));
                skinWalkR5 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Right/SKIN_RWALK5.png"));
                skinWalkR6 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Right/SKIN_RWALK6.png"));
                skinWalkR7 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Right/SKIN_RWALK7.png"));
                skinWalkR8 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Right/SKIN_RWALK8.png"));
                
                skinWalkRI1 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Right/SKIN_RIDLE1.png"));
                skinWalkRI2 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Right/SKIN_RIDLE2.png"));
                skinWalkRI3 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Right/SKIN_RIDLE3.png"));
                skinWalkRI4 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Right/SKIN_RIDLE4.png"));
                skinWalkRI5 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Right/SKIN_RIDLE5.png"));
                skinWalkRI6 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Right/SKIN_RIDLE6.png"));
                skinWalkRI7 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Right/SKIN_RIDLE7.png"));

                skinWalkL1 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Left/SKIN_LWALK1.png"));
                skinWalkL2 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Left/SKIN_LWALK2.png"));
                skinWalkL3 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Left/SKIN_LWALK3.png"));
                skinWalkL4 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Left/SKIN_LWALK4.png"));
                skinWalkL5 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Left/SKIN_LWALK5.png"));
                skinWalkL6 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Left/SKIN_LWALK6.png"));
                skinWalkL7 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Left/SKIN_LWALK7.png"));
                skinWalkL8 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Left/SKIN_LWALK8.png"));

                skinWalkLI1 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Left/SKIN_LIDLE1.png"));
                skinWalkLI2 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Left/SKIN_LIDLE2.png"));
                skinWalkLI3 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Left/SKIN_LIDLE3.png"));
                skinWalkLI4 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Left/SKIN_LIDLE4.png"));
                skinWalkLI5 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Left/SKIN_LIDLE5.png"));
                skinWalkLI6 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Left/SKIN_LIDLE6.png"));
                skinWalkLI7 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Left/SKIN_LIDLE7.png"));

                skinWalkD1 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Down/SKIN_DWALK1.png"));
                skinWalkD2 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Down/SKIN_DWALK2.png"));
                skinWalkD3 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Down/SKIN_DWALK3.png"));
                skinWalkD4 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Down/SKIN_DWALK4.png"));
                skinWalkD5 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Down/SKIN_DWALK5.png"));
                skinWalkD6 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Down/SKIN_DWALK6.png"));
                skinWalkD7 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Down/SKIN_DWALK7.png"));
                skinWalkD8 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Down/SKIN_DWALK8.png"));

                skinWalkDI1 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Down/SKIN_DIDLE1.png"));
                skinWalkDI2 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Down/SKIN_DIDLE2.png"));
                skinWalkDI3 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Down/SKIN_DIDLE3.png"));
                skinWalkDI4 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Down/SKIN_DIDLE4.png"));
                skinWalkDI5 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Down/SKIN_DIDLE5.png"));
                skinWalkDI6 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Down/SKIN_DIDLE6.png"));
                skinWalkDI7 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Down/SKIN_DIDLE7.png"));

                skinWalkU1 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Up/SKIN_UWALK1.png"));
                skinWalkU2 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Up/SKIN_UWALK2.png"));
                skinWalkU3 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Up/SKIN_UWALK3.png"));
                skinWalkU4 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Up/SKIN_UWALK4.png"));
                skinWalkU5 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Up/SKIN_UWALK5.png"));
                skinWalkU6 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Up/SKIN_UWALK6.png"));
                skinWalkU7 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Up/SKIN_UWALK7.png"));
                skinWalkU8 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Up/SKIN_UWALK8.png"));

                skinWalkUI1 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Up/SKIN_UIDLE1.png"));
                skinWalkUI2 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Up/SKIN_UIDLE2.png"));
                skinWalkUI3 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Up/SKIN_UIDLE3.png"));
                skinWalkUI4 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Up/SKIN_UIDLE4.png"));
                skinWalkUI5 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Up/SKIN_UIDLE5.png"));
                skinWalkUI6 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Up/SKIN_UIDLE6.png"));
                skinWalkUI7 = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Up/SKIN_UIDLE7.png"));

                prop1 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame91.png"));
                prop2 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame92.png"));
                prop3 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame93.png"));
                prop4 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame94.png"));
                prop5 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame95.png"));
                prop6 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame96.png"));
                prop7 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame97.png"));
                prop8 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame98.png"));
                prop9 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame99.png"));
                prop10 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame100.png"));
                prop11 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame101.png"));
                prop12 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame102.png"));
                prop13 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame103.png"));
                prop14 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame104.png"));
                prop15 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame105.png"));
                prop16 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame106.png"));
                prop17 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame107.png"));
                prop18 = ImageIO.read(getClass().getResourceAsStream("/Prop Sprites/TilemapForGame108.png"));

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

}

