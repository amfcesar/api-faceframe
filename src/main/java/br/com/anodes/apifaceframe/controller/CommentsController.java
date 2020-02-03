package br.com.anodes.apifaceframe.controller;

import br.com.anodes.apifaceframe.dtos.CommentsDto;

import br.com.anodes.apifaceframe.entities.Comments;

import br.com.anodes.apifaceframe.response.Response;
import br.com.anodes.apifaceframe.services.CommentsService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.text.ParseException;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*")
public class CommentsController {

    private static final Logger log = LoggerFactory.getLogger(CommentsController.class);

    @Autowired
    CommentsService commentsService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping()
    public ResponseEntity<Response<CommentsDto>> save(@Valid @RequestBody CommentsDto commentsDto,
                                                  BindingResult result) throws ParseException {

        log.info("Adicionando Comentario {} " , commentsDto.toString());
        Response<CommentsDto> response = new Response<>();

        Comments comments = modelMapper.map(commentsDto, Comments.class);

        if(result.hasErrors()) {
            log.error("Erro ao validar lançamento {} ", result.getAllErrors());
            result.getAllErrors().forEach(err -> response.getErros().add(err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        comments = commentsService.presist(comments);
        response.setData(modelMapper.map(comments, CommentsDto.class));

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Response<CommentsDto>> getComment(@NotEmpty @PathVariable("id") final Long id) {
        log.info("Buscando Comments por id: {}", id);

        Response<CommentsDto> response = new Response<>();
        Optional<Comments> comments = commentsService.findById(id);

        if(!comments.isPresent()) {
            return ResponseEntity.noContent().build();
        }

        response.setData( modelMapper.map(comments.get(), CommentsDto.class));
        return ResponseEntity.ok(response);
    }
}
