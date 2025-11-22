import  java.util.*;

public class Cube {
    int size = 4;
    
    String[][][] stickerPermutation = new String[6][size][size]; //config short for configuration

    public CornerCubit[] cornerCubits = new CornerCubit[8];
    public EdgeCubit[] edgeCubits = new EdgeCubit[24];
    public CenterCubit[] centerCubits = new CenterCubit[24];

    //Cube faces
    final int U = 0;
    final int D = 1;
    final int F = 2;
    final int B = 3;
    final int L = 4;
    final int R = 5;

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

    static Map<Integer, Map<String, Neighbour>> adjacentStickerMap = new HashMap<>();

    static {
        adjacentStickerMap.put(0, Map.of( //U
            "up", new Neighbour("up", "B", true),
            "down", new Neighbour("up", "F", false),
            "left", new Neighbour("up", "L", false),
            "right", new Neighbour("up", "R", true)
        ));

        adjacentStickerMap.put(1, Map.of( //D
            "up", new Neighbour("down", "F", false),
            "down", new Neighbour("down", "B", true),
            "left", new Neighbour("down", "L", true),
            "right", new Neighbour("down", "R", false)
        ));

        adjacentStickerMap.put(2, Map.of( //F
            "up", new Neighbour("down", "U", false),
            "down", new Neighbour("up", "D", false),
            "left", new Neighbour("right", "L", false),
            "right", new Neighbour("left", "R", false)
        ));

        adjacentStickerMap.put(3, Map.of( //B
            "up", new Neighbour("up", "U", true),
            "down", new Neighbour("down", "D", true),
            "left", new Neighbour("right", "R", false),
            "right", new Neighbour("left", "L", false)
        ));

        adjacentStickerMap.put(4, Map.of( //L
            "up", new Neighbour("left", "U", false),
            "down", new Neighbour("left", "D", true),
            "left", new Neighbour("right", "B", false),
            "right", new Neighbour("left", "F", false)
        ));

        adjacentStickerMap.put(5, Map.of( //R
            "up", new Neighbour("right", "U", true),
            "down", new Neighbour("right", "D", false),
            "left", new Neighbour("right", "F", false),
            "right", new Neighbour("left", "B", false)
        ));
    }
    //i think i may only need to include up, down, front, and back, but I added left and right just in case

    

/*
 * Example stickerPermutation
 * Should be [6][4][4]
 * white is up, yellow is down, red is front, orange is back, green is left, and blue is right
 * {top}{bottom}{front}{back}{left}{right}
 * white is 1, yellow is 2, red is 3, orange is 4, green is 5, blue is 6; 0 is unknown/filler
 */

    public Cube(String[][][] p){
        stickerPermutation = p;

        mapArrayToCubits();
    }

