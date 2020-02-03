package br.com.anodes.apifaceframe.config;

import br.com.anodes.apifaceframe.entities.User;
import br.com.anodes.apifaceframe.services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class AuthenticationFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private UserService userService;

    public AuthenticationFilter(TokenService tokenService, UserService userService){
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token  = getToken(request);

        boolean valido = tokenService.isTokenValid(token);

        if(valido){
            authenticateClient(token);
        }

        filterChain.doFilter(request,response);
    }

    private void authenticateClient(String token) {
        Long idUser = tokenService.getIdUsuario(token);

        User user = userService.findById(idUser).get();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }

        return token.substring(7, token.length());
    }

}
