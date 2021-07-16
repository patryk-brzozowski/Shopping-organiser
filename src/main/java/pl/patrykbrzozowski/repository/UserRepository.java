package pl.patrykbrzozowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.patrykbrzozowski.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName (String userName);

    User findUserByEmail (String email);
}
