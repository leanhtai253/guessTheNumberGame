package model;

import java.util.Comparator;

public class User implements Comparable<User> {
    private String username;
    private int best_guess = 0;
    private int current_guess = -1;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBest_guess() {
        return best_guess;
    }

    public void setBest_guess(int best_guess) {
        this.best_guess = best_guess;
    }

    public int getCurrent_guess() {
        return this.current_guess;
    }

    public void setCurrent_guess(int guess) {
        this.current_guess = guess;
    }

    @Override
    public int compareTo(User otherUser) {
        return Integer.compare(getBest_guess(), otherUser.getBest_guess());
    }
}
