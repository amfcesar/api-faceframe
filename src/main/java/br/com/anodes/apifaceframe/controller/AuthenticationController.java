package br.com.anodes.apifaceframe.controller;

import br.com.anodes.apifaceframe.config.TokenService;
import br.com.anodes.apifaceframe.dtos.Login;

import br.com.anodes.apifaceframe.dtos.TokenDto;
import br.com.anodes.apifaceframe.response.Response;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<Response<TokenDto>> auth(@RequestBody @Valid Login login) {


        UsernamePasswordAuthenticationToken loginToken = login.ToConvert();
        Response<TokenDto> response = new Response<>();

        try {
            Authentication authentication = authenticationManager.authenticate(loginToken);
            String token = tokenService.createToken(authentication);
            response.setData(new TokenDto(token, "Bearer"));

            return ResponseEntity.ok(response);
        }catch (AuthenticationException e) {
                return ResponseEntity.badRequest().build();
        }

    }

}
