
public class Main{
    public static void main(String[] args) {
        try{
            captureFace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void captureFace(){
        try{
            bashCommand("adb shell am start -a android.media.action.STILL_IMAGE_CAMERA");

            bashCommand("adb shell input keyevent 27");
        }
        catch(Exception e){

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
}