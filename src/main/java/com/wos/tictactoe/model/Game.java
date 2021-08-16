package com.wos.tictactoe.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Game {

    final Logger logger = LoggerFactory.getLogger(Game.class);

    private int id;

    private GameState state = GameState.NEW;

    Board board = new Board();

    private Player playerOne;
    private Player playerTwo;

    private boolean isCurrentPlayerMove = true;
    private boolean isPlayerOneMoveFirst = true;

    public Game() { }

    public Game(Player playerX, Player playerO) {
        setPlayers(playerX, playerO);
        setGameState(GameState.PLAYING);
    }

    /**
     * <p> Make a move to {@code position} </p>
     * @param position [0,...9)
     * @return {@code true} if the move is made
     */
    public boolean move(int position) {
        return move(position, currentPlayer().getMark());
    }

    /**
     * <p> Make a move to {@code position} and set {@code mark} </p>
     * @param position [0,...9)
     * @param mark (X, O, EMPTY)
     * @return {@code true} if the move is made
     */
    public boolean move(int position, Mark mark) {

        if (getGameState().isPlaying() &&
                (currentPlayer().getMark() == mark) &&
                board.setIfEmpty(position, mark)) {

            logger.info("Player {} move on {}", currentPlayer().getMark(), position);

            if (board.isWinCombination()) {
                logger.info("Player {} win", currentPlayer().getMark());
                currentPlayer().setState(PlayerState.WIN);
                currentPlayer().addScore(1);
                nextPlayer().setState(PlayerState.LOSS);
                setGameState(GameState.FINISHED);

            } else if(board.isFull()) {
                logger.info("Draw");
                playerOne.setState(PlayerState.DRAW);
                playerTwo.setState(PlayerState.DRAW);
                setGameState(GameState.FINISHED);
            }
            else
                isCurrentPlayerMove = !isCurrentPlayerMove;

            return true;
        }
        return false;
    }

    /**
     * Make a random move to an available {@code square} by the <i>current</i> {@code player}
     */
    public void randomMove() {
        logger.info("PC is move");
        move(board.getRandomAvailable(), currentPlayer().getMark());
    }

    /**
     * The current state of the game on the {@code board}
     * @return board
     */
    public Board board() {
        return board;
    }

    /**
     * Exchange {@code marks} between {@code players}
     */
    public void swapMarks() {

        playerOne.setMark(playerTwo.getMark());
        playerTwo.setMark(playerOne.getMark() == Mark.X ? Mark.O : Mark.X);
    }

    public void setPlayers(Player playerX, Player playerO) {

        playerOne = playerX;
        playerOne.setMark(Mark.X);

        playerTwo = playerO;
        playerTwo.setMark(Mark.O);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public GameState getGameState() { return state; }
    public void setGameState(GameState state) { this.state = state; }

    /**
     * Whose move
     * @return player
     */
    public Player currentPlayer() {
        return isCurrentPlayerMove ? playerOne : playerTwo;
    }

    /**
     * Whose next move
     * @return player
     */
    public Player nextPlayer() {
        return isCurrentPlayerMove ? playerTwo : playerOne;
    }

    /**
     * @return {@code true} if the first {@code player} goes first
     */
    public boolean isPlayerMoveFirst() {  return isPlayerOneMoveFirst; }

    public Player getPlayerOne() {
        return playerOne;
    }
    public Player getPlayerTwo() {
        return playerTwo;
    }

    /**
     * Restart game:
     * - {@code player} one moves first
     * - change the {@code state of the players}
     * - change {@code game state}
     * - clear {@code board}
     */
    public void restart() {

        logger.info("Restarting");

        setGameState(GameState.PLAYING);
        isCurrentPlayerMove = isPlayerOneMoveFirst;
        playerOne.setState(PlayerState.PLAYING);
        playerTwo.setState(PlayerState.PLAYING);
        board.clear();
    }

    /**
     * Restart game:
     * - change the {@code state of the players}
     * - change {@code game state}
     * - clear {@code board}
     * @param isPlayerOneMoveFirst {@code true} if {@code player} one moves first
     */
    public void restart(boolean isPlayerOneMoveFirst) {
        this.isPlayerOneMoveFirst = isPlayerOneMoveFirst;
        restart();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Game other = (Game) obj;
        return Objects.equals(getId(), other.getId());
    }

    @Override
    public String toString() {
        return "TicTacToeGame [getId()=" + getId() + "]";
    }
}
