package com.wos.tictactoe.model;

import java.util.Objects;

public class Player {

    private Long id;
    private String name;

    private Mark mark;
    private Integer score = 0;

    private PlayerState state;

    public Player() { }

    public Player(Long id, String name, Mark mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
        setState(PlayerState.PLAYING);
    }

    /**
     * @return id of the {@code player}
     */
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return name of the {@code player}
     */
    public String getName() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    /**
     * @return state of the {@code player}
     */
    public PlayerState getState() { return state; }

    public void setState(PlayerState state) { this.state = state; }

    /**
     * @return mark of the {@code player}
     */
    public Mark getMark() { return mark; }

    public void setMark(Mark mark) { this.mark = mark; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    /**
     * Add points to the {@code player}
     * @param value
     */
    public void addScore(int value) {

        if (value < 0)
            this.subScore(-value);
        else if (Integer.MAX_VALUE - value >= score)
            this.score += value;
    }

    /**
     * Take points away from the {@code player}
     * @param value
     */
    public void subScore(int value) {

        if (value < 0)
            this.addScore(-value);
        else if (Integer.MIN_VALUE + value <= score)
            this.score -= value;
    }

    /**
     * Reset the number of points
     */
    public void resetScore() {
        this.score = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player user = (Player) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

