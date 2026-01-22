package com.cube_solver._x4_solver.API;

import java.util.Map;
import com.cube_solver._x4_solver.cube.CubeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/cube")
@CrossOrigin(origins = "http://localhost:63342")
public class CubeController {

    private final CubeService cubeService;

    public CubeController(CubeService cs){
        this.cubeService = cs;
    }

    @GetMapping("/state")
    public String[][][] getState(){
        return cubeService.getState();
    }

    @PostMapping("/move")
    public void move(@RequestBody String m){
        cubeService.move(m.replace("\"",""));
    }

    @PostMapping("/solve")
    public Map<String, String> solve(@RequestBody String[][][] cubeState) {
        String solution = cubeService.solveFromState(cubeState);
        return Map.of("moves", solution);
    }

    @PostMapping("/applyMoves")
    public void applyMoves(@RequestBody String moves){
        cubeService.applyMoves(moves.replace("\"",""));
    }

    @PostMapping("/reset")
    public void resetCube() {
        cubeService.reset(); // Add this method to CubeService to set cube to solved state
    }

    @PostMapping("/scramble")
    public Map<String, String> scramble() {
        String s = cubeService.scramble();
        return Map.of("scramble", s);
    }
}