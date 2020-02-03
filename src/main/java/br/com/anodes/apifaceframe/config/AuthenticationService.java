package br.com.anodes.apifaceframe.config;

import br.com.anodes.apifaceframe.entities.User;
import br.com.anodes.apifaceframe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userService.findByEmail(username);

        if(user.isPresent()) {
            user.get().setEmail(username);
            return user.get();
        }

        throw new UsernameNotFoundException("Usuario e senha invalidos!");
    }
}
