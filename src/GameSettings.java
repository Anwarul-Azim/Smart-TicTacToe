package tictactoe;

import tictactoe.players.*;

import java.util.*;

public class GameSettings {
    public Player firstPlayer;
    public Player secondPlayer;
    public boolean isClosing;


    public GameSettings() {
        takeInput();
    }
    public void takeInput() {
        System.out.println("Input command:");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] commands = s.split(" ");

        if (commands[0].equals("exit")) {
            isClosing = true;
        } else {
            try {
                firstPlayer = getInstance(commands[1]);
                secondPlayer = getInstance(commands[2]);

                firstPlayer.symbol = 'X';
                firstPlayer.level = commands[1];
                firstPlayer.rndGen = new Random();
                firstPlayer.opponent = secondPlayer;


                secondPlayer.symbol = 'O';
                secondPlayer.level = commands[2];
                secondPlayer.rndGen = new Random();
                secondPlayer.opponent = firstPlayer;

            } catch (Exception e) {
                System.out.println("Bad parameters!");
                takeInput();
            }
        }
    }
    public Player getInstance(String name) {
        Player player;
        switch (name) {
            case "easy" :
                player = new Easy();
                break;
            case "medium" :
                player = new Medium();
                break;
            case "hard" :
                player = new Hard();
                break;
            case "user" :
                player = new User();
                break;
            default:
                player = null;
        }
        return player;
    }

}
