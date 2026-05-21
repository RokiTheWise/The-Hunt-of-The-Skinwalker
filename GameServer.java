import java.io.*;
import java.net.*;

public class GameServer {
    
    private ServerSocket ss;
    private int numPlayers;
    private int maxPlayers;
    private int sent;

    private Socket p1Socket, p2Socket;
    private ReadFromClient p1ReadRunnable, p2ReadRunnable;
    private WriteToClient p1WriteRunnable, p2WriteRunnable;
    private double p1x, p1y, p2x, p2y;
    private String p1status, p2status, player1Role,player2Role, p1Direction, p2Direction;
    private double bulletX, bulletY;
    private boolean  bulletExist, p1IsMoving,p2IsMoving, p1IsAProp, p2IsAProp, p1isAttacked, p2isAttacked, theSkinWalkerAttacked;
    private int numBulletsLeft, p1Scroll, p2Scroll;


    /**
     * Constructor initializes the variables used
     */
    public GameServer(){
        System.out.println("===== GAME SERVER =====");
        numPlayers = 0;
        maxPlayers = 2;
        bulletX = -1;
        bulletY = -1;
        bulletExist = false;
        numBulletsLeft = 6;
        p1IsMoving = false;
        p1Direction = "none";
        p2IsMoving = false;
        p2Direction = "none";
        p1isAttacked = false;
        p2isAttacked = false;
        theSkinWalkerAttacked = false;

        p1x = 100;
        p1y = 400;
        p2x = 490;
        p2y = 400;
        sent = 0;

        try {
            ss = new ServerSocket(45731);
        } catch (IOException ex) {
            System.out.println("IOException from GameServer constructor");
        }

    }

    /**
     * method resposible for accepting serverside connections
     */
    public void acceptConnections(){
        try {
            System.out.println("Waiting for Connections...");
            while (numPlayers<maxPlayers){
                Socket s = ss.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                numPlayers++;
                out.writeInt(numPlayers);
                System.out.println("Players #" + numPlayers + " has connected.");

                ReadFromClient rfc = new ReadFromClient(numPlayers, in);
                WriteToClient wtc = new WriteToClient(numPlayers, out);

                if (numPlayers == 1){
                    p1Socket = s;
                    p1ReadRunnable = rfc;
                    p1WriteRunnable = wtc;
                } else{
                    p2Socket = s;
                    p2ReadRunnable = rfc;
                    p2WriteRunnable = wtc;
                    randomStatusRoll();
                    p1WriteRunnable.sendStartMsg();
                    p2WriteRunnable.sendStartMsg();
                    Thread readThread1 = new Thread(p1ReadRunnable);
                    Thread readThread2 = new Thread(p2ReadRunnable);
                    readThread1.start();
                    readThread2.start();
                    Thread writeThread1 = new Thread(p1WriteRunnable);
                    Thread writeThread2 = new Thread(p2WriteRunnable);
                    writeThread1.start();
                    writeThread2.start();

                }

            }

            
            System.out.println("No longer accepting connections");
        } catch (IOException ex) {
            System.out.println("IOException from acceptConnections()");
        }
    }
    
    /**
     * An inner class responsible for reading the output of the clients
     */
    private class ReadFromClient implements Runnable {

        private int playerID;
        private DataInputStream dataIn;
        
        public ReadFromClient(int pid, DataInputStream in){
            playerID = pid;
            dataIn = in;
            System.out.println("RFC" + playerID + " Runnable created");
        
        }

