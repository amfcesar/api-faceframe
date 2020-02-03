package br.com.anodes.apifaceframe.repositories;

import br.com.anodes.apifaceframe.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Transactional(readOnly = true)
    Optional<Album> findById(Long id);

    @Transactional(readOnly = true)
    List<Album> findByUserId(Long userId);

}
