package dev.raj.authproject.SpringSecurity.Models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.raj.authproject.Models.Role;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

//When your application receives a JSON representation of a CustomGrantedAuthority object, it needs
// to convert (or deserialize) this JSON back into a CustomGrantedAuthority object.
// The deserialization process requires a no-argument constructor to instantiate the CustomGrantedAuthority
// object before setting its fields from the JSON data.
@JsonDeserialize
@NoArgsConstructor
public class CustomGrantedAutority implements GrantedAuthority {


    private String authority;
    public CustomGrantedAutority(Role role){

        this.authority = role.getRole();

    }
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
