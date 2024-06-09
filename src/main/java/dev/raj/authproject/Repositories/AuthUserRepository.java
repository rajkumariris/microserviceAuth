package dev.raj.authproject.Repositories;

import dev.raj.authproject.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<User, Long> {

     User save(User user);


     Optional<User> findUserByEmail(String email);

     User findById(long id);

//        Optional<User> findUserByEmail(String email);
}
