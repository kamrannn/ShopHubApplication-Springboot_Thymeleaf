package cf.ac.uk.shophub.shophubapp.web.controllers.publicCon;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @GetMapping({"/", "home"})
    public String Home(Model model) {
        return "index.html";
    }
}
