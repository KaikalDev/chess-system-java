package dev.kaique.luan.boardGame;

public abstract class Piece {
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

    public abstract boolean[][] possibleMoves();
    public abstract boolean[][] possibleMovesCheck();

    public boolean possibleMoves(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove() {
        boolean[][] mat  = possibleMovesCheck();
        for (boolean[] booleans : mat) {
            for (int j = 0; j < mat.length; j++) {
                if (booleans[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