        public void run(){
            try {
                while (true) { 
                    if (playerID == 1){
                        String stringToIntepret = dataIn.readUTF();
                        String[] arrayOfStrings = stringToIntepret.split(",");
                        player1Role = arrayOfStrings[1];
                        p1x = Double.parseDouble(arrayOfStrings[2]);
                        p1y = Double.parseDouble(arrayOfStrings[3]);
                        if (arrayOfStrings[4].equals("1")){
                            bulletX = Double.parseDouble(arrayOfStrings[5]);
                            bulletY = Double.parseDouble(arrayOfStrings[6]);
                            bulletExist = true;
                        }
                        if (player1Role.equals("Hunter") ){
                            numBulletsLeft = Integer.parseInt(arrayOfStrings[7]);
                        } else{
                            theSkinWalkerAttacked = Boolean.parseBoolean(arrayOfStrings[13]);
                        }
                        p1IsMoving = Boolean.parseBoolean(arrayOfStrings[8]);
                        p1Direction = arrayOfStrings[9];
                        p1IsAProp = Boolean.parseBoolean(arrayOfStrings[10]);
                        p1Scroll = Integer.parseInt(arrayOfStrings[11]);
                        p1isAttacked = Boolean.parseBoolean(arrayOfStrings[12]);

                    } else{
                        String stringToIntepret = dataIn.readUTF();
                        String[] arrayOfStrings = stringToIntepret.split(",");
                        player2Role = arrayOfStrings[1];
                        p2x = Double.parseDouble(arrayOfStrings[2]);
                        p2y = Double.parseDouble(arrayOfStrings[3]);
                        if (arrayOfStrings[4].equals("1")){
                            bulletX = Double.parseDouble(arrayOfStrings[5]);
                            bulletY = Double.parseDouble(arrayOfStrings[6]);
                            bulletExist = true;
                        }
                        if (player2Role.equals("Hunter") ){
                            numBulletsLeft = Integer.parseInt(arrayOfStrings[7]);
                        }else{
                            theSkinWalkerAttacked = Boolean.parseBoolean(arrayOfStrings[13]);
                        }
                        p2IsMoving = Boolean.parseBoolean(arrayOfStrings[8]);
                        p2Direction = arrayOfStrings[9];
                        p2IsAProp = Boolean.parseBoolean(arrayOfStrings[10]);
                        p2Scroll = Integer.parseInt(arrayOfStrings[11]);
                        p2isAttacked = Boolean.parseBoolean(arrayOfStrings[12]);
                    }
                }
            } catch (IOException ex) {
                System.out.println("IOException from RFC run()");
            }
        }
    }

    /**
     * An inner class responsible for sneding outputs to the clients
     */
    private class WriteToClient implements Runnable {

        private int playerID;
        private DataOutputStream dataOut;
        
        public WriteToClient(int pid, DataOutputStream out){
            playerID = pid;
            dataOut = out;
            System.out.println("WTC" + playerID + " Runnable created");
        
        }

        public void run(){
            try {
                while (true) { 
                    if (playerID == 1){
                        String stringtoSend =  p2x + "," + p2y + "," + bulletExist + "," + bulletX + "," + 
                        bulletY + "," + numBulletsLeft + "," + p2IsMoving + "," + p2Direction + "," + p2IsAProp +
                        "," + p2Scroll + "," + p2isAttacked + "," + theSkinWalkerAttacked;
                        dataOut.writeUTF(stringtoSend);
                    }else{
                        String stringtoSend =  p1x + "," + p1y + "," + bulletExist + "," + bulletX + "," + 
                        bulletY + "," + numBulletsLeft + "," + p1IsMoving + "," + p1Direction + "," + p1IsAProp +
                        "," + p1Scroll + "," + p1isAttacked + "," + theSkinWalkerAttacked;
                        dataOut.writeUTF(stringtoSend);
                    }
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException ex) {
                        System.out.println("InterruptedException from WTC run()");
                    }
                }
            } catch (IOException ex) {
                System.out.println("IOException from WTC run()");
            }
        }

        public void sendStartMsg(){
            try {
                if (sent == 0){
                dataOut.writeUTF("We now have 2 players. You are the " + p1status + ".");
                sent++;
                }else{
                dataOut.writeUTF("We now have 2 players. You are the " + p2status + ".");
                }
            } catch (IOException ex) {
                System.out.println("IOException from sendStartMsg");
            }
        }
    }
    /**
     * method responsible for the randomization of roles
     */
    private void randomStatusRoll(){
        double randomNumber = Math.random();
        System.out.println(randomNumber);
        if (randomNumber >= 0.5){
            p1status = "Skinwalker";
            p2status = "Hunter";
            System.out.println("P1 is Skinwalker, and P2 is Hunter.");
        } else{
            p2status = "Skinwalker";
            p1status = "Hunter";
            System.out.println("P2 is Skinwalker, and P1 is Hunter.");
        }
    }
    /**
     * Main method responsible for running the GameServer
     */
    public static void main(String[] args){
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }

}
