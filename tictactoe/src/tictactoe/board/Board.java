package tictactoe.board;

import tictactoe.player.PlayingPiece;

public class Board {
    public int size;
    public PlayingPiece[][] board;

    public Board(int size) {
        this.board = new PlayingPiece[size][size];
        this.size = size;
    }

    public boolean addPiece(BoardCell boardCell) {
        if (board[boardCell.row][boardCell.col] != null) {
            return false;
        } else {
            board[boardCell.row][boardCell.col] = boardCell.playingPiece;
            return true;
        }
    }

    public int getSize() {
        return size;
    }
}