    private void mapArrayToCubits(){
        int count = 0;

        //corner pieces
        String[][] tempCorner = new String[2][3];
        CornerCubit tempCornerCubit;
        
        int start = 0;
        int end = size - 1;
        
        int[][][] cornerCoordinates = {   
            { {size-1, 0}, {0, 0}, {0, size-1} }, // UFL  
            { {size-1, size-1}, {0, size-1}, {0, 0} },// UFR
            { {0, size-1}, {0, 0}, {0, size-1} },//UBR
            { {0, 0}, {0, size-1}, {0, 0} },// UBL
            { {0, 0}, {size-1, 0}, {size-1, size-1} },// DFL
            { {0, size-1}, {size-1, size-1}, {size-1, 0} },// DFR
            { {size-1, size-1}, {size-1, 0}, {size-1, size-1} },// DBR
            { {size-1, 0}, {size-1, size-1}, {size-1, 0} }// DBL
        };
        
        tempCorner = new String[2][3]; // [color/face][3]
        for (int i = 0; i < 8; i++) {          
            for (int j = 0; j < 3; j++) {
                int face = CORNER_FACES[i][j];
                int row = cornerCoordinates[i][j][0];
                int col = cornerCoordinates[i][j][1];

                tempCorner[0][j] = stickerPermutation[face][row][col]; // color
                tempCorner[1][j] = faceToString(face);
            }
            System.out.println(count);
            tempCornerCubit = new CornerCubit(tempCorner);

            cornerCubits[count] = tempCornerCubit;

            count++;
        }

        //edge pieces
        count = 0;
        //i give up i'm going by hand; the missing piece is getting the edges into a neat order for eas-ish turns

        //0-7 (from the top face)
        edgeCubits[count] = makeEdgeCubit(0, 0+1, size-1);
        count++;

        edgeCubits[count] = makeEdgeCubit(0, 0+2, size-1);
        count++;

        edgeCubits[count] = makeEdgeCubit(0, size-1, 0+2);
        count++;

        edgeCubits[count] = makeEdgeCubit(0, size-1, 0+1);
        count++;

        edgeCubits[count] = makeEdgeCubit(0, 0+2, 0);
        count++;

        edgeCubits[count] = makeEdgeCubit(0, 0+1, 0);
        count++;

        edgeCubits[count] = makeEdgeCubit(0, 0, 0+1);
        count++;

        edgeCubits[count] = makeEdgeCubit(0, 0, 0+2);
        count++;

        //8-15 (center edges)
        edgeCubits[count] = makeEdgeCubit(2, 0, 0+1);
        count++;

        edgeCubits[count] = makeEdgeCubit(2, 0, 0+2);
        count++;

        edgeCubits[count] = makeEdgeCubit(2, size-1, 0+1);
        count++;

        edgeCubits[count] = makeEdgeCubit(2, size-1, 0+2);
        count++;

        edgeCubits[count] = makeEdgeCubit(3, 0, 0+1);
        count++;

        edgeCubits[count] = makeEdgeCubit(3, 0, 0+2);
        count++;

        edgeCubits[count] = makeEdgeCubit(3, size-1, 0+1);
        count++;

        edgeCubits[count] = makeEdgeCubit(3, size-1, 0+2);
        count++;

        //16-23 (down/yellow edges)
        edgeCubits[count] = makeEdgeCubit(1, 0+1, 0);
        count++;

        edgeCubits[count] = makeEdgeCubit(1, 0+2, 0);
        count++;

        edgeCubits[count] = makeEdgeCubit(1, size-1, 0+1);
        count++;

        edgeCubits[count] = makeEdgeCubit(1, size-1, 0+2);
        count++;

        edgeCubits[count] = makeEdgeCubit(1, 0+2, size-1);
        count++;

        edgeCubits[count] = makeEdgeCubit(1, 0+1, size-1);
        count++;

        edgeCubits[count] = makeEdgeCubit(1, 0, 0+2);
        count++;

        edgeCubits[count] = makeEdgeCubit(1, 0, 0+1);
        count++;

        //center pieces
        count = 0;
        for (int i = 0; i < stickerPermutation.length; i++){
            for (int j = 1; j < stickerPermutation[i].length - 1; j++){ //only intrested in center pieces
                for (int k = 1; k < stickerPermutation[i][j].length - 1; k++){
                    String temp = stickerPermutation[i][j][k];

                    centerCubits[count] = new CenterCubit(temp);
                    count ++;
                }
            }
        }
    }

    private EdgeCubit makeEdgeCubit(int face, int row, int col){
        String[][] temp = new String[2][2];

        temp[0][0] = stickerPermutation[face][row][col];
        temp[1][0] = faceToString(face);

        temp[0][1] = getAdjacentColour(face, row, col);
        temp[1][1] = getAdjacentFace(face, row, col);

        return new EdgeCubit(temp);
    }

