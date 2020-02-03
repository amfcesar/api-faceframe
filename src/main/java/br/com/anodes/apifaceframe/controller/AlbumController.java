package br.com.anodes.apifaceframe.controller;


import br.com.anodes.apifaceframe.dtos.AlbumDto;
import br.com.anodes.apifaceframe.entities.Album;
import br.com.anodes.apifaceframe.entities.Photo;
import br.com.anodes.apifaceframe.response.Response;
import br.com.anodes.apifaceframe.services.AlbumService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/album")
@CrossOrigin(origins = "*")
public class AlbumController {

    private static final Logger log = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    AlbumService albumService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    public ResponseEntity<Response<AlbumDto>> save(@Valid @RequestBody AlbumDto albumDto,
                                                   BindingResult result) {

        log.info("Castrando Album {}", albumDto.toString());
        Response<AlbumDto> response = new Response<>();


        Album album = modelMapper.map(albumDto, Album.class);
        album.setPhotos(new ArrayList<>());

//        albumDto.getPhotosId().forEach( al -> {
//
//            Photo photo = Photo.builder().build();
//            photo.setId(al);
//
//            album.getPhotos().add(photo);
//        });

        if (result.hasErrors()) {
            log.error("Erro ao validar dados do usuario {}", result.getAllErrors());
            result.getAllErrors().forEach(err -> response.getErros().add(err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        this.albumService.presist(album);

        response.setData(modelMapper.map(album, AlbumDto.class));
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Response<AlbumDto>> getAlbum(@NotEmpty @PathVariable("id") final Long id) {
        log.info("Buscando Album por id: {}", id);

        Response<AlbumDto> response = new Response<>();
        Optional<Album> album = albumService.findById(id);

        if(!album.isPresent()) {
            return ResponseEntity.noContent().build();
        }

        response.setData( modelMapper.map(album.get(), AlbumDto.class));
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<Response<List<AlbumDto>>> getAbumForUser(@NotEmpty @PathVariable("userId") final Long userId) {
        log.info("Buscando Album por id: {}", userId);

        Response<List<AlbumDto>> response = new Response<>();
        List<Album> album = albumService.findByUserId(userId);

        if(album.isEmpty() && album.size() > 0) {
            return ResponseEntity.noContent().build();
        }


        response.setData(modelMapper.map(album, new TypeToken<List<AlbumDto>>(){}.getType()));
        return ResponseEntity.ok(response);
    }


}
