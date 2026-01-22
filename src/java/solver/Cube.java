import java.util.*;

public class Cube {
    int size = 4;

    String[][][] permutation = new String[6][size][size]; // config short for configuration

    String solveStr = "";

    // Cube faces
    final int U = 0;
    final int D = 1;
    final int F = 2;
    final int B = 3;
    final int L = 4;
    final int R = 5;

    final String[] FACES = { "U", "D", "F", "B", "L", "R" };

    // corners
    final int[][] CORNER_FACES = {
            { U, F, L }, // 0
            { U, F, R }, // 1
            { U, B, R }, // 2
            { U, B, L }, // 3
            { D, F, L }, // 4
            { D, F, R }, // 5
            { D, B, R }, // 6
            { D, B, L } // 7
    };

    final int[][] EDGE_FACES = {
            { U, F }, // 0, 1
            { U, R }, // 2, 3
            { U, B }, // 4, 5
            { U, L }, // 6, 7
            { F, L }, // 8, 9
            { F, R }, // 10, 11
            { B, R }, // 12, 13
            { B, L }, // 14, 15
            { D, F }, // 16, 17
            { D, R }, // 18, 19
            { D, B }, // 20, 21
            { D, L } // 22, 23
    };

    /*
     * Example stickerPermutation
     * Should be [6][4][4]
     * white is up, yellow is down, red is front, orange is back, green is left, and
     * blue is right
     * {top}{bottom}{front}{back}{left}{right}
     * white is 1, yellow is 2, red is 3, orange is 4, green is 5, blue is 6; 0 is
     * unknown/filler
     */

    // idk if i want to do this, but i'm initiating all the position, with colour
    // (w, y, r, o, g, b), type (center, edge, corner), and array position (e.g.
    // 011)

    // there are only 4x4x6 stickers, so only 96

    // centers
    Pos wCenter011 = new Pos(0, 1, 1);
    Pos wCenter012 = new Pos(0, 1, 2);
    Pos wCenter021 = new Pos(0, 2, 1);
    Pos wCenter022 = new Pos(0, 2, 2);

    Pos yCenter111 = new Pos(1, 1, 1);
    Pos yCenter112 = new Pos(1, 1, 2);
    Pos yCenter121 = new Pos(1, 2, 1);
    Pos yCenter122 = new Pos(1, 2, 2);

    Pos rCenter211 = new Pos(2, 1, 1);
    Pos rCenter212 = new Pos(2, 1, 2);
    Pos rCenter221 = new Pos(2, 2, 1);
    Pos rCenter222 = new Pos(2, 2, 2);

    Pos oCenter311 = new Pos(3, 1, 1);
    Pos oCenter312 = new Pos(3, 1, 2);
    Pos oCenter321 = new Pos(3, 2, 1);
    Pos oCenter322 = new Pos(3, 2, 2);

    Pos gCenter411 = new Pos(4, 1, 1);
    Pos gCenter412 = new Pos(4, 1, 2);
    Pos gCenter421 = new Pos(4, 2, 1);
    Pos gCenter422 = new Pos(4, 2, 2);

    Pos bCenter511 = new Pos(5, 1, 1);
    Pos bCenter512 = new Pos(5, 1, 2);
    Pos bCenter521 = new Pos(5, 2, 1);
    Pos bCenter522 = new Pos(5, 2, 2);

    // edges
    Pos wEdge001 = new Pos(0, 0, 1);
    Pos wEdge002 = new Pos(0, 0, 2);
    Pos wEdge010 = new Pos(0, 1, 0);
    Pos wEdge020 = new Pos(0, 2, 0);
    Pos wEdge013 = new Pos(0, 1, 3);
    Pos wEdge023 = new Pos(0, 2, 3);
    Pos wEdge031 = new Pos(0, 3, 1);
    Pos wEdge032 = new Pos(0, 3, 2);

    Pos yEdge101 = new Pos(1, 0, 1);
    Pos yEdge102 = new Pos(1, 0, 2);
    Pos yEdge110 = new Pos(1, 1, 0);
    Pos yEdge120 = new Pos(1, 2, 0);
    Pos yEdge113 = new Pos(1, 1, 3);
    Pos yEdge123 = new Pos(1, 2, 3);
    Pos yEdge131 = new Pos(1, 3, 1);
    Pos yEdge132 = new Pos(1, 3, 2);

    Pos rEdge201 = new Pos(2, 0, 1);
    Pos rEdge202 = new Pos(2, 0, 2);
    Pos rEdge210 = new Pos(2, 1, 0);
    Pos rEdge220 = new Pos(2, 2, 0);
    Pos rEdge213 = new Pos(2, 1, 3);
    Pos rEdge223 = new Pos(2, 2, 3);
    Pos rEdge231 = new Pos(2, 3, 1);
    Pos rEdge232 = new Pos(2, 3, 2);

    Pos oEdge301 = new Pos(3, 0, 1);
    Pos oEdge302 = new Pos(3, 0, 2);
    Pos oEdge310 = new Pos(3, 1, 0);
    Pos oEdge320 = new Pos(3, 2, 0);
    Pos oEdge313 = new Pos(3, 1, 3);
    Pos oEdge323 = new Pos(3, 2, 3);
    Pos oEdge331 = new Pos(3, 3, 1);
    Pos oEdge332 = new Pos(3, 3, 2);

    Pos gEdge401 = new Pos(4, 0, 1);
    Pos gEdge402 = new Pos(4, 0, 2);
    Pos gEdge410 = new Pos(4, 1, 0);
    Pos gEdge420 = new Pos(4, 2, 0);
    Pos gEdge413 = new Pos(4, 1, 3);
    Pos gEdge423 = new Pos(4, 2, 3);
    Pos gEdge431 = new Pos(4, 3, 1);
    Pos gEdge432 = new Pos(4, 3, 2);

    Pos bEdge501 = new Pos(5, 0, 1);
    Pos bEdge502 = new Pos(5, 0, 2);
    Pos bEdge510 = new Pos(5, 1, 0);
    Pos bEdge520 = new Pos(5, 2, 0);
    Pos bEdge513 = new Pos(5, 1, 3);
    Pos bEdge523 = new Pos(5, 2, 3);
    Pos bEdge531 = new Pos(5, 3, 1);
    Pos bEdge532 = new Pos(5, 3, 2);

    Pos wCorner000 = new Pos(0, 0, 0);
    Pos wCorner003 = new Pos(0, 0, 3);
    Pos wCorner030 = new Pos(0, 3, 0);
    Pos wCorner033 = new Pos(0, 3, 3);

    Pos yCorner100 = new Pos(1, 0, 0);
    Pos yCorner103 = new Pos(1, 0, 3);
    Pos yCorner130 = new Pos(1, 3, 0);
    Pos yCorner133 = new Pos(1, 3, 3);

    Pos rCorner200 = new Pos(2, 0, 0);
    Pos rCorner203 = new Pos(2, 0, 3);
    Pos rCorner230 = new Pos(2, 3, 0);
    Pos rCorner233 = new Pos(2, 3, 3);

    Pos oCorner300 = new Pos(3, 0, 0);
    Pos oCorner303 = new Pos(3, 0, 3);
    Pos oCorner330 = new Pos(3, 3, 0);
    Pos oCorner333 = new Pos(3, 3, 3);

    Pos gCorner400 = new Pos(4, 0, 0);
    Pos gCorner403 = new Pos(4, 0, 3);
    Pos gCorner430 = new Pos(4, 3, 0);
    Pos gCorner433 = new Pos(4, 3, 3);

    Pos bCorner500 = new Pos(5, 0, 0);
    Pos bCorner503 = new Pos(5, 0, 3);
    Pos bCorner530 = new Pos(5, 3, 0);
    Pos bCorner533 = new Pos(5, 3, 3);

    public Cube(String[][][] p) {
        permutation = p;
    }

    public Cube(){
        String[][] up = {{"W", "W", "W", "W"}, {"W", "W", "W", "W"}, {"W", "W", "W", "W"}, {"W", "W", "W", "W"}};

        String[][] down = {{"Y", "Y", "Y", "Y"}, {"Y", "Y", "Y", "Y"}, {"Y", "Y", "Y", "Y"}, {"Y", "Y", "Y", "Y"}};

        String[][] front = {{"R", "R", "R", "R"}, {"R", "R", "R", "R"}, {"R", "R", "R", "R"}, {"R", "R", "R", "R"}};

        String[][] back = {{"O", "O", "O", "O"}, {"O", "O", "O", "O"}, {"O", "O", "O", "O"}, {"O", "O", "O", "O"}};

        String[][] left = {{"G", "G", "G", "G"}, {"G", "G", "G", "G"}, {"G", "G", "G", "G"}, {"G", "G", "G", "G"}};

        String[][] right = {{"B", "B", "B", "B"}, {"B", "B", "B", "B"}, {"B", "B", "B", "B"}, {"B", "B", "B", "B"}};

        String[][][] temp = 
        {
            up, down, front, back, left, right
        };

        permutation = temp;
    }

    public void solve() {
        solveStr = ""; //reset any moves solve string might have picked up
        System.out.println("PRE-SOLVE:\n" +this.toString());

        // solving centers
        solveWhiteCenter();
        solveYellowCenter();
        solveRedCenter();
        solveOrangeCenter();
        solveGreenCenter();
        // blue center is automatically solved with green center

        // onto the edges (i still have no idea how parity happens!)
        solveEdge();

        // onto the actual cube (cfop (cross, f2l, oll, pll))
        // I have realized idk how to program intuitive f2l, so we swap to beginners
        // method... (what's the worst that could happen?)
        // at least there's like an algorithm I can use
        // maybe i should have used zb??
        System.out.println("POST REDUCTION, BEFORE CFOP: \n" + this.toString());

        cfop();

        System.out.println("POST-SOLVE:\n" +this.toString());

    }

    private void cfop() {
        cross();
        corners();
        layer2();
        oll(); // orientation of last layer
        pll(); //permutation of the last layer
    }

    private void cross() {
        while (!(getPosColour(yEdge101).equals("Y") && getPosColour(rEdge231).equals("R"))) {
            // System.out.println("1");
            solveOneCrossPiece("R", 2);
        }

        while (!(getPosColour(yEdge131).equals("Y") && getPosColour(oEdge331).equals("O"))) {

            solveOneCrossPiece("O", 3);
        }

        while (!(getPosColour(yEdge110).equals("Y") && getPosColour(gEdge431).equals("G"))) {
            solveOneCrossPiece("G", 4);
        }

        while (!(getPosColour(yEdge113).equals("Y") && getPosColour(bEdge531).equals("B"))) {
            solveOneCrossPiece("B", 5);
        }
    }

    private void corners() {
        // corner1 (yellow red blue)
        solveOneCornerPiece("R", "B", rCorner233, bCorner530, 2, 5);

        // yellow red green
        solveOneCornerPiece("R", "G", rCorner230, gCorner433, 2, 4);

        // yellow orange green
        solveOneCornerPiece("O", "G", oCorner333, gCorner430, 3, 4);

        // yellow orange blue
        solveOneCornerPiece("O", "B", oCorner330, bCorner533, 3, 5);
    }

    private void layer2() {
        while (!(getPosColour(rEdge213).equals("R") && getPosColour(bEdge510).equals("B"))) {
            oneLayerTwoPiece("R", "B");
        }

        while (!(getPosColour(rEdge210).equals("R") && getPosColour(gEdge413).equals("G"))) {
            oneLayerTwoPiece("R", "G");
        }

        while (!(getPosColour(oEdge310).equals("O") && getPosColour(bEdge513).equals("B"))) {
            oneLayerTwoPiece("O", "B");
        }
        while (!(getPosColour(oEdge313).equals("O") && getPosColour(gEdge410).equals("G"))) {
            oneLayerTwoPiece("O", "G");
        }

    }

