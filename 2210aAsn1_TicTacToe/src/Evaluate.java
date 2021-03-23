//Made by Jordan Avelar
//CS2210a October 18 2020
//Student number 251083623

public class Evaluate {

	//Instance variables
	private char[][] gameBoard;
	private int rows, columns, tiles, level;
	private Dictionary d;
	
	//Evaluate constructor (making each tile on the board)
	public Evaluate (int boardRows, int boardColumns, int tilesNeeded, int maxLevel) {
		rows = boardRows;
		columns = boardColumns;
		tiles = tilesNeeded;
		level = maxLevel;
		
		gameBoard = new char[rows][columns];
		//Setting all tiles as 'g' which is empty
		for(int r=0;r<rows;r++) {
			for(int c=0;c<columns;c++) {
				gameBoard[r][c] = 'g';
			}
		}
	}
	
	
	public Dictionary createDictionary() {
		d = new Dictionary(tiles);
		return d;
	}
	
	//Makes string of all tiles in gameBoard, then checks if it's in the dictionary
	public Data repeatedConfig(Dictionary dict) {
		String fullName = "";
		//Loops through gameBoard to make a full string
		for(int r=0;r<rows;r++) {
			for(int c=0;c<columns;c++) {
				fullName = gameBoard[r][c] + fullName;
			}
		}
		
		if(dict.get(fullName) == null) return null;
		else {
			return dict.get(fullName);
		}
	}
	
	//Makes a string containing the tiles, then places it in dictionary if not already in
	public void insertConfig(Dictionary dict, int score, int level) {
		String fullName = "";
		
		for(int r=0; r<rows; r++) {
			for(int c=0; c<columns; c++) {
				fullName = gameBoard[r][c] + fullName;
			}
		}
		//Try catch to check if key is already in dictionary
		try {
			dict.put(new Data(fullName, score, level));
		}
		catch(DuplicatedKeyException e){
			System.out.println("Duplicate Key Exception while inserting");
		}
	}
	
	public void storePlay(int row, int column, char symbol) {
		gameBoard[row][column] = symbol;
	}
	
	public boolean squareIsEmpty(int row, int col) {
		if(gameBoard[row][col] == 'g') return true;
		
		return false;
	}
	
	public boolean tileOfComputer(int row, int col) {
		if(gameBoard[row][col] == 'o') return true;
		
		return false;
	}
	
	public boolean tileOfHuman(int row, int col) {
		if(gameBoard[row][col] == 'b') return true;
		
		return false;
	}
	
	//Wins checks if either the computer or player win
	public boolean wins(char symbol) {
		boolean win = false;
		int n = 0;
		
		//Check if there's a winning row
		//(tiles is number in a row needed to win)
		for(int r=0;r<rows;r++) {
			n = 0;
			//n counts how many of same tiles in a row
			for(int c=0; c<columns;c++) {
				
				if(gameBoard[r][c] == symbol) {
					n++;
					
					if(n == tiles) {
						win = true;
						break;
					}
				}
				
				else {
					n=0;
				}
			}
		}
		
		//Check if there's a winning column
		for(int c=0;c<columns;c++) {
			n = 0;
			//n counts how many of same tiles in column
			for(int r=0; r<rows;r++) {
				
				if(gameBoard[r][c] == symbol) {
					n++;
					
					if(n == tiles) {
						win = true;
						break;
					}
				}
				
				else {
					n=0;
				}
			}
		}
		
		//Checks if there's a win going from bottom to up right
		for(int c=0;c<columns;c++) {
			for(int r=0;r<rows;r++) {
				
				if(gameBoard[r][c] == symbol) {
					int tempCol = c;
					int tempRow = r;
					int wins = 0;
					
					while((tempCol < columns)&&(tempRow < rows)) {
						if(gameBoard[tempRow][tempCol] == symbol) {
							wins++;
						}
						else {
							wins = 0;
						}
						
						if(wins == tiles) {
							return true;
						}
						else {
							tempCol++;
							tempRow++;
						}
					}
					
				}
			}
		}
		
		//Checks if there's a win going from top to bottom right
		for(int c=0;c<columns;c++) {
			for(int r=0;r<rows;r++) {
				
				if(gameBoard[r][c] == symbol) {
					int tempCol = c;
					int tempRow = r;
					int wins = 0;
					
					while((tempCol < columns)&&(tempRow >= 0)) {
						if(gameBoard[tempRow][tempCol] == symbol) {
							wins++;
						}
						else {
							wins = 0;
						}
						
						if(wins == tiles) {
							return true;
						}
						else {
							tempCol++;
							tempRow--;
						}
					}
					
				}
			}
		}
		
		return win;
		
	}
	
	//Checks if there's a draw yet
	public boolean isDraw() {
		boolean draw = true;
		
		if(wins('b')) {
			draw = false;
		}
		
		if(wins('o')) {
			draw = false;
		}
		//Checks if there's any empty tiles left in the board
		else {
			
			for(int r = 0;r<rows;r++) {
				for(int c = 0;c<columns;c++) {
					
					if(gameBoard[r][c] == 'g') draw = false;
				}
			}
		}
		return draw;
	}
	
	//Returns the current state of the game
	//3 for computer
	//0 for player
	//2 for draw
	//1 for undecided
	public int evalBoard() {
		if (wins('o')){
			return 3;
		}
		else if (wins('b')){
			return 0;
		}
		else if (isDraw()){
			return 2;
		}
		else 
			return 1;
	}
	
}
