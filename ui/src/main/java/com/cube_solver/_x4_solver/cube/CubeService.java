package com.cube_solver._x4_solver.cube;

import org.springframework.stereotype.Service;

@Service
public class CubeService {

    private final Cube cube = new Cube();  // your existing class

    public String[][][] getState(){
        return cube.permutation;
    }

    public void move(String m){
        cube.move(m);
    }

    public void applyMoves(String moves){
        try{
            cube.applyMoves(moves);
        } catch (Exception e){

        }

    }

    public String solveFromState(String[][][] state){

        try{
            Cube cube = new Cube(state);
            cube.solve();
            return "Solve: " + cube.solveStr;
        } catch (Exception e){
            return "Please check if cube is inputted correctly!";
        }

    }

    public String solve(){
        try{
            Cube copy = new Cube(cube);
            copy.solve();
            return "Solve: " + copy.solveStr;
        } catch (Exception e){
            return "Please check if cube is inputted correctly!";
        }

    }

    public void reset(){
        cube.reset();
    }

    public String scramble(){
        cube.scramble();
        return cube.solveStr;
    }

}