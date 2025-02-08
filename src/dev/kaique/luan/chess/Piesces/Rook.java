package dev.kaique.luan.chess.Piesces;

import dev.kaique.luan.boardGame.Board;
import dev.kaique.luan.chess.ChessMatch;
import dev.kaique.luan.chess.Enums.ChessIconPieces;
import dev.kaique.luan.chess.ChessPiece;
import dev.kaique.luan.chess.Enums.Color;
import dev.kaique.luan.chess.Moves.IMoves;
import dev.kaique.luan.chess.Moves.Moves;

public class Rook extends ChessPiece {

    private static IMoves moves = new Moves();

    public Rook(Board board, Color color, ChessMatch match) {
        super(board, color, match);
    }

    @Override
    public String toString() {
        return ChessIconPieces.ROOK.getIcon();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCols()];

        mat = moves.AllHorizontal(mat,getBoard(),position,this, 1);
        mat = moves.AllHorizontal(mat,getBoard(),position,this, -1);
        mat = moves.AllVertical(mat,getBoard(),position,this, 1);
        mat = moves.AllVertical(mat,getBoard(),position,this, -1);

        return mat;
    }

    @Override
    public boolean[][] possibleMovesCheck() {
        boolean[][] mat = possibleMoves();

        mat = moves.testMovesCheck(mat, getBoard(), position, getMatch(), getColor());

        return mat;
    }
}
