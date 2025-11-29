import  java.util.*;

public class Cube {
    int size = 4;
    
    String[][][] permutation = new String[6][size][size]; //config short for configuration

    String solveStr = "";

    //Cube faces
    final int U = 0;
    final int D = 1;
    final int F = 2;
    final int B = 3;
    final int L = 4;
    final int R = 5;

    final String[] FACES = {"U", "D", "F", "B", "L", "R"};

    //corners
    final int[][] CORNER_FACES = {
        {U, F, L}, // 0
        {U, F, R}, // 1
        {U, B, R}, // 2
        {U, B, L}, // 3
        {D, F, L}, // 4
        {D, F, R}, // 5
        {D, B, R}, // 6
        {D, B, L}  // 7
    };

    final int[][] EDGE_FACES = {
        {U, F}, //0, 1
        {U, R}, //2, 3
        {U, B}, //4, 5
        {U, L}, //6, 7
        {F, L}, //8, 9
        {F, R}, //10, 11
        {B, R}, //12, 13
        {B, L}, //14, 15
        {D, F}, //16, 17
        {D, R}, //18, 19
        {D, B}, //20, 21
        {D, L} //22, 23
    };

    

/*
 * Example stickerPermutation
 * Should be [6][4][4]
 * white is up, yellow is down, red is front, orange is back, green is left, and blue is right
 * {top}{bottom}{front}{back}{left}{right}
 * white is 1, yellow is 2, red is 3, orange is 4, green is 5, blue is 6; 0 is unknown/filler
 */

    public Cube(String[][][] p){
        permutation = p;
    }


    public void solve(){
        solveWhiteCenter();
    }

    private void solveWhiteCenter(){
        Pos whiteCenter011 = new Pos(0, 1, 1);
        Pos whiteCenter012 = new Pos(0, 1, 2);
        Pos whiteCenter021 = new Pos(0, 2, 1);
        Pos whiteCenter022 = new Pos(0, 2, 2);

        ArrayList<Pos> foundCenters = new ArrayList<Pos>();
        Pos whiteCenterOne = findCenterPos("W", foundCenters);

        System.out.println("check one");

        System.out.println(whiteCenterOne.toString());

        if (whiteCenterOne.face == 0){
            while (!(getPosColour(whiteCenter011).equals("W"))){
                move_U();
            }
        }
    }

    private Pos findCenterPos(String colour, ArrayList<Pos> exclude){
        for (int i = 0; i < permutation.length; i++){
            for (int j = 1; j < permutation[i].length - 1; j++){
                for (int k = 1; k < permutation[i][j].length - 1; k++){
                    if (permutation[i][j][k].equals(colour)){
                        Pos temp = new Pos(i, j, k);

                        for (int a = 0; a < exclude.size(); a++){
                            if (exclude.get(a).equals(temp)){
                                break;
                            }
                        }

                        return temp;
                    }
                }
            }
        }

        return null;
    }

    private String getPosColour(Pos pos){
        return permutation[pos.face][pos.row][pos.col];
    }

    public void move_U(){
        rotateU();

        solveStr += "U, ";
    }

    public void move_Uw(){
        rotateUSlice();
        rotateU();

        solveStr += "Uw, ";
    }

    public void move_u(){
        rotateUSlice();

        solveStr += "u, ";
    }


    public void move_D(){
        flipZPrime();
        flipZPrime();
        
        rotateU();

        flipZPrime();
        flipZPrime();

        solveStr += "D, ";
    }

    public void move_Dw(){
        flipZPrime();
        flipZPrime();
        
        rotateUSlice();
        rotateU();

        flipZPrime();
        flipZPrime();
        
        solveStr += "Dw, ";
    }

    public void move_d(){
        flipZPrime();
        flipZPrime();
        
        rotateUSlice();

        flipZPrime();
        flipZPrime();

        solveStr += "d, ";
    }


    public void move_F(){
        flipZPrime();

        rotateU();

        for (int i = 0; i < 3; i++){
            flipZPrime();
        }

        solveStr += "F, ";
    }

    public void move_Fw(){
        flipZPrime();

        rotateUSlice();
        rotateU();

        for (int i = 0; i < 3; i++){
            flipZPrime();
        }

        solveStr += "Fw, ";
    }

    public void move_f(){
        flipZPrime();

        rotateUSlice();

        for (int i = 0; i < 3; i++){
            flipZPrime();
        }

        solveStr += "f, ";
    }


    public void move_B(){
        for (int i = 0; i < 3; i++){
            flipZPrime();
        }

        rotateU();

        flipZPrime();

        solveStr += "B, ";
    }

    public void move_Bw(){
        for (int i = 0; i < 3; i++){
            flipZPrime();
        }

        rotateUSlice();
        rotateU();

        flipZPrime();

        solveStr += "Bw, ";
    }

