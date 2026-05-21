public class GameStarter {
    

    public static void main(String[] args) {
    GameFrame gf = new GameFrame(1024,768); // assuming 1 tile is 64x64 then it should display 16 x 12
                System.setProperty("sun.java2d.uiScale", "1.0"); 
                gf.connectToServer();
                gf.setUpGUI();
                
}
}
