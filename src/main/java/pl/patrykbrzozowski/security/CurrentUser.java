package pl.patrykbrzozowski.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {
    private final pl.patrykbrzozowski.model.User user;
    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.patrykbrzozowski.model.User user) {
        super(username, password, authorities);
        this.user = user;
    }
    public pl.patrykbrzozowski.model.User getUser() {return user;}
}
