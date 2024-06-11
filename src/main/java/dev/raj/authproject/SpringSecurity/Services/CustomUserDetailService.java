package dev.raj.authproject.SpringSecurity.Services;

import dev.raj.authproject.Models.User;
import dev.raj.authproject.Repositories.AuthUserRepository;
import dev.raj.authproject.SpringSecurity.Models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    AuthUserRepository authUserRepository;

    public CustomUserDetailService(AuthUserRepository authUserRepository){
        this.authUserRepository = authUserRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<User> user =   authUserRepository.findUserByEmail(username);

      if(user.isEmpty()){
          throw new UsernameNotFoundException("User not found");
      }
        return new CustomUserDetails(user.get());
    }
}
