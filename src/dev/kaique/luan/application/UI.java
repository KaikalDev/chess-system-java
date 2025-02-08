package dev.kaique.luan.application;

import dev.kaique.luan.boardGame.Position;
import dev.kaique.luan.chess.Check.Check;
import dev.kaique.luan.chess.ChessMatch;
import dev.kaique.luan.chess.ChessPiece;
import dev.kaique.luan.chess.ChessPosition;
import dev.kaique.luan.chess.Enums.Color;
import dev.kaique.luan.chess.Piesces.King;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> capturedPieces) {
        printBoard(chessMatch.getPieces(), chessMatch.getCheck());
        System.out.println();
        printCapturedpiece(capturedPieces);
        System.out.println();
        System.out.println("Turn: " + chessMatch.getTurn());
        System.out.println("Waiting player: " + chessMatch.getcurrentPlayer());
    }

    public static void printBoard(ChessPiece[][] pieces, Check check) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                String backgroundColor = (i + j) % 2 == 0 ? ANSI_WHITE_BACKGROUND : ANSI_GRAY_BACKGROUND;
                System.out.print(backgroundColor);
                printPiece(pieces[i][j], false, null, check);
                System.out.print(ANSI_RESET);
            }
            System.out.println();
        }
        System.out.println("   aㅤ bㅤ cㅤ dㅤ eㅤ fㅤ gㅤ h");
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves, ChessPosition source, Check check) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                String backgroundColor = (i + j) % 2 == 0 ? ANSI_WHITE_BACKGROUND : ANSI_GRAY_BACKGROUND;
                System.out.print(backgroundColor);
                printPiece(pieces[i][j], possibleMoves[i][j], source, check);
                System.out.print(ANSI_RESET);
            }
            System.out.println();
        }
        System.out.println("   aㅤ bㅤ cㅤ dㅤ eㅤ fㅤ gㅤ h");
    }

    private static void printPiece(ChessPiece piece, boolean moves, ChessPosition source, Check check) {
        if (moves) {
            if (source != null && piece != null && piece.isThereOpponentPiece(source.toPosition())) {
                System.out.print(ANSI_RED_BACKGROUND);
            } else {
                System.out.print(ANSI_BLUE_BACKGROUND);
            }
        }
        if (check != null && piece instanceof King) {
            assert source != null;
            if (check.getPlayer() == piece.getColor() && check.getCheck()) {
                System.out.print(ANSI_RED_BACKGROUND);
            }
        }


        if (source != null && piece != null) {
            if(source.equals(piece.getChessPosition())) {
                System.out.print(ANSI_YELLOW_BACKGROUND);
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

    private static void printCapturedpiece(List<ChessPiece> capturaed) {
        System.out.println("Captured pieces: ");
        System.out.print(ANSI_GRAY_BACKGROUND + ANSI_GRAY_BACKGROUND);
        System.out.print("White: ");
        System.out.println(Arrays.toString(capturaed.stream().filter(p -> p.getColor() == Color.WHITE).toArray()) + ANSI_RESET);
        System.out.print(ANSI_BLACK + ANSI_WHITE_BACKGROUND);
        System.out.print("Black: ");
        System.out.println(Arrays.toString(capturaed.stream().filter(p -> p.getColor() == Color.BLACK).toArray()) + ANSI_RESET);
    }
}
