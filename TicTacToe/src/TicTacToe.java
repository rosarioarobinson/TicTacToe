
/*
 * This project is for practice purposes only. 
 * Rosario A. Robinson
 * This project is from Alex Lee on YouTube: https://www.youtube.com/watch?v=gQb3dE-y1S4 
 */

// imports
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	// Making global method so all the methods know it well 
	// Make it static so an object every time to access it is not necessary 
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	

	// Main method
	public static void main(String[] args) {
		
		// Create a 2D array with all the symbols needed for tic-tac-toe
		// Includes the characters needed to create board
		 
		char [][] gameBoard = { {' ', '|', ' ', '|', ' '}, 
								 {'-', '+', '-', '+', '-'},
								 {' ', '|', ' ', '|', ' '},
								 {'-', '+', '-', '+', '-'},
								 {' ', '|', ' ', '|', ' '},};
		
		// Call method to print game board and run
		printGameBoard(gameBoard);
		
		
		// Creating while loop so computer listens forever
		while(true) {
			
			// Create scanner to get an input from the user
			Scanner scan = new Scanner(System.in);
			
			// Asking user for a number 1 through 9
			System.out.println("Enter your placement (1-9):");
			int playerPos = scan.nextInt();
			
			// while loop that lets user or computer know the position is taken
			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)) {
				System.out.println("position taken! Enter a correct position!");
				playerPos = scan.nextInt();
			}
					
			// Call method for user to place piece on the game board
			placePiece(gameBoard, playerPos, "player");
			
			// Create string to call for checkWinner
			String result = checkWinner();
			if(result.length() > 0) {
				 System.out.println(result);
				 break;
			 }
			
			// To create illusion of a 'computer' playing, we will add in randomizing
			Random rand = new Random();
			int cpuPos = rand.nextInt(9) + 1;
			
			// while loop that lets user or computer know the position is taken
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				cpuPos = rand.nextInt(9) + 1;
			}
			
			// Call method for computer to place piece on the game board
			placePiece(gameBoard, cpuPos, "cpu");
			
			// Call method to print game board and run
			printGameBoard(gameBoard);
			
			// Call method to check who won the board
			// Print out string that checks for winner
			 result = checkWinner();
			 if(result.length() > 0) {
				 System.out.println(result);
				 break;
			 }
			
		}
		
		
	}
	
	// Call in char gameBoard in argument to pass printGameBoard
	public static void printGameBoard(char [] [] gameBoard) {
		// Print out game board using two for loops


	for(char[] row : gameBoard) {
						for(char c :row) {
							System.out.print(c);
						}
						
						System.out.println();
					}
	}
	
	// Create method for switch case to insert user placement on the board
	// Pass in int pos so method will know location and string user so the computer or player can change a position to X or O
	public static void placePiece(char [] [] gameBoard, int pos, String user) {
		
		char symbol = ' ';
		
		// If the user is a player then it'll be X
		// If the user is the computer then it'll be O
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		} else if(user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		

		// Using a switch case here to go through each position on the board
		// Reminder: an array starts with a row
		switch(pos) {
			case 1:
				gameBoard [0] [0] = symbol;
				break;
			case 2:
				gameBoard [0] [2] = symbol;
				break;
			case 3:
				gameBoard [0] [4] = symbol;
				break;
			case 4:
				gameBoard [2] [0] = symbol;
				break;
			case 5:
				gameBoard [2] [2] = symbol;
				break;
			case 6:
				gameBoard [2] [4] = symbol;
				break;
			case 7:
				gameBoard [4] [0] = symbol;
				break;
			case 8:
				gameBoard [4] [2] = symbol;
				break;
			case 9:
				gameBoard [4] [4] = symbol;
				break;
			default:
				break;
		}
		
	}
	
	// Create a check winner method
	public static String checkWinner() {
		
		// List of arrays for each row and column
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(7, 5, 3);
		
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);

		// Create a loop for lists
		for(List l: winning) {
			if(playerPositions.containsAll(l)) {
				return "Congratulations you won!";
			} else if(cpuPositions.containsAll(l)) {
				return "CPU wins! Sorry :(";	
			} else if (playerPositions.size() + cpuPositions.size() == 9) {
				return "CAT!";
			}
		}
		
		return "";
	}

}
