package pl.patrykbrzozowski.service;

import pl.patrykbrzozowski.exceptions.RegisterFailedException;
import pl.patrykbrzozowski.model.User;
import pl.patrykbrzozowski.model.dto.RegisterDto;
import pl.patrykbrzozowski.security.CurrentUser;

public interface UserService {

    User findByUserName(String name);

    void save(User user);

    void update(User user, CurrentUser currentUser);

    User registerUser(RegisterDto dto) throws RegisterFailedException;;
}
