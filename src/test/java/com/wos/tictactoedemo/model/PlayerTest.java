package com.wos.tictactoedemo.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.wos.tictactoe.model.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void init() {

        Player player = new Player();

        assertNull(player.getId());
        assertNull(player.getName());
        assertNull(player.getMark());
        assertNull(player.getState());

        player = new Player(1L, "player_1", Mark.X);

        assertEquals(1, player.getId());
        assertEquals("player_1", player.getName());
        assertEquals(Mark.X, player.getMark());
        assertEquals(PlayerState.PLAYING, player.getState());
    }

    @Test
    void equals() {

        Player playerOne = new Player(1L, "player_1", Mark.X);
        Player playerTwo = new Player(2L, "player_2", Mark.O);
        Player playerThree = new Player(1L, "player_1", Mark.O);

        assertTrue(playerOne.equals(playerOne));
        assertTrue(playerOne.equals(playerThree));

        assertFalse(playerOne.equals(playerTwo));

        assertEquals(playerOne.hashCode(), playerOne.hashCode());
        assertEquals(playerOne.hashCode(), playerThree.hashCode());

        assertNotEquals(playerOne.hashCode(), playerTwo.hashCode());
    }

    @Test
    void score() {

        Player playerOne = new Player(1L, "player_1", Mark.X);
        Player playerTwo = new Player(2L, "player_2", Mark.O);

        playerOne.setState(PlayerState.DRAW);
        assertEquals(0, playerOne.getScore());

        playerOne.setState(PlayerState.LOSS);
        assertEquals(0, playerOne.getScore());

        playerOne.setState(PlayerState.WIN);
        assertEquals(PlayerState.WIN, playerOne.getState());

        playerOne.addScore(1);
        assertEquals(1, playerOne.getScore());

        playerOne.subScore(1);
        assertEquals(0, playerOne.getScore());

        playerOne.setScore(5);
        assertEquals(5, playerOne.getScore());
    }
}


