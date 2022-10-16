package service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ClickedButtons {
    static private String[] options = {
            "play_again",
            "reset",
            "new_player",
            "ranking",
    };

    static public String check_button(HttpServletRequest req) {
        String param = "";
        for (String opt : ClickedButtons.options) {
            try {
                param = Optional.ofNullable(req.getParameter(opt)).orElse("");
            } finally {
                if (param.equals("true")) return opt;
            }
        }
        return "";
    }

    static public String[] getOptions(){
        return ClickedButtons.options;
    }
}
