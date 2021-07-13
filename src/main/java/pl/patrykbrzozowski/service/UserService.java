package pl.patrykbrzozowski.service;

import pl.patrykbrzozowski.exceptions.RegisterFailedException;
import pl.patrykbrzozowski.model.User;
import pl.patrykbrzozowski.model.dto.RegisterDto;

public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);

    User registerUser(RegisterDto dto) throws RegisterFailedException;;
}