    // this is just copy pasting algorithms (yay)
    private void oll() {
        // here are all the combinations for edges and corner
        ollEdges();
        ollCorners();
    }

    private void pll() {
        pllCorners();
        pllEdges();

        while(!(getPosColour(rCorner200).equals("R"))){
            move_U();
        }
    }

    private void pllEdges() {
        for (int i = 0; i < 4; i++) {
            if (getPosColour(rEdge201).equals(getPosColour(oCorner300)) &&
                    getPosColour(oEdge301).equals(getPosColour(rCorner200)) &&
                    getPosColour(gEdge401).equals(getPosColour(bCorner500)) &&
                    getPosColour(bEdge501).equals(getPosColour(gCorner400))) {
                alg_pllH();
                return;
            } else if (getPosColour(rEdge201).equals(getPosColour(bCorner500)) &&
                    getPosColour(oEdge301).equals(getPosColour(oCorner300)) &&
                    getPosColour(gEdge401).equals(getPosColour(rCorner200)) &&
                    getPosColour(bEdge501).equals(getPosColour(gCorner400))) {
                alg_pllUa();
                return;
            } else if (getPosColour(rEdge201).equals(getPosColour(gCorner400)) &&
                    getPosColour(oEdge301).equals(getPosColour(oCorner300)) &&
                    getPosColour(gEdge401).equals(getPosColour(bCorner500)) &&
                    getPosColour(bEdge501).equals(getPosColour(rCorner200))) {
                alg_pllUb();
                return;
            } else if (getPosColour(rEdge201).equals(getPosColour(gCorner400)) &&
                    getPosColour(oEdge301).equals(getPosColour(bCorner500)) &&
                    getPosColour(gEdge401).equals(getPosColour(rCorner200)) &&
                    getPosColour(bEdge501).equals(getPosColour(oCorner300))) {
                alg_pllZ();
                return;
            } else if (getPosColour(rEdge201).equals(getPosColour(oCorner300)) &&
                    getPosColour(oEdge301).equals(getPosColour(rCorner200)) &&
                    getPosColour(gEdge401).equals(getPosColour(gCorner400)) &&
                    getPosColour(bEdge501).equals(getPosColour(bCorner500))) {
                alg_pllParity();
                return;
            }

            move_U();
        }

    }

    private void pllCorners() {
        for (int i = 0; i < 4; i++) {
            if (getPosColour(oCorner303).equals(getPosColour(rCorner200)) &&
                    getPosColour(bCorner503).equals(getPosColour(gCorner400)) &&
                    getPosColour(gCorner403).equals(getPosColour(bCorner500)) &&
                    getPosColour(rCorner203).equals(getPosColour(oCorner300))) {
                System.out.println("pll diagonal");
                alg_pllDiagonal();
                return;
            } else if (getPosColour(gCorner400).equals(getPosColour(gCorner403)) &&
                    getPosColour(oCorner303).equals(getPosColour(bCorner500)) &&
                    getPosColour(oCorner300).equals(getPosColour(rCorner203)) &&
                    getPosColour(bCorner503).equals(getPosColour(rCorner200))) {
                System.out.println("pll headlights");
                alg_pllHeadlights();
                return;
            }

            move_U();
        }
    }

    private void alg_pllZ() {
        move_lPrime();
        move_r();
        move_U();
        move_l();
        move_r();
        move_l();
        move_r();
        move_U();
        move_l();
        move_r();
        move_l();
        move_r();
        move_U();
        move_lPrime();
        move_r();
        move_U();
        move_U();
        move_l();
        move_r();
        move_l();
        move_r();
    }

    private void alg_pllUb() {
        move_R();
        move_R();
        move_U();
        move_R();
        move_U();
        move_RPrime();
        move_UPrime();
        move_RPrime();
        move_UPrime();
        move_RPrime();
        move_U();
        move_RPrime();
    }

    private void alg_pllUa() {
        move_R();
        move_UPrime();
        move_R();
        move_U();
        move_R();
        move_U();
        move_R();
        move_UPrime();
        move_RPrime();
        move_UPrime();
        move_R();
        move_R();
    }

    private void alg_pllH() {
        move_l();
        move_l();
        move_r();
        move_r();
        move_U();
        move_l();
        move_l();
        move_r();
        move_r();
        move_U();
        move_U();
        move_l();
        move_l();
        move_r();
        move_r();
        move_U();
        move_l();
        move_l();
        move_r();
        move_r();
    }

    private void alg_pllHeadlights() {
        move_R();
        move_U();
        move_RPrime();
        move_UPrime();
        move_RPrime();
        move_F();
        move_R();
        move_R();
        move_UPrime();
        move_RPrime();
        move_UPrime();
        move_R();
        move_U();
        move_RPrime();
        move_FPrime();
    }

    private void alg_pllDiagonal() {
        move_F();
        move_R();
        move_UPrime();
        move_RPrime();
        move_UPrime();
        move_R();
        move_U();
        move_RPrime();
        move_FPrime();
        move_R();
        move_U();
        move_RPrime();
        move_UPrime();
        move_RPrime();
        move_F();
        move_R();
        move_FPrime();
    }

    private void alg_pllParity() {
        move_r();
        move_r();
        move_U();
        move_U();
        move_r();
        move_r();
        move_Uw();
        move_Uw();
        move_r();
        move_r();
        move_u();
        move_u();
    }

    private void ollCorners() {
        // "W" for white, "" for other
        // going from wEdge001, wEdge010, wEdge031, wEdge023
        String[] antisune = { "", "W", "", "", "W", "", "", "", "W", "", "W", "" };
        String[] hCorner =  { "", "", "", "", "W", "", "", "W", "W", "", "", "W" };
        String[] lCorner =  { "W", "", "W", "", "", "", "", "W", "", "", "W", "" };
        String[] pi =       { "", "", "", "", "W", "", "W", "", "", "W", "", "W" };
        String[] sune =     { "", "", "", "W", "", "W", "", "W", "", "W", "", "" };
        String[] tCorner =  { "", "W", "W", "", "", "W", "", "", "", "", "W", "" };
        String[] uCorner =  { "W", "W", "", "", "", "", "", "", "", "W", "W", "" };

        for (int i = 0; i < 4; i++) {
            String[] cubeCorners = { getIfWhite(wCorner000), getIfWhite(wCorner003), getIfWhite(wCorner033),
                    getIfWhite(wCorner030), getIfWhite(gCorner400), getIfWhite(oCorner303), getIfWhite(oCorner300),
                    getIfWhite(bCorner503), getIfWhite(bCorner500), getIfWhite(rCorner203), getIfWhite(rCorner200),
                    getIfWhite(gCorner403) };

            System.out.println(Arrays.toString(cubeCorners));

            if (Arrays.equals(cubeCorners, antisune)) {
                alg_ollAntisune();
                return;
            } else if (Arrays.equals(cubeCorners, hCorner)) {
                alg_ollHCorner();
                return;
            } else if (Arrays.equals(cubeCorners, lCorner)) {
                alg_ollLCorner();
                return;
            } else if (Arrays.equals(cubeCorners, pi)) {
                alg_ollPi();
                return;
            } else if (Arrays.equals(cubeCorners, sune)) {
                alg_ollSune();
                return;
            } else if (Arrays.equals(cubeCorners, tCorner)) {
                alg_ollTCorner();
                return;
            } else if (Arrays.equals(cubeCorners, uCorner)) {
                alg_ollUCorner();
                return;
            }

            move_U();
        }
    }

    private void alg_ollAntisune() {
        move_R();
        move_U();
        move_U();
        move_RPrime();
        move_UPrime();
        move_R();
        move_UPrime();
        move_RPrime();
    }

    private void alg_ollHCorner() {
        move_R();
        move_U();
        move_RPrime();
        move_U();
        move_R();
        move_UPrime();
        move_RPrime();
        move_U();
        move_R();
        move_U();
        move_U();
        move_RPrime();
    }

    private void alg_ollLCorner() {
        move_F();
        move_RPrime();
        move_FPrime();
        move_L();
        move_F();
        move_R();
        move_FPrime();
        move_LPrime();
    }

    private void alg_ollPi() {
        move_R();
        move_U();
        move_U();
        move_R();
        move_R();
        move_UPrime();
        move_R();
        move_R();
        move_UPrime();
        move_R();
        move_R();
        move_U();
        move_U();
        move_R();
    }

    private void alg_ollSune() {
        move_R();
        move_U();
        move_RPrime();
        move_U();
        move_R();
        move_U();
        move_U();
        move_RPrime();
    }

    private void alg_ollTCorner() {
        move_L();
        move_F();
        move_RPrime();
        move_FPrime();
        move_LPrime();
        move_F();
        move_R();
        move_FPrime();
    }

    private void alg_ollUCorner() {
        move_R();
        move_R();
        move_D();
        move_RPrime();
        move_U();
        move_U();
        move_R();
        move_DPrime();
        move_RPrime();
        move_U();
        move_U();
        move_rPrime();
    }

    private void ollEdges() {
        // "W" for white, "" for other
        // going from wEdge001, wEdge010, wEdge031, wEdge023
        String[] zeroEdge = { "", "", "", "" }; // 1
        String[] oneEdge = { "W", "", "", "" }; // 2
        String[] twoEdgeI = { "", "W", "", "W" }; // 3
        String[] twoEdgeL = { "", "", "W", "W" }; // 4
        String[] threeEdge = { "W", "W", "", "W" }; // 5

        for (int i = 0; i < 4; i++) {
            String[] cubeEdges = { getIfWhite(wEdge001), getIfWhite(wEdge010), getIfWhite(wEdge031),
                    getIfWhite(wEdge013) };

            // System.out.println(Arrays.toString(cubeEdges));

            if (Arrays.equals(cubeEdges, zeroEdge)) {
                alg_ollDot();
                return;

            } else if (Arrays.equals(cubeEdges, oneEdge)) {
                alg_ollParity();
                move_U();
                alg_ollIShape();
                return;
            } else if (Arrays.equals(cubeEdges, twoEdgeI)) {
                alg_ollIShape();
                return;

            } else if (Arrays.equals(cubeEdges, twoEdgeL)) {
                alg_ollLShape();
                return;
            } else if (Arrays.equals(cubeEdges, threeEdge)) {
                alg_ollParity();
            }

            move_U();
        }
    }

    private void alg_ollDot() {
        alg_ollIShape();
        alg_ollLShape();
    }

    private void alg_ollIShape() {
        move_F();
        move_R();
        move_U();
        move_RPrime();
        move_UPrime();
        move_FPrime();
    }

    private void alg_ollLShape() {
        move_B();
        move_U();
        move_L();
        move_UPrime();
        move_LPrime();
        move_BPrime();
    }

    private void alg_ollParity() {
        move_Rw();
        move_U();
        move_U();
        move_X();
        move_Rw();
        move_U();
        move_U();
        move_Rw();
        move_U();
        move_U();
        move_RwPrime();
        move_U();
        move_U();
        move_Lw();
        move_U();
        move_U();
        move_RwPrime();
        move_U();
        move_U();
        move_Rw();
        move_U();
        move_U();
        move_RwPrime();
        move_U();
        move_U();
        move_RwPrime();
    }

    private String getIfWhite(Pos pos) {
        String temp = getPosColour(pos);
        if (!(temp.equals("W"))) {
            temp = "";
        }
        return temp;
    }

