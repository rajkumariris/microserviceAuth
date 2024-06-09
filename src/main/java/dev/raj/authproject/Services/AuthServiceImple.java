package dev.raj.authproject.Services;

import ch.qos.logback.core.testUtil.RandomUtil;
import dev.raj.authproject.Dtos.UserDto;
import dev.raj.authproject.Exceptions.UserAlreadyExists;
import dev.raj.authproject.Exceptions.UserNotFoundException;
import dev.raj.authproject.Models.Session;
import dev.raj.authproject.Models.SessionStatus;
import dev.raj.authproject.Models.User;
import dev.raj.authproject.Repositories.AuthSessionRepository;
import dev.raj.authproject.Repositories.AuthUserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.UnknownServiceException;
import java.util.Date;
import java.util.Optional;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
@Service
public class AuthServiceImple {

    AuthUserRepository authUserRepository;
    AuthSessionRepository authSessionRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
   public AuthServiceImple(AuthUserRepository authUserRepository, AuthSessionRepository authSessionRepository) {
       //, BCryptPasswordEncoder bCryptPasswordEncoder
        this.authUserRepository= authUserRepository;
         this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
         this.authSessionRepository = authSessionRepository;
    }

    public ResponseEntity<UserDto> login(String email, String password) throws UserNotFoundException {
       Optional<User> usergiven =  authUserRepository.findUserByEmail(email);
       if(usergiven.isEmpty()){
        throw new UserNotFoundException("User not found");
       }
       User user = usergiven.get();

       // trying to check with the given password and hashed password in db
       if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
           throw new UserNotFoundException("paaaword not matched");
        }
       //generates random string of 20 characters and for now sends as a token
        // we can send jwt token in headers can send it as user as welll but header is better



        String randomString  = RandomStringUtils.randomAscii(20);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("AUTH_TOKEN", randomString);

        Session session = new Session();
        session.setToken(randomString);
        session.setUser(user);
        session.setStatus(SessionStatus.ACTIVE);
        System.out.println(session.getStatus());
        authSessionRepository.save(session);
        System.out.println(session.getStatus());


        UserDto userDto = UserDto.from(user);
        ResponseEntity<UserDto> returnUser = new ResponseEntity<>(
                userDto,
                headers,
                HttpStatus.OK
        );
         return returnUser;
    }

    public UserDto signup(String email, String password) throws UserAlreadyExists {
        Optional<User> user  =  authUserRepository.findUserByEmail(email);
        if(!user.isEmpty()){
            throw new UserAlreadyExists("User already exists");
        }
            User user1 = new User();
            user1.setEmail(email);
            user1.setPassword(bCryptPasswordEncoder.encode(password));
            User savedUser = authUserRepository.save(user1);
            return UserDto.from(savedUser);
    }
    public Optional<UserDto> validate(Long userid, String token){
            Optional<Session> session =  authSessionRepository.findByTokenAndUser_Id(token, userid);
            if(session.isEmpty()){
                return Optional.empty();
            }
            Session session1 = session.get();
            if(!session1.getStatus().equals(SessionStatus.ACTIVE)){
                return Optional.empty();
            }
//            if(session1.getExpiredAt().before(new Date())){
//                return SessionStatus.EXPIRED;
//            }
    //  User user =  session1.getUser();
        Optional<User> user = authUserRepository.findById(userid);
            UserDto userDto = UserDto.from(user.get());
            return Optional.of(userDto);
   }
   public SessionStatus logout(String token, Long userId){
      Optional<Session> session =  authSessionRepository.findByTokenAndUser_Id(token, userId);
      if(session.isEmpty()){
          return null;
      }
      Session session1 = session.get();
      session1.setStatus(SessionStatus.LOGGED_OUT);
       Session session2 =  authSessionRepository.save(session1);
        if(session2.getStatus().equals(SessionStatus.LOGGED_OUT)){
            return SessionStatus.LOGGED_OUT;
        }
        return SessionStatus.ACTIVE;
   }
}
