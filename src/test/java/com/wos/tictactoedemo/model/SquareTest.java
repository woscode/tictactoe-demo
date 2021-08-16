package com.wos.tictactoedemo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.wos.tictactoe.model.*;
import org.junit.jupiter.api.Test;

class SquareTest {

    @Test
    void init() {

        Square square = new Square(0);
        assertTrue(square.isEmpty());
        assertEquals(Mark.EMPTY, square.getMark());

        square.setMark(Mark.X);
        assertFalse(square.isEmpty());
    }
}
