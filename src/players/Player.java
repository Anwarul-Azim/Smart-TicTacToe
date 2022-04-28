package tictactoe.players;

import tictactoe.GameState;

import java.util.Random;

public abstract class Player {
    public String level;
    public char symbol;
    public GameState gameState;
    public Player opponent;
    public Random rndGen;


    public  void prompt() {
        System.out.println("Making move level \"" + level + "\"");
    }


    public  int[] makeRandomMove(Random rnd) {
        int[] coOrd = {rnd.nextInt(3), rnd.nextInt(3)};
        while (!gameState.isLegalMove(coOrd)) {
            coOrd[0] = rnd.nextInt(3);
            coOrd[1] = rnd.nextInt(3);
        }
        return coOrd;
    }
    public abstract int[] makeMove();

}
