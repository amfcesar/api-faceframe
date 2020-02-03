package br.com.anodes.apifaceframe.services.impl;

import br.com.anodes.apifaceframe.entities.Photo;
import br.com.anodes.apifaceframe.repositories.PhotoRepository;
import br.com.anodes.apifaceframe.repositories.PostRepository;
import br.com.anodes.apifaceframe.services.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl  implements PhotoService {

    private static final Logger log = LoggerFactory.getLogger(PhotoServiceImpl.class);

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Photo presist(Photo photo) {
        log.info("Persistindo photo {}", photo);
        return photoRepository.save(photo);
    }
}
