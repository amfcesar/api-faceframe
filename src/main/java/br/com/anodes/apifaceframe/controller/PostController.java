package br.com.anodes.apifaceframe.controller;


import br.com.anodes.apifaceframe.dtos.PostDto;
import br.com.anodes.apifaceframe.entities.Post;
import br.com.anodes.apifaceframe.entities.User;
import br.com.anodes.apifaceframe.response.Response;
import br.com.anodes.apifaceframe.services.PostService;
import br.com.anodes.apifaceframe.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "*")
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${pagina.quantidade_pagina}")
    private Long qtdPaginas;

    @GetMapping()
    public ResponseEntity<Response<PostDto>> getForAuthor(@NotEmpty @RequestParam(value = "search", defaultValue = "") final String name) {

        log.info("Buscando Author por nome: {}", name);

        Response<PostDto> response = new Response<>();
        Optional<Post> post = postService.findByAuthorContaining(name);

        if (!post.isPresent()) {
            log.info("Autor não encontrada para o CNPJ {}", name);
              return ResponseEntity.noContent().build();
        }

        response.setData(modelMapper.map(post.get(), PostDto.class));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<Response<Page<PostDto>>> getForPostUserId(
            @PathVariable("userId") final Long userId,
            @RequestParam(value = "pag", defaultValue = "0") final int pag,
            @RequestParam(value = "ord", defaultValue = "id") final String ord,
            @RequestParam(value = "dir", defaultValue = "DESC") final String dir) {

        log.info("Buscando lançamento por ID do funcionario: {}, página: {} ", userId, pag);

        Response<Page<PostDto>> response = new Response<Page<PostDto>>();

        Pageable pg = PageRequest.of(pag,  Integer.valueOf(this.qtdPaginas.toString()), Sort.Direction.valueOf(dir), ord);

        Page<Post> posts = postService.findByUserId(userId, pg);

        Page<PostDto> postDto = posts.map(item -> modelMapper.map(item, PostDto.class));

        response.setData(postDto);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<PostDto>> save(@Valid @RequestBody PostDto postDto,
                                                             BindingResult result) {

        log.info("Adicionando Post {} " , postDto.toString());
        Response<PostDto> response = new Response<>();

        Post post = modelMapper.map(postDto, Post.class);

        if(postDto.getUser() != null) {
            Optional<User> user = userService.findById(postDto.getUser().getId());
            if(!user.isPresent()){
                log.error("Usuario não encontrado {}", result.getAllErrors());
                result.addError(new ObjectError("Post", "Usuario não encontrado!"));
            } else {
                post.setUser(user.get());
            }
        }

        if(result.hasErrors()) {
            log.error("Erro ao validar lançamento {} ", result.getAllErrors());
            result.getAllErrors().forEach(err -> response.getErros().add(err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        post = postService.presist(post);
        response.setData(modelMapper.map(post, PostDto.class));
        return ResponseEntity.ok().body(response);
    }
}
