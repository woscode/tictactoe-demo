package com.wos.tictactoe.model;

import java.util.Objects;


public class Board {

	private Square [] field = new Square[9];
	
	private static final int [][] WIN_COMBINATION = {
		{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, 
		{1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}
	};
	
	public Board() {
		for (int i = 0; i < field.length; i++)
			field[i] = new Square(i);
	}

	/**
	 * Сlear all squares on the board
	 */
	public void clear() {
		for (short i = 0; i < field.length; i++)
			field[i].setMark(Mark.EMPTY);
	}

	/**
	 * Check if the {@code board} is full
	 * @return {@code true} if the {@code board} is full
	 */
	public boolean isFull() {
		for (short i = 0; i < field.length; i++)
			if (field[i].isEmpty())
				return false;
		return true;
	}

	/**
	 * Check if there is a winning combination on the {@code board}
	 * @return  if there is a winning combination on the board
	 */
	public boolean isWinCombination() {
		for (short i = 0; i < WIN_COMBINATION.length; i++)
			if (!field[WIN_COMBINATION[i][0]].isEmpty() &&
				Objects.equals(field[WIN_COMBINATION[i][0]].getMark(), field[WIN_COMBINATION[i][1]].getMark()) &&
				Objects.equals(field[WIN_COMBINATION[i][1]].getMark(), field[WIN_COMBINATION[i][2]].getMark()))
				return true;
		return false;
	}

	/**
	 * Get all fields from the {@code board}
	 * @return all {@code squares} from the {@code board}
	 */
	public Square[][] get() {
		return new Square[][]  { {field[0], field[1], field[2]}, 
								 {field[3], field[4], field[5]}, 
								 {field[6], field[7], field[8]} };
	}

	/**
	 * Get {@code field} by {@code index}
	 * @param i (0 {@literal <}= {@code i} {@literal <} 9)
	 * @return square by {@code i}
	 * @throws IllegalAccessException if {@code i} {@literal <} 0 or {@code i} {@literal >}= 9
	 */
	public Square getSquare(int i) throws IllegalAccessException{
		if (isIllegalAcces(i))
			throw new IllegalAccessException();
		return field[i];
	}

	/**
	 * Set a {@code mark} on a {@code field} by {@code index}
	 * @param i (0 {@literal <}= {@code i} {@literal <} 9)
	 * @param mark (X, O, EMPTY)
	 * @return {@code true} if set the {@code mark}
	 */
	public boolean set(int i, Mark mark) {
		if (isIllegalAcces(i))
			return false;
		field[i].setMark(mark);
		return true;
	}

	/**
	 * Set a {@code mark} on a {@code field} by {@code index} if the {@code field} is empty
	 * @param i (0 {@literal <}= {@code i} {@literal <} 9)
	 * @param mark (X, O, EMPTY)
	 * @return {@code true} if set the {@code mark}
	 */
	public boolean setIfEmpty(int i, Mark mark) {
		if (isIllegalAcces(i) || !field[i].isEmpty())
			return false;
		field[i].setMark(mark);
		return true;
	}

	/**
	 * Check if the index is valid
	 * @param i
	 * @return {@code true} if the index is valid
	 */
	private boolean isIllegalAcces(int i) {
		return i < 0 || i >= field.length;
	}

	/**
	 * Get a random available field (marked blank)
	 * @return index of the field
	 */
	public int getRandomAvailable() {

		java.util.List<Integer> available = new java.util.ArrayList<>();

		for (int i = 0; i < field.length; i++)
			if (field[i].isEmpty())
				available.add(i);

		if (available.isEmpty())
			return 0;

		int randomPlace = new java.util.Random().nextInt(available.size());
		return available.get(randomPlace);
	}
}
