package dev.kaique.luan.chess.Piesces;

import dev.kaique.luan.boardGame.Board;
import dev.kaique.luan.chess.ChessPiece;
import dev.kaique.luan.chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "♚";
    }
}
