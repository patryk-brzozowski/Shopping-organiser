package pl.patrykbrzozowski.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.patrykbrzozowski.exceptions.RegisterFailedException;
import pl.patrykbrzozowski.model.Role;
import pl.patrykbrzozowski.model.User;
import pl.patrykbrzozowski.model.dto.RegisterDto;
import pl.patrykbrzozowski.repository.RoleRepository;
import pl.patrykbrzozowski.repository.UserRepository;
import pl.patrykbrzozowski.security.CurrentUser;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user, CurrentUser currentUser) {
        User dbUser = currentUser.getUser();
        dbUser.setUserName(user.getUserName());
        dbUser.setEmail(user.getEmail());

        user.setId(dbUser.getId());
        user.setPassword(dbUser.getPassword());
        user.setRoles(dbUser.getRoles());

        userRepository.save(user);
    }

    @Override
    public User registerUser(RegisterDto dto) throws RegisterFailedException {
        if(!dto.getPassword().equals(dto.getConfirm_password()) || dto.getPassword()==null || dto.getPassword().isEmpty()
                || dto.getConfirm_password()==null || dto.getConfirm_password().isEmpty()){
            throw new RegisterFailedException("Password incorrect");
        }
        Role userRole = roleRepository.findByName("ROLE_USER");
        User user = new User(dto.getUserName(),dto.getEmail(),passwordEncoder.encode(dto.getPassword()),new HashSet<Role>(Arrays.asList(userRole)));

        return userRepository.save(user);
    }
}
