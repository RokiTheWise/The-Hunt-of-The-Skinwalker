import java.awt.*;
import java.awt.image.BufferedImage;

public  abstract class Player{


        
    abstract void leftCycle();

    abstract void downCycle();

    abstract void rightCycle();

    abstract void upCycle();

    abstract void leftIdleCycle();

    abstract void rightIdleCycle();

    abstract void upIdleCycle();

    abstract void downIdleCycle();

    abstract void scrollCycle();

    abstract void drawSprite(Graphics2D g2d);

    abstract void moveH(double n);

    abstract void moveV(double n);

    abstract void setX(double n);

    abstract void setY(double n);

    abstract double getX();

    abstract double getY();

    abstract void setFrames(int n);

    abstract BufferedImage getImage();

    abstract void setScroll(int n);

    abstract void deathAnimation();

    abstract void loadSprites();

}