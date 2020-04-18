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

	//code to take a partial board as input
	public Board(int[] partBoard){
	    size = 9;
	    this.workingBoard = new Cell[size * size];
	    this.solvedBoard = new int[size * size];
	    for(int i = 0; i < size * size; ++i){
	        if(partBoard[i] != 0){
	            workingBoard[i] = new Cell(-1);
	            solvedBoard[i] = partBoard[i];
            }
	        else{
	            workingBoard[i] = new Cell(0);
	            solvedBoard[i] = partBoard[i];
            }
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
	    if(!isComplete())
	        return false;
		for(int i = 0; i < (size * size); ++i){
			if(workingBoard[i].getActual() == -1)
			    continue;
			if(!isValid(i))
			    return false;
		}
		return true;
	}

	public boolean isComplete(){
	    for(int i = 0; i < size * size; ++i){
	        if(workingBoard[i].getActual() == 0)
	            return false;
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

	//internal getter for a cell's numerical value
	private int getValue(int index){
		if(workingBoard[index].getActual() <= -1)
			return solvedBoard[index];
		else
			return workingBoard[index].getActual();
	}
	
	//method to set the value of a cell.
	public void setSquare(int index, int value){
		if(workingBoard[index].getActual() >= 0)
			workingBoard[index].setActual(value);
	}

	public boolean isValid(int index){
		return !rowConflict(index) && !colConflict(index) && !quadConflict(index);
	}
	//detects row conflicts, true if a conflict exist false if not
	public boolean rowConflict(int index){
		int row = index / size;
		for(int i = 0; i < size; ++i){
			int checkIndex = row * size + i;
			if(checkIndex == index);
			else if(getValue(checkIndex) == getValue(index))
				return true;
		}
		return false;
	}

	//false if no conflict exist
	public boolean colConflict(int index){
		int col = index % size;
		for(int i = 0; i < size; ++i){
			int checkIndex = col + (size * i);
			if(checkIndex == index);
			else if(getValue(checkIndex) == getValue(index))
				return true;
		}
		return false;
	}

	public boolean quadConflict(int index){
		int colQuad = (int)((index % 9) / 3);
		int rowQuad = (int)((int)(index / 9) / 3);
		int checkIndex = colQuad * 3 + (9 * (rowQuad * 3));
		for(int i = 0; i < 3; ++i){
			for(int j = 0; j < 3; ++j){
				if(checkIndex + j +(9*i) == index);
				else if(getValue(checkIndex + j + (9 * i)) == getValue(index))
					return true;
			}
		}
		return false;
	}
}

