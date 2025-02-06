package dev.kaique.luan.chess;

import dev.kaique.luan.boardGame.Board;
import dev.kaique.luan.boardGame.Piece;

public class ChessPiece extends Piece {

    private Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
