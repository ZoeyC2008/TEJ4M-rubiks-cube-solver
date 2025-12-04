import java.io.*;
public class ArduinoUploader{
    String filePath;
    
    public ArduinoUploader(String filePath_){
        filePath = filePath_;
    }

    public void runIno(){
        try{
            ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Git\\bin\\bash.exe", "arduino-cli", "compile", "--fqbn", "arduino:avr:nano:cpu=atmega328old", filePath);
            //make a process builder

            Process p = pb.start();

            p.waitFor();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Git\\bin\\bash.exe", "arduino-cli", "upload", "-p", "COM4", "--fqbn", "arduino:avr:nano:cpu=atmega328old", filePath);

            Process p = pb.start();

            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }



        //arduino-cli upload -p COM4 --fqbn arduino:avr:nano:cpu=atmega328old "C:\Users\zixiu\Projects\TEJ4M-Rubiks-Cube-Solver\src\arduino\stepperMotor"

    }
}