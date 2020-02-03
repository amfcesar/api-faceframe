package br.com.anodes.apifaceframe.services;

import br.com.anodes.apifaceframe.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface PostService {

    /**
     *
     * @param name
     * @return
     */
    Optional<Post> findByAuthorContaining(String name);

    /**
     *
     * @param post
     * @return
     */
    Post presist(Post post);

    /**
     *
     * @param sort
     * @return
     */
    List<Post> findAll(Sort sort);


    Page<Post> findByUserId(Long userId, Pageable pageRequest);
}
