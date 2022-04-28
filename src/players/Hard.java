package tictactoe.players;

import tictactoe.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Hard extends Player {


    @Override
    public int[] makeMove() {

        int[] coOrd;
        if (gameState.emptyCellCounter == 9) return super.makeRandomMove(rndGen);

        int[] legalMoves = gameState.getLegalMoves();
        int bestPos = 0;
        int bestPosScore = minMax(legalMoves[bestPos], gameState, true);

        for (int i = 1; i < legalMoves.length; i++) {
            int tempScore = minMax(legalMoves[i], gameState, true);
            if (tempScore > bestPosScore) {
                bestPos = i;
                bestPosScore = tempScore;
            }
        }
        return new int[]{legalMoves[bestPos] / 3, legalMoves[bestPos] % 3};
    }

    public int minMax(int pos, GameState gameState, boolean isThisPlayer) {
        GameState tempGameState = new GameState(gameState.getCopyOfState(), gameState.emptyCellCounter);
        Player nowPlaying;
        BiFunction<Integer, Integer, Integer> getBestLambda;
        if (isThisPlayer) {
            nowPlaying = this;
            getBestLambda = Math::min;
        } else {
            nowPlaying = this.opponent;
            getBestLambda = Math::max;
        }
        int[] coOrd = new int[]{pos / 3, pos % 3};
        if (tempGameState.isWiningMove(coOrd, nowPlaying.symbol)) {
            if (isThisPlayer) {
                return 10;
            } else {
                return -10;
            }
        } else if (tempGameState.emptyCellCounter == 1) {
            return 0;
        } else {
            tempGameState.state[coOrd[0]][coOrd[1]] = nowPlaying.symbol;
            --tempGameState.emptyCellCounter;
            int[] legalMoves = tempGameState.getLegalMoves();
            List<Integer> scoreList = new ArrayList<>();
            for (int i = 0; i < legalMoves.length; i++) {
                scoreList.add(minMax(legalMoves[i], tempGameState, !isThisPlayer));
            }
            int best = scoreList.get(0);
            for (int i = 1; i < scoreList.size(); i++) {
                best = getBestLambda.apply(best, scoreList.get(i));
            }
            return best;
        }
    }
}
