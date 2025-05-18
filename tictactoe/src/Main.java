import tictactoe.board.Board;
import tictactoe.game.Game;
import tictactoe.player.PieceType;
import tictactoe.player.Player;
import tictactoe.player.PlayingPiece;


import java.util.LinkedList;
import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome to TicTacToe Game!");

        int boardSize = 3;
        int playersCount = boardSize-1;
        Queue<Player> players = new LinkedList<>();

        if (playersCount > PieceType.values().length) {
            System.out.printf("Maximum number of players supported is %d\n", PieceType.values().length);
            return;
       }
        // Initialise players list
        for (int i = 0; i < playersCount; i++) {
            Player player = new Player(new PlayingPiece(PieceType.values()[i]));
            players.add(player);
        }
        // Build the game object
        Game newGame = Game.builder().withBoard(new Board(boardSize)).addPlayers(players).build();
        // start game
        newGame.startGame();

    }
}