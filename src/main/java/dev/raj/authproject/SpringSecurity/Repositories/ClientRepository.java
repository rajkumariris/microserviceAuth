package dev.raj.authproject.SpringSecurity.Repositories;

import java.util.Optional;


import dev.raj.authproject.SpringSecurity.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {


    Client save(Client client);

    Optional<Client> findByClientId(String clientId);
}