import java.io.*;

public class Main{
    
    static final String IMAGE_FILE_PATH = "";

    public static void main(String[] args) {
        
        String[][] up = {{"W", "O", "W", "R"}, {"W", "O", "W", "R"}, {"W", "O", "W", "R"}, {"W", "O", "W", "R"}};

        String[][] down = {{"Y", "R", "Y", "O"}, {"Y", "R", "Y", "O"}, {"Y", "R", "Y", "O"}, {"Y", "R", "Y", "O"}};

        String[][] front = {{"R", "W", "R", "Y"}, {"R", "W", "R", "Y"}, {"R", "W", "R", "Y"}, {"R", "W", "R", "Y"}};

        String[][] back = {{"W", "O", "Y", "O"}, {"W", "O", "Y", "O"}, {"W", "O", "Y", "O"}, {"W", "O", "Y", "O"}};

        String[][] left = {{"G", "G", "G", "G"}, {"G", "G", "G", "G"}, {"G", "G", "G", "G"}, {"G", "G", "G", "G"}};

        String[][] right = {{"B", "B", "B", "B"}, {"B", "B", "B", "B"}, {"B", "B", "B", "B"}, {"B", "B", "B", "B"}};

        String[][][] test = 
        {
            up, down, front, back, left, right
        };

        Cube testCube  = new Cube(test);

        //System.out.println(testCube.toString());

        
        //System.out.println(testCube.toString());
        testCube.solve();

        System.out.println(testCube.toString());

        System.out.println(testCube.solveStr);


    }

    public static void captureFace(){
        try{
            //open camera
            bashCommand("adb shell am start -a android.media.action.STILL_IMAGE_CAMERA");

            //take photo
            bashCommand("adb shell input keyevent 27");

            //identify the photo
            String imgName = bashCommandString("adb shell 'ls -t /sdcard/DCIM/Camera/ | head -n 1'");

            bashCommand("adb pull //sdcard/DCIM/Camera/" + imgName + " \"/C:/Users/zoeyz/Documents/projects/TEJ4M-Rubiks-Cube-Solver/src/images/\"");

            ColourProcessor cp = new ColourProcessor(imgName);
            cp.test();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void bashCommand(String command){
        try{
            ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Git\\bin\\bash.exe", "-lc", command);
            //make a process builder

            Process p = pb.start();

            p.waitFor();

        }
        catch (Exception e) {
            e.printStackTrace();
        }        
    }

    public static String bashCommandString(String command){
        String line = "";
        try{
            ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Git\\bin\\bash.exe", "-lc", command);

            Process p = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            line = br.readLine();

            p.waitFor();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return line;
    }
}