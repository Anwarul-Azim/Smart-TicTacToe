package tictactoe.players;

import java.util.Random;

public class Easy extends Player {
    @Override
    public int[] makeMove() {
        return super.makeRandomMove(new Random());
    }

}
