package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameState {
    public int emptyCellCounter;
    public boolean isGameOver;
    public char[][] state;

    public int[] lastMove;
    public char lastMoveSymbol;



    public GameState() {
        emptyCellCounter = 9;
        state = new char[3][3];
    }

    public GameState(char[][] state, int emptyCellCounter) {
        this.state = state;
        this.emptyCellCounter = emptyCellCounter;
    }


    public boolean isLegalMove(int[] coOrd) {
        return (this.state[coOrd[0]][coOrd[1]] != 'X'
                && this.state[coOrd[0]][coOrd[1]] != 'O');
    }

    public void placeMove(int[] coOrd, char symbol) {
        lastMove = coOrd;
        lastMoveSymbol = symbol;
        state[lastMove[0]][lastMove[1]] = symbol;
        --emptyCellCounter;
        System.out.println(lastMoveSymbol + " at " + (lastMove[0] + 1) + " " + (lastMove[1] + 1));
    }

    public boolean isWiningMove(int[] coOrd, char symbol) {
        int row = coOrd[0];
        int column = coOrd[1];
        boolean winAtFirstDiagonal = (row == column && areEqual(symbol, state[(row + 1) % 3][(column + 1) % 3], state[(row + 2) % 3][(column + 2) % 3]));
        boolean winAtSecondDiagonal = row + column == 2 && ((row != 1 && areEqual(symbol, state[column][row], state[1][1])) || (row == 1 && areEqual(state[2][0], symbol, state[0][2])));
        boolean winAtColumn = areEqual(symbol, state[(row + 1) % 3][column], state[(row + 2) % 3][column]);
        boolean winAtRow = areEqual(symbol, state[row][(column + 1) % 3], state[row][(column + 2) % 3]);
        if (winAtFirstDiagonal || winAtSecondDiagonal || winAtRow || winAtColumn) {
            return true;
        } else {
            return false;
        }
    }

    private boolean areEqual(char a, char b, char c) {
        return a == b && b == c;
    }


    public void getEvaluation() {
        if (isWiningMove(lastMove, lastMoveSymbol)) {
            isGameOver = true;
            System.out.print(lastMoveSymbol + " wins\n");
        } else if (emptyCellCounter == 0) {
            isGameOver = true;
            System.out.print("Draw\n");
        }
    }

    public int[] getLegalMoves() {
        List<Integer> legalMovesList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            int[] temp = new int[]{i / 3, i % 3};
            if (isLegalMove(temp)) {
                legalMovesList.add(i);
            }
        }
        int[] legalMoves = new int[legalMovesList.size()];
        for (int i = 0; i < legalMoves.length; i++) {
            legalMoves[i] = legalMovesList.get(i);
        }
        return legalMoves;
    }


    public void printState() {
        String margin = "-".repeat(9);
        System.out.println(margin);
        for (int i = 0; i < 3; i++) {
            System.out.print('|');
            for (int j = 0; j < 3; j++) {
                if (Character.isAlphabetic(state[i][j])) {
                    System.out.print(" " + state[i][j]);
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println(" |");
        }
        System.out.println(margin);
    }

    public char[][] getCopyOfState() {
        char[][] copy = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(state[i], 0, copy[i], 0, 3);
        }
        return copy;
    }
}

