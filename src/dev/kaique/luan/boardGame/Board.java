package dev.kaique.luan.boardGame;

import dev.kaique.luan.Exceptions.BoardException;

public class Board {

    private final int rows;
    private final int cols;
    private Piece[][] pieces;

    public Board(int rows, int cols) {
        if (rows < 1 || cols < 1) {
            throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
        }
        this.rows = rows;
        this.cols = cols;
        pieces = new Piece[rows][cols];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Piece getPiece(int row, int col) {
        if(!positionExists(row, col)) {
            throw new BoardException("position not on the board");
        }
        return pieces[row][col];
    }

    public Piece getPiece(Position position) {
        if(!positionExists(position)) {
            throw new BoardException("position not on the board");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position) {
        if(thereIsAPiece(position)) {
            throw new BoardException("there is already a piece on position" + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position) {
        if(!positionExists(position)) {
            throw new BoardException("position not on the board");
        }
        if(getPiece(position) == null) {
            return null;
        }
        Piece aux = getPiece(position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;

    }

    private boolean positionExists(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position) {
        if(!positionExists(position)) {
            throw new BoardException("position not on the board");
        }
        return getPiece(position) != null;
    }

}
