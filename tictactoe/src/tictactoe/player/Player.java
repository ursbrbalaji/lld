package tictactoe.player;

public class Player {
    public PlayingPiece playingPiece;

    public Player(PlayingPiece playingPiece) {
        this.playingPiece = playingPiece;
    }

    public PlayingPiece getPlayingPiece() {
        return playingPiece;
    }
}
