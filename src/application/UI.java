package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {
	
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	
	// Regular
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	// Background
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	// Bold
    public static final String BLACK_BOLD = "\033[1;30m";  
    public static final String RED_BOLD = "\033[1;31m";    
    public static final String GREEN_BOLD = "\033[1;32m";  
    public static final String YELLOW_BOLD = "\033[1;33m"; 
    public static final String BLUE_BOLD = "\033[1;34m";   
    public static final String PURPLE_BOLD = "\033[1;35m"; 
    public static final String CYAN_BOLD = "\033[1;36m";   
    public static final String WHITE_BOLD = "\033[1;37m";  
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
			
		} catch(RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
		}
		
	}
	
	public static void printMatch(ChessMatch chessMatch) {
		printBoard(chessMatch.getPieces());
		System.out.println("\nTurn: " + chessMatch.getTurn());
		System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
	}
	
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i=0; i < pieces.length; i++) {
			System.out.print((8-i) + " ");
			
			for (int j=0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i=0; i < pieces.length; i++) {
			System.out.print((8-i) + " ");
			
			for (int j=0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			System.out.print(ANSI_GREEN_BACKGROUND);
		}
    	if (piece == null) {
            System.out.print("-" + ANSI_RESET);
            
        } else if (piece.getColor() == Color.WHITE) {
            System.out.print(ANSI_WHITE + piece + ANSI_RESET);
        } else {
            System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
        }
        System.out.print(" ");
	}
	
}
