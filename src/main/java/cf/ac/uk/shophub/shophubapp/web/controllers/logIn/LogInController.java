package cf.ac.uk.shophub.shophubapp.web.controllers.logIn;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LogInController {
    @GetMapping({"/login"})
    public String logIn(Model model) {
        return "login.html";
    }
}
