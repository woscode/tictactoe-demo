package com.wos.tictactoedemo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.wos.tictactoe.model.*;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    void init() throws IllegalAccessException {

        Board board = new Board();
        for (short i = 0; i < 9; i++)
            assertEquals(Mark.EMPTY, board.getSquare(i).getMark());
    }

    @Test
    void mark() throws IllegalAccessException {

        Board board = new Board();
        for (short i = 0; i < 9; i++)
            board.set(i, Mark.X);

        for (short i = 0; i < 9; i++)
            assertEquals(Mark.X, board.getSquare(i).getMark());

        for (short i = 0; i < 9; i++)
            board.set(i, Mark.O);

        for (short i = 0; i < 9; i++)
            assertEquals(Mark.O, board.getSquare(i).getMark());

        board.clear();
        assertTrue(board.setIfEmpty(0, Mark.X));
        assertFalse(board.setIfEmpty(0, Mark.X));
        assertFalse(board.setIfEmpty(0, Mark.O));
    }

    @Test
    void clear() throws IllegalAccessException {

        Board board = new Board();

        board.clear();
        for (int i = 0; i < 9; i++)
            assertEquals(Mark.EMPTY, board.getSquare(i).getMark());

        assertEquals(false, board.isFull());
    }

    @Test
    void full() {

        Board board = new Board();

        assertEquals(false, board.isFull());

        for (int i = 0; i < 9; i++)
            board.set(i, Mark.X);

        assertEquals(true, board.isFull());
    }

    @Test
    void win() {

        Board board = new Board();

        board.set(0, Mark.X); board.set(1, Mark.X); board.set(2, Mark.X);
        assertTrue(board.isWinCombination());

        board.clear();
        board.set(3, Mark.X); board.set(4, Mark.X); board.set(5, Mark.X);
        assertTrue(board.isWinCombination());

        board.clear();
        board.set(6, Mark.X); board.set(7, Mark.X); board.set(8, Mark.X);
        assertTrue(board.isWinCombination());

        board.clear();
        board.set(0, Mark.O); board.set(3, Mark.O); board.set(6, Mark.O);
        assertTrue(board.isWinCombination());

        board.clear();
        board.set(1, Mark.O); board.set(4, Mark.O); board.set(7, Mark.O);
        assertTrue(board.isWinCombination());

        board.clear();
        board.set(2, Mark.O); board.set(5, Mark.O); board.set(8, Mark.O);
        assertTrue(board.isWinCombination());

        board.clear();
        board.set(0, Mark.O); board.set(4, Mark.O); board.set(8, Mark.O);
        assertTrue(board.isWinCombination());

        board.clear();
        board.set(2, Mark.X); board.set(4, Mark.X); board.set(6, Mark.X);
        assertTrue(board.isWinCombination());
    }

    @Test
    void notWin() {
        Board board = new Board();

        board.set(0, Mark.X); board.set(1, Mark.O); board.set(2, Mark.O);
        board.set(3, Mark.O); board.set(4, Mark.X); board.set(5, Mark.X);
        board.set(6, Mark.X); board.set(7, Mark.O); board.set(8, Mark.O);
        assertEquals(false, board.isWinCombination());

        board.clear();
        board.set(0, Mark.O); board.set(1, Mark.O); board.set(2, Mark.X);
        board.set(3, Mark.X); board.set(4, Mark.X); board.set(5, Mark.O);
        board.set(6, Mark.O); board.set(7, Mark.X); board.set(8, Mark.O);
        assertEquals(false, board.isWinCombination());
    }

    @Test
    void fields() throws IllegalAccessException {
        Board board = new Board();
        Square[][]squares =  board.get();

        for (short i = 0; i < 3; i++)
            for (short j = 0; j < 3; j++)
                assertTrue(squares[i][j].isEmpty());

    }
}
