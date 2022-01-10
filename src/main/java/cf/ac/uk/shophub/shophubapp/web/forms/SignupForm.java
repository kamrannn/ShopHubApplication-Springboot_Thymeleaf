package cf.ac.uk.shophub.shophubapp.web.forms;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupForm {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;

//    public SignupForm(UserDTO userDTO) {}
}