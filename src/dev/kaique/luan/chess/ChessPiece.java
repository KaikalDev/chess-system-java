package dev.kaique.luan.chess;

import dev.kaique.luan.boardGame.Board;
import dev.kaique.luan.boardGame.Piece;
import dev.kaique.luan.boardGame.Position;
import dev.kaique.luan.chess.Enums.Color;
import dev.kaique.luan.chess.Moves.IMoves;
import dev.kaique.luan.chess.Moves.Moves;

import java.util.List;

public abstract class ChessPiece extends Piece {

    private Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p != null && p.getColor() != color;
    }
}
