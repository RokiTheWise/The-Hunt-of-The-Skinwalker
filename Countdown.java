public class Countdown implements  Runnable{
    
    int hideSeconds, huntSeconds, revengeSeconds;
    String playerType;
    boolean gameIsOver;

    /**
     * The Constructor sets the GameTimers, wherein hideseconds is the amount of time for the skinwalker to hide,
     * huntseconds as the amount of time for the hunter to hunt, and revenge seconds is the amount of time for the 
     * skinwalker to take revenge.
     */
    public Countdown(){
        hideSeconds = 15;
        huntSeconds = 60;
        revengeSeconds = 30;
        playerType = "None";
        gameIsOver = false;
    }

    /**
     * an ovverride of the run method of Runnable, so that it can run on a separate thread.
     */
    @Override
    public void run(){
            this.runHideTimer();
        
    }

    /**
     * a method that runs the hide timer for the game. It also automatically runs the hunt timer.
     */
    public void runHideTimer(){
        if (playerType.equals("Hunter")){
            System.out.println("The Skinwalker will have " + hideSeconds +  " seconds to hide. Please wait patiently.");
        } else{
             System.out.println("You have " + hideSeconds +  " seconds to hide. HIDE NOW!");
        }
        
        while (hideSeconds>0 && gameIsOver == false){
          System.out.println("Remaining: "+hideSeconds+" seconds");
          try {
            hideSeconds--;
            Thread.sleep(1000L);    // 1000L = 1000ms = 1 second
           }
           catch (InterruptedException e) {
               //I don't think you need to do anything for your particular problem
           }
         }
         if (hideSeconds == 0){
            runHuntTimer();
         }
    }

    /**
     * a method that runs the hunt timer for the game. It also automatically runs the revenge timer.
     */
    public void runHuntTimer(){
        if (playerType.equals("Hunter")){
            System.out.println("You have " + huntSeconds +  " seconds to kill the skinwalker. Good luck!");
        } else{
            System.out.println("The Hunter has " + hideSeconds +  " seconds to kill you. Good luck!");
        }
        while (huntSeconds>0 && gameIsOver == false){
          System.out.println("Remaining: "+huntSeconds+" seconds");
          try {
            huntSeconds--;
            Thread.sleep(1000L);    // 1000L = 1000ms = 1 second
           }
           catch (InterruptedException e) {
               //I don't think you need to do anything for your particular problem
           }
         }
         if (huntSeconds == 0){
            revengeTimer();
         }
    }

    /**
     * a method that runs the revenge timer for the game. which also marks the end of the game.
     */
    public void revengeTimer(){
        if (playerType.equals("Hunter")){
            System.out.println("You have " + revengeSeconds +  " seconds to have your revenge. FIND HIM!");
        } else{
            System.out.println("The Skinwalker has been enraged. He has " + revengeSeconds +  " seconds to kill you. Hide or get slain.");
        }
        while (revengeSeconds>0 && gameIsOver == false){
          System.out.println("Remaining: "+revengeSeconds+" seconds");
          try {
            revengeSeconds--;
            Thread.sleep(1000L);    // 1000L = 1000ms = 1 second
           }
           catch (InterruptedException e) {
               //I don't think you need to do anything for your particular problem
           }
         }
    }

     /**
     * returns the amount of time for hiding
     * @return the amount of time
     */
    public int  getHideSeconds(){
        return hideSeconds;
    }

    /**
     * returns the amount of time for hunting
     * @return the amount of time
     */
    public int getHuntSeconds(){
        return huntSeconds;
    }

    /**
     * returns the amount of time for revenge
     * @return the amount of time
     */
    public int getRevengeSeconds(){
        return revengeSeconds;
    }

    /**
     * sets the type of the Player for custom system outputs
     * @param s the playertype
     */
    public void setPlayerType(String s){
        playerType = s;
    }


    /**
     * sets the state of the game if it is still working. It only gets called whenever a person is hit by the 
     * other's attack.
     * @param b the truth value if the game is still running.
     */
    public void setGameisOver(boolean b){
        gameIsOver = b;
    }
}
