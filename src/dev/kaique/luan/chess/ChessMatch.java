package dev.kaique.luan.chess;

import dev.kaique.luan.Exceptions.ChessException;
import dev.kaique.luan.boardGame.Board;
import dev.kaique.luan.boardGame.Piece;
import dev.kaique.luan.boardGame.Position;
import dev.kaique.luan.chess.Check.Check;
import dev.kaique.luan.chess.Enums.Color;
import dev.kaique.luan.chess.Piesces.King;
import dev.kaique.luan.chess.Piesces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    private final Board board;
    private Check check;

    private List<Piece> piecesOnTheBoard;
    private List<Piece> capturedPieces;

    public ChessMatch() {
        board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.WHITE;
        check = new Check(false, currentPlayer);
        piecesOnTheBoard = new ArrayList<Piece>();
        capturedPieces = new ArrayList<Piece>();
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getcurrentPlayer() {
        return currentPlayer;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private Color opponent(Color color) {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color) {
        List<Piece> list = piecesOnTheBoard.stream().filter(
                p -> ( (ChessPiece) p ).getColor() == color).collect(Collectors.toList()
        );
        for (Piece piece : list) {
            if (piece instanceof King) {
                return (ChessPiece) piece;
            }
        }
        throw new IllegalStateException("There is no " + color + " king on the board");
    }

    private boolean testCheck(Color color) {
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(
                p -> ((ChessPiece)p).getColor() == opponent(color)).collect(Collectors.toList()
        );
        for (Piece p : opponentPieces) {
            boolean[][] mat = p.possibleMoves();
            if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
                return true;
            }
        }
        return false;
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getCols()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                mat[i][j] = (ChessPiece) board.getPiece(i, j);
            }
        }
        return mat;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.getPiece(position).possibleMovesCheck();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validatetargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);

        if(testCheck(currentPlayer)) {
            undoMove(source,target,capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }

        if(testCheck(opponent(currentPlayer))) {
            check.updateCheck(true, opponent(currentPlayer));
        } else {
            check.updateCheck(false, null);
        }

        nextTurn();

        return (ChessPiece) capturedPiece;
    }

    public Check performChessMoveMock(Position source, Position target) {
        Piece capturedPiece = makeMove(source, target);

        Check checkMock = new Check(false, null);
        if(testCheck(currentPlayer)) {
            checkMock.updateCheck(true, currentPlayer);
        } else {
            checkMock.updateCheck(false, null);
        }

        undoMove(source, target, capturedPiece);

        return checkMock;
    }

    private void validatetargetPosition(Position source, Position target) {
        if(!board.getPiece(source).possibleMoves(target)) {
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturedPiece) {
        Piece p = board.removePiece(target);
        board.placePiece(p, source);
        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            piecesOnTheBoard.add(capturedPiece);
            capturedPieces.remove(capturedPiece);
        }
    }

    private void validateSourcePosition(Position position) {
        if(!board.thereIsAPiece(position)) {
            throw new ChessException("There is no piece on source position");
        }
        if(currentPlayer != ((ChessPiece) board.getPiece(position)).getColor()) {
            throw new ChessException("The chosen piece is not yours");
        }
        if (!board.getPiece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible moves for the chosen piece");
        }
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup() {
        placeNewPiece('a', 1, new Rook(board, Color.WHITE, this));
        placeNewPiece('b', 1, new Rook(board, Color.WHITE, this));
        placeNewPiece('c', 1, new Rook(board, Color.WHITE, this));
        placeNewPiece('d', 1, new Rook(board, Color.WHITE, this));
        placeNewPiece('e', 1, new King(board, Color.WHITE, this));
        placeNewPiece('f', 1, new Rook(board, Color.WHITE, this));
        placeNewPiece('g', 1, new Rook(board, Color.WHITE, this));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE, this));
        placeNewPiece('a', 2, new Rook(board, Color.WHITE, this));
        placeNewPiece('b', 2, new Rook(board, Color.WHITE, this));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE, this));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE, this));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE, this));
        placeNewPiece('f', 2, new Rook(board, Color.WHITE, this));
        placeNewPiece('g', 2, new Rook(board, Color.WHITE, this));
        placeNewPiece('h', 2, new Rook(board, Color.WHITE, this));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK, this));
        placeNewPiece('b', 8, new Rook(board, Color.BLACK, this));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK, this));
        placeNewPiece('d', 8, new Rook(board, Color.BLACK, this));
        placeNewPiece('e', 8, new King(board, Color.BLACK, this));
        placeNewPiece('f', 8, new Rook(board, Color.BLACK, this));
        placeNewPiece('g', 8, new Rook(board, Color.BLACK, this));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK, this));
        placeNewPiece('a', 7, new Rook(board, Color.BLACK, this));
        placeNewPiece('b', 7, new Rook(board, Color.BLACK, this));
        placeNewPiece('c', 7, new Rook(board, Color.BLACK, this));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK, this));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK, this));
        placeNewPiece('f', 7, new Rook(board, Color.BLACK, this));
        placeNewPiece('g', 7, new Rook(board, Color.BLACK, this));
        placeNewPiece('h', 7, new Rook(board, Color.BLACK, this));
    }
}
