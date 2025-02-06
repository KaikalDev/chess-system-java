package dev.kaique.luan.boardGame;

public class Piece {
    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
    }

    protected Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
