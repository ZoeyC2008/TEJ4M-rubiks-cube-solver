import java.io.*;

public class Cube {
    static int[][][] permutation; //config short for configuration
    static final String ADB_PATH = "C:\\Users\\zoeyz\\Documents\\projects\\platform-tools-latest-windows\\platform-tools";

/*
 * Example permutation
 * Should be [6][4][4]
 * white is top, yellow is bottom, red is front, orange is back, green is left, and blue is right
 * {top}{bottom}{front}{back}{left}{right}
 * white is 1, yellow is 2, red is 3, orange is 4, green is 5, blue is 6; 0 is unknown/filler
 */

    public Cube(int[][][] p){
        permutation = p;
    }
    
    private static String runCommandAndGetOutput(String command) throws IOException, InterruptedException {
        Process p = new ProcessBuilder("cmd.exe", "/c", command)
                .redirectErrorStream(true)
                .start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        p.waitFor();
        return sb.toString();
    }


}
