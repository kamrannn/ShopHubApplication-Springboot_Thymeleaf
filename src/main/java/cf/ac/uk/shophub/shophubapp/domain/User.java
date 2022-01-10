package cf.ac.uk.shophub.shophubapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
/*@Entity(name = "users")
@Table(name = "users")*/
@AllArgsConstructor
public class User {

    @Id
    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private String role;
    private boolean enabled;
    private String email;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {
    }
}