    public void move_b(){
        for (int i = 0; i < 3; i++){
            flipZPrime();
        }

        rotateUSlice();

        flipZPrime();

        solveStr += "b, ";
    }


    public void move_L(){
        flipX();

        rotateU();

        for (int i = 0; i < 3; i++){
            flipX();
        }

        solveStr += "L, ";
    }

    public void move_Lw(){
        flipX();

        rotateUSlice();
        rotateU();

        for (int i = 0; i < 3; i++){
            flipX();
        }

        solveStr += "Lw, ";
    }

    public void move_l(){
        flipX();

        rotateUSlice();

        for (int i = 0; i < 3; i++){
            flipX();
        }

        solveStr += "l, ";
    }

    
    public void move_R(){
        for (int i = 0; i < 3; i++){
            flipX();
        }

        rotateU();

        flipX();

        solveStr += "R, ";
    }

    public void move_Rw(){
        for (int i = 0; i < 3; i++){
            flipX();
        }

        rotateUSlice();
        rotateU();

        flipX();

        solveStr += "Rw, ";
    }

    public void move_r(){
        for (int i = 0; i < 3; i++){
            flipX();
        }

        rotateU();

        flipX();

        solveStr += "r, ";
    }


    public void move_x(){
        flipX();

        solveStr += "x, ";
    }

    public void move_z(){
        for (int i = 0; i < 3; i++){
            flipZPrime();
        }  

        solveStr += "z, ";
    }

    public void move_y(){
        flipZPrime();
        flipX();
        for (int i = 0; i < 3; i++){
            flipZPrime();
        }

        solveStr += "y, ";
    }



    private void rotateU(){
        int[][][] cycle = {      
            //top layer  
            {{0, 1, 1}, {0, 2, 1}, {0, 2, 2}, {0, 1, 2}},
            {{0, 0, 0}, {0, 3, 0}, {0, 3, 3}, {0, 0, 3}},
            {{0, 1, 0}, {0, 3, 1}, {0, 2, 3}, {0, 0, 2}},
            {{0, 2, 0}, {0, 3, 2}, {0, 1, 3}, {0, 0, 1}},
            //layer up
            {{4, 0, 0}, {2, 0, 0}, {5, 0, 0}, {3, 0, 0}},
            {{4, 0, 3}, {2, 0, 3}, {5, 0, 3}, {3, 0, 3}},
            {{4, 0, 1}, {2, 0, 1}, {5, 0, 1}, {3, 0, 1}},
            {{4, 0, 2}, {2, 0, 2}, {5, 0, 2}, {3, 0, 2}}
        };
        
        for (int i = 0; i < cycle.length; i++){
            cycle(cycle[i]);
        }
    }

    private void rotateUSlice(){
        int[][][] cycle = {
            //layer two
            {{4, 1, 0}, {2, 1, 0}, {5, 1, 0}, {3, 1, 0}},
            {{4, 1, 3}, {2, 1, 3}, {5, 1, 3}, {3, 1, 3}},
            {{4, 1, 1}, {2, 1, 1}, {5, 1, 1}, {3, 1, 1}},
            {{4, 1, 2}, {2, 1, 2}, {5, 1, 2}, {3, 1, 2}}
        };

        for (int i = 0; i < cycle.length; i++){
            cycle(cycle[i]);
        }
    }

    private void flipX(){
        int[][][] cycle = { 
            //front face       
            {{2, 1, 1}, {2, 2, 1}, {2, 2, 2}, {2, 1, 2}},
            {{2, 0, 0}, {2, 3, 0}, {2, 3, 3}, {2, 0, 3}},
            {{2, 1, 0}, {2, 3, 1}, {2, 2, 3}, {2, 0, 2}},
            {{2, 2, 0}, {2, 3, 2}, {2, 1, 3}, {2, 0, 1}},

            //back face
            {{3, 1, 1}, {3, 1, 2}, {3, 2, 2}, {3, 2, 1}},
            {{3, 0, 0}, {3, 0, 3}, {3, 3, 3}, {3, 3, 0}},
            {{3, 1, 0}, {3, 0, 2}, {3, 2, 3}, {3, 3, 1}},
            {{3, 2, 0}, {3, 0, 1}, {3, 1, 3}, {3, 3, 2}},
            
            //corners
            {{1, 0, 0}, {5, 3, 0}, {0, 3, 3}, {4, 0, 3}},
            {{1, 3, 0}, {5, 3, 3}, {0, 0, 3}, {4, 0, 0}},
            {{1, 3, 3}, {5, 0, 3}, {0, 0, 0}, {4, 3, 0}},
            {{1, 0, 3}, {5, 0, 0}, {0, 3, 0}, {4, 3, 3}},

            //edges
            {{1, 2, 0}, {5, 3, 2}, {0, 1, 3}, {4, 0, 1}},
            {{1, 1, 0}, {5, 3, 1}, {0, 2, 3}, {4, 0, 2}},
            {{1, 0, 1}, {5, 2, 0}, {0, 3, 2}, {4, 1, 3}},
            {{1, 0, 2}, {5, 1, 0}, {0, 3, 1}, {4, 2, 3}},
            {{1, 3, 1}, {5, 2, 3}, {0, 0, 2}, {4, 1, 0}},
            {{1, 3, 2}, {5, 1, 3}, {0, 0, 1}, {4, 2, 0}},
            {{1, 2, 3}, {5, 0, 2}, {0, 1, 0}, {4, 3, 1}},
            {{1, 1, 3}, {5, 0, 1}, {0, 2, 0}, {4, 3, 2}},

            //centers
            {{1, 2, 1}, {5, 2, 2}, {0, 1, 2}, {4, 1, 1}},
            {{1, 1, 1}, {5, 2, 1}, {0, 2, 2}, {4, 1, 2}},
            {{1, 2, 2}, {5, 1, 2}, {0, 1, 1}, {4, 2, 1}},
            {{1, 1, 2}, {5, 1, 1}, {0, 2, 1}, {4, 2, 2}}
        };

        for (int i = 0; i < cycle.length; i++){
            cycle(cycle[i]);
        }
    }

