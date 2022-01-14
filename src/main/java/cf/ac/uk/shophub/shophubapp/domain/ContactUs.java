package cf.ac.uk.shophub.shophubapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Entity(name = "contact_us")
@Table(name = "contact_us")
@AllArgsConstructor
@NoArgsConstructor
public class ContactUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private int id;
    @NotEmpty(message = "Name is mandatory")
    private String name;
    @NotEmpty(message = "Email is mandatory")
    @Email
    private String email;
    @NotEmpty(message = "message is mandatory")
    private String message;

    public ContactUs(@NotEmpty(message = "Name is mandatory") String name, @NotEmpty(message = "Email is mandatory") @Email String email, @NotEmpty(message = "message is mandatory") String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }
}
