package dev.kaique.luan.chess.Enums;

public enum ChessIconPieces {
    KING("♚"),
    QUEEN("♛"),
    ROOK("♜"),
    BISHOP("♝"),
    KNIGHT("♞"),
    PAWN("♟");

    private final String icon;

    ChessIconPieces(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}

