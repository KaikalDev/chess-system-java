package dev.kaique.luan.chess;

import dev.kaique.luan.Exceptions.ChessException;
import dev.kaique.luan.boardGame.Position;

public class ChessPosition {

    private final char col;
    private final int row;

    public ChessPosition(char col, int row) {
        if (col < 'a' || col > 'h' || row < 1 || row > 8) {
            throw new ChessException("Error instantiating ChessPosition. valid values are from a1 to h8");
        }
        this.col = col;
        this.row = row;
    }

    public char getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Position toPosition() {
        return new Position(8 - row, col - 'a');
    }

    protected static ChessPosition fromPosition(Position position) {
        return new ChessPosition((char) ('a' + position.getColumn()), 8 - position.getRow());
    }

    @Override
    public String toString() {
        return "" + col + row;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ChessPosition other = (ChessPosition) obj;
        return this.col == other.col && this.row == other.row;
    }
}
