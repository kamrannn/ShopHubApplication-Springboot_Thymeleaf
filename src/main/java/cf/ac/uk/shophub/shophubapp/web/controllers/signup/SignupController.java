package cf.ac.uk.shophub.shophubapp.web.controllers.signup;

import cf.ac.uk.shophub.shophubapp.service.BusinessSearch;
import cf.ac.uk.shophub.shophubapp.web.forms.SignupForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.SecureRandom;

@Controller
public class SignupController {
    private BusinessSearch businessSearch;

    public SignupController(BusinessSearch aBusinessSearch) { businessSearch = aBusinessSearch;}

    @GetMapping("/signup")
    public String getSignupForm(Model model) {
        SignupForm signupForm = new SignupForm();
        model.addAttribute("signupForm", signupForm);
        return "signup.html";
    }

    @PostMapping({"/signup"})
    public String postSignup(SignupForm signupForm, BindingResult bindingResult, Model model) {
        String username = signupForm.getUsername();
        String rawPassword = signupForm.getPassword();
        String rawConfirmPassword = signupForm.getConfirmPassword();
        String email = signupForm.getEmail();

        if (bindingResult.hasErrors()) {
            return "";
        }
        System.out.println(rawPassword);
        System.out.println(rawConfirmPassword);
        System.out.println("passed?");
        if (rawPassword.equals(rawConfirmPassword)) {
            System.out.println("passed!");
            int strength = 12; // work factor of bcrypt
            BCryptPasswordEncoder bCryptPasswordEncoder =
                    new BCryptPasswordEncoder(strength, new SecureRandom());
            String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
            businessSearch.insertUser(username, encodedPassword, email);
        }
        return "redirect:/";
    }
}
