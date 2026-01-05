import java.io.*;

public class Main{
    
    static final String IMAGE_FILE_PATH = "";

    public static void main(String[] args) {
        /* 
        String[][] up = {{"W", "O", "R", "R"}, {"W", "O", "R", "R"}, {"W", "O", "R", "R"}, {"W", "O", "R", "R"}};

        String[][] down = {{"Y", "R", "O", "O"}, {"Y", "R", "O", "O"}, {"Y", "R", "O", "O"}, {"Y", "R", "O", "O"}};

        String[][] front = {{"R", "W", "Y", "Y"}, {"B", "B", "B", "B"}, {"R", "W", "Y", "Y"}, {"R", "W", "Y", "Y"}};

        String[][] back = {{"W", "W", "Y", "O"}, {"G", "G", "G", "G"}, {"W", "W", "Y", "O"}, {"W", "W", "Y", "O"}};

        String[][] left = {{"G", "G", "G", "G"}, {"R", "W", "Y", "Y"}, {"G", "G", "G", "G"}, {"G", "G", "G", "G"}};

        String[][] right = {{"B", "B", "B", "B"}, {"W", "W", "Y", "O"}, {"B", "B", "B", "B"}, {"B", "B", "B", "B"}};

        */

        String[][] up = {{"W", "W", "W", "W"}, {"W", "W", "W", "W"}, {"W", "W", "W", "W"}, {"W", "W", "W", "W"}};

        String[][] down = {{"Y", "Y", "Y", "Y"}, {"Y", "Y", "Y", "Y"}, {"Y", "Y", "Y", "Y"}, {"Y", "Y", "Y", "Y"}};

        String[][] front = {{"R", "R", "R", "R"}, {"R", "R", "R", "R"}, {"R", "R", "R", "R"}, {"R", "R", "R", "R"}};

        String[][] back = {{"O", "O", "O", "O"}, {"O", "O", "O", "O"}, {"O", "O", "O", "O"}, {"O", "O", "O", "O"}};

        String[][] left = {{"G", "G", "G", "G"}, {"G", "G", "G", "G"}, {"G", "G", "G", "G"}, {"G", "G", "G", "G"}};

        String[][] right = {{"B", "B", "B", "B"}, {"B", "B", "B", "B"}, {"B", "B", "B", "B"}, {"B", "B", "B", "B"}};

        String[][][] test = 
        {
            up, down, front, back, left, right
        };

        Cube testCube = new Cube(test);

        //testCube.move_uPrime();
        //System.out.println(testCube.toString());

         
        testCube.move_R();
        testCube.move_r();
        testCube.move_l();
        testCube.move_u();
        testCube.move_f();
        testCube.move_r();
        testCube.move_dPrime();
        testCube.move_X();
        testCube.move_B();
        testCube.move_u();
        
        System.out.println(testCube.toString());
        testCube.solve();

        System.out.println("\n\nPOST SOLVE:");
        System.out.println(testCube.toString());

        System.out.println(testCube.solveStr);

        
        //captureFace();

        ArduinoUploader au = new ArduinoUploader("C:\\Users\\zixiu\\Projects\\TEJ4M-Rubiks-Cube-Solver\\src\\arduino\\stepperMotor");

        //au.runIno();
    }

    public static void captureFace(){
        try{
            //open camera
            bashCommand("adb shell am start -a android.media.action.STILL_IMAGE_CAMERA");

            //take photo
            bashCommand("adb shell input keyevent 27");

            //wait a moment
            Thread.sleep(500);

            //identify the photo
            String imgName = bashCommandString("adb shell 'ls -t /sdcard/DCIM/Camera/ | head -n 1'");

            bashCommand("adb pull //sdcard/DCIM/Camera/" + imgName + " \"/C:/Users/zixiu/projects/TEJ4M-Rubiks-Cube-Solver/src/images/\"");

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