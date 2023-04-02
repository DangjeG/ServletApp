package ServicePackage;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class UserProfile {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String login;
    @Column(name = "password")
    private String pass;
    @Column
    private String email;

    public UserProfile(){}
    public UserProfile(String login, String pass, String email) {
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    public UserProfile(String login) {
        this.login = login;
        this.pass = login;
        this.email = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }
}
