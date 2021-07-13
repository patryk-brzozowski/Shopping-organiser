package pl.patrykbrzozowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.patrykbrzozowski.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName (String name);
}
