package com.example.supersudoku;

class Board{
	private Cell[] workingBoard;
	private int[] solvedBoard;
	private int size;
	
	public Board(){
		workingBoard = new Cell[81];
		solvedBoard = new int[81];
		size = 9;
		for(int i = 0; i < 81; ++i){
			workingBoard[i] = new Cell();
		}
	}
	
	public Board(int size){
		workingBoard = new Cell[size * size];
		solvedBoard = new int[size * size];
		this.size = size;
		for(int i = 0; i < (size * size); ++i){
			workingBoard[i] = new Cell();
			solvedBoard[i] = 0;
		}
	}
	
	public Board(int size, int[] workingBoard, int[] solvedBoard){
		this.workingBoard = new Cell[size * size];
		this.solvedBoard = new int[size * size];
		this.size = size;
		for(int i = 0; i < size * size; ++i){
			this.workingBoard[i] = new Cell(workingBoard[i]);
			this.solvedBoard[i] = solvedBoard[i];
		}
	}
	
	//loops through to check if the board is solved
	public boolean isSolved(){
		for(int i = 0; i < (size * size); ++i){
			if(this.getSquare(i) != solvedBoard[i]){
				return false;
			}
		}
		return true;
	}
	
	//getter for a cells value, returns solvedBoard's value if the cell is negative.
	public char getSquare(int index){
		int result = workingBoard[index].getActual();
		if(result < 0)
			return (char)(solvedBoard[index] + 48);
		else if(result == 0){
		    return ' ';
        }
		else
			return (char)(result + 48);
	}
	
	//method to set the value of a cell.
	public void setSquare(int index, int value){
		if(workingBoard[index].getActual() >= 0)
			workingBoard[index].setActual(value);
	}
	
	//Work in progress! Code to check if a particular cell's value is valid
	public boolean isValid(int index){  
		int row = index / size;
		int column = index % size;
		int value = this.getSquare(index);
		for(int i = 0; i < size; ++i){
			int checkIndex = row * size + i;
			if(checkIndex == index);
			else if(this.getSquare(checkIndex) == value) return false;
			checkIndex = column + (size * i);
			if(checkIndex == index);
			else if(this.getSquare(checkIndex) == value) return false;
		}
		return false;
	}
	
}

