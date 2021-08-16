package com.wos.tictactoe.model;

public enum Mark {
	
	EMPTY (" "),
	X ("x"),
	O ("o");
	
	private String symbol;
	
	Mark (String symbol) {
		this.symbol = symbol; 
	}
	Mark () { 
		this(" ");
	}
	
	@Override
	public String toString() { return symbol; }
}
