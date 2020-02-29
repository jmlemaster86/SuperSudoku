package com.example.supersudoku;

class Cell{
	private int[] impNum; //array of values deemed impossible by the user.
	private int[] posNum; //array of values deemed possible by the user.
	private int actualNum; //the actual value of the cell as selected by the user or already on the board.
	
	public Cell(){
		impNum = new int[9];
		posNum = new int[9];
		actualNum = 0;
	}
	
	public Cell(int value){
		impNum = new int[9];
		posNum = new int[9];
		actualNum = value;
	}
	
	public int getActual(){
		return actualNum;
	}
	
	public int getImp(int index){
		return impNum[index];
	}
	public int getPos(int index){
		return posNum[index];
	}
	
	public void setActual(int value){
		actualNum = value;
	}
	
	public void setImp(int index, int value){
		impNum[index] = value;
	}
	
	public void setPos(int index, int value){
		posNum[index] = value;
	}
}