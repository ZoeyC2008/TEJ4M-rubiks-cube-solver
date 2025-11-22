public class Move {
    Cube cube;

    //All of the rotation arrays
    //for top
    final int[] CORNER_TOP = {0, 1, 2, 3};
    final int[] EDGE_TOP_ONE = {0, 2, 4, 6};
    final int[] EDGE_TOP_TWO = {1, 2, 5, 7};
    final int[] CENTER_TOP = {0, 3, 2, 1};

    //for wide

    final int[] EDGE_WIDE = {8, 10, 12, 14};
    final int[] CENTER_WIDE_ONE = {8, 20, 12, 16};
    final int[] CENTER_WIDE_TWO = {9, 21, 13, 17};
    
    public Move(Cube cube_){
        cube = cube_;
    }


    private void basicRotateTop(){
        //permutate corners
        cycleCorner(CORNER_TOP, 0); // no orientation change

        //permutate corners
        cycleEdge(EDGE_TOP_ONE, false);
        cycleEdge(EDGE_TOP_TWO, false);
        

        //permutate centers
        cycleCenter(CENTER_TOP);
    }

    private void basicRotateTopWide(){
        //move top
        basicRotateTop();

        //permutate edges (yes flip)
        cycleEdge(EDGE_WIDE, true);

        //permutate center
        cycleCenter(CENTER_WIDE_ONE);
        cycleCenter(CENTER_WIDE_TWO);

    }

    private void basicFlipX(){
        //
    }

    public void basicFlipZ(){
        //
    }

    public void cycleCenter(int[] cycle){
        CenterCubit tempCenterOne = cube.centerCubits[cycle[0]];
        CenterCubit tempCenter;
        
        for (int i = 0; i < cycle.length - 1; i ++){
            tempCenter = cube.centerCubits[cycle[i+1]];
            cube.centerCubits[cycle[i]] = tempCenter;
        }

        cube.centerCubits[cycle[cycle.length-1]] = tempCenterOne;
    }

    public void cycleEdge(int[] cycle, boolean flip){
        EdgeCubit tempEdgeOne = cube.edgeCubits[cycle[0]];
        EdgeCubit tempEdge;
        
        for (int i = 0; i < cycle.length - 1; i ++){
            tempEdge = cube.edgeCubits[cycle[i+1]];
            cube.edgeCubits[cycle[i]] = tempEdge;
            cube.edgeCubits[cycle[i]].flip(flip);
        }

        cube.edgeCubits[cycle[cycle.length-1]] = tempEdgeOne;
        cube.edgeCubits[cycle[cycle.length-1]].flip(flip);
    }

    public void cycleCorner(int[] cycle, int orient){
        CornerCubit tempCornerOne = cube.cornerCubits[cycle[0]];
        CornerCubit tempCorner;
        
        for (int i = 0; i < cycle.length - 1; i ++){
            tempCorner = cube.cornerCubits[cycle[i+1]];
            cube.cornerCubits[cycle[i]] = tempCorner;
            cube.cornerCubits[cycle[i]].orient(orient);
        }

        cube.cornerCubits[cycle[cycle.length-1]] = tempCornerOne;
        cube.cornerCubits[cycle[cycle.length-1]].orient(orient);
    }
}
