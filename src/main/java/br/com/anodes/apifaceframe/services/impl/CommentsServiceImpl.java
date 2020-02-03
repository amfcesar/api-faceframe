package br.com.anodes.apifaceframe.services.impl;

import br.com.anodes.apifaceframe.entities.Comments;
import br.com.anodes.apifaceframe.repositories.CommentsRepository;
import br.com.anodes.apifaceframe.repositories.PostRepository;
import br.com.anodes.apifaceframe.services.CommentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentsService {

    private static final Logger log = LoggerFactory.getLogger(CommentsServiceImpl.class);

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public Optional<Comments> findById(Long id) {
        log.info("Buscando um comentario por o id {} ", id);
        return this.commentsRepository.findById(id);
    }

    @Override
    public Comments presist(Comments comments){
        log.info("Persistindo post {}", comments);
        return this.commentsRepository.save(comments);
    };

}