    private void oneLayerTwoPiece(String colour1, String colour2) {
        depth++;
        if (depth > 30) {
            // System.out.println("e stop used");
            return;
        }
        Pos tempEdge = getEdgePiece(colour1, colour2);

        int face1 = tempEdge.face;
        int face2 = getWing(tempEdge).face;

        // System.out.println("Edge is: " + getPosColour(tempEdge) + " at " +
        // tempEdge.toString());
        // System.out.println("Edge is: " + getPosColour(getWing(tempEdge)) + " at " +
        // getWing(tempEdge).toString() + "\n");

        int minFace = Math.min(face1, face2);
        int maxFace = Math.max(face1, face2);

        if (minFace == 0) {
            switch (maxFace) {
                case 2:

                    break;
                case 3:
                    move_U();
                    move_U();
                    break;
                case 4:
                    move_UPrime();
                    break;
                case 5:
                    move_U();
                    break;
                default:
                    throw new AssertionError();
            }

            String matchColour = getPosColour(rEdge201);

            // System.out.println(matchColour);

            int centerNeeded = colourToFace(matchColour);

            boolean isMoved = false;
            align_middleLayer(centerNeeded, isMoved);

            if (getPosColour(wEdge031).equals(getPosColour(bCenter511))) {
                alg_secondLayerRight();
            } else if (getPosColour(wEdge031).equals(getPosColour(gCenter411))) {
                alg_secondLayerLeft();
            }

            isMoved = true;
            align_middleLayer(centerNeeded, isMoved);

            return;
        }

        if (minFace == 2) {
            if (maxFace == 5) {
                alg_secondLayerRight();
            } else if (maxFace == 4) {
                alg_secondLayerLeft();
            }
        } else if (minFace == 3) {
            boolean isMoved = false;
            align_middleLayer(3, isMoved);

            // stuff gets reversed after turning it
            if (maxFace == 4) {
                alg_secondLayerRight();
            } else if (maxFace == 5) {
                alg_secondLayerLeft();
            }

            isMoved = true;
            align_middleLayer(3, isMoved);
        }

    }

    private int colourToFace(String colour) {
        int returnInt = -1;

        switch (colour) {
            case "W":
                returnInt = 0;
                break;
            case "Y":
                returnInt = 1;
                break;
            case "R":
                returnInt = 2;
                break;
            case "O":
                returnInt = 3;
                break;
            case "G":
                returnInt = 4;
                break;
            case "B":
                returnInt = 5;
                break;
            default:
                throw new AssertionError();
        }

        return returnInt;
    }

