package tictactoe.players;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;

public class Medium extends Player {
    @Override
    public int[] makeMove() {
        int[] coOrd = {0, 0};
        int[] finalCoOrd = coOrd;
        Predicate<Character> selectiveSearch = (c) -> {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    int[] temp = new int[]{row, col};
                    if (gameState.isLegalMove(temp) && gameState.isWiningMove(temp, c)) {
                        finalCoOrd[0] = temp[0];
                        finalCoOrd[1] = temp[1];
                        return true;
                    }
                }
            }
            return false;
        };
        if (!selectiveSearch.test(symbol) && !selectiveSearch.test(opponent.symbol)) {
            coOrd = super.makeRandomMove(rndGen);
        }
        return coOrd;

    }


}
