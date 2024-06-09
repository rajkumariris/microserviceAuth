package dev.raj.authproject.Repositories;

import dev.raj.authproject.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthSessionRepository extends JpaRepository<Session, Long> {

        Session save(Session session);



        Optional<Session> findByTokenAndUser_Id(String token, Long userId);

}
