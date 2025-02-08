package dev.kaique.luan.chess;

import dev.kaique.luan.boardGame.Board;
import dev.kaique.luan.boardGame.Piece;
import dev.kaique.luan.boardGame.Position;
import dev.kaique.luan.chess.Enums.Color;

public abstract class ChessPiece extends Piece {

    private final Color color;
    private final ChessMatch match;

    public ChessPiece(Board board, Color color, ChessMatch match) {
        super(board);
        this.color = color;
        this.match = match;
    }

    public Color getColor() {
        return color;
    }

    public ChessMatch getMatch() {
        return match;
    }

    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(position);
    }

    public boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p != null && p.getColor() != color;
    }
}
