package dev.kaique.luan.chess.Moves;

import dev.kaique.luan.boardGame.Board;
import dev.kaique.luan.boardGame.Position;
import dev.kaique.luan.chess.ChessMatch;
import dev.kaique.luan.chess.ChessPiece;
import dev.kaique.luan.chess.Enums.Color;

public interface IMoves {

    boolean[][] testMovesCheck(boolean[][] mat, Board board, Position position, ChessMatch match, Color color);

    boolean[][] AllVertical(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction);
    boolean[][] AllHorizontal(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction);

    boolean[][] AllDiagonalR(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction);
    boolean[][] AllDiagonalL(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction);

    boolean[][] OneVertical(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction);
    boolean[][] OneHorizontal(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction);

    boolean[][] OneDiagonalR(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction);
    boolean[][] OneDiagonalL(boolean[][] mat, Board board, Position position, ChessPiece piece, int Direction);

    boolean[][] KNIGHT(boolean[][] mat, Board board, Position position, ChessPiece piece);
}