    private void align_middleLayer(int side, boolean isMoved) {
        if (!isMoved) {
            switch (side) {
                case 2:

                    break;
                case 3:
                    move_u();
                    move_u();
                    move_d();
                    move_d();
                    move_D();
                    move_D();
                    break;
                case 4:
                    move_uPrime();
                    move_d();
                    move_D();
                    break;
                case 5:
                    move_u();
                    move_dPrime();
                    move_DPrime();
                    break;
                default:
                    throw new AssertionError();
            }
        } else {
            switch (side) {
                case 2:

                    break;
                case 3:
                    move_u();
                    move_u();
                    move_d();
                    move_d();
                    move_D();
                    move_D();
                    break;
                case 4:
                    move_u();
                    move_dPrime();
                    move_DPrime();
                    break;
                case 5:
                    move_uPrime();
                    move_d();
                    move_D();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private void alg_secondLayerRight() {
        move_U();
        move_R();
        move_UPrime();
        move_RPrime();
        move_UPrime();
        move_FPrime();
        move_U();
        move_F();
    }

    private void alg_secondLayerLeft() {
        move_UPrime();
        move_LPrime();
        move_U();
        move_L();
        move_U();
        move_F();
        move_UPrime();
        move_FPrime();
    }

    private void solveOneCornerPiece(String colour1, String colour2, Pos pos1, Pos pos2, int side1, int side2) {
        if (getPosColour(pos1).equals(colour1) && getPosColour(pos2).equals(colour2)) {
            return;
        }

        moveOneCornerPiece(colour1, colour2, side1, side2);

        boolean isMoved = false;
        // System.out.println("The sides: " + side1 + " " + side2);
        alignFirstLayerCorner(side1, side2, isMoved);

        // System.out.println("At the corners: " + getPosColour(rEdge232) + " " +
        // getPosColour(bEdge531));

        Pos tempPos = getCornerPos("Y", colour1, colour2);
        Pos[] tempWings = getCornerWings(tempPos);

        int face;

        if (getPosColour(tempPos).equals("Y")) {
            face = tempPos.face;
        } else if (getPosColour(tempWings[0]).equals("Y")) {
            face = tempWings[0].face;
        } else {
            face = tempWings[1].face;
        }

        // System.out.println("Here's the top(i think): " + getPosColour(tempPos) + "
        // and it's position: " + tempPos.toString());
        // System.out.println("Here's the top(i think): " + getPosColour(tempWings[0]) +
        // " and it's position: " + tempWings[0].toString());
        // System.out.println("Here's the top(i think): " + getPosColour(tempWings[1]) +
        // " and it's position: " + tempWings[1].toString());

        // System.out.println("Which side yellow is on: " + face);

        if (face == 0) {
            for (int i = 0; i < 3; i++) {
                alg_firstLayerCorner();
            }
        } else if (face == 2) {
            for (int i = 0; i < 5; i++) {
                alg_firstLayerCorner();
            }
        } else if (face == 5) {
            for (int i = 0; i < 1; i++) {
                alg_firstLayerCorner();
            }
        }

        isMoved = true;
        alignFirstLayerCorner(side1, side2, isMoved);

        // System.out.println("After moving back, the corners: " +
        // getPosColour(rEdge232) + " " + getPosColour(bEdge531));
    }

    // 3x3 helper methods (it's turning into a very strange combo of cfop and
    // beginner's method)
    int depth = 0;

    private void moveOneCornerPiece(String colour1, String colour2, int side1, int side2) {
        String primary = "Y";
        String wing1 = colour1;
        String wing2 = colour2;

        Pos cornerPos = getCornerPos(primary, wing1, wing2);

        Pos[] tempWings = getCornerWings(cornerPos);

        int face1 = cornerPos.face;
        int face2 = tempWings[0].face;
        int face3 = tempWings[1].face;

        // System.out.println("Corner found here: " + cornerPos.toString());

        // reoder in ascending order
        // bad bubble sort, basically
        int temp;
        if (face1 > face2) {
            temp = face1;
            face1 = face2;
            face2 = temp;
        }
        if (face2 > face3) {
            temp = face2;
            face2 = face3;
            face3 = temp;
        }
        if (face1 > face2) {
            temp = face1;
            face1 = face2;
            face2 = temp;
        }

        if (face1 == 0 && face2 == 2 && face3 == 5) {
            return;
        }

        // System.out.println("The faces: " + face1 + ", " + face2 + ", " + face3);

        // check if corner is in top layer or bottom layer
        if (face1 == 1) {
            boolean isMoved = false;
            alignFirstLayerCorner(face2, face3, isMoved);

            alg_firstLayerCorner();

            isMoved = true;
            alignFirstLayerCorner(face2, face3, isMoved);
        }

        if (face1 == 0) {
            if (face2 == 2 && face3 == 5) {

            } else if (face2 == 2 && face3 == 4) {
                move_UPrime();
            } else if (face2 == 3 && face3 == 4) {
                move_U();
                move_U();
            } else if (face2 == 3 && face3 == 5) {
                move_U();
            }
        }

        moveOneCornerPiece(colour1, colour2, side1, side2);
    }

    private void solveOneCrossPiece(String colour, int side) {
        String primary = "Y"; // we solve the yellow cross, so this is always yellow
        String wing = colour;

        Pos crossPiece = getEdgePiece(primary, wing);
        // System.out.println("Looking for: " + primary + " " + wing);
        // System.out.println("where the cross piece is: " + crossPiece.toString());

        int face1 = crossPiece.face;
        int face2 = getWing(crossPiece).face;

        if (face1 == 0) {
            if (face2 == 2) {
                boolean isMoved = false;
                alignCross(side, isMoved);

                move_F();
                move_F();

                isMoved = true;
                alignCross(side, isMoved);

                return;
            }
            move_U();
            return;
        }

        if (face2 == 0) {
            if (face1 == 2) {
                boolean isMoved = false;
                alignCross(side, isMoved);

                move_U();
                move_L();
                move_FPrime();
                move_LPrime();

                isMoved = true;
                alignCross(side, isMoved);

                return;

            }

            move_U();
            return;
        }

        // now that order doesn't matter (we're just trying to get the edge to the top
        // layer):
        int min = Math.min(face1, face2);
        int max = Math.max(face1, face2);

        if (min == 1) {
            // figure out which side it's on
            int notYellow = max;

            boolean isMoved = false;
            alignCross(notYellow, isMoved);

            move_F();
            move_F();

            isMoved = true;
            alignCross(notYellow, isMoved);

            return;
        }

        if (min == 2 && max == 4) {
            move_F();
            move_U();
            move_FPrime();
        } else if (min == 2 && max == 5) {
            move_FPrime();
            move_U();
            move_F();
        } else if (min == 3 && max == 4) {
            move_BPrime();
            move_U();
            move_B();
        } else if (min == 3 && max == 5) {
            move_B();
            move_U();
            move_BPrime();
        }
    }

    private void alignFirstLayerCorner(int side1, int side2, boolean isMoved) {
        if (!isMoved) {
            if (side1 == 2 && side2 == 5) {

            } else if (side1 == 2 && side2 == 4) {
                move_D();
            } else if (side1 == 3 && side2 == 4) {
                move_D();
                move_D();
            } else if (side1 == 3 && side2 == 5) {
                move_DPrime();
            }
        } else {
            if (side1 == 2 && side2 == 5) {

            } else if (side1 == 2 && side2 == 4) {
                move_DPrime();
            } else if (side1 == 3 && side2 == 4) {
                move_D();
                move_D();
            } else if (side1 == 3 && side2 == 5) {
                move_D();
            }
        }
    }

    // for the right corner
    private void alg_firstLayerCorner() {
        move_R();
        move_U();
        move_RPrime();
        move_UPrime();
    }

    private Pos[] getCornerWings(Pos corner) {
        if (!(isCorner(corner))) {
            throw new RuntimeException("why is a not corner trying to find its two adjacent colour?");
        }

        Pos[][] cornerLookUp = {
                // wog, ubl
                { wCorner000, oCorner303, gCorner400 },
                // wob, ubr
                { wCorner003, oCorner300, bCorner503 },
                // wrg, ufl
                { wCorner030, rCorner200, gCorner403 },
                // wrb, ufg
                { wCorner033, rCorner203, bCorner500 },
                // yrg, dfl
                { yCorner100, rCorner230, gCorner433 },
                // yrb, dfr
                { yCorner103, rCorner233, bCorner530 },
                // yog, dbl
                { yCorner130, oCorner333, gCorner430 },
                // yob, dbr
                { yCorner133, oCorner330, bCorner533 }
        };

        for (int i = 0; i < cornerLookUp.length; i++) {
            for (int j = 0; j < 3; j++) { // by definiton, edges cover 2 positions/stickers
                if (cornerLookUp[i][j].equals(corner)) {
                    if (j == 0) {
                        Pos[] returnPos = { cornerLookUp[i][1], cornerLookUp[i][2] };
                        return returnPos;
                    } else if (j == 1) {
                        Pos[] returnPos = { cornerLookUp[i][0], cornerLookUp[i][2] };
                        return returnPos;
                    } else {
                        Pos[] returnPos = { cornerLookUp[i][0], cornerLookUp[i][1] };
                        return returnPos;
                    }
                }
            }
        }

        return null;
    }

    private Pos getCornerPos(String c1, String c2, String c3) {
        Pos[][] cornerLookUp = {
                // wog, ubl
                { wCorner000, oCorner303, gCorner400 },
                // wob, ubr
                { wCorner003, oCorner300, bCorner503 },
                // wrg, ufl
                { wCorner030, rCorner200, gCorner403 },
                // wrb, ufg
                { wCorner033, rCorner203, bCorner500 },
                // yrg, dfl
                { yCorner100, rCorner230, gCorner433 },
                // yrb, dfr
                { yCorner103, rCorner233, bCorner530 },
                // yog, dbl
                { yCorner130, oCorner333, gCorner430 },
                // yob, dbr
                { yCorner133, oCorner330, bCorner533 }
        };

        for (int i = 0; i < cornerLookUp.length; i++) {
            for (int j = 0; j < 4; j++) { // by definiton, edges cover 2 positions/stickers
                for (int k = 0; k < 4; k++) {
                    Pos temp = new Pos(i, j, k);

                    if (isCorner(temp)) {
                        Pos[] tempWings = getCornerWings(temp);

                        String testColour1 = getPosColour(temp);
                        String testColour2 = getPosColour(tempWings[0]);
                        String testColour3 = getPosColour(tempWings[1]);

                        String[] ogColours = { c1, c2, c3 };
                        String[] ogColoursSort = ogColours.clone();
                        Arrays.sort(ogColoursSort);

                        String[] testColours = { testColour1, testColour2, testColour3 };
                        String[] testColoursSort = testColours.clone();
                        Arrays.sort(testColoursSort);

                        if (Arrays.equals(ogColoursSort, testColoursSort)) {
                            return temp;
                        }
                    }
                }
            }
        }

        return null;
    }

    private boolean isCorner(Pos corner) {
        int row = corner.row;
        int col = corner.col;

        return (row == 0 || row == 3) && (col == 0 || col == 3);
    }

    private void alignCross(int side, boolean isMoved) {
        if (!isMoved) {
            switch (side) {
                case 2:
                    // System.out.println("we aligned correctly");
                    break;
                case 3:
                    move_D();
                    move_D();
                    break;
                case 4:
                    move_D();
                    break;
                case 5:
                    move_DPrime();
                    break;
                default:
                    throw new AssertionError();
            }
        } else {
            switch (side) {
                case 2:
                    break;
                case 3:
                    move_D();
                    move_D();
                    break;
                case 4:
                    move_DPrime();
                    break;
                case 5:
                    move_D();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    // it's the same thing as getotherEdgePiece but doesn't exclude the edge solvign
    // area
    private Pos getEdgePiece(String primary, String wing) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    Pos temp = new Pos(i, j, k);

                    if (isEdge(temp)) {
                        if (getPosColour(temp).equals(primary) && getPosColour(getWing(temp)).equals(wing)) {
                            return temp;
                        }
                    }
                }
            }
        }

        return null;
    }

    private void solveWhiteCenter() {
        whiteCenterOne();
        whiteCenterTwo();
        whiteCenterThree();
        whiteCenterFour();

        move_r();
        move_U();
        move_U();
        move_r();
        move_r();
        move_r();
    }

    private void whiteCenterOne() {
        Pos tempWhite = findCenterPos("W");

        if (tempWhite.face == 0) {
            while (!(getPosColour(wCenter011).equals("W"))) {
                move_U();
            }
        }

        if (getPosColour(wCenter011).equals("W")) {
            return;
        }

        int face = tempWhite.face;

        switch (face) {
            case 1:
                for (int i = 0; i < 2; i++) {
                    move_l();
                    move_r();
                }
                break;

            case 2:
                move_r();
                move_lPrime();
                break;

            case 3:
                move_rPrime();
                move_l();
                break;

            case 4:
                move_f();
                move_bPrime();
                break;

            case 5:
                move_fPrime();
                move_b();
                break;

            default:
                throw new AssertionError();
        }

        whiteCenterOne();
    }

    private void whiteCenterTwo() {

        Pos[] exclude = { wCenter011 };

        Pos tempWhite = findCenterPos("W", exclude);

        // System.out.println("White center 2 at: " + tempWhite.toString());

        if (getPosColour(wCenter012).equals("W")) {
            move_U();
            return;
        }

        if (getPosColour(wCenter021).equals("W")) {
            move_U();
            move_U();
            return;
        }

        int face = tempWhite.face;

        switch (face) {
            case 0:
                move_r();
                move_r();
                // fall through to face 1

            case 1:
                while (!(getPosColour(yCenter112).equals("W"))) {
                    move_B();
                }

                move_r();
                move_r();
                break;

            case 2:
                while (!(getPosColour(rCenter212).equals("W"))) {
                    move_F();
                }

                move_r();
                break;

            case 3:
                move_u();
                move_u();
                move_d();
                move_d();
                break;

            case 4:
                move_uPrime();
                move_d();
                break;

            case 5:
                move_u();
                move_dPrime();
                break;

            default:
                throw new AssertionError();
        }

        whiteCenterTwo();

    }

    private void whiteCenterThree() {
        Pos[] exclude = { wCenter012, wCenter022 };

        Pos tempWhite = findCenterPos("W", exclude);

        // System.out.println("White center 3 at: " + tempWhite.toString());

        if (getPosColour(rCenter212).equals("W")) {
            return;
        }

        int face = tempWhite.face;

        switch (face) {
            case 0:
                move_l();
                move_l();
                // fall through to face 1

            case 1:
                while (!(getPosColour(yCenter111).equals("W"))) {
                    move_D();
                }
                move_lPrime();
                // fall through to face 2

            case 2:
                while (!(getPosColour(rCenter212).equals("W"))) {
                    move_F();
                }
                break;

            case 3:
                move_u();
                move_u();
                move_d();
                move_d();
                break;

            case 4:
                move_uPrime();
                move_d();
                break;

            case 5:
                move_u();
                move_dPrime();
                break;

            default:
                throw new AssertionError();
        }

        whiteCenterThree();
    }

    private void whiteCenterFour() {
        Pos[] exclude = { wCenter012, wCenter022, rCenter212 };

        if (getPosColour(rCenter211).equals("W")) {
            move_F();
            return;
        }

        if (getPosColour(rCenter222).equals("W")) {
            return;
        }

        Pos tempWhite = findCenterPos("W", exclude);

        int face = tempWhite.face;

        switch (face) {
            case 0:
                move_lPrime();
                break;

            case 1:
                while (!(getPosColour(yCenter111).equals("W"))) {
                    move_D();
                }
                move_lPrime();
                break;

            case 2:
                move_d();
                break;

            case 3:
                while (!(getPosColour(oCenter322).equals("W"))) {
                    move_B();
                }
                move_d();
                move_d();
                break;

            case 4:
                while (!(getPosColour(gCenter422).equals("W"))) {
                    move_L();
                }
                move_d();
                break;

            case 5:
                while (!(getPosColour(bCenter522).equals("W"))) {
                    move_R();
                }
                move_dPrime();
                break;

            default:
                throw new AssertionError();
        }

        whiteCenterFour();
    }

    private void solveYellowCenter() {
        boolean flipYellowFace = false; // I only want to change the yellow face once per function
        solveYellowCenterOne(flipYellowFace);

        flipYellowFace = false;
        solveYellowCenterTwo(flipYellowFace);

        // move the mini column + reset to prepare for second mini column
        move_l();
        move_D();
        move_D();
        move_l();
        move_l();
        move_l();
        move_D();
        move_D();

        flipYellowFace = false;
        solveYellowCenterThree(flipYellowFace);

        flipYellowFace = false;
        solveYellowCenterFour(flipYellowFace);

        // move the mini column
        move_l();
        move_D();
        move_D();
        move_l();
        move_l();
        move_l();
    }

    private void solveYellowCenterOne(boolean flipYellowFace) {
        if (getPosColour(rCenter211).equals("Y")) {
            return;
        }

        Pos tempYellow;
        if (flipYellowFace) {
            Pos[] exclude = { yCenter111, yCenter112, yCenter121, yCenter122 };
            tempYellow = findCenterPos("Y", exclude);
        } else {
            tempYellow = findCenterPos("Y");
        }

        // System.out.println("Yellow center 1 is at: " + tempYellow.toString());

        int face = tempYellow.face;

        boolean flipYellowFaceNext = flipYellowFace;
        switch (face) {
            case 1:
                if (!flipYellowFace) {
                    move_lPrime();
                    move_F();
                    move_F();
                    move_l();

                    move_r();
                    move_F();
                    move_F();
                    move_rPrime();

                    flipYellowFaceNext = true;
                }
                break;

            case 2:
                while (!(getPosColour(rCenter211).equals("Y"))) {
                    move_F();
                }
                break;

            case 3:
                move_u();
                move_u();
                move_d();
                move_d();
                break;
            case 4:
                move_uPrime();
                move_d();
                break;
            case 5:
                move_u();
                move_dPrime();
                break;
            default:
                throw new AssertionError();
        }

        solveYellowCenterOne(flipYellowFaceNext);
    }

    private void solveYellowCenterTwo(boolean flipYellowFace) {
        if (getPosColour(rCenter221).equals("Y")) {
            return;
        }

        Pos tempYellow;
        if (flipYellowFace) {
            Pos[] exclude = { yCenter111, yCenter112, yCenter121, yCenter122, rCenter211 };
            tempYellow = findCenterPos("Y", exclude);
        } else {
            Pos[] exclude = { rCenter211 };
            tempYellow = findCenterPos("Y", exclude);
        }

        int face = tempYellow.face;

        boolean flipYellowFaceNext = flipYellowFace;

        switch (face) {
            case 1:
                if (!flipYellowFace) {
                    move_fPrime();
                    move_R();
                    move_R();
                    move_f();

                    move_b();
                    move_R();
                    move_R();
                    move_bPrime();
                    flipYellowFaceNext = true;
                }
                break;
            case 2:
                if (getPosColour(rCenter212).equals("Y")) {
                    move_FPrime();
                } else if (getPosColour(rCenter222).equals("Y")) {
                    move_d();
                }
            case 3:
                while (!(getPosColour(oCenter321).equals("Y"))) {
                    move_B();
                }
                move_d();
                move_d();
                break;
            case 4:
                while (!(getPosColour(gCenter421).equals("Y"))) {
                    move_L();
                }
                move_d();
                break;
            case 5:
                while (!(getPosColour(bCenter521).equals("Y"))) {
                    move_R();
                }
                move_dPrime();
                break;
            default:
                throw new AssertionError();
        }

        solveYellowCenterTwo(flipYellowFaceNext);
    }

    private void solveYellowCenterThree(boolean flipYellowFace) {
        if (getPosColour(rCenter211).equals("Y")) {
            return;
        }

        Pos tempYellow;
        if (flipYellowFace) {
            Pos[] exclude = { yCenter111, yCenter112, yCenter121, yCenter122 };
            tempYellow = findCenterPos("Y", exclude);
        } else {
            Pos[] exclude = { yCenter111, yCenter121 };
            tempYellow = findCenterPos("Y", exclude);
        }

        int face = tempYellow.face;

        boolean flipYellowFaceNext = flipYellowFace;
        switch (face) {
            case 1:
                if (!flipYellowFace) {
                    move_r();
                    move_F();
                    move_F();
                    move_rPrime();
                    flipYellowFaceNext = true;
                }
                break;
            case 2:
                while (!(getPosColour(rCenter211).equals("Y"))) {
                    move_F();
                }
                break;

            case 3:
                move_u();
                move_u();
                move_d();
                move_d();
                break;
            case 4:
                move_uPrime();
                move_d();
                break;
            case 5:
                move_u();
                move_dPrime();
                break;
            default:
                throw new AssertionError();
        }

        solveYellowCenterThree(flipYellowFaceNext);
    }

    private void solveYellowCenterFour(boolean flipYellowFace) {

        if (getPosColour(rCenter221).equals("Y")) {
            return;
        }

        Pos tempYellow;
        if (flipYellowFace) {
            Pos[] exclude = { yCenter111, yCenter112, yCenter121, yCenter122, rCenter211 };
            tempYellow = findCenterPos("Y", exclude);
        } else {
            Pos[] exclude = { yCenter111, yCenter121, rCenter211 };
            tempYellow = findCenterPos("Y", exclude);
        }

        int face = tempYellow.face;

        boolean flipYellowFaceNext = flipYellowFace;
        switch (face) {
            case 1:
                if (!flipYellowFace) {
                    move_r();
                    move_F();
                    move_F();
                    move_rPrime();
                    flipYellowFaceNext = true;
                }
                break;
            case 2:
                if (getPosColour(rCenter212).equals("Y")) {
                    move_F();
                    move_F();
                    move_F();
                } else if (getPosColour(rCenter222).equals("Y")) {
                    move_d();
                }
            case 3:
                while (!(getPosColour(oCenter321).equals("Y"))) {
                    move_B();
                }
                move_d();
                move_d();
                break;
            case 4:
                while (!(getPosColour(gCenter421).equals("Y"))) {
                    move_L();
                }
                move_d();
                break;
            case 5:
                while (!(getPosColour(bCenter521).equals("Y"))) {
                    move_R();
                }
                move_dPrime();
                break;
            default:
                throw new AssertionError();
        }

        solveYellowCenterFour(flipYellowFaceNext);
    }

    // for the rest of the centers, only move_ u, d, F, B, L, R
    private void solveRedCenter() {
        solveRedCenterOne();
        solveRedCenterTwo();
        solveRedCenterThree();
        solveRedCenterFour();
    }

    private void solveRedCenterOne() {
        if (getPosColour(rCenter211).equals("R")) {
            return;
        }

        Pos tempRed = findCenterPos("R");
        int face = tempRed.face;

        switch (face) {
            case 2:
                while (!(getPosColour(rCenter211).equals("R"))) {
                    move_F();
                }
                break;
            case 3:
                move_u();
                move_u();
                move_d();
                move_d();
                break;
            case 4:
                move_uPrime();
                move_d();
                break;
            case 5:
                move_u();
                move_dPrime();
                break;
            default:
                throw new AssertionError();
        }

        solveRedCenterOne();
    }

    private void solveRedCenterTwo() {
        if (getPosColour(rCenter212).equals("R")) {
            return;
        }

        if (getPosColour(rCenter221).equals("R")) {
            move_F();
            return;
        }

        Pos[] exclude = { rCenter211 };
        Pos tempRed = findCenterPos("R", exclude);
        int face = tempRed.face;

        switch (face) {
            case 2:
                move_d();
                move_d(); // intentionally letting fall to case 3
            case 3:
                while (!(getPosColour(oCenter321).equals("R"))) {
                    move_B();
                }
                move_d();
                move_d();
                break;
            case 4:
                while (!(getPosColour(gCenter421).equals("R"))) {
                    move_L();
                }
                move_d();
                break;
            case 5:
                while (!(getPosColour(bCenter521).equals("R"))) {
                    move_R();
                }
                move_dPrime();
                break;
            default:
                throw new AssertionError();
        }
        solveRedCenterTwo();
    }

    private void solveRedCenterThree() {
        if (getPosColour(oCenter311).equals("R")) {
            return;
        }

        if (getPosColour(rCenter221).equals("R") && getPosColour(rCenter222).equals("R")) {
            return;
        }

        Pos[] exclude = { rCenter211, rCenter212 };
        Pos tempRed = findCenterPos("R", exclude);
        int face = tempRed.face;

        // System.out.println("White red 3 at: " + tempRed.toString());

        switch (face) {
            case 2:
                // automatically go to case 3 (literally no reason to try and manipulate at case
                // 2)
            case 3:
                while (!(getPosColour(oCenter311).equals("R"))) {
                    move_B();
                }
                break;
            case 4:
                while (!(getPosColour(gCenter421).equals("R"))) {
                    move_L();
                }
                move_dPrime();
                break;
            case 5:
                while (!(getPosColour(bCenter521).equals("R"))) {
                    move_R();
                }
                move_d();
                break;
            default:
                throw new AssertionError();
        }
        solveRedCenterThree();
    }

    private void solveRedCenterFour() {
        if (getPosColour(oCenter321).equals("R")) {
            move_BPrime();// turn so bottom row lines up

            move_d();
            move_d();// move to face 3
            return;
        }

        if (getPosColour(oCenter312).equals("R")) {
            move_B();
            move_B();// turn so bottom row lines up

            move_d();
            move_d();// move to face 3
            return;
        }

        if (getPosColour(rCenter221).equals("R") && getPosColour(rCenter222).equals("R")) {
            return;
        }

        Pos[] exclude = { rCenter211, rCenter212, oCenter311 };
        Pos tempRed = findCenterPos("R", exclude);
        int face = tempRed.face;

        switch (face) {
            case 2:
                move_d();
                break;
            case 3:
                move_d();
                break;
            case 4:
                while (!(getPosColour(gCenter421).equals("R"))) {
                    move_L();
                }
                move_dPrime();
                break;
            case 5:
                while (!(getPosColour(bCenter521).equals("R"))) {
                    move_R();
                }
                move_d();
                break;
            default:
                throw new AssertionError();
        }
        solveRedCenterFour();
    }

    private void solveOrangeCenter() {
        boolean flipOrangeFace = false;
        solveOrangeCenterOne(flipOrangeFace);

        flipOrangeFace = false;
        solveOrangeCenterTwo(flipOrangeFace);

        move_u();
        move_B();
        move_B();
        move_uPrime();
        move_B();
        move_B();

        flipOrangeFace = false;
        solveOrangeCenterThree(flipOrangeFace);

        flipOrangeFace = false;
        solveOrangeCenterFour(flipOrangeFace);

        move_u();
        move_B();
        move_B();
        move_uPrime();

    }

    private void solveOrangeCenterOne(boolean flipOrangeFace) {
        if (getPosColour(gCenter411).equals("O")) {
            return;
        }

        Pos tempOrange;
        if (flipOrangeFace) {
            Pos[] exclude = { oCenter311, oCenter312, oCenter321, oCenter322 };
            tempOrange = findCenterPos("O", exclude);
        } else {
            tempOrange = findCenterPos("O");
        }

        int face = tempOrange.face;

        boolean flipOrangeFaceNext = flipOrangeFace;

        switch (face) {
            case 3:
                if (!flipOrangeFace) {
                    move_uPrime();
                    move_L();
                    move_L();
                    move_u();

                    move_d();
                    move_L();
                    move_L();
                    move_dPrime();
                    flipOrangeFaceNext = true;
                }
                break;
            case 4:
                while (!(getPosColour(gCenter411).equals("O"))) {
                    move_L();
                }
                break;
            case 5:
                while (!(getPosColour(bCenter511).equals("O"))) {
                    move_R();
                }
                for (int i = 0; i < 2; i++) {
                    move_u();
                    move_u();
                    move_L();
                    move_L();
                }

                break;
            default:
                throw new AssertionError();
        }

        solveOrangeCenterOne(flipOrangeFaceNext);
    }

    private void solveOrangeCenterTwo(boolean flipOrangeFace) {

        if (getPosColour(gCenter412).equals("O")) {
            return;
        }
        if (getPosColour(gCenter421).equals("O")) {
            move_L();
            return;
        }

        Pos tempOrange;
        if (flipOrangeFace) {
            Pos[] exclude = { oCenter311, oCenter312, oCenter321, oCenter322, gCenter411 };
            tempOrange = findCenterPos("O", exclude);
        } else {
            Pos[] exclude = { gCenter411 };
            tempOrange = findCenterPos("O", exclude);
        }

        int face = tempOrange.face;

        // System.out.println("orange center 2 at: " + tempOrange.toString());

        boolean flipOrangeFaceNext = flipOrangeFace;

        switch (face) {
            case 3:
                if (!flipOrangeFace) {
                    move_u();
                    move_R();
                    move_R();
                    move_uPrime();

                    move_dPrime();
                    move_R();
                    move_R();
                    move_d();
                    flipOrangeFaceNext = true;
                }
                break;
            case 4:
                // letting it fall through to case 5 because there's no way there's no orange on
                // face 5
            case 5:
                while (!(getPosColour(bCenter521).equals("O"))) {
                    move_R();
                }

                move_d();
                move_d();

                move_L();

                move_d();
                move_d();
                break;

            default:
                throw new AssertionError();
        }

        solveOrangeCenterTwo(flipOrangeFaceNext);
    }

    private void solveOrangeCenterThree(boolean flipOrangeFace) {
        if (getPosColour(gCenter411).equals("O")) {
            return;
        }

        Pos tempOrange;
        if (flipOrangeFace) {
            Pos[] exclude = { oCenter311, oCenter312, oCenter321, oCenter322, gCenter411 };
            tempOrange = findCenterPos("O", exclude);
        } else {
            Pos[] exclude = { oCenter311, oCenter312 };
            tempOrange = findCenterPos("O", exclude);
        }

        int face = tempOrange.face;

        boolean flipOrangeFaceNext = flipOrangeFace;

        switch (face) {
            case 3:
                if (!flipOrangeFace) {
                    move_d();
                    move_L();
                    move_L();
                    move_dPrime();
                    flipOrangeFaceNext = true;
                }
                break;
            case 4:
                while (!(getPosColour(gCenter411).equals("O"))) {
                    move_L();
                }
                break;
            case 5:
                while (!(getPosColour(bCenter511).equals("O"))) {
                    move_R();
                }
                for (int i = 0; i < 2; i++) {
                    move_u();
                    move_u();
                    move_L();
                    move_L();
                }

                break;
            default:
                throw new AssertionError();
        }

        solveOrangeCenterThree(flipOrangeFaceNext);
    }

    private void solveOrangeCenterFour(boolean flipOrangeFace) {
        if (getPosColour(gCenter412).equals("O")) {
            return;
        }
        if (getPosColour(gCenter421).equals("O")) {
            move_L();
            return;
        }

        Pos tempOrange;
        if (flipOrangeFace) {
            Pos[] exclude = { oCenter311, oCenter312, oCenter321, oCenter322, gCenter411 };
            tempOrange = findCenterPos("O", exclude);
        } else {
            Pos[] exclude = { oCenter311, oCenter312, gCenter411 };
            tempOrange = findCenterPos("O", exclude);
        }

        // System.out.println("orange center 4 at: " + tempOrange.toString());

        int face = tempOrange.face;

        boolean flipOrangeFaceNext = flipOrangeFace;

        switch (face) {
            case 3:
                if (!flipOrangeFace) {
                    move_dPrime();
                    move_R();
                    move_R();
                    move_d();
                    flipOrangeFaceNext = true;
                }
                break;
            case 4:
                move_d();
                move_d();
                move_R();
                move_R();
                move_d();
                move_d();
                break;
            case 5:
                while (!(getPosColour(bCenter521).equals("O"))) {
                    move_R();
                }

                move_d();
                move_d();
                move_L();
                move_d();
                move_d();
                break;

            default:
                throw new AssertionError();
        }

        solveOrangeCenterFour(flipOrangeFaceNext);
    }

    int greenu2Count = 0;
    int greenDepth = 0;

    private void solveGreenCenter() {
        greenDepth++;
        if (greenDepth > 50) {
            return;
        } // I don't trust this not to break at some point, it feels so shoddy... (but I
          // also don't know know to make it better, so failsafe it is), and I'm not that
          // happy with the greenu2Count, but it is what it is

        if (getPosColour(gCenter411).equals("G") && getPosColour(gCenter412).equals("G")
                && getPosColour(gCenter421).equals("G") && getPosColour(gCenter422).equals("G")) {

            if (greenu2Count % 2 == 1) {
                move_B();
                move_B();
                move_u();
                move_u();
                move_B();
                move_B();
                move_u();
                move_u();
            }

            return;
        }

        if (getPosColour(gCenter411).equals("G") && getPosColour(gCenter412).equals("G")
                && getPosColour(bCenter511).equals("G") && getPosColour(bCenter512).equals("G")) {
            if (greenu2Count % 2 == 1) {
                move_u();
                move_u();
            } else {
                move_u();
                move_u();
                move_L();
                move_L();
                move_u();
                move_u();
            }
            return;
        }

        if (getPosColour(gCenter421).equals("G") && getPosColour(gCenter422).equals("G")
                && getPosColour(bCenter521).equals("G") && getPosColour(bCenter522).equals("G")) {
            move_L();
            move_L();
            move_R();
            move_R();
        }

        // set up colum 1 (i think)
        while (!(getPosColour(gCenter411).equals("G"))) {
            move_L();
        }
        while (!(getPosColour(bCenter521).equals("G"))) {
            move_R();
        }

        if (greenu2Count > 1) {
            while (!(getPosColour(gCenter412).equals("B"))) {
                move_L();
            }
        }

        move_u();
        move_u();
        greenu2Count++;

        move_R();

        move_u();
        move_u();
        greenu2Count++;

        solveGreenCenter();
    }

    // edges and edge helper functions
    // so what's happening is that edges are manipulate until they reach front-left
    // and front-right where they become one (I swear this makes sense in reduction
    // method, this is the right way to do things)

    // i'm still nervous about these two going into infinite recursion somehow
    int edgeDepth = 0;
    int oneDepth = 0;

    private void solveEdge() {
        edgeDepth++;
        if (edgeDepth > 30) {
            return;
        }

        int unsolvedEdge = getNumUnsolvedEdges();

        System.out.println(unsolvedEdge);

        if (unsolvedEdge == 0) {
            return;
        } else if (unsolvedEdge == 1) {
            throw new RuntimeException("idk how there could be one unsolved edge??");
        } else if (unsolvedEdge == 2) {
            // must unalign edge
            solveLastTwoEdge(); // i think this works for now?
        } else if (unsolvedEdge == 3) {
            // must align the top edge
            solveLastThreeEdge();
            return;
        } else {
            // System.out.println("Is something happenign here?");
            solveOneEdge();
        }

        solveEdge();

    }

    // edge helpers

    // i was worried I'd need this, honestly want to just break one, let's try that
    private void solveLastTwoEdge() {
        // move unsolved into the set pos front-left
        while (!(isEdgeSolved(wEdge031))) {
            move_U();
        }
        move_LPrime();
        move_U();
        move_L();

        // repeat for front-left
        while (!(isEdgeSolved(wEdge031))) {
            move_U();
        }
        move_R();
        move_UPrime();
        move_RPrime();

        // modified solve alg (and then it should be good to go through the 4->3
        // reduction)
        // doesn't need a buffer, the two edges are their own buffers
        move_u();
        move_F();
        move_U();
        move_FPrime();
        move_UPrime();
        move_LPrime();
        move_U();
        move_L();
        move_uPrime();
    }

    // to solve the last layer (yeah, most of the code is just solveOneEdge again,
    // but here's another conditional)
    private void solveLastThreeEdge() {
        if (isEdgeSolved(gEdge423)) {
            // System.out.println("This is getting messed up, isn't it?");
            // i think this is working just fine
            if (isEdgeSolved(rEdge213)) {
                Pos temp = getRandUnsolvedEdge();

                while (isEdgeSolved(rEdge213)) {
                    moveEdgeToFrontRight(temp);
                }
            }
            move_F();
            move_F();
        }

        String primary = getPosColour(gEdge423);
        String wing = getPosColour(rEdge220);

        // System.out.println("looking for edge: " + primary + " " + wing);

        Pos other = getOtherEdgePiece(primary, wing);

        // System.out.println("other edge piece at: " + other.toString());

        if (other.equals(rEdge213)) {
            if (!isEdgeSolved(wEdge013)) {
                alg_reduceEdge();
                return;
            }

            while (isEdgeSolved(wEdge013)) {
                moveUnsolvedToUpRight();
            }

            if (!(getPosColour(wEdge013).equals(getPosColour(rEdge223))
                    && getPosColour(bEdge502).equals(getPosColour(bEdge520)))) {
                // reverse direction of the buffer, since it's reversed per if statement
                move_UPrime();
                move_BPrime();
                move_RPrime();
                move_U();
                move_R();
                move_UPrime();
            }

            alg_reduceEdge();
            return;
        }

        moveEdgeToFrontRight(other);

        solveLastThreeEdge();
    }

    // solving edges
    private void solveOneEdge() {
        oneDepth++;
        if (oneDepth > 100) {
            return;
        }
        if (isEdgeSolved(gEdge423)) {
            System.out.println("This is getting messed up, isn't it?");
            // i think this is working just fine
            while (isEdgeSolved(gEdge423)) { // because we are moving directly to moveUnsolvedTOFrontLeft, can probably
                                             // drop the if statement as well...
                System.out.println("infintie loop?");
                moveUnsolvedToFrontLeft();
            }
        }

        String primary = getPosColour(gEdge423);
        String wing = getPosColour(rEdge220);

        System.out.println("looking for edge: " + primary + " " + wing);

        Pos other = getOtherEdgePiece(primary, wing);

        System.out.println("other edge piece at: " + other.toString());

        if (other.equals(rEdge213)) {
            while (isEdgeSolved(wEdge013)) {
                System.out.println("check for unsolved to up right");
                moveUnsolvedToUpRight();
            }

            alg_reduceEdge();
            return;
        }

        moveEdgeToFrontRight(other);

        solveOneEdge();
    }

    // move other buffer
    private void moveUnsolvedToFrontLeft() {
        Pos temp = getRandUnsolvedEdge();

        System.out.println("loc of unsolved edge: " + temp.toString());

        int face1 = temp.face;
        int face2 = getWing(temp).face;

        if (face1 == 2 || face2 == 2) {
            move_F();
        }

        int min = Math.min(face1, face2);
        int max = Math.max(face1, face2);

        if (min == 0) {
            switch (max) {
                case 2:

                    break;
                case 3:
                    move_U();
                    move_U();
                    break;
                case 4:
                    move_UPrime();
                    break;
                case 5:
                    move_U();
                    break;
                default:
                    throw new AssertionError();
            }
            move_FPrime();
            return;
        }

        if (min == 1) {
            switch (max) {
                case 2:
                    break;
                case 3:
                    move_D();
                    move_D();
                    break;
                case 4:
                    move_D();
                    break;
                case 5:
                    move_DPrime();
                    break;
                default:
                    throw new AssertionError();
            }
            move_F();
            return;
        }

        // only case left is either back-left or back-right and this brings it to either
        // U or D
        move_B();
    }

    // move buffer
    private void moveUnsolvedToUpRight() {
        // System.out.println("Just wondering if this is used");
        Pos temp = getRandUnsolvedEdge();

        int face1 = temp.face;
        int face2 = getWing(temp).face;

        System.out.println("Where does it think unsolved is: " + temp.toString());

        int min = Math.min(face1, face2);
        int max = Math.max(face1, face2);

        if (min == 0) {
            move_U();
            return;
        }

        System.out.println("On the faces: " + min + " " + max);

        if (min == 1) {
            switch (max) {
                case 2:
                    move_D();
                    move_D();
                    break;
                case 3:
                    break;
                case 4:
                    move_DPrime();
                    break;
                case 5:
                    move_D();
                    break;
                default:
                    throw new AssertionError();
            }
            move_B();
            move_B();
            return;
        }

        // only case left is either back-left or back-right
        move_B();
    }

    // find corresponding edge (idk if i want it to just use 423-220, but for now it
    // takes colours)
    // automatically exclude 423-220, which i'm using as the set location
    private Pos getOtherEdgePiece(String primary, String wing) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    Pos temp = new Pos(i, j, k);

                    if (isEdge(temp)) {
                        if (getPosColour(temp).equals(primary) && getPosColour(getWing(temp)).equals(wing)) {
                            if (!gEdge423.equals(temp)) {
                                return temp;
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    // the edge given will be manipulate to red213 eventually
    private void moveEdgeToFrontRight(Pos edge) {
        int face1 = edge.face;
        int face2 = getWing(edge).face;

        if (edge.equals(gEdge413)) {
            return;
        }

        if (edge.equals(rEdge210)) {
            move_u();

        }

        if (!(face1 == 0 || face1 == 1 || face2 == 0 || face2 == 1)) {
            if (face1 == 2 || face2 == 2) {
                move_R();
            } else {
                move_B();
            }
            return;
        }

        if (face1 == 0 || face2 == 0) {
            if (!(face1 == 2 || face2 == 2)) {
                move_U();
                return;
            }

            // actually putting piece into position
            if (edge.equals(rEdge201)) {
                move_UPrime();
                move_FPrime();
                move_U();
                move_F();
            } else if (edge.equals(wEdge032)) {
                move_UPrime();
                move_RPrime();
            } else {
                move_F();
            }
            return;
        }

        if (face1 == 1 || face2 == 1) {
            if (!(face1 == 2 || face2 == 2)) {
                move_D();
                return;
            }

            // actually putting piece into position
            if (edge.equals(rEdge232)) {
                move_D();
                move_F();
                move_DPrime();
                move_FPrime();

            } else if (edge.equals(yEdge101)) {
                move_D();
                move_R();
            } else {
                move_FPrime();
            }
            return;

        }
    }

    // find an unsolved edge
    // automaticall excludes solving area
    private Pos getRandUnsolvedEdge() {
        Pos[] exclude = { gEdge413, gEdge423, rEdge210, rEdge220, rEdge213, rEdge223, bEdge510, bEdge520 };

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if ((j == 0 || j == 3) ^ (k == 0 || k == 3)) {
                        Pos edge1 = new Pos(i, j, k);

                        if (!isEdgeSolved(edge1)) {
                            boolean excluded = false;

                            for (int a = 0; a < exclude.length; a++) {
                                if (exclude[a].equals(edge1)) {
                                    excluded = true;
                                    break;
                                }
                            }

                            if (!excluded)
                                return edge1;
                        }
                    }
                }
            }
        }

        return null;
    }

    // find number of unsolved edges
    private int getNumUnsolvedEdges() {
        int count = 0;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if ((j == 0 || j == 3) ^ (k == 0 || k == 3)) {
                        Pos edge1 = new Pos(i, j, k);
                        if (!isEdgeSolved(edge1)) {
                            count++;
                        }
                    }
                }
            }
        }

