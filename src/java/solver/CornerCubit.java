import java.util.Arrays;

public class CornerCubit extends Cubit{
    int orientation;
    String[][] permutation = new String[2][3]; //encodes everything but won't be updates
    String[] colours = new String[3];

    public CornerCubit (String[][] permutation_){
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 3; j++){
                permutation[i][j] = permutation_[i][j];
            }
        }

        orientation = findOrientation();

        for (int i = 0; i < 3; i++){
            colours[i] = permutation_[0][i];
        }
    }

    public int findOrientation(){
        System.out.println(Arrays.deepToString(permutation));

        for (int i = 0; i < 3; i++){
            if (permutation[0][i].equals("W") || permutation[0][i].equals("Y")){
                return i;   // index of UD sticker in cubie's sticker order
            }
            }
        throw new IllegalStateException("Corner has no white/yellow??");
    }

    public void orient(int orient){
        orientation = (orientation + orient) % 3;
    }

    @Override
    public String toString(){
        String str = "";
        for (int i = 0; i < 3; i++){
            str += " colour: " + this.permutation[0][i] + "  face: " + this.permutation[1][i] + " |";
        }
            str += "| " + this.orientation;
            
        return str;
    }

    
}
