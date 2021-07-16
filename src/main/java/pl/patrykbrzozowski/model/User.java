package pl.patrykbrzozowski.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 25)
    @NotNull
    @Size(min = 3, max = 25)
    private String userName;
    @Column(nullable = false, unique = true, length = 60)
    @NotNull
    @Email
    private String email;
    @Column(nullable = false, unique = true, length = 60)
    @NotNull
    @Size(min = 6)
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(String userName, String email, String password, Set<Role> roles) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
