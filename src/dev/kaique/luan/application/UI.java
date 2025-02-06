package dev.kaique.luan.application;

import dev.kaique.luan.boardGame.Position;
import dev.kaique.luan.chess.ChessPiece;
import dev.kaique.luan.chess.ChessPosition;
import dev.kaique.luan.chess.Enums.Color;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_LIGHT_GRAY = "\u001B[90m";

    public static final String ANSI_GRAY_BACKGROUND = "\u001B[100m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner scanner) {
        try {
            String s = scanner.nextLine();
            char col = s.charAt(0);
            int row = Integer.parseInt(s.substring(1 ));
            return new ChessPosition(col, row);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Error reading chess position. Valid values are from a1 to h8.");
        }
    }


    public static void printBoard(ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                String backgroundColor = (i + j) % 2 == 0 ? ANSI_WHITE_BACKGROUND : ANSI_GRAY_BACKGROUND;
                System.out.print(backgroundColor);
                printPiece(pieces[i][j], false, null);
                System.out.print(ANSI_RESET);
            }
            System.out.println();
        }
        System.out.println("   aㅤ bㅤ cㅤ dㅤ eㅤ fㅤ gㅤ h");
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves, ChessPosition source) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                String backgroundColor = (i + j) % 2 == 0 ? ANSI_WHITE_BACKGROUND : ANSI_GRAY_BACKGROUND;
                System.out.print(backgroundColor);
                printPiece(pieces[i][j], possibleMoves[i][j], source);
                System.out.print(ANSI_RESET);
            }
            System.out.println();
        }
        System.out.println("   aㅤ bㅤ cㅤ dㅤ eㅤ fㅤ gㅤ h");
    }

    private static void printPiece(ChessPiece piece, boolean moves, ChessPosition source) {
        if (moves) {
            if (source != null && piece != null && piece.isThereOpponentPiece(source.toPosition())) {
                System.out.print(ANSI_RED_BACKGROUND);
            } else {
                System.out.print(ANSI_BLUE_BACKGROUND);
            }
        }
        if (piece == null) {
            System.out.print(" ㅤ " + ANSI_RESET);
        } else {
            if (piece.getColor() == Color.BLACK) {
                System.out.print(ANSI_BLACK + " " + piece + " " + ANSI_RESET);
            } else {
                System.out.print(" " + piece + " ");
            }
        }
    }
}
