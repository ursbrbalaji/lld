package tictactoe.game;

import tictactoe.board.Board;
import tictactoe.board.BoardCell;
import tictactoe.player.PieceType;
import tictactoe.player.Player;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class Game {
    // Builder Pattern immutable attributes
    private Board board;
    private Queue<Player> players;
    private int rowCount[][];
    private int colCount[][];
    private int diaCount[];
    private int aDiaCount[];

    private Game() {
    }

    public static Builder builder() {
        return new Builder();
    }

    // Getters
    public Board getBoard() {
        return board;
    }

    public Queue<Player> getPlayers() {
        return players;
    }

    // start game
    public void startGame() {

        int boardSize = this.board.getSize();
        int boardMaxIndex = boardSize * boardSize;
        int addedPiecesCount = 0;
        // Run until there is a winner or game ends in draw
        while (true) {
            // Get the first player in the queue
            Player player = this.players.peek();
            // make move
            int index = new Random().nextInt(boardMaxIndex);
            int row = index / boardSize;
            int col = index % boardSize;
            // Board Cell
            BoardCell playerMove = new BoardCell(row, col, player.getPlayingPiece());
            if (!this.board.addPiece(playerMove)) {
                // Cannot add piece as the index is already taken so continue
                continue;
            }
            addedPiecesCount++;
            // Move player to the end as player was able to make a successful move
            player = this.players.remove();
            this.players.add(player);

            System.out.printf("Turn: %d player : %s\n", addedPiecesCount, player.getPlayingPiece().getPieceType().toString());
            System.out.printf("row: %d col: %d\n", row, col);
            // check for winner
            if (this.isWinner(playerMove)) {
                System.out.printf("Player %s Wins!", player.getPlayingPiece().getPieceType().toString());
                return;
            }
            // game ends in draw
            if (boardMaxIndex == addedPiecesCount) {
                System.out.println("Game ends in a Draw!!");
                return;
            }
        }
    }

    // Check if player isWinner
    // TC O(1) SC O(N)
    private boolean isWinner(BoardCell lastMove) {

        ArrayList<Integer> counts = new ArrayList<>();
        int playerIndex = PieceType.valueOf(lastMove.getPlayingPiece().getPieceType().toString()).ordinal();

        // increment row count for player
        this.rowCount[playerIndex][lastMove.getRow()]++;
        counts.add(this.rowCount[playerIndex][lastMove.getRow()]);

        // increment col count for player
        this.colCount[playerIndex][lastMove.getCol()]++;
        counts.add(this.colCount[playerIndex][lastMove.getCol()]);

        if (lastMove.getRow() == lastMove.getCol()) {
            // increment dia count for player
            this.diaCount[playerIndex]++;
            counts.add(this.diaCount[playerIndex]);
        }

        if (lastMove.getRow() + lastMove.getCol() == this.board.getSize()) {
            // increment dia count for player
            this.aDiaCount[playerIndex]++;
            counts.add(this.aDiaCount[playerIndex]);
        }

        // Found the winner
        return counts.contains(this.board.getSize());
    }

    // Builder class
    public static class Builder {
        private Board board;
        private Queue<Player> players;

        public Builder withBoard(Board board) {
            this.board = board;
            return this;
        }

        public Builder addPlayers(Queue<Player> players) {
            this.players = players;
            return this;
        }

        public Builder addPlayer(Player player) {
            this.players.add(player);
            return this;
        }

        public Game build() {
            // Raise Exception when No players are added
            if (this.players.isEmpty()) {
                throw new RuntimeException();
            }

            Game game = new Game();
            game.board = this.board;
            game.players = this.players;

            // Board Size
            int boardSize = board.getSize();
            // # of players
            int noOfPlayers = boardSize-1;

            // initialise counts to check for winner in O(1)
            game.rowCount = new int[noOfPlayers][boardSize];
            game.colCount = new int[noOfPlayers][boardSize];
            game.diaCount = new int[noOfPlayers];
            game.aDiaCount = new int[noOfPlayers];

            return game;
        }
    }
}

