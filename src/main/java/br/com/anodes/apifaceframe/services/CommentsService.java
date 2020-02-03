package br.com.anodes.apifaceframe.services;

import br.com.anodes.apifaceframe.entities.Comments;


import java.util.Optional;

public interface CommentsService {

    Optional<Comments> findById(Long id);

    Comments presist(Comments comments);

}
