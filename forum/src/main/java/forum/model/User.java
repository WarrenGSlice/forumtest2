package forum.model;

import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    @ManyToOne
    private Authority authority;
    private boolean enabled;

    public static User of(String name, String pass) {
        User user = new User();
        user.username = name;
        user.password = pass;
        return user;
    }
}
