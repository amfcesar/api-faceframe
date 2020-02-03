package br.com.anodes.apifaceframe.services;

import br.com.anodes.apifaceframe.entities.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {

    Optional<Album> findById(Long id);

    Album presist(Album album);

    List<Album> findByUserId(Long userId);
}
