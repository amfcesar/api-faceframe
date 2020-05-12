package br.com.anodes.apifaceframe.controller;


import br.com.anodes.apifaceframe.dtos.UserDto;
import br.com.anodes.apifaceframe.entities.User;
import br.com.anodes.apifaceframe.response.Response;
import br.com.anodes.apifaceframe.services.UserService;
import br.com.anodes.apifaceframe.utils.PasswordUtils;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
//@Api(tags = { "Controlador Usuario" })
//@Api(value = "Employee Management System", description = "ABCD")
@Api(value = "Employee Management System", tags = {
        "Tag1" })
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ApiOperation(value = "Cria Usuario")
    @ApiResponses({ @ApiResponse(code = 201, message = "Usuario criado")})
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
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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

    @GetMapping
    public ResponseEntity<Response<List<UserDto>>> getAllUser() {
        log.info("Buscando todos os usuarios");

        Response<List<UserDto>> response = new Response<>();
        List<User> users = userService.findAll(Sort.by(Sort.Direction.ASC, "name"));



        if(users == null || users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        response.setData(modelMapper.map(users, new TypeToken<List<UserDto>>(){}.getType()));
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Response<UserDto>> putUser(@NotEmpty @PathVariable("id") final Long id, @Valid @RequestBody UserDto userDto) {
        log.info("Buscando usuario por id: {}", id);

        Response<UserDto> response = new Response<>();
        Optional<User> newUser = userService.findById(id);

        if(!newUser.isPresent()) {
            return ResponseEntity.noContent().build();
        }

        User user = this.convertUserDtoForUser(userDto, newUser.get());

        user = userService.presist(user);

        response.setData(modelMapper.map(user, UserDto.class));
        return ResponseEntity.ok(response);
    }


    private void validarDadosExistentes(UserDto dto, BindingResult result) {

        Optional<User> user = this.userService.findByCpfOrEmail(dto.getCpf(), dto.getEmail());
        if (user.isPresent()) {
            result.addError(new ObjectError("Usuario", "Usuario já cadastrado!"));
        }
    }

    private User convertUserDtoForUser(UserDto dto, User user) {

        if(dto.getPassword() != null) {
            user.setPassword(dto.getPassword());
        }
        if(dto.getCpf() != null) {
            user.setCpf(dto.getCpf());
        }
        if(dto.getName() != null){
            user.setName(dto.getName());
        }
        if(dto.getEmail()!=null){
            user.setEmail(dto.getEmail());
        }

        return user;
    }

}