    private void flipZPrime(){
        int[][][] cycle = { 
            //right face       
            {{5, 1, 1}, {5, 2, 1}, {5, 2, 2}, {5, 1, 2}},
            {{5, 0, 0}, {5, 3, 0}, {5, 3, 3}, {5, 0, 3}},
            {{5, 1, 0}, {5, 3, 1}, {5, 2, 3}, {5, 0, 2}},
            {{5, 2, 0}, {5, 3, 2}, {5, 1, 3}, {5, 0, 1}},

            //left face
            {{4, 1, 1}, {4, 1, 2}, {4, 2, 2}, {4, 2, 1}},
            {{4, 0, 0}, {4, 0, 3}, {4, 3, 3}, {4, 3, 0}},
            {{4, 1, 0}, {4, 0, 2}, {4, 2, 3}, {4, 3, 1}},
            {{4, 2, 0}, {4, 0, 1}, {4, 1, 3}, {4, 3, 2}},

            //faces -corners
            {{2, 0, 0}, {1, 0, 0}, {3, 3, 3}, {0, 0, 0}}, 
            {{2, 0, 3}, {1, 0, 3}, {3, 3, 0}, {0, 0, 3}},
            {{2, 3, 0}, {1, 3, 0}, {3, 0, 3}, {0, 3, 0}},
            {{2, 3, 3}, {1, 3, 3}, {3, 0, 0}, {0, 3, 3}},

            //faces -edges
            {{2, 0, 1}, {1, 0, 1}, {3, 3, 2}, {0, 0, 1}},
            {{2, 0, 2}, {1, 0, 2}, {3, 3, 1}, {0, 0, 2}},
            {{2, 1, 0}, {1, 1, 0}, {3, 2, 3}, {0, 1, 0}},
            {{2, 2, 0}, {1, 2, 0}, {3, 1, 3}, {0, 2, 0}},
            {{2, 3, 1}, {1, 3, 1}, {3, 0, 2}, {0, 3, 1}},
            {{2, 3, 2}, {1, 3, 2}, {3, 0, 1}, {0, 3, 2}},
            {{2, 2, 3}, {1, 2, 3}, {3, 1, 0}, {0, 2, 3}},
            {{2, 1, 3}, {1, 1, 3}, {3, 2, 0}, {0, 1, 3}},

            //faces -centers
            {{2, 1, 1}, {1, 1, 1}, {3, 2, 2}, {0, 1, 1}},
            {{2, 1, 2}, {1, 1, 2}, {3, 2, 1}, {0, 1, 2}},
            {{2, 2, 1}, {1, 2, 1}, {3, 1, 2}, {0, 2, 1}},
            {{2, 2, 2}, {1, 2, 2}, {3, 1, 1}, {0, 2, 2}}
        };

        for (int i = 0; i < cycle.length; i++){
            cycle(cycle[i]);
        }
    }

    private void cycle(int[][] cycle){
        String temp = permutation[cycle[0][0]][cycle[0][1]][cycle[0][2]];
        
        for (int i = 0; i < cycle.length-1; i++){
            permutation[cycle[i][0]][cycle[i][1]][cycle[i][2]] = permutation[cycle[i+1][0]][cycle[i+1][1]][cycle[i+1][2]];
        }
        permutation[cycle[cycle.length-1][0]][cycle[cycle.length-1][1]][cycle[cycle.length-1][2]] = temp;
    }

    @Override
    public String toString(){
        String returnString = "";

        for (int i = 0; i < 6; i++){
            returnString += "\nFace " + FACES[i] + ": \n";
            for (int j = 0; j < size; j++){
                returnString += "    " + Arrays.deepToString(permutation[i][j]) + "\n";
            }
            
        }

        return returnString;
    }

}
