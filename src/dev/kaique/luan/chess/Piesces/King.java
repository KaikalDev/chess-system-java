package dev.kaique.luan.chess.Piesces;

import dev.kaique.luan.boardGame.Board;
import dev.kaique.luan.boardGame.Position;
import dev.kaique.luan.chess.Enums.ChessIconPieces;
import dev.kaique.luan.chess.ChessPiece;
import dev.kaique.luan.chess.Enums.Color;
import dev.kaique.luan.chess.Moves.IMoves;
import dev.kaique.luan.chess.Moves.Moves;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    private static IMoves moves = new Moves();

    @Override
    public String toString() {
        return ChessIconPieces.KING.getIcon();
    }

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p == null || p.getColor() != getColor();
    }


    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCols()];

        mat = moves.OneDiagonalL(mat, getBoard(), position, this, 1);
        mat = moves.OneDiagonalL(mat, getBoard(), position, this, -1);
        mat = moves.OneDiagonalR(mat, getBoard(), position, this, 1);
        mat = moves.OneDiagonalR(mat, getBoard(), position, this, -1);
        mat = moves.OneHorizontal(mat, getBoard(), position, this, 1);
        mat = moves.OneHorizontal(mat, getBoard(), position, this, -1);
        mat = moves.OneVertical(mat, getBoard(), position, this, 1);
        mat = moves.OneVertical(mat, getBoard(), position, this, -1);

        return mat;
    }
}
