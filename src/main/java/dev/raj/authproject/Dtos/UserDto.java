package dev.raj.authproject.Dtos;

import dev.raj.authproject.Models.Role;
import dev.raj.authproject.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
public class UserDto {
   private String email;
   private Set<Role> roles = new HashSet<>();

   public static UserDto from(User user){
       UserDto userDto = new UserDto();
       userDto.setEmail(user.getEmail());
       userDto.setRoles(user.getRoles());
       return userDto;
   }
}
