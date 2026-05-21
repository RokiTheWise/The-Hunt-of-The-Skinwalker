import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class GameFrame extends JFrame { 
    private GameCanvas gc;
    private int width, height, playerID;
    private Container contentPane;
    private static Player me;
    private static Player other;
    private static double bulletX, bulletY; 
    private static boolean isFired, gameFinished;
    private int scroll, otherScroll;
    private Timer animationTimer;
    private boolean up, down, left, right, sprint;
    private double speed, otherX, otherY;
    private Socket socket;
    private ReadFromServer rfsRunnable;
    private WriteToServer wtsRunnable;
    private int bulletCount, oldBulletCount, clipCounter;
    private boolean needReload, hasBulletsLeft;
    private String playerStatus, playerType, direction, otherDirection;
    private static BulletSprite bullet;
    private double mouseX, mouseY,bulletSpeedX, bulletSpeedY;
    private BufferedImage placeholderSkinWalker, placeholderHunter;
    private Boolean isMoving, isOtherMoving, isAProp, isOtherAProp, isColliding, isAttacked, isCancelled, loserHasPlayed, winnerHasPlayed,
    skinwalkerAttacked;
    private final  int PLAYERWIDTH, BULLETWIDTH;
    private Clip clip1, clip;
    private double sprintMeter;
    private int hideSeconds, huntSeconds, revengeSeconds;
    private Countdown cd;


    /**
     * The constructor intiializes the various variables to be used for the program
     * @param w the desired width
     * @param h the desired height
     */
    public GameFrame(int w, int h){
        width = w;
        height = h;
        up = false;
        down = false;
        left = false;
        right = false;
        speed = 5;
        bulletCount = 25;
        oldBulletCount = bulletCount;
        needReload = false;
        playerStatus = null;
        isFired = false;
        playerType = null;
        hasBulletsLeft = true;
        direction = "none";
        isMoving = false;
        isOtherMoving = false;
        otherDirection = "none";
        isAProp = false;
        otherScroll = 0;
        PLAYERWIDTH = 64;
        BULLETWIDTH = 32;
        isColliding = false;
        otherX = 0;
        otherY = 0;
        gameFinished = false;
        isAttacked = false;
        isCancelled = false;
        loserHasPlayed = false;
        winnerHasPlayed = false;
        clipCounter = 0;
        sprintMeter = 50;
        cd = new Countdown();
        hideSeconds = -1;
        huntSeconds = -1;
        revengeSeconds = -1;
        skinwalkerAttacked = false;
        
    }

    /**
     * the setUpGUI method is responsible for setting up the GUI so the clients can view the game. It also plays the
     * game's music immediately once created.
     */
    public void setUpGUI(){
        contentPane = this.getContentPane();
        this.setTitle("The Hunt of the SkinWalker - Player #" + playerID);
        this.setPreferredSize(new Dimension(width,height));
        createSprites();
        gc = new GameCanvas();
        gc.setPlayerType(playerType);
        cd.setPlayerType(playerType);
        contentPane.add(gc);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        setUpAnimationTimer();
        setUpKeyListener();
        setUpMouseListener();
        setUpMouseWheelListener();
        try {
            miniAudioPlayer("TungSahurTheme.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error at Miniplayer");
        }
        cd.run();

    }
    /**
     * The method responsible for creating the sprites of the game, which is passed to game canvas.
     */
    private void createSprites(){
        getPlayerModels();
        if ("Hunter".equals(playerType)){
        me = new Hunter(100, 400, PLAYERWIDTH, placeholderHunter);
        other = new SkinWalker(490, 400, PLAYERWIDTH, placeholderSkinWalker);
        } else{
        me = new SkinWalker(100, 400, PLAYERWIDTH, placeholderHunter);
        other = new Hunter(490, 400, PLAYERWIDTH, placeholderSkinWalker);
        }
    }
    

    /**
     * The method responsible for setting up the animation timer, giving the game life. Most of the game's function
     * falls here
     */
    private void setUpAnimationTimer(){
        int interval = 10;

        /**
         * An inner class also responsible for th game's features. It also attempts retrieving audio files for game sound
         * effects. Through this class, the canvas keeps getting repainted, allowing streamline animations.
         */
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent ae){


                gc.setPlayerX((int) me.getX());
                gc.setPlayerY((int) me.getY());
                if (gameFinished != true){
                    hideSeconds = cd.getHideSeconds();
                    huntSeconds = cd.getHuntSeconds();
                    revengeSeconds = cd.getRevengeSeconds();
                    gc.setHidingTime(hideSeconds);
                    gc.setHuntingTime(huntSeconds);
                    gc.setRevengeTime(revengeSeconds);
                    gc.setSkinWalkerAttacked(skinwalkerAttacked);

                    if (playerType.equals("Skinwalker")){
                        gc.setSkinWalkerX(me.getX());
                        gc.setSkinWalkerY(me.getY());
                    } else{
                        gc.setSkinWalkerX(otherX);
                        gc.setSkinWalkerY(otherY);
                    }
                        
                    

                    if (playerType.equals("Skinwalker") && oldBulletCount != bulletCount){
                        if (bulletCount > 0){
                            System.out.println("A shot is fired. Hunter has " + bulletCount +  " bullets left.");
                            try {
                                miniAudioPlayer("GunShot.wav");
                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("The hunter has no bullets left. it's time for you to hunt him down.");
                        }
                        oldBulletCount = bulletCount;
                    }

                    if (skinwalkerAttacked){
                        try {
                            miniAudioPlayer("SkinWalkerStomp.wav");
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
                            e2.printStackTrace();
                        }
                        if (playerType.equals("Hunter")){
                            System.out.println("Run! the Skinwalker is Attacking!");
                        }
                        skinwalkerAttacked = false;
                    }

                    if (revengeSeconds <= 0){
                        clip1.stop();
                        clip1.close();
                        cd.setGameisOver(true);
                            try {
                                miniAudioPlayer("drawSound.wav");
                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                e.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(null, "The time has ran out. it's a draw!", "Game Over", JOptionPane.WARNING_MESSAGE);
                            System.out.println("It's a Draw!");
                            gameFinished = true;
                    }

                    collisionTester();
                    if (isColliding){
                        clip1.stop();
                        clip1.close();
                        cd.setGameisOver(true);
                        if (playerType.equals("Skinwalker")){
                            try {
                                miniAudioPlayer("GTADeathSound.wav");
                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                e.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(null, "Nice Try! Better Luck Next Time:(", "Game Over", JOptionPane.ERROR_MESSAGE);
                            System.out.println("gang you just died");
                            gameFinished = true;
                        } else{
                            try {
                                miniAudioPlayer("pvz-victory.wav");
                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                e.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(null, "Congratulations! You Won:)", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println("Congrats you won!");
                            gameFinished = true;
                        }
                    }

                    if (isAttacked){
                        clip1.stop();
                        clip1.close();
                        cd.setGameisOver(true);
                        if (playerType.equals("Hunter")){
                            try {
                                miniAudioPlayer("GTADeathSound.wav");
                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                e.printStackTrace();
                            }
                             JOptionPane.showMessageDialog(null, "Nice Try! Better Luck Next Time:(", "Game Over", JOptionPane.ERROR_MESSAGE);
                            System.out.println("gang you just died");
                            gameFinished = true;
                        } else{
                            try {
                                miniAudioPlayer("pvz-victory.wav");
                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                e.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(null, "Congratulations! You Won:)", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println("Congrats you won!");
                            gameFinished = true;
                        }
                    }

                    if (scroll != 0){
                        isAProp = true;
                    }else{
                        isAProp = false;
                    }
                    if (scroll > 10){
                        scroll = -8;
                    } else if (scroll < -8){
                        scroll = 10;
                    }

                    selfAnimate();
                    otherAnimate();

                    if (bullet!= null){
                        bullet.moveH(bulletSpeedX);
                        bullet.moveV(bulletSpeedY);
                    }
                    gc.repaint();
                } else{
                    if (isColliding){
                    if (playerType.equals("Skinwalker")){
                        me.deathAnimation();
                    } else{
                        other.deathAnimation();
                    }
                    }
                    if (isAttacked){
                        if (playerType.equals("Hunter")){
                        me.deathAnimation();
                    } else{
                        other.deathAnimation();
                    }
                    }
                    gc.repaint();
                }
            }
        };
        animationTimer = new Timer(interval,al);
        animationTimer.start();
    }
    /**
     * The method responsble for setting up the mousewheel listener
     */
    private void setUpMouseWheelListener(){

        /**
         * An inner class responsible for assigning a scroll wheel  function for the skinwalker to shift
         */
        MouseWheelListener mwl = new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (playerType.equals("Skinwalker")){
                    int notches = e.getWheelRotation();
                    if (notches < 0){
                        scroll++;
                        System.out.println("Mouse has moved up");
                    } else{
                        scroll--;
                        System.out.println("Mouse has moved down");
                    }
                    try {
                        miniAudioPlayer("WarpSound.wav");
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e3) {
                        System.out.println("Error at Miniplayer");
                    }
                }
            }
        };
        gc.addMouseWheelListener(mwl);
        gc.setFocusable(true);
        gc.requestFocusInWindow();
    }
    /**
     * The method responsble for setting up the mouse listener
     */
    private void setUpMouseListener(){
        
        /**
         * An inner class responsible for assigning the attack function for both players
         */
        MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (playerType.equals("Hunter") && huntSeconds> 0 && hideSeconds <= 0){
                    if (needReload != true && hasBulletsLeft){
                    bulletX = me.getX()+BULLETWIDTH-16;
                    bulletY = me.getY()+BULLETWIDTH-16;
                    mouseX = e.getX() - 16 + gc.getCameraX();
                    System.out.println("true mouse x is at :" + e.getX());
                    System.out.println("mouseX is at:" + mouseX);
                    mouseY = e.getY() - 16 + gc.getCameraY();
                    System.out.println("true mouse x is at :" + e.getY());
                    System.out.println("mouseY is at:" + mouseY);
                    shootTrajectory();
                    isFired = true;
                    bulletCount--;
                    try {
                        miniAudioPlayer("GunShot.wav");
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("Fire! " + bulletCount + " bullets left.");
                    if (bulletCount == 0){
                        hasBulletsLeft = false;
                        System.out.println("You shot your final bullet. RUN FOR YOUR LIFE!");
                    }
                    needReload = true;
                    } else if (hasBulletsLeft == false) {
                        System.out.println("You dont have bullets left.");
                         try {
                        miniAudioPlayer("Error.wav");
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                            e1.printStackTrace();
                        }
                    } 
                    else{
                        System.out.println("You need to reload.");
                        try {
                        miniAudioPlayer("Error.wav");
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                            e1.printStackTrace();
                        }
                    }
                } else if (playerType.equals("Skinwalker") && (bulletCount == 0 || (revengeSeconds > 0 && huntSeconds <=0))){
                    skinwalkerAttacked = true;
                    skinwalkerAttack();
                }
            } 
            
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {;
            }
            
        };
        gc.addMouseListener(ml);
        gc.setFocusable(true);
        gc.requestFocusInWindow();
    }
    /**
     * The method responsble for setting up the key listener
     */
    private void setUpKeyListener(){

        /**
         * The inner class responsible for tracking keymovemens for directional movement as well as reloading.
         */
        KeyListener kl = new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch(keyCode){
                    case KeyEvent.VK_W:
                        up = true;
                        break;
                    case KeyEvent.VK_S:
                        down = true;
                        break;
                    case KeyEvent.VK_A:
                        left = true;
                        break;
                    case KeyEvent.VK_D:
                        right = true;
                        break;
                    case KeyEvent.VK_SHIFT:
                    if (sprintMeter > 0){
                        sprint = true;
                    } else {
                        sprint = false;
                    }
                    break;
                    case KeyEvent.VK_R:
                    if (playerStatus.substring(56).equals("Hunter.")){
                        if (hasBulletsLeft == true && needReload == true){
                            System.out.println("Reload Successful.");
                            needReload = false;
                            try {
                                miniAudioPlayer("Reload.wav");
                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                                e1.printStackTrace();
                            }
                        }else if(hasBulletsLeft == true && needReload == false){
                            System.out.println("Your gun is already loaded.");
                             try {
                            miniAudioPlayer("Error.wav");
                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                                e1.printStackTrace();
                            }
                        }else{
                            System.out.println("You don't have bullets to reload");
                             try {
                            miniAudioPlayer("Error.wav");
                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                                e1.printStackTrace();
                            }
                        }
                        break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch(keyCode){
                    case KeyEvent.VK_W:
                        up = false;
                        break;
                    case KeyEvent.VK_S:
                        down = false;
                        break;
                    case KeyEvent.VK_A:
                        left = false;
                        break;
                    case KeyEvent.VK_D:
                        right = false;
                        break;
                    case KeyEvent.VK_SHIFT:
                        sprint = false;
                        break;
                }
            }
        };
        gc.addKeyListener(kl);
        gc.setFocusable(true);
        gc.requestFocusInWindow();
    }

    /**
     * the method responsible for connecting to the server through a socket connection.
     */
    public void connectToServer(){
        try {
            socket = new Socket("localhost", 45731);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            playerID = in.readInt();
            System.out.println("You are Player #" + playerID +".");
            if (playerID == 1){
                System.out.println("Waiting for player #2 to connect...");
            }
            rfsRunnable = new ReadFromServer(in);
            wtsRunnable = new WriteToServer(out);
            rfsRunnable.waitForStartMsg();
            
        } catch (IOException ex) {
            System.out.println("IOException from connectToServer()");
        }
    }
    /**
     * This inner class is responsible for reading ouputs of the server, and breaking down the string that it sent
     */
    private class ReadFromServer implements Runnable{

        private DataInputStream dataIn;
        /**
         * Constructor initializes the datainput stream
         * @param in the datainput stream
         */
        public ReadFromServer(DataInputStream in){
            dataIn = in;
            System.out.println("RFS Runnable Created");
        }
        /**
         * The run method from runnable, so that it can be ran on a thread.
         */
        public void run(){
            try {
                while(true){
                    String stringToIntepret = dataIn.readUTF();
                    String[] arrayOfStrings = stringToIntepret.split(",");
                    otherX = Double.parseDouble(arrayOfStrings[0]);
                    otherY = Double.parseDouble(arrayOfStrings[1]);
                    if (playerType.equals("Skinwalker")){
                        bulletCount = Integer.parseInt(arrayOfStrings[5]);
                        if (arrayOfStrings[2].equals("true")){
                        bulletX = Double.parseDouble(arrayOfStrings[3]);
                        bulletY = Double.parseDouble(arrayOfStrings[4]);
                        isFired = true;
                        }
                    }else{
                        skinwalkerAttacked = Boolean.parseBoolean(arrayOfStrings[11]);
                    }
                    
                    if (other != null){
                        other.setX(otherX);
                        other.setY(otherY);
                        isOtherMoving = Boolean.parseBoolean(arrayOfStrings[6]);
                        otherDirection = arrayOfStrings[7];
                        isOtherAProp = Boolean.parseBoolean(arrayOfStrings[8]);
                        otherScroll = Integer.parseInt(arrayOfStrings[9]);
                        if(playerType.equals("Hunter")){
                            isAttacked = Boolean.parseBoolean(arrayOfStrings[10]);
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println("IOException from RFS run()");
            }
        }

        /**
         * The method is responsible for updating the users about the current players on, and preventing a GUI
         * to appear before both server connects.
         */
        public void waitForStartMsg() {
            try {
                String startMsg = dataIn.readUTF();
                playerStatus = ("Message from server: " + startMsg);
                System.out.println(playerStatus);
                if (playerStatus.substring(56).equals("Hunter.")){
                    playerType = "Hunter";
                }else{
                    playerType = "Skinwalker";
                }
                Thread readThread = new Thread(rfsRunnable);
                Thread writeThread = new Thread(wtsRunnable);
                readThread.start();
                writeThread.start();
            } catch (IOException ex) {
                System.out.println("IOException from waitForStartMsg()");
            }
        }  

    }
    /**
     * This inner class is responsible for writing a concatenated string for the server to breakdown
     */
    private class WriteToServer implements Runnable{
        
        private DataOutputStream dataOut;
        /**
         * Constructor initializes the datoutputstream
         * @param out the dataoutput stream
         */
        public WriteToServer(DataOutputStream out){
            dataOut = out;
            System.out.println("WTS Runnable Created");
        }


        /**
         * the method from runnable in order for it to run on a separate thread
         */
        public void run(){
            try {
                int bulletExist = -1;
                while (true) { 
                    if (me != null){
                        if (playerType.equals("Hunter")){
                                String stringtoSend = "error";
                            if (bullet != null){
                                bulletExist = 1;
                                stringtoSend = /*0*/playerID + "," + /*1*/playerType + "," + /*2*/me.getX() + "," + 
                                /*3*/me.getY() + "," + /*4*/bulletExist + "," + /*5*/bullet.getX() + "," + /*6*/bullet.getY() + 
                                "," + /*7*/bulletCount + "," + /*8*/isMoving + "," +/*9*/ direction + "," + /*10*/isAProp +
                                "," + /*11*/scroll + "," + isAttacked + "," + /*13*/skinwalkerAttacked;
                            }else{
                                stringtoSend = playerID + "," + playerType + "," + me.getX() + "," + me.getY() + "," + 
                                bulletExist + "," + -1 + "," + -1 + "," + bulletCount + "," + isMoving + "," + direction + "," + /*10*/isAProp +
                                "," + /*11*/scroll + "," + isAttacked + "," + /*13*/skinwalkerAttacked;
                            }
                            dataOut.writeUTF(stringtoSend);
                        } else{
                             String stringtoSend = playerID + "," + playerType + "," + me.getX() + "," + me.getY() + "," + bulletExist + 
                             "," + -1 + "," + -1 + "," + -1 + "," + isMoving + "," + direction + "," + /*10*/isAProp + "," + /*11*/scroll + "," + 
                             isAttacked + "," + /*13*/skinwalkerAttacked;
                            dataOut.writeUTF(stringtoSend);
                        }
                    
                    //either prefix playerxy and bullet xy or have a number that if bullet dne, its -1.
                    
                    dataOut.flush();
                    }
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException ex) {
                        System.out.println("InterruptedException from WTS run()");
                    }
                }
            } catch (IOException ex) {
                System.out.println("IOException from WTS run()");
            }
        }

        

    }
    /**
     * The method responsible for computing the bullet's trajectory
     */
    private void shootTrajectory(){
        double yDifference = mouseY - bulletY;
        double xDifference = mouseX - bulletX;
        double distance = Math.sqrt(Math.pow(xDifference,2) + Math.pow(yDifference,2));
        bulletSpeedX = (xDifference/distance) * 10;
        bulletSpeedY = (yDifference/distance) * 10;
    }

    /**
     * tries to retrieve player models to be used for the game
     */
     public void getPlayerModels(){
            try {
                placeholderSkinWalker = ImageIO.read(getClass().getResourceAsStream("/SkinWalker Sprites/SkinWalker Down/SKIN_DIDLE1.png"));
                placeholderHunter = ImageIO.read(getClass().getResourceAsStream("/Hunter Sprites/Hunter Down/HunterDown1.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    /*
     * tests if there is a collision between the Skinwalker and the bullet
     */
    public void collisionTester(){
        if (bullet != null){
            if (playerType.equals("Skinwalker")){
                if(!(me.getX() + PLAYERWIDTH <= bulletX||
                    me.getX() >= bulletX + BULLETWIDTH ||
                    me.getY() + PLAYERWIDTH <= bulletY ||
                    me.getY() >= bulletY + BULLETWIDTH)){
                        isColliding = true;
                }else{
                    isColliding = false;
                }
            }else{
                if(!(other.getX() + PLAYERWIDTH <= bullet.getX()||
                    other.getX() >= bullet.getX() + BULLETWIDTH ||
                    other.getY() + PLAYERWIDTH <= bullet.getY() ||
                    other.getY() >= bullet.getY() + BULLETWIDTH)){
                        isColliding = true;
                }else{
                    isColliding = false;
                }
            }
        }
    }
    /**
     * Detects for a collision between the skinwalker's attack and the hunter
     */
    private void skinwalkerAttack(){
        if(!(me.getX() + 2*PLAYERWIDTH<= other.getX()||
            me.getX() >= other.getX() + 2*PLAYERWIDTH ||
            me.getY() + 2*PLAYERWIDTH <= other.getY() ||
            me.getY() >= other.getY() + 2*PLAYERWIDTH)){
            isAttacked = true;
        }else{
            isAttacked = false;
        }
    }
    /**
     * Method responsible for animation and movement of the client
     */
    public void selfAnimate(){
        if (sprint){
                    if (sprintMeter > 0){
                    sprintMeter-=0.5;
                    speed = 7.5;
                    me.setFrames(3);
                    } else {
                        sprintMeter = 0;
                        speed = 5;
                        me.setFrames(5);
                    }
                    
                } else{
                    speed = 5;
                    me.setFrames(5);
                    if (sprintMeter < 50){
                    sprintMeter += 0.1;
                    } else{
                        sprintMeter = 50;
                    }
                }

                if (right == false && direction.equals("right")){
                    me.rightIdleCycle();
                    isMoving = false;
                }
                if (left == false && direction.equals("left")){
                    me.leftIdleCycle();
                    isMoving = false;
                }
                if (down == false && direction.equals("down") || "none".equals(direction)){
                    me.downIdleCycle();
                    isMoving = false;
                }
                if (up == false && direction.equals("up")){
                    me.upIdleCycle();
                        isMoving = false;
                }

                if (up && right && left && down) {
                    me.downIdleCycle();
                    isMoving = false;
                }else if (up && right){ //fixed
                    if (down){ //take
                        direction = "right";
                        me.moveH(speed);
                        me.rightCycle();
                    } else if (left){
                        direction = "up";
                        me.moveV(-speed);
                        me.upCycle();
                    } else {
                        direction = "right";
                        me.moveV(-speed / Math.sqrt(2));
                        me.moveH(speed / Math.sqrt(2));
                        me.rightCycle();
                    }
                    isMoving = true;
                }else if(up && left){ //fixed
                     if (down){ //take
                        direction = "left";
                        me.moveH(-speed);
                        me.leftCycle();
                    } else {
                        direction = "left";
                        me.moveV(-speed / Math.sqrt(2));
                        me.moveH(-speed / Math.sqrt(2));
                        me.leftCycle();
                    }
                    isMoving = true;
                }else if(down && left){ //fixed
                    if (right){
                        direction = "down";
                        me.moveV(speed);
                        me.downCycle();
                        isMoving = true;
                    }else{
                        direction = "left";
                        me.moveV(speed / Math.sqrt(2));
                        me.moveH(-speed / Math.sqrt(2));
                        me.leftCycle();
                    }
                    isMoving = true;
                }else if(down && right){
                    direction = "right";
                    me.moveV(speed / Math.sqrt(2));
                    me.moveH(speed / Math.sqrt(2));
                    me.rightCycle();
                    isMoving = true;
                }else if (right && left){
                    me.downIdleCycle();
                    isMoving = false;
                }else if (up && down){
                    me.downIdleCycle();
                    isMoving = false;
                }else{
                    if (up){
                        direction = "up";
                        me.moveV(-speed);
                        me.upCycle();
                        isMoving = true;
                    } if (down){
                        direction = "down";
                        me.moveV(speed);
                        me.downCycle();
                        isMoving = true;
                    } if (left){
                        direction = "left";
                        me.moveH(-speed);
                        me.leftCycle();
                        isMoving = true;
                    } if (right){
                        direction = "right";
                        me.moveH(speed);
                        me.rightCycle();
                        isMoving = true;
                    }
                }   
                me.setScroll(scroll);
                if (isAProp){
                        me.scrollCycle();
                    }
    }
    /**
     * returns the Player
     * @return player
     */
    public static Player getMe(){
        return me;
    }

    /**
     * returns the other player
     * @return other
     */
    public static Player getOther(){
        return other;
    }
    /**
     * sets the bullet 
     * @param b the bullet
     */
    public static void  setBullet(BulletSprite b){
        bullet = b;
    }
    /**
     * returns the bullet
     * @return bullet
     */
    public static BulletSprite getBullet(){
        return bullet;
    }

    public static double getBulletX(){
        return bulletX;
    }
    public static double getBulletY(){
        return bulletY;
    }
    public static void setIsFired(boolean b){
        isFired = b;
    }
    public static boolean  getIsFired(){
        return isFired;
    }

    /**
     * Animates the other player
     */
    public void otherAnimate(){
        if (isOtherMoving == false){
            if (otherDirection.equals("right")){
                other.rightIdleCycle();
            }
            if (otherDirection.equals("left")){
                other.leftIdleCycle();
            }
            if (otherDirection.equals("down") || "none".equals(otherDirection)){
                other.downIdleCycle();
            }
            if (otherDirection.equals("up")){
                other.upIdleCycle();
            } 
        } else{
            if (otherDirection.equals("right")){
                other.rightCycle();
            }
            if (otherDirection.equals("left")){
                other.leftCycle();
            }
            if (otherDirection.equals("down") || "none".equals(otherDirection)){
                other.downCycle();
            }
            if (otherDirection.equals("up")){
                other.upCycle();
            } 
        }
        other.setScroll(otherScroll);
        if (isOtherAProp){
            other.scrollCycle();
        }
    }
    /**
     * A mini Audioplayer for playing the game's soundeffects
     * @param s the file name of the sfx to be played.
     */
    public void miniAudioPlayer(String s) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
      File f = new File(s);
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f);
      if(clipCounter == 0){
        clip1 = AudioSystem.getClip();
        clip1.open(audioInputStream);
        clip1.start();
        clip1.loop(Clip.LOOP_CONTINUOUSLY);
      } else{
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
      }
      clipCounter++;
    }



}
