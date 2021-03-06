package chess;
import java.util.*;

public class Game
{
	private static boolean gameEnd=false;

	//Accepts Coordinate Inputs --Helper
	private static String inputCoordinates(){
		Scanner keyboardString = new Scanner(System.in);
		CheckInput checker = new CheckInput();
		String coordinates = keyboardString.next().toUpperCase();
		while (!checker.checkCoordinateValidity(coordinates)){
			System.out.println("Invalid entry. Entry must be in the range [1-8][a-h]");
			coordinates = keyboardString.next().toUpperCase();
		}
		return coordinates;
	}

	//Turn Management // Validates Origin Input --Helper
	private static boolean isValidOrigin(String originIn, boolean blackToPlayIn){

		int i0 = (int)originIn.charAt(0) - 49;
		int j0 = (int)originIn.charAt(1) - 65;

		if (!Board.hasPiece(i0, j0)){
			 System.out.println("Square is empty");
		}
		else if (blackToPlayIn && Board.getPiece(i0, j0).getColour() != PieceColour.BLACK){
			System.out.println("Invalid Square: Black to move.");
		}
		else if (!blackToPlayIn && Board.getPiece(i0, j0).getColour() != PieceColour.WHITE){
			System.out.println("Invalid Square: White to move.");
		}
		else {
			return true;
		}
		return false;
	}

	//Prints Turn --Helper
	private static boolean displayTurn(Piece pIn){
		if(pIn.getColour() == PieceColour.WHITE){
			System.out.println("*** BLACK TO PLAY ***");
			return true;
		}
		else {
			System.out.println("*** WHITE TO PLAY ***");
			return false;
		}
	}

	//This method requires your input
	public static void play(){
		Scanner keyboardString = new Scanner(System.in);
		final int charUnicodeA = 65; //used to translate unicode characters a-zA-Z's ASCII values to index value
		final int intUnicode1 = 49; //used to translate unicode characters 1-8's ASCII values to index value
		boolean blackToPlay = false;
		boolean checkmate;
		Piece p, q;
		int i0, j0, i1, j1, moveNumber;
		String origin, destination;
		System.out.println("*** WHITE TO PLAY ***");

		while (!gameEnd){

			System.out.println("Enter origin:");
			origin = inputCoordinates();
			if (origin.equals("END")){
				System.out.println("Goodbye!");
				break;
			}
			while (!isValidOrigin(origin, blackToPlay)){
				origin = inputCoordinates();
			}
			i0 = (int)origin.charAt(0) - intUnicode1;
			j0 = (int)origin.charAt(1) - charUnicodeA;
			p = Board.getPiece(i0, j0);

		System.out.println("Enter destination");
		destination = inputCoordinates();
		if (destination.equals("END")){
			System.out.println("Goodbye!");
			break;
		}
		i1 = (int)destination.charAt(0) - intUnicode1;
		j1 = (int)destination.charAt(1) - charUnicodeA;
		q = Board.getPiece(i1, j1);

		while (!p.isLegitMove(i0, j0, i1, j1)) {
			destination = inputCoordinates();
			}
			i1 = (int)destination.charAt(0) - intUnicode1;
			j1 = (int)destination.charAt(1) - charUnicodeA;
			q = Board.getPiece(i1, j1);

			//Implement changes
			checkmate = Board.movePiece(i0, j0, i1, j1, p);
			if (checkmate){
				break;
			}
			Board.printBoard();
			blackToPlay = displayTurn(p);
		}
	}
	//This method should not be edited
	public static void main (String args[]){
		Board.initialiseBoard();
		Board.initialisePieces();
		Board.printBoard();
		Game.play();
	}
}
