import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class ColourProcessor{
    Color WHITE = new Color(200, 200, 200);//W
    Color YELLOW = new Color(200, 200, 0);//Y
    Color RED = new Color(200, 0, 30);//R
    Color ORANGE = new Color(200, 100, 0);//O
    Color BLUE = new Color(30, 100, 180);//B
    Color GREEN = new Color(30, 180, 30);//G

    Color[] cubeColours = {WHITE, YELLOW, RED, ORANGE, BLUE, GREEN};
    String[] cubeLabels = {"W", "Y", "R", "O", "B", "G"};

    int brightnessThreshold = 50;

    String imgPath;
    BufferedImage bimg;

    public ColourProcessor(String imgName){
        try {
            imgPath = "src/images/" + imgName;
            bimg = ImageIO.read(new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void test(){
        cropImg();
        String[][] top = findFaceColours();
        
        String[][] front = findFaceColours();
        String[][] left = findFaceColours();
        String[][] back = findFaceColours();
        String[][] right = findFaceColours();

        String[][] bottom = findFaceColours();

        String[][][] wholeCube = {top, bottom, front, back, left, right};

        

        System.out.println(Arrays.deepToString(front));
    }

    private void cropImg(){
        int width = bimg.getWidth();
        int height = bimg.getHeight();
        
        int top;
        int bottom;
        int left;
        int right;

        int count = 0;
        int tempPosition = 0;

        //top edge
        for (int y = 0; y < height; y +=5){
            //get rgb from img
            int rgb = bimg.getRGB(width/2, y);

            //seperate into seperate rgb values
            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = rgb & 0xFF;

            int brightness = (r + g + b)/3;

            if (brightness > brightnessThreshold){                
                if (count == 0){
                    tempPosition = y;
                }
                else if (count > 4){
                    break;
                }
                count ++;

            }
            else{
                count = 0;
            }
        }

        top = tempPosition;
        
        count = 0;
        tempPosition = 0;

        //bottom edge
        for (int y = height - 5; y > 0; y-=5){
            //get rgb from img
            int rgb = bimg.getRGB(width/2, y);

            //seperate into seperate rgb values
            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = rgb & 0xFF;

            int brightness = (r + g + b)/3;

            if (brightness > brightnessThreshold){                
                if (count == 0){
                    tempPosition = y;
                }
                else if (count > 4){
                    break;
                }
                count ++;

            }
            else{
                count = 0;
            }
        }

        bottom = tempPosition;
        
        count = 0;
        tempPosition = 0;

        //left edge
        for (int x = 0; x < width; x+=5){
            //get rgb from img
            int rgb = bimg.getRGB(x, height/2);

            //seperate into seperate rgb values
            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = rgb & 0xFF;

            int brightness = (r + g + b)/3;

            if (brightness > brightnessThreshold){                
                if (count == 0){
                    tempPosition = x;
                }
                else if (count > 4){
                    break;
                }
                count ++;

            }
            else{
                count = 0;
            }
        }

        left = tempPosition;
        
        count = 0;
        tempPosition = 0;

        //right
        for (int x = width - 5; x > 0; x-=5){
            //get rgb from img
            int rgb = bimg.getRGB(x, height/2);

            //seperate into seperate rgb values
            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = rgb & 0xFF;

            int brightness = (r + g + b)/3;

            if (brightness > brightnessThreshold){                
                if (count == 0){
                    tempPosition = x;
                }
                else if (count > 4){
                    break;
                }
                count ++;

            }
            else{
                count = 0;
            }
        }

        right = tempPosition;


        BufferedImage croppedImg = bimg.getSubimage(left, top, right-left, bottom-top);
        try {
            ImageIO.write(croppedImg, "jpg", new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        bimg = croppedImg;
    }

    private String[][] findFaceColours(){
        int rows = 4;
        int cols = 4;

        System.out.println("Width: " + bimg.getWidth() + " Height: " + bimg.getHeight());

        int cellHeight = bimg.getHeight() / cols; //y valus
        int cellWidth = bimg.getWidth() / rows; //x valus

        System.out.println("Cell width: " + cellWidth + " Cell height: " + cellHeight);

        Color[][] faceColours = new Color[rows][cols];
        String[][] faceLabels = new String[rows][cols];

        for (int row = 0; row < rows; row++){
            for (int col = 0; col < cols; col++){
                faceColours[row][col] = findCellColor(cellWidth * row, cellHeight * col, cellWidth, cellHeight);
            }
        }

        for (int i = 0; i < faceColours.length; i++){
            for (int j = 0; j < faceColours[i].length; j++){
                Color tempColour = faceColours[i][j];

                faceLabels[i][j] = findFaceLabel(tempColour);

            }
        }

        return faceLabels;

    }

    private Color findCellColor(int xPos, int yPos, int cellWidth, int cellHeight){
        int count = 0;
        long red = 0;
        long green = 0;
        long blue = 0;
        
        for (int x = xPos; x <= xPos + cellWidth; x += 20){
            for (int y = yPos; y <= yPos + cellHeight; y += 20){
                int rgb = bimg.getRGB(x, y);

                red += (rgb >> 16) & 0xFF;
                green += (rgb >> 8) & 0xFF;
                blue += rgb & 0xFF;

                count++;
            }
        }

        int avgR = (int) (red / count);
        int avgG = (int) (green / count);
        int avgB = (int) (blue / count);

        Color returnColor = new Color(avgR, avgG, avgB);

        return returnColor;
    }

    public String findFaceLabel(Color colour){
        
        int index = -1;
        int minValue = 1000;

        for (int i = 0; i < cubeColours.length; i++){
            int value = Math.abs(colour.getRed() - cubeColours[i].getRed()) + Math.abs(colour.getGreen() - cubeColours[i].getGreen()) + Math.abs(colour.getBlue() - cubeColours[i].getBlue());

            if (value < minValue) {
                minValue = value;
                index = i;
            }
        }

        return cubeLabels[index];
    }

}