package br.com.anodes.apifaceframe.services.impl;

import br.com.anodes.apifaceframe.entities.Album;
import br.com.anodes.apifaceframe.repositories.AlbumRepository;
import br.com.anodes.apifaceframe.services.AlbumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private static final Logger log = LoggerFactory.getLogger(AlbumServiceImpl.class);

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public Optional<Album> findById(Long id) {
        log.info("Buscando um Album por o id {} ", id);
        return Optional.ofNullable(albumRepository.findById(id).get());
    }

    @Override
    public List<Album> findByUserId(Long userId) {
        log.info("Buscando um Album por o userId {} ", userId);
        return albumRepository.findByUserId(userId);
    }

    @Override
    public Album presist(Album album) {
        log.info("Persistindo post {}", album);
        return albumRepository.save(album);
    }
}
