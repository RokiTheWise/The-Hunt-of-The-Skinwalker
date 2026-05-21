
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class TileManager {
    GameCanvas gc;
    Tile [] tile;
    int mapTileNum[][];
    int mapTileNum2[][];

    String filestream;

    public TileManager (GameCanvas gc) {
        this.gc = gc;

        tile = new Tile[133];
        mapTileNum = new int [gc.maxWorldCol][gc.maxWorldRow];
        mapTileNum2 = new int [gc.maxWorldCol][gc.maxWorldRow];

        getTileImage();
        drawMap("/maps/FinalMapPlatform.txt", mapTileNum);
        drawMap("/maps/FinalProject2ndLayer.txt", mapTileNum2);

    }

    public void drawMap(String filestream, int [][] mapTileNum) {
        try {
            InputStream is = getClass().getResourceAsStream(filestream);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            int col = 0;

            while (col < gc.maxWorldCol && row < gc.maxWorldRow) {
                String line = br.readLine();

                while(col < gc.maxWorldCol) {
                    String numbers[] = line.split(",");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gc.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }
    }

    public void getTileImage(){
        
        try {
            for (int i = 0; i < 131; i++) {
                tile[i] = new Tile();
            }

            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame1.png"));
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame2.png"));
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame3.png"));
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame4.png"));
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame5.png"));
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame6.png"));
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame7.png"));
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame8.png"));
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame9.png"));
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame10.png"));
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame11.png"));
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame12.png"));
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame13.png"));
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame14.png"));
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame15.png"));
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame16.png"));
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame17.png"));
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame18.png"));
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame19.png"));
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame20.png"));
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame21.png"));
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame22.png"));
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame23.png"));
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame24.png"));
            tile[24].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame25.png"));
            tile[25].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame26.png"));
            tile[26].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame27.png"));
            tile[27].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame28.png"));
            tile[28].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame29.png"));
            tile[29].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame30.png"));
            tile[30].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame31.png")); //final consecutive
            tile[33].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame34.png"));
            tile[34].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame35.png"));
            tile[35].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame36.png"));
            tile[36].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame37.png"));
            tile[37].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame38.png"));
            tile[38].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame39.png"));
            tile[39].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame40.png"));
            tile[40].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame41.png"));
            tile[41].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame42.png"));
            tile[44].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame45.png"));
            tile[45].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame46.png"));
            tile[46].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame47.png"));
            tile[47].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame48.png"));
            tile[48].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame49.png"));
            tile[49].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame50.png"));
            tile[50].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame51.png"));
            tile[51].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame52.png"));
            tile[52].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame53.png"));
            tile[55].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame56.png"));
            tile[56].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame57.png"));
            tile[57].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame58.png"));
            tile[58].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame59.png"));
            tile[59].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame60.png"));
            tile[60].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame61.png"));
            tile[61].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame62.png"));
            tile[62].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame63.png"));
            tile[63].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame64.png"));
            tile[66].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame67.png"));
            tile[67].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame68.png"));
            tile[68].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame69.png"));
            tile[69].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame70.png"));
            tile[70].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame71.png"));
            tile[71].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame72.png"));
            tile[72].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame73.png"));
            tile[73].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame74.png"));
            tile[74].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame75.png"));
            tile[77].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame78.png"));
            tile[78].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame79.png"));
            tile[79].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame80.png"));
            tile[80].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame81.png"));
            tile[81].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame82.png"));
            tile[82].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame83.png"));
            tile[83].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame84.png"));
            tile[84].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame85.png"));
            tile[85].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame86.png"));
            tile[88].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame89.png"));
            tile[89].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame90.png"));
            tile[90].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame91.png"));
            tile[91].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame92.png"));
            tile[92].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame93.png"));
            tile[93].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame94.png"));
            tile[94].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame95.png"));
            tile[95].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame96.png"));
            tile[96].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame97.png"));
            tile[99].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame100.png"));
            tile[100].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame101.png"));
            tile[101].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame102.png"));
            tile[102].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame103.png"));
            tile[103].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame104.png"));
            tile[104].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame105.png"));
            tile[105].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame106.png"));
            tile[106].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame107.png"));
            tile[107].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame108.png"));
            tile[110].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame111.png"));
            tile[111].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame112.png"));
            tile[112].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame113.png"));
            tile[113].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame114.png"));
            tile[114].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame115.png"));
            tile[114].Collision = true;
            tile[115].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame116.png"));
            tile[116].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame117.png"));
            tile[117].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame118.png"));
            tile[118].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame119.png"));
            tile[121].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame122.png"));
            tile[122].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame123.png"));
            tile[123].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame124.png"));
            tile[124].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame125.png"));
            tile[125].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame126.png"));
            tile[126].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame127.png"));
            tile[127].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame128.png"));
            tile[128].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame129.png"));
            tile[129].image = ImageIO.read(getClass().getResourceAsStream("/tile/TilemapForGame130.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawTiles(Graphics2D g2d) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gc.maxWorldCol && row < gc.maxWorldRow){ 
            int tileNum = mapTileNum[col][row];
            int tileNum2 = mapTileNum2[col][row];
           
            g2d.drawImage(tile[tileNum-1].image, x, y, gc.tileSize, gc.tileSize, null);

            if (tileNum2 != 0) {
                g2d.drawImage(tile[tileNum2-1].image, x, y, gc.tileSize, gc.tileSize, null);
            }
            
            col++;
            x += gc.tileSize;

            if (col == gc.maxWorldCol){
                col = 0;
                x = 0;
                row ++;
                y += gc.tileSize;
            }
        }
    }
}