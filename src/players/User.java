package tictactoe.players;

import java.util.Scanner;

public class User extends Player {
    @Override
    public void prompt() {
        System.out.println("Enter the coordinates:");
    }
    @Override
    public int[] makeMove() {
        String move = new Scanner(System.in).nextLine();
        if (move.length() > 3) {
            System.out.println("You should enter numbers!");
            return makeMove();
        }
        int[] coOrd = new int[2];
        try {
            coOrd[0] = Integer.parseInt(move.charAt(0) + "") - 1;
            coOrd[1] = Integer.parseInt(move.charAt(2) + "") - 1;
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            return makeMove();
        }
        if (coOrd[0] < 0 || coOrd[0] > 2 || coOrd[1] < 0 || coOrd[1] > 2) {
            System.out.println("Coordinates should be from 1 to 3!");
            return makeMove();
        }
        if (!gameState.isLegalMove(coOrd)) {
            System.out.println("This cell is occupied! Choose another one!");
            return makeMove();
        }
        return coOrd;
    }

}
