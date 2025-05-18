package tictactoe.board;

import tictactoe.player.PlayingPiece;

public class BoardCell {
    public int row;
    public int col;
    public PlayingPiece playingPiece;

    public BoardCell(int row, int col, PlayingPiece playingPiece) {
        this.row = row;
        this.col = col;
        this.playingPiece = playingPiece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public PlayingPiece getPlayingPiece() {
        return playingPiece;
    }
}

