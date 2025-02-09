package dev.kaique.luan.chess.Moves;

import dev.kaique.luan.boardGame.Board;
import dev.kaique.luan.boardGame.Position;
import dev.kaique.luan.chess.Check.Check;
import dev.kaique.luan.chess.ChessMatch;
import dev.kaique.luan.chess.ChessPiece;
import dev.kaique.luan.chess.Enums.Color;

public class Moves implements IMoves{

    private boolean canMove(Position position, Board board, ChessPiece piece) {
        ChessPiece p = (ChessPiece) board.getPiece(position);
        return p == null || p.getColor() != piece.getColor();
    }

    @Override
    public boolean[][] testMovesCheck(boolean[][] mat, Board board, Position position, ChessMatch match, Color color) {
        for(int i = 0; i < board.getRows(); i++) {
            for(int j = 0; j < board.getCols(); j++) {
                if(mat[i][j]) {
                    Check test = match.performChessMoveMock(position, new Position(i,j));
                    if(test.getCheck() && test.getPlayer() == color) {
                        mat[i][j] = false;
                    }
                }
            }
        }
        return mat;
    }

    @Override
    public boolean[][] AllVertical(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction) {
        Position p = new Position(0,0);
        p.setValues(position.getRow() - Direction, position.getColumn());
        while (board.positionExists(p) && !board.thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() - Direction);
        }
        if (board.positionExists(p) && piece.isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        return mat;
    }

    @Override
    public boolean[][] AllHorizontal(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction) {
        Position p = new Position(0,0);
        p.setValues(position.getRow(), position.getColumn() - Direction);
        while (board.positionExists(p) && !board.thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() - Direction);
        }
        if (board.positionExists(p) && piece.isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        return mat;
    }

    @Override
    public boolean[][] AllDiagonalR(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction) {
        Position p = new Position(0,0);
        p.setValues(position.getRow() - Direction, position.getColumn() + Direction);
        while (board.positionExists(p) && !board.thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() - Direction, p.getColumn() + Direction);
        }
        if (board.positionExists(p) && piece.isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        return mat;
    }

    @Override
    public boolean[][] AllDiagonalL(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction) {
        Position p = new Position(0,0);
        p.setValues(position.getRow() - Direction, position.getColumn() - Direction);
        while (board.positionExists(p) && !board.thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() - Direction, p.getColumn() - Direction);
        }
        if (board.positionExists(p) && piece.isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        return mat;
    }

    @Override
    public boolean[][] OneVertical(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction) {
        Position p = new Position(0,0);
        p.setValues(position.getRow() - Direction, position.getColumn());
        if (board.positionExists(p) && canMove(p,board,piece)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        return mat;
    }

    @Override
    public boolean[][] OneHorizontal(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction) {
        Position p = new Position(0,0);
        p.setValues(position.getRow(), position.getColumn() - Direction);
        if (board.positionExists(p) && canMove(p,board,piece)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        return mat;
    }

    @Override
    public boolean[][] OneDiagonalR(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction) {
        Position p = new Position(0,0);
        p.setValues(position.getRow() - Direction, position.getColumn() + Direction);
        if (board.positionExists(p) && canMove(p,board,piece)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        return mat;
    }

    @Override
    public boolean[][] OneDiagonalL(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction) {
        Position p = new Position(0,0);
        p.setValues(position.getRow() - Direction, position.getColumn() - Direction);
        if (board.positionExists(p) && canMove(p,board,piece)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        return mat;
    }

    @Override
    public boolean[][] KNIGHT(boolean[][] mat, Board board, Position position, ChessPiece piece) {
        Position p = new Position(0,0);
        p.setValues(position.getRow() - 1, position.getColumn() - 2);
        if (board.positionExists(p) && canMove(p,board,piece)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValues(position.getRow() - 2, position.getColumn() - 1);
        if (board.positionExists(p) && canMove(p,board,piece)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValues(position.getRow() - 2, position.getColumn() + 1);
        if (board.positionExists(p) && canMove(p,board,piece)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValues(position.getRow() - 1, position.getColumn() + 2);
        if (board.positionExists(p) && canMove(p,board,piece)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValues(position.getRow() + 1, position.getColumn() + 2);
        if (board.positionExists(p) && canMove(p,board,piece)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValues(position.getRow() + 2, position.getColumn() + 1);
        if (board.positionExists(p) && canMove(p,board,piece)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValues(position.getRow() + 2, position.getColumn() - 1);
        if (board.positionExists(p) && canMove(p,board,piece)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValues(position.getRow() + 1, position.getColumn() - 2);
        if (board.positionExists(p) && canMove(p,board,piece)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }

    @Override
    public boolean[][] PAWN(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction) {
        Position p = new Position(0,0);
        p.setValues(position.getRow() - Direction, position.getColumn());
        if(board.positionExists(p) && !board.thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        p.setValues(position.getRow() - (2 * Direction), position.getColumn());
        Position p2 = new Position(position.getRow() - Direction, position.getColumn());
        if(
            board.positionExists(p)
            && board.positionExists(p2)
            && !board.thereIsAPiece(p)
            && !board.thereIsAPiece(p2)
            && piece.getMoveCount() == 0
        ) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValues(position.getRow() - Direction, position.getColumn() - Direction);
        if(board.positionExists(p) && piece.isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        p.setValues(position.getRow() - Direction, position.getColumn() + Direction);
        if(board.positionExists(p) && piece.isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }


        return mat;
    }
}
