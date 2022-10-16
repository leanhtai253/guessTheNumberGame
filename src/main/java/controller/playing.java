package controller;

import model.Game;
import model.User;
import service.ClickedButtons;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "playing-servlet", urlPatterns = {"/playing"})
public class playing extends HttpServlet {
    Game game = new Game();
    int trials = 0;

    protected String getUserPlaying (HttpServletRequest req, HttpServletResponse resp){
        String username = "";
        try {
            username = Optional.ofNullable(req.getParameter("username")).orElse("");
        } finally {
            return username;
        }
    }

    protected void playAgain(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        trials = 0;

        game.setCurrent_guess(game.generate_guess());
        game.getCurrent_user().setCurrent_guess(-1);

        System.out.println("Play again:" + game.getCurrent_guess());

        toPlaying(req, resp);
    }

    protected void newUser(String username) {
        User new_user = new User(username);
        game.addUser(new_user);
        trials = 0;
    }

    protected void adjustMaxScoreGuess(int trial) {
        int cur_max_scr = game.getMax_score();
        if (cur_max_scr == 0 || trial < cur_max_scr) {
            game.setMax_score(trial);
            game.setBest_user(game.getCurrent_user().getUsername());
        }
        int user_best = game.getCurrent_user().getBest_guess();
        if (user_best == 0 || trial < user_best) {
            game.getCurrent_user().setBest_guess(trial);
        }
    }

    protected void checkingAnswers(int value, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (!game.checkGuess(value)) {
            if (value < game.getCurrent_guess()) {
                req.setAttribute("falseAnswer", "Số bạn nhập nhỏ hơn số chương trình");
            } else {
                req.setAttribute("falseAnswer", "Số bạn nhập lớn hơn số chương trình");
            }
            req.setAttribute("correct", false);
            req.getRequestDispatcher("resources/playing.jsp").forward(req, resp);
        } else {
            adjustMaxScoreGuess(trials);
            req.setAttribute("gameBestGuess", game.getMax_score());
            req.setAttribute("bestUser", game.getBest_user());
            req.setAttribute("correctAnswer", game.getCurrent_guess());
            req.setAttribute("userBestGuess", game.getCurrent_user().getBest_guess());
            req.setAttribute("userGuess", trials);
            req.getRequestDispatcher("resources/results.jsp").forward(req, resp);
        }


    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Try to get parameter username
        // If username exists => new player with new game
        // If username not exists => same user who plays again (will be checked when going through authFilter)
        String username = getUserPlaying(req, resp);

        if (username != "") {
            newUser(username);
            System.out.println("username game score: " + game.getMax_score());
            System.out.println("Danh sach nguoi choi: ");
            for (User u : game.getAll_users()) System.out.println(u.getUsername());
        } else {
            if (game.getAll_users().size()==0) {
                toHomepage(req,resp);
                return;
            }
        }
        username = game.getCurrent_user().getUsername();
        int user_guess = game.getCurrent_user().getCurrent_guess();

        // Set attributes for display

        req.setAttribute("username", username);
        req.setAttribute("gameBestGuess", game.getMax_score());
        req.setAttribute("gameAnswer", game.getCurrent_guess());
        req.setAttribute("bestUser", game.getBest_user());

        if (user_guess != -1) {
            System.out.println("User guess:" + user_guess);
            trials += 1;
            System.out.println("Lần thứ " + trials);
            checkingAnswers(user_guess, req, resp);

        } else {
            req.getRequestDispatcher("resources/playing.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_guess = getUserGuess(req);
        if (user_guess != "") {
            int user_guess_int = Integer.parseInt(user_guess);
            if (user_guess_int >= 0 && user_guess_int <= 1000) {
                game.getCurrent_user().setCurrent_guess(user_guess_int);
            }
            toPlaying(req, resp);
        }

        String param = ClickedButtons.check_button(req);
        if (param != "") redirectPage(param, req, resp);

    }
    protected void redirectPage(String button, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String[] options = ClickedButtons.getOptions();
        int button_index = -1;
        for (int i = 0; i < options.length; i++) {
            if (button.equals(options[i])) {
                button_index = i;
                break;
            }
        }
        switch(button_index) {
            case 0: // play again
                playAgain(req, resp);
                break;
            case 1: // reset
                resetGame();
                toHomepage(req, resp);
                break;
            case 2:
                newPlayer(req, resp);
                break;
            case 3:
                showRanking(req, resp);
                break;
        }
    }


    protected void resetGame() {
        this.game = new Game();
        trials = 0;
    }

    protected String getUserGuess(HttpServletRequest req) {
        String user_guess = "";
        try{
            user_guess = Optional.ofNullable((req.getParameter("user_guess"))).orElse("");
        } finally {
            return user_guess;
        }
    }

    protected void newPlayer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        trials = 0;
        game.setCurrent_guess(game.generate_guess());
        toHomepage(req, resp);
    }

    protected void showRanking(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<User> rankedUsers = game.getAll_users().stream().sorted(
                Comparator.comparing(User::getBest_guess)).collect(
                        Collectors.toList());
        req.setAttribute("rankingInfo", rankedUsers);

        req.setAttribute("username", game.getCurrent_user().getUsername());
        req.setAttribute("gameBestGuess", game.getMax_score());
        req.setAttribute("gameAnswer", game.getCurrent_guess());
        req.setAttribute("bestUser", game.getBest_user());

        toRanking(req, resp);
    }

    protected void toPlaying(HttpServletRequest req, HttpServletResponse resp, String param, String value) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/playing" + "?" + param + "=" + value);
    }
    protected void toPlaying(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/playing");
    }
    protected void toHomepage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/");
    }
    protected void toRanking(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("resources/ranking.jsp").forward(req, resp);
    }

}
