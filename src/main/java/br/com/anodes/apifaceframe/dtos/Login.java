package br.com.anodes.apifaceframe.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Login {

    private String email;
    private String password;

    public UsernamePasswordAuthenticationToken ToConvert () {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
