package pl.patrykbrzozowski.service;

import pl.patrykbrzozowski.exceptions.ConfirmationFailedException;
import pl.patrykbrzozowski.exceptions.EmailAlreadyExistException;
import pl.patrykbrzozowski.exceptions.RegisterFailedException;
import pl.patrykbrzozowski.exceptions.UserAlreadyExistException;
import pl.patrykbrzozowski.model.User;
import pl.patrykbrzozowski.model.dto.RegisterDto;
import pl.patrykbrzozowski.security.CurrentUser;

public interface UserService {

    User findByUserName(String name);

    User findUserByEmail(String email);
    void userExist (String username) throws UserAlreadyExistException;

    void emailExist (String email) throws EmailAlreadyExistException;

    void save(User user);

    void update(User user, CurrentUser currentUser);

    void updatePassword(RegisterDto dto, CurrentUser currentUser) throws ConfirmationFailedException;

    User registerUser(RegisterDto dto) throws RegisterFailedException;

    void confirmPassword(User user, String password) throws ConfirmationFailedException;

    void delete(User user);
}
