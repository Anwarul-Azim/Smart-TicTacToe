package tictactoe;


import tictactoe.players.Player;

public class Main {
    static void playGame() throws InterruptedException {
        GameSettings settings;
        GameState state;
        while (true) {
            settings = new GameSettings();
            if(settings.isClosing) break;
            state = new GameState();
            int[] coOrd = new int[2];
            state.printState();
            Player[] players = {settings.firstPlayer, settings.secondPlayer};
            players[0].gameState = state;
            players[1].gameState = state;
            int counter = 0;
            Player player;
            while (!state.isGameOver) {
                player = players[counter % 2];
                player.prompt();
                coOrd = player.makeMove();
                state.placeMove(coOrd, player.symbol);
                state.printState();
                state.getEvaluation();
                ++counter;
                Thread.sleep(200);
            }

        }
    }


    public static void main(String[] args) throws InterruptedException {
        playGame();
    }

}