    private String getAdjacentFace(int face, int row, int col){
        String sideEdge = "";
        
        if (row == size - 1){
            sideEdge = "right";
        }
        else if (row == 0){
            sideEdge = "left";
        }
        else if (col == size - 1){
            sideEdge = "down";
        }
        else if (col == 0){
            sideEdge = "up";
        }
        else{
            throw new IllegalStateException("Not an edge piece"); 
        }

        Neighbour neighbour = adjacentStickerMap.get(face).get(sideEdge);

        return neighbour.face;
    }
    private String getAdjacentColour(int face, int row, int col){
        String sideEdge = "";
        int index = -1;
        
        if (row == size - 1){
            sideEdge = "right";
            index = col;
        }
        else if (row == 0){
            sideEdge = "left";
            index = col;
        }
        else if (col == size - 1){
            sideEdge = "down";
            index = row;
        }
        else if (col == 0){
            sideEdge = "up";
            index = row;
        }
        else{
            throw new IllegalStateException("Not an edge piece"); 
        }

        Neighbour neighbour = adjacentStickerMap.get(face).get(sideEdge);

        int newFace = faceToInt(neighbour.face);
        int newRow;
        int newCol;

        if (neighbour.flip){
            index = size - index - 1;
        }

        if (neighbour.side.equals("up")){
            newCol = 0;
            newRow = index;
        }
        else if (neighbour.side.equals("down")){
            newCol = size - 1;
            newRow = index;
        }
        else if (neighbour.side.equals("left")){
            newRow = 0;
            newCol = index;
        }
        else if (neighbour.side.equals("right")){
            newRow = size - 1;
            newCol = index;
        }
        else{
            throw new IllegalStateException("Genuinly confufused how neighbour's sticker's side isn't up, down, left, or right."); 
        }

        return stickerPermutation[newFace][newRow][newCol];
        
    }

    private String faceToString(int face){
        return switch (face) {
            case 0 -> "U";
            case 1 -> "D";
            case 2 -> "F";
            case 3 -> "B";
            case 4 -> "R";
            case 5 -> "L";
            default -> "";
        };
    }

    private int faceToInt(String face){
        return switch (face) {
            case "U" -> 0;
            case "D" -> 1;
            case "F" -> 2;
            case "B" -> 3;
            case "R" -> 4;
            case "L" -> 5;
            default -> -1;
        };
    }


    public CornerCubit getCornerCubit(int index){
        return cornerCubits[index];
    }

    public EdgeCubit getEdgeCubit(int index){
        return edgeCubits[index];
    }

    public CenterCubit getCenterCubit(int index){
        return centerCubits[index];
    }

