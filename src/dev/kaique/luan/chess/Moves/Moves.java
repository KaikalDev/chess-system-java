package dev.kaique.luan.chess.Moves;

import dev.kaique.luan.boardGame.Board;
import dev.kaique.luan.boardGame.Position;
import dev.kaique.luan.chess.ChessPiece;

public class Moves implements IMoves{

    private boolean canMove(Position position, Board board, ChessPiece piece) {
        ChessPiece p = (ChessPiece) board.getPiece(position);
        return p == null || p.getColor() != piece.getColor();
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
        return new boolean[0][];
    }

    @Override
    public boolean[][] AllDiagonalL(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction) {
        return new boolean[0][];
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
        return new boolean[0][];
    }
}