        return (count / 4); // every unsolved edge is counted 4 times for each of the stickers
    }

    // find the edge a piece is actually close to / touching (an edge piece should
    // only be touching one other edge)
    private Pos getTouchingEdge(Pos edge) {
        if (!isEdge(edge)) {
            throw new RuntimeException("why is a not edge trying to find its adjacent colour?");
        }

        // lookup table (i stole this from findAdjacentColour and did some copy pasting,
        // which is why the order is wonky)
        Pos[][] edgeLookUp = {
                { wEdge001, wEdge002 },
                { oEdge302, oEdge301 },

                { wEdge010, wEdge020 },
                { gEdge401, gEdge402 },

                { wEdge013, wEdge023 },
                { bEdge502, bEdge501 },

                { wEdge031, wEdge032 },
                { rEdge201, rEdge202 },

                { yEdge101, yEdge102 },
                { rEdge231, rEdge232 },

                { yEdge110, yEdge120 },
                { gEdge432, gEdge431 },

                { yEdge113, yEdge123 },
                { bEdge531, bEdge532 },

                { yEdge131, yEdge132 },
                { oEdge332, oEdge331 },

                { rEdge210, rEdge220 },
                { gEdge413, gEdge423 },

                { rEdge213, rEdge223 },
                { bEdge510, bEdge520 },

                { oEdge310, oEdge320 },
                { bEdge513, bEdge523 },

                { oEdge313, oEdge323 },
                { gEdge410, gEdge420 }
        };

        for (int i = 0; i < edgeLookUp.length; i++) {
            for (int j = 0; j < 2; j++) { // by definiton, edges cover 2 positions/stickers
                if (edgeLookUp[i][j].equals(edge)) {
                    if (j == 0) {
                        return edgeLookUp[i][1];
                    } else {
                        return edgeLookUp[i][0];
                    }
                }
            }
        }

        return null;
    }

    // find second edge colour
    // wing as in the other colour on the edge (also I just like the word wing)
    private Pos getWing(Pos edge) {
        if (!isEdge(edge)) {
            throw new RuntimeException("why is a not edge trying to find its adjacent colour?");
        }

        // lookup table
        Pos[][] edgeLookUp = {
                // white orange (up front)
                { wEdge001, oEdge302 },
                { wEdge002, oEdge301 },

                // white green (up left)
                { wEdge010, gEdge401 },
                { wEdge020, gEdge402 },

                // white blue (up right)
                { wEdge013, bEdge502 },
                { wEdge023, bEdge501 },

                // white red (up back)
                { wEdge031, rEdge201 },
                { wEdge032, rEdge202 },

                // yellow red (down front)
                { yEdge101, rEdge231 },
                { yEdge102, rEdge232 },

                // yellow green (down left)
                { yEdge110, gEdge432 },
                { yEdge120, gEdge431 },

                // yellow blue (down right)
                { yEdge113, bEdge531 },
                { yEdge123, bEdge532 },

                // yellow orange (down back)
                { yEdge131, oEdge332 },
                { yEdge132, oEdge331 },

                // red green (front left)
                { rEdge210, gEdge413 },
                { rEdge220, gEdge423 },

                // red blue (front right)
                { rEdge213, bEdge510 },
                { rEdge223, bEdge520 },

                // orange blue (back right)
                { oEdge310, bEdge513 },
                { oEdge320, bEdge523 },

                // orange green (back left)
                { oEdge313, gEdge410 },
                { oEdge323, gEdge420 }
        };

        for (int i = 0; i < edgeLookUp.length; i++) {
            for (int j = 0; j < 2; j++) { // by definiton, edges cover 2 positions/stickers
                if (edgeLookUp[i][j].equals(edge)) {
                    if (j == 0) {
                        return edgeLookUp[i][1];
                    } else {
                        return edgeLookUp[i][0];
                    }
                }
            }
        }

        return null;
    }

    // check if a position's associated edge has been solved
    private boolean isEdgeSolved(Pos edge) {
        // edge 1 alreay there
        Pos edge2 = getTouchingEdge(edge);

        Pos edge3 = getWing(edge);
        Pos edge4 = getTouchingEdge(edge3); // two ways to find edge 4

        return (getPosColour(edge).equals(getPosColour(edge2)) && getPosColour(edge3).equals(getPosColour(edge4)));
    }

    // check if a piece is an edge piece
    private boolean isEdge(Pos edge) {
        int row = edge.row;
        int col = edge.col;

        return (row == 0 || row == 3) ^ (col == 0 || col == 3);
    }

    // that one edge solving algorithm (trust)
    private void alg_reduceEdge() {
        move_u();
        move_F();
        move_U();
        move_FPrime();
        move_uPrime();
    }

    // center helper functions other stuf, not solving

    private Pos findCenterPos(String colour) {
        for (int i = 0; i < permutation.length; i++) {
            for (int j = 1; j < permutation[i].length - 1; j++) {
                for (int k = 1; k < permutation[i][j].length - 1; k++) {
                    if (permutation[i][j][k].equals(colour)) {
                        Pos temp = new Pos(i, j, k);

                        return temp;
                    }
                }
            }
        }

        return null;
    }

    private Pos findCenterPos(String colour, Pos[] exclude) {
        for (int i = 0; i < permutation.length; i++) {
            for (int j = 1; j < permutation[i].length - 1; j++) {
                for (int k = 1; k < permutation[i][j].length - 1; k++) {
                    if (permutation[i][j][k].equals(colour)) {

                        Pos temp = new Pos(i, j, k);

                        boolean excluded = false;
                        for (int a = 0; a < exclude.length; a++) {
                            if (exclude[a].equals(temp)) {
                                excluded = true;
                                break;
                            }
                        }

                        if (!excluded)
                            return temp;
                    }
                }
            }
        }

        return null;
    }

    private String getPosColour(Pos pos) {
        return permutation[pos.face][pos.row][pos.col];
    }

    // MOVES

    // up
    public void move_U() {
        rotateU();

        solveStr += "U, ";
    }

    public void move_UPrime() {
        for (int i = 0; i < 3; i++) {
            rotateU();
        }

        solveStr += "U', ";
    }

    public void move_Uw() {
        rotateUSlice();
        rotateU();

        solveStr += "Uw, ";
    }

    public void move_UwPrime(){
        for (int i = 0; i < 3; i++){
            rotateUSlice();
            rotateU();
        }

        solveStr += "Uw', ";

    }

    public void move_u() {
        rotateUSlice();

        solveStr += "u, ";
    }

    public void move_uPrime() {
        for (int i = 0; i < 3; i++) {
            rotateUSlice();
        }

        solveStr += "u', ";
    }

    // down
    public void move_D() {
        flipX();
        flipX();

        rotateU();

        flipX();
        flipX();

        solveStr += "D, ";
    }

    public void move_DPrime() {
        for (int i = 0; i < 3; i++) {
            flipX();
            flipX();

            rotateU();

            flipX();
            flipX();
        }

        solveStr += "D', ";
    }

    public void move_Dw() {
        flipX();
        flipX();

        rotateUSlice();
        rotateU();

        flipX();
        flipX();

        solveStr += "Dw, ";
    }

    public void move_DwPrime() {
        for (int i = 0; i < 3; i++) {
            flipX();
            flipX();

            rotateUSlice();
            rotateU();

            flipX();
            flipX();
        }

        solveStr += "Dw', ";
    }

    public void move_d() {
        flipX();
        flipX();

        rotateUSlice();

        flipX();
        flipX();

        solveStr += "d, ";
    }

    public void move_dPrime() {
        for (int i = 0; i < 3; i++) {
            flipX();
            flipX();

            rotateUSlice();

            flipX();
            flipX();
        }

        solveStr += "d', ";
    }

    // front
    public void move_F() {
        flipX();

        rotateU();

        for (int i = 0; i < 3; i++) {
            flipX();
        }

        solveStr += "F, ";
    }

    public void move_FPrime() {
        for (int i = 0; i < 3; i++) {
            flipX();

            rotateU();

            for (int j = 0; j < 3; j++) {
                flipX();
            }
        }

        solveStr += "F', ";
    }

    public void move_Fw() {
        flipX();

        rotateUSlice();
        rotateU();

        for (int i = 0; i < 3; i++) {
            flipX();
        }

        solveStr += "Fw, ";
    }

    public void move_FwPrime() {
        for (int i = 0; i < 3; i++) {
            flipX();

            rotateUSlice();
            rotateU();

            for (int j = 0; j < 3; j++) {
                flipX();
            }
        }

        solveStr += "Fw', ";
    }

    public void move_f() {
        flipX();

        rotateUSlice();

        for (int i = 0; i < 3; i++) {
            flipX();
        }

        solveStr += "f, ";
    }

    public void move_fPrime() {
        for (int i = 0; i < 3; i++) {
            flipX();

            rotateUSlice();

            for (int j = 0; j < 3; j++) {
                flipX();
            }
        }

        solveStr += "f', ";
    }

    // back
    public void move_B() {
        for (int i = 0; i < 3; i++) {
            flipX();
        }

        rotateU();

        flipX();

        solveStr += "B, ";
    }

    public void move_BPrime() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                flipX();
            }

            rotateU();

            flipX();
        }

        solveStr += "B', ";
    }

    public void move_Bw() {
        for (int i = 0; i < 3; i++) {
            flipX();
        }

        rotateUSlice();
        rotateU();

        flipX();

        solveStr += "Bw, ";
    }

    public void move_BwPrime() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                flipX();
            }

            rotateUSlice();
            rotateU();

            flipX();
        }

        solveStr += "Bw', ";
    }

    public void move_b() {
        for (int i = 0; i < 3; i++) {
            flipX();
        }

        rotateUSlice();

        flipX();

        solveStr += "b, ";
    }

    public void move_bPrime() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                flipX();
            }

            rotateUSlice();

            flipX();
        }

        solveStr += "b', ";
    }

    // left
    public void move_L() {
        flipZ();

        rotateU();

        for (int i = 0; i < 3; i++) {
            flipZ();
        }

        solveStr += "L, ";
    }

    public void move_LPrime() {
        for (int i = 0; i < 3; i++) {
            flipZ();

            rotateU();

            for (int j = 0; j < 3; j++) {
                flipZ();
            }
        }

        solveStr += "L', ";
    }

    public void move_Lw() {
        flipZ();

        rotateUSlice();
        rotateU();

        for (int i = 0; i < 3; i++) {
            flipZ();
        }

        solveStr += "Lw, ";
    }

    public void move_LwPrime() {
        for (int i = 0; i < 3; i++) {
            flipZ();

            rotateUSlice();
            rotateU();

            for (int j = 0; j < 3; j++) {
                flipZ();
            }
        }

        solveStr += "Lw', ";
    }

    public void move_l() {
        flipZ();

        rotateUSlice();

        for (int i = 0; i < 3; i++) {
            flipZ();
        }

        solveStr += "l, ";
    }

    public void move_lPrime() {
        for (int i = 0; i < 3; i++) {
            flipZ();

            rotateUSlice();

            for (int j = 0; j < 3; j++) {
                flipZ();
            }
        }

        solveStr += "l', ";
    }

    // right
    public void move_R() {
        for (int i = 0; i < 3; i++) {
            flipZ();
        }

        rotateU();

        flipZ();

        solveStr += "R, ";
    }

    public void move_RPrime() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                flipZ();
            }

            rotateU();

            flipZ();
        }

        solveStr += "R', ";
    }

    public void move_Rw() {
        for (int i = 0; i < 3; i++) {
            flipZ();
        }

        rotateUSlice();
        rotateU();

        flipZ();

        solveStr += "Rw, ";
    }

    public void move_RwPrime() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                flipZ();
            }

            rotateUSlice();
            rotateU();

            flipZ();
        }

        solveStr += "Rw', ";
    }

    public void move_r() {
        for (int i = 0; i < 3; i++) {
            flipZ();
        }

        rotateUSlice();

        flipZ();

        solveStr += "r, ";
    }

    public void move_rPrime() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                flipZ();
            }

            rotateUSlice();

            flipZ();
        }

        solveStr += "r', ";
    }

    // flips (I think thery'll only be used for certain CFOP algs)
    public void move_X() {
        flipX();

        solveStr += "X, ";
    }

    public void move_Z() {
        for (int i = 0; i < 3; i++) {
            flipZ();
        }

        solveStr += "Z, ";
    }

    public void move_Y() {
        flipX();

        flipZ();

        for (int i = 0; i < 3; i++) {
            flipX();
        }

        solveStr += "Y, ";
    }

    public void applyMoves(String moves){
        moves = moves.replaceAll("\\s+", "");

        while (moves.contains(",")){
            int temp = moves.indexOf(",");

            System.out.println(temp);

            String move = moves.substring(0, temp);
            moves = moves.substring(temp+1, moves.length());

            move(move);
        }

        move(moves);
    }

    public void move(String move){
        switch (move) {
            case "u":
                this.move_u();
                break;
            case "u'":
                this.move_uPrime();
                break;
            case "U":
                this.move_U();
                break;
            case "U'":
                this.move_UPrime();
                break;
            case "Uw":
                this.move_Uw();
                break;
            case "Uw'":
                this.move_UwPrime();
                break;
            case "d":
                this.move_d();
                break;
            case "d'":
                this.move_dPrime();
                break;
            case "D":
                this.move_D();
                break;
            case "D'":
                this.move_DPrime();
                break;
            case "Dw":
                this.move_Dw();
                break;
            case "Dw'":
                this.move_DwPrime();
                break;
            case "f":
                this.move_f();
                break;
            case "f'":
                this.move_fPrime();
                break;
            case "F":
                this.move_F();
                break;
            case "F'":
                this.move_FPrime();
                break;
            case "Fw":
                this.move_Fw();
                break;
            case "Fw'":
                this.move_FwPrime();
                break;
            case "b":
                this.move_b();
                break;
            case "b'":
                this.move_bPrime();
                break;
            case "B":
                this.move_B();
                break;
            case "B'":
                this.move_BPrime();
                break;
            case "Bw":
                this.move_Bw();
                break;
            case "Bw'":
                this.move_BwPrime();
                break;
            case "l":
                this.move_l();
                break;
            case "l'":
                this.move_lPrime();
                break;
            case "L":
                this.move_L();
                break;
            case "L'":
                this.move_LPrime();
                break;
            case "Lw":
                this.move_Lw();
                break;
            case "Lw'":
                this.move_LwPrime();
                break;
            case "r":
                this.move_r();
                break;
            case "r'":
                this.move_rPrime();
                break;
            case "R":
                this.move_R();
                break;
            case "R'":
                this.move_RPrime();
                break;
            case "Rw":
                this.move_Rw();
                break;
            case "Rw'":
                this.move_RwPrime();
                break;
            case "X":
                this.move_X();
                break;
            case "Y":
                this.move_Y();
                break;
            case "Z":
                this.move_Z();
                break;
            default:
                throw new AssertionError("not a valid move");
        }
    }

    // base moves that that the others are derived from
    private void rotateU() {
        int[][][] cycle = {
                // top layer
                { { 0, 1, 1 }, { 0, 2, 1 }, { 0, 2, 2 }, { 0, 1, 2 } },
                { { 0, 0, 0 }, { 0, 3, 0 }, { 0, 3, 3 }, { 0, 0, 3 } },
                { { 0, 1, 0 }, { 0, 3, 1 }, { 0, 2, 3 }, { 0, 0, 2 } },
                { { 0, 2, 0 }, { 0, 3, 2 }, { 0, 1, 3 }, { 0, 0, 1 } },
                // layer up
                { { 4, 0, 0 }, { 2, 0, 0 }, { 5, 0, 0 }, { 3, 0, 0 } },
                { { 4, 0, 3 }, { 2, 0, 3 }, { 5, 0, 3 }, { 3, 0, 3 } },
                { { 4, 0, 1 }, { 2, 0, 1 }, { 5, 0, 1 }, { 3, 0, 1 } },
                { { 4, 0, 2 }, { 2, 0, 2 }, { 5, 0, 2 }, { 3, 0, 2 } }
        };

        for (int i = 0; i < cycle.length; i++) {
            cycle(cycle[i]);
        }
    }

    private void rotateUSlice() {
        int[][][] cycle = {
                // layer two
                { { 4, 1, 0 }, { 2, 1, 0 }, { 5, 1, 0 }, { 3, 1, 0 } },
                { { 4, 1, 3 }, { 2, 1, 3 }, { 5, 1, 3 }, { 3, 1, 3 } },
                { { 4, 1, 1 }, { 2, 1, 1 }, { 5, 1, 1 }, { 3, 1, 1 } },
                { { 4, 1, 2 }, { 2, 1, 2 }, { 5, 1, 2 }, { 3, 1, 2 } }
        };

        for (int i = 0; i < cycle.length; i++) {
            cycle(cycle[i]);
        }
    }

    private void flipZ() {
        int[][][] cycle = {
                // front face
                { { 2, 1, 1 }, { 2, 2, 1 }, { 2, 2, 2 }, { 2, 1, 2 } },
                { { 2, 0, 0 }, { 2, 3, 0 }, { 2, 3, 3 }, { 2, 0, 3 } },
                { { 2, 1, 0 }, { 2, 3, 1 }, { 2, 2, 3 }, { 2, 0, 2 } },
                { { 2, 2, 0 }, { 2, 3, 2 }, { 2, 1, 3 }, { 2, 0, 1 } },

                // back face
                { { 3, 1, 1 }, { 3, 1, 2 }, { 3, 2, 2 }, { 3, 2, 1 } },
                { { 3, 0, 0 }, { 3, 0, 3 }, { 3, 3, 3 }, { 3, 3, 0 } },
                { { 3, 1, 0 }, { 3, 0, 2 }, { 3, 2, 3 }, { 3, 3, 1 } },
                { { 3, 2, 0 }, { 3, 0, 1 }, { 3, 1, 3 }, { 3, 3, 2 } },

                // corners
                { { 1, 0, 0 }, { 5, 3, 0 }, { 0, 3, 3 }, { 4, 0, 3 } },
                { { 1, 3, 0 }, { 5, 3, 3 }, { 0, 0, 3 }, { 4, 0, 0 } },
                { { 1, 3, 3 }, { 5, 0, 3 }, { 0, 0, 0 }, { 4, 3, 0 } },
                { { 1, 0, 3 }, { 5, 0, 0 }, { 0, 3, 0 }, { 4, 3, 3 } },

                // edges
                { { 1, 2, 0 }, { 5, 3, 2 }, { 0, 1, 3 }, { 4, 0, 1 } },
                { { 1, 1, 0 }, { 5, 3, 1 }, { 0, 2, 3 }, { 4, 0, 2 } },
                { { 1, 0, 1 }, { 5, 2, 0 }, { 0, 3, 2 }, { 4, 1, 3 } },
                { { 1, 0, 2 }, { 5, 1, 0 }, { 0, 3, 1 }, { 4, 2, 3 } },
                { { 1, 3, 1 }, { 5, 2, 3 }, { 0, 0, 2 }, { 4, 1, 0 } },
                { { 1, 3, 2 }, { 5, 1, 3 }, { 0, 0, 1 }, { 4, 2, 0 } },
                { { 1, 2, 3 }, { 5, 0, 2 }, { 0, 1, 0 }, { 4, 3, 1 } },
                { { 1, 1, 3 }, { 5, 0, 1 }, { 0, 2, 0 }, { 4, 3, 2 } },

                // centers
                { { 1, 2, 1 }, { 5, 2, 2 }, { 0, 1, 2 }, { 4, 1, 1 } },
                { { 1, 1, 1 }, { 5, 2, 1 }, { 0, 2, 2 }, { 4, 1, 2 } },
                { { 1, 2, 2 }, { 5, 1, 2 }, { 0, 1, 1 }, { 4, 2, 1 } },
                { { 1, 1, 2 }, { 5, 1, 1 }, { 0, 2, 1 }, { 4, 2, 2 } }
        };

        for (int i = 0; i < cycle.length; i++) {
            cycle(cycle[i]);
        }
    }

    private void flipX() {
        int[][][] cycle = {
                // right face
                { { 5, 1, 1 }, { 5, 2, 1 }, { 5, 2, 2 }, { 5, 1, 2 } },
                { { 5, 0, 0 }, { 5, 3, 0 }, { 5, 3, 3 }, { 5, 0, 3 } },
                { { 5, 1, 0 }, { 5, 3, 1 }, { 5, 2, 3 }, { 5, 0, 2 } },
                { { 5, 2, 0 }, { 5, 3, 2 }, { 5, 1, 3 }, { 5, 0, 1 } },

                // left face
                { { 4, 1, 1 }, { 4, 1, 2 }, { 4, 2, 2 }, { 4, 2, 1 } },
                { { 4, 0, 0 }, { 4, 0, 3 }, { 4, 3, 3 }, { 4, 3, 0 } },
                { { 4, 1, 0 }, { 4, 0, 2 }, { 4, 2, 3 }, { 4, 3, 1 } },
                { { 4, 2, 0 }, { 4, 0, 1 }, { 4, 1, 3 }, { 4, 3, 2 } },

                // faces -corners
                { { 2, 0, 0 }, { 1, 0, 0 }, { 3, 3, 3 }, { 0, 0, 0 } },
                { { 2, 0, 3 }, { 1, 0, 3 }, { 3, 3, 0 }, { 0, 0, 3 } },
                { { 2, 3, 0 }, { 1, 3, 0 }, { 3, 0, 3 }, { 0, 3, 0 } },
                { { 2, 3, 3 }, { 1, 3, 3 }, { 3, 0, 0 }, { 0, 3, 3 } },

                // faces -edges
                { { 2, 0, 1 }, { 1, 0, 1 }, { 3, 3, 2 }, { 0, 0, 1 } },
                { { 2, 0, 2 }, { 1, 0, 2 }, { 3, 3, 1 }, { 0, 0, 2 } },
                { { 2, 1, 0 }, { 1, 1, 0 }, { 3, 2, 3 }, { 0, 1, 0 } },
                { { 2, 2, 0 }, { 1, 2, 0 }, { 3, 1, 3 }, { 0, 2, 0 } },
                { { 2, 3, 1 }, { 1, 3, 1 }, { 3, 0, 2 }, { 0, 3, 1 } },
                { { 2, 3, 2 }, { 1, 3, 2 }, { 3, 0, 1 }, { 0, 3, 2 } },
                { { 2, 2, 3 }, { 1, 2, 3 }, { 3, 1, 0 }, { 0, 2, 3 } },
                { { 2, 1, 3 }, { 1, 1, 3 }, { 3, 2, 0 }, { 0, 1, 3 } },

                // faces -centers
                { { 2, 1, 1 }, { 1, 1, 1 }, { 3, 2, 2 }, { 0, 1, 1 } },
                { { 2, 1, 2 }, { 1, 1, 2 }, { 3, 2, 1 }, { 0, 1, 2 } },
                { { 2, 2, 1 }, { 1, 2, 1 }, { 3, 1, 2 }, { 0, 2, 1 } },
                { { 2, 2, 2 }, { 1, 2, 2 }, { 3, 1, 1 }, { 0, 2, 2 } }
        };

        for (int i = 0; i < cycle.length; i++) {
            cycle(cycle[i]);
        }
    }

    private void cycle(int[][] cycle) {
        String temp = permutation[cycle[0][0]][cycle[0][1]][cycle[0][2]];

        for (int i = 0; i < cycle.length - 1; i++) {
            permutation[cycle[i][0]][cycle[i][1]][cycle[i][2]] = permutation[cycle[i + 1][0]][cycle[i + 1][1]][cycle[i
                    + 1][2]];
        }
        permutation[cycle[cycle.length - 1][0]][cycle[cycle.length - 1][1]][cycle[cycle.length - 1][2]] = temp;
    }

    @Override
    public String toString() {
        String returnString = "";

        for (int i = 0; i < 6; i++) {
            returnString += "\nFace " + FACES[i] + ": \n";
            for (int j = 0; j < size; j++) {
                returnString += "    " + Arrays.deepToString(permutation[i][j]) + "\n";
            }

        }

        return returnString;
    }

}
