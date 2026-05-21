import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BulletSprite {
     private double x, y, size;
        private Color color;
        private Image image;

        /**
         * The constructor accepts three parameters to create the bullet, which will set the location and size of the
         * bullet. Additionally, it attempts to get an image of the bullet.
         * @param a the desired x coordinate of the bullet
         * @param b the desired y coordinate of the bullet
         * @param s the desired size of the bullet 
         */
        public BulletSprite(double a, double b, double s){
            x = a;
            y = b;
            size = s;
            try{
            image = ImageIO.read(getClass().getResourceAsStream("/BulletModel.png"));
            } catch (IOException ie){
                ie.printStackTrace();
            }
        }

        /**
         * Passes down the parameters to draw an image of the bullet
         */
         public void drawSprite(Graphics2D g2d){
            g2d.drawImage(image, (int)x, (int)y, (int)size, (int)size, null);
        }

        /**
         * shifts the bullet in the horizontal direction
         * @param n the desired movement rate
         */
        public void moveH(double n){
            x += n;
        }

        /**
         * shifts the bullet in the vertical direction
         * @param n the desired movement rate
         */
        public void moveV(double n){
            y += n;
        }

        /**
         * sets x to a certain double
         * @param n the desired double
         */
        public void setX(double n){
            x = n;
        }
        /**
         * sets y to a certain double
         * @param n the desired double
         */
        public void setY(double n){
            y = n;        
        }
        /**
         * sets the desired size of the bullet
         * @param n the desired size of the bullet (applies for both width and height)
         */
        public void setSize(double n){
            size = n;
        }

        /**
         * returns the x coordinate of the bullet
         * @return the x coordinate
         */
        public double getX(){
            return x;
        }

        /**
         * returns the y coordinate of the bullet
         * @return the y coordinate
         */
        public double getY(){
            return y;
        }
}
