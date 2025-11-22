public class EdgeCubit extends Cubit{
    int orientation;
    String[][] permutation = new String[2][2];
    String[] colours = new String[2];

    public EdgeCubit (String[][] permutation_){
        permutation = permutation_;

        orientation = findOrientation();

        for (int i = 0; i < 2; i++){
            colours[i] = permutation_[0][i];
        }
    }

    public int findOrientation(){
        String faceWhiteYellow;
        String faceRedOrange;
        for (int i = 0; i < permutation[0].length; i++){
            if (permutation[0][i].equals("W") || permutation[0][i].equals("Y")){

                faceWhiteYellow = permutation[1][i];

                if (faceWhiteYellow.equals("U") || faceWhiteYellow.equals("D")){
                    return 0;
                }

                return 1;
            }
        }

        for (int i = 0; i < permutation[0].length; i++){
            if (permutation[0][i].equals("R") || permutation[0][i].equals("O")){
                faceRedOrange = permutation[1][i];

                if (faceRedOrange.equals("F") || faceRedOrange.equals("B")){
                    return 0;
                }
                return 1;
            }
        }

        throw new IllegalStateException("Edge cubit missing white/yellow or red/oragne!"); 
    }

    public void flip(boolean flip){
        if (flip){
            if (orientation == 0){
                orientation = 1;
            }
            else{
                orientation = 0;
            }
        }
    }

    @Override
    public String toString(){
        String str = "";
        for (int i = 0; i < 2; i++){
            str += " colour: " + this.permutation[0][i] + "  face: " + this.permutation[1][i] + " |";
        }
            str += "| " + this.orientation;
            
        return str;
    }


}

