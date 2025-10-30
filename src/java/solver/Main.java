

public class Main{
    public static void main(String[] args) {
        try{
            bashCommand("adb shell input keyevent 27");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void bashCommand(String command){
        try{
            ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Git\\bin\\bash.exe", "-lc", command);
            //make a process builder

            Process p = pb.start();

            p.waitFor();

            System.out.println("Git bash works??");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}