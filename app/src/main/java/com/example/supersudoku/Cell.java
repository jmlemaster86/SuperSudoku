package com.example.supersudoku;

class Cell{
	private int[] posNum; //array of values deemed possible by the user.
	private int actualNum; //the actual value of the cell as selected by the user or already on the board.
	
	public Cell(){
		posNum = new int[10];
		actualNum = 0;
	}
	
	public Cell(int value){
		posNum = new int[10];
		actualNum = value;
	}
	
	public int getActual(){
		return actualNum;
	}

	public int getPos(int index){
		return posNum[index];
	}
	
	public void setActual(int value){
		actualNum = value;
	}
	
	public void setPos(int index){
		if(posNum[index] == index)
			posNum[index] = 0;
		else
			posNum[index] = index;
	}
}