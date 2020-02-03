package br.com.anodes.apifaceframe.repositories;

import br.com.anodes.apifaceframe.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository  extends JpaRepository<Photo, Long> {
}
