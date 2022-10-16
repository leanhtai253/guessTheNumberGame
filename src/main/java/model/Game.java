package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private int MIN = 1;
    private int MAX = 1000;
    private int max_score = 0;
    private String best_user = "Chưa có";
    private List<User> all_users = new ArrayList<User>();
    private int current_guess;

    public User getCurrent_user() {
        return all_users.get(all_users.size()-1);
    }

    public Game() {
        this.current_guess = generate_guess();
    }

    public boolean checkGuess(int val) {
        return val == current_guess;
    }

    public int generate_guess() {
        Random rand = new Random();
        int guess = rand.nextInt(1000) + 1;
        return guess;
    }

    public int getMax_score() {
        return max_score;
    }

    public void setMax_score(int max_score) {
        this.max_score = max_score;
    }

    public void addUser(User new_user) {
        all_users.add(new_user);
    }

    public int getCurrent_guess() {
        return current_guess;
    }

    public void setCurrent_guess(int current_guess) {
        this.current_guess = current_guess;
    }

    public int getMIN() {
        return MIN;
    }

    public int getMAX() {
        return MAX;
    }

    public void adjustBest() {
        int cur_user_scr = this.getCurrent_user().getBest_guess();
        if (cur_user_scr < this.getMax_score()) {
            this.setMax_score(cur_user_scr);
            best_user = this.getCurrent_user().getUsername();
        }
    }

    public String getBest_user() {
        return best_user;
    }

    public List<User> getAll_users() {
        return all_users;
    }

    public void setBest_user(String best_user) {
        this.best_user = best_user;
    }
}
