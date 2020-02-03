package br.com.anodes.apifaceframe.controller;

import br.com.anodes.apifaceframe.dtos.PostDto;
import br.com.anodes.apifaceframe.dtos.UserDto;
import br.com.anodes.apifaceframe.entities.User;
import br.com.anodes.apifaceframe.response.Response;
import br.com.anodes.apifaceframe.services.UserService;
import br.com.anodes.apifaceframe.utils.PasswordUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Response<UserDto>> save(@Valid @RequestBody UserDto userDto,
                                                       BindingResult result) {

        log.info("Castrando Usuario {}", userDto.toString());
        Response<UserDto> response = new Response<>();

        validarDadosExistentes(userDto, result);
        User user = modelMapper.map(userDto, User.class);

        if (result.hasErrors()) {
            log.error("Erro ao validar dados do usuario {}", result.getAllErrors());
            result.getAllErrors().forEach(err -> response.getErros().add(err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        user.setPassword(PasswordUtils.gerarBCrypt(user.getPassword()));
        this.userService.presist(user);

        response.setData(modelMapper.map(user, UserDto.class));
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Response<UserDto>> getUser(@NotEmpty @PathVariable("id") final Long id) {
        log.info("Buscando usuario por id: {}", id);

        Response<UserDto> response = new Response<>();
        Optional<User> user = userService.findById(id);

        if(!user.isPresent()) {
            return ResponseEntity.noContent().build();
        }

        response.setData( modelMapper.map(user.get(), UserDto.class));
        return ResponseEntity.ok(response);
    }


    private void validarDadosExistentes(UserDto dto, BindingResult result) {

        Optional<User> user = this.userService.findByCpf(dto.getCpf());
        if (user.isPresent()) {
            result.addError(new ObjectError("Usuario", "Usuario já cadastrado!"));
        }
    }

}