    private void defunctCode(){
        int count = 0;
        int[][][] cornerCoordinates = {   
            { {0, size-1}, {0, 0}, {size-1, 0} }, // UFL  
            { {size-1, size-1}, {0, size-1}, {0, 0} },// UFR
            { {size-1, 0}, {0, 0}, {size-1, 0} },//UBR
            { {0, 0}, {size-1, 0}, {0, 0} },// UBL
            { {0, 0}, {0, size-1}, {size-1, size-1} },// DFL
            { {size-1, 0}, {size-1, size-1}, {0, size-1} },// DFR
            { {size-1, size-1}, {0, size-1}, {size-1, size-1} },// DBR
            { {0, size-1}, {size-1, size-1}, {0, size-1} }// DBL
        };
        
        String[][] tempCorner = new String[2][3]; // [color/face][3]
        for (int i = 0; i < 8; i++) {          
            for (int j = 0; j < 3; j++) {
                int face = CORNER_FACES[i][j];
                int row = cornerCoordinates[i][j][0];
                int col = cornerCoordinates[i][j][1];

                tempCorner[0][j] = stickerPermutation[face][row][col]; // color
                tempCorner[1][j] = faceToString(face);
            }

        cornerCubits[count] = new CornerCubit(tempCorner);
        count++;
        }

        count = 0;

        //corner pieces
        //String[][] tempCorner = new String[2][3];
        CornerCubit tempCornerCubit;
        
        int start = 0;
        int end = size - 1;

        //corner zero
        tempCorner[0][0] = stickerPermutation[0][start][end];
        tempCorner[1][0] = "U";

        tempCorner[0][1] = stickerPermutation[2][start][start];
        tempCorner[1][1] = "F";

        tempCorner[0][2] = stickerPermutation[4][end][start];
        tempCorner[1][2] = "L";

        tempCornerCubit = new CornerCubit(tempCorner);

        cornerCubits[count] = tempCornerCubit;
        count++;

        //corner one
        tempCorner[0][0] = stickerPermutation[0][end][end];
        tempCorner[1][0] = "U";

        tempCorner[0][1] = stickerPermutation[2][start][end];
        tempCorner[1][1] = "F";

        tempCorner[0][2] = stickerPermutation[5][start][start];
        tempCorner[1][2] = "R";

        tempCornerCubit = new CornerCubit(tempCorner);

        cornerCubits[count] = tempCornerCubit;
        count++;

        //corner two
        tempCorner[0][0] = stickerPermutation[0][end][start];
        tempCorner[1][0] = "U";

        tempCorner[0][1] = stickerPermutation[3][end][end];
        tempCorner[1][1] = "B";

        tempCorner[0][2] = stickerPermutation[5][end][start];
        tempCorner[1][2] = "R";

        tempCornerCubit = new CornerCubit(tempCorner);

        cornerCubits[count] = tempCornerCubit;
        count++;

        //corner three
        tempCorner[0][0] = stickerPermutation[0][start][start];
        tempCorner[1][0] = "U";

        tempCorner[0][1] = stickerPermutation[3][start][end];
        tempCorner[1][1] = "B";

        tempCorner[0][2] = stickerPermutation[4][start][start];
        tempCorner[1][2] = "L";

        tempCornerCubit = new CornerCubit(tempCorner);

        cornerCubits[count] = tempCornerCubit;
        count++;

        //corner four
        tempCorner[0][0] = stickerPermutation[1][start][start];
        tempCorner[1][0] = "D";

        tempCorner[0][1] = stickerPermutation[2][start][end];
        tempCorner[1][1] = "F";

        tempCorner[0][2] = stickerPermutation[4][end][end];
        tempCorner[1][2] = "L";

        tempCornerCubit = new CornerCubit(tempCorner);

        cornerCubits[count] = tempCornerCubit;
        count++;

        //corner five
        tempCorner[0][0] = stickerPermutation[1][end][start];
        tempCorner[1][0] = "D";

        tempCorner[0][1] = stickerPermutation[2][end][end];
        tempCorner[1][1] = "F";

        tempCorner[0][2] = stickerPermutation[5][start][end];
        tempCorner[1][2] = "R";

        tempCornerCubit = new CornerCubit(tempCorner);

        cornerCubits[count] = tempCornerCubit;
        count++;

        //corner six
        tempCorner[0][0] = stickerPermutation[1][end][end];
        tempCorner[1][0] = "D";

        tempCorner[0][1] = stickerPermutation[3][end][start];
        tempCorner[1][1] = "B";

        tempCorner[0][2] = stickerPermutation[5][end][end];
        tempCorner[1][2] = "R";

        tempCornerCubit = new CornerCubit(tempCorner);

        cornerCubits[count] = tempCornerCubit;
        count++;

        //corner seven
        tempCorner[0][0] = stickerPermutation[1][start][end];
        tempCorner[1][0] = "D";

        tempCorner[0][1] = stickerPermutation[3][start][start];
        tempCorner[1][1] = "B";

        tempCorner[0][2] = stickerPermutation[4][start][end];
        tempCorner[1][2] = "L";

        tempCornerCubit = new CornerCubit(tempCorner);

        cornerCubits[count] = tempCornerCubit;
        count++;
    }


    public String toStringCubits(){
        String returnStr = "";

        returnStr += "Center cubits: \n";
        for (int i = 0; i < centerCubits.length; i++){
            returnStr += "    " + i + ": " + centerCubits[i].colour + "\n";
        }

        returnStr += "\n\nEdge cubits: \n";
        for (int i = 0; i < edgeCubits.length; i++){
            returnStr += "    " + i + ":" + edgeCubits[i].toString() + "\n";            
        }

        returnStr += "\n\nCorner cubits: \n";
        for (int i = 0; i < cornerCubits.length; i++){
            returnStr += "    " + i + ":" + cornerCubits[i].toString() + "\n";
            
        }

        return returnStr;
    }


}
