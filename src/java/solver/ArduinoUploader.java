public class ArduinoUploader{
    String filePath;
    
    public ArduinoUploader(String filePath_){
        filePath = filePath_;
    }

    public void runIno(){
        try{
            //ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Git\\bin\\bash.exe", "-lc", "arduino-cli compile --fqbn arduino:avr:nano:cpu=atmega328old " + filePath);
            //make a process builder

            ProcessBuilder pb = new ProcessBuilder("C:\\Users\\zixiu\\Projects\\arduino-cli_1.3.1_Windows_64bit\\arduino-cli.exe", "compile", "--fqbn", "arduino:avr:nano:cpu=atmega328old", filePath);
            Process p = pb.start();

            p.waitFor();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Git\\bin\\bash.exe", "-lc", "arduino-cli upload -p COM4 --fqbn arduino:avr:nano:cpu=atmega328old "+ filePath);

            ProcessBuilder pb = new ProcessBuilder("C:\\Users\\zixiu\\Projects\\arduino-cli_1.3.1_Windows_64bit\\arduino-cli.exe", "upload", "-p", "COM4", "--fqbn", "arduino:avr:nano:cpu=atmega328old", filePath);

            Process p = pb.start();

            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        //arduino-cli compile --fqbn arduino:avr:nano:cpu=atmega328old "C:\Users\zixiu\Projects\TEJ4M-Rubiks-Cube-Solver\src\arduino\stepperMotor"

        //arduino-cli upload -p COM4 --fqbn arduino:avr:nano:cpu=atmega328old "C:\Users\zixiu\Projects\TEJ4M-Rubiks-Cube-Solver\src\arduino\stepperMotor" --verbose

    }
}