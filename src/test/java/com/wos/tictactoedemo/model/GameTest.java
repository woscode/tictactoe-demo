package com.wos.tictactoedemo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.wos.tictactoe.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class GameTest {

    private static List<Player> players;

    @BeforeAll
    static void initAll() {

        players = new ArrayList<>();
        players.add(new Player(0L, "player_1", Mark.X));
        players.add(new Player(1L, "player_2", Mark.O));
        players.add(new Player(1L, "player_3", Mark.X));
    }

    @Test
    void init() {
        Game game = new Game();

        assertEquals(GameState.NEW, game.getGameState());
        assertEquals(0, game.getId());
        assertNotNull(game.board());
        assertNull(game.currentPlayer());
    }

    @Test
    void start() {
        Game game = new Game(players.get(1), players.get(2));

        assertFalse(game.board().isFull());
        assertEquals(GameState.PLAYING, game.getGameState());
        assertEquals(players.get(1), game.currentPlayer());

        game.restart();
        assertFalse(game.board().isFull());
        assertEquals(GameState.PLAYING, game.getGameState());
        assertEquals(players.get(1), game.currentPlayer());
    }

    @Test
    void players() {
        Game game = new Game(players.get(1), players.get(2));
        assertEquals(players.get(1), game.currentPlayer());
        assertEquals(players.get(2), game.nextPlayer());
    }

    @Test
    void move() {
        Game game = new Game(players.get(1), players.get(2));

        assertTrue(game.move(1,Mark.X));

        assertFalse(game.move(1,Mark.O));
        assertFalse(game.move(8,Mark.X));

        assertTrue(game.move(2,Mark.O));
        assertTrue(game.move(4,Mark.X));

        assertTrue(game.move(5,Mark.O));
        assertTrue(game.move(8,Mark.X));
    }
}

