public class Pos {
    int face;
    int row;
    int col;

    public Pos(int face_, int row_, int col_){
        face = face_;
        row = row_;
        col = col_;
    }

    public boolean isSameIgnoreFace(Pos pos){
          
        if (this.row == pos.row && this.col == pos.col){
            return true;
        }

        return false;
    }

    

    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }

        if (obj == null || !(obj instanceof Pos)) {
            return false;
        }

        Pos test = (Pos) obj;

        if (this.face == test.face && this.row == test.row && this.col == test.col){
            return true;
        }

        return false;
    }

    @Override
    public String toString(){
        return "Face: " + face + ", Row: " + row + ", Col: " + col;
    }
}
