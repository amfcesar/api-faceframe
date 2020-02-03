package br.com.anodes.apifaceframe.services.impl;

import br.com.anodes.apifaceframe.entities.Post;
import br.com.anodes.apifaceframe.repositories.PostRepository;
import br.com.anodes.apifaceframe.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl  implements PostService {

    private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private PostRepository postRepository;

    @Override
    public  Optional<Post> findByAuthorContaining(String name) {
        log.info("Buscando um post por o nome autor {} ", name);
        return Optional.ofNullable(this.postRepository.findByAuthorContaining(name));
    };

    @Override
    public Post presist(Post post) {
        log.info("Persistindo post {}", post);
        return this.postRepository.save(post);
    }

    @Override
    public List<Post> findAll(Sort sort){
        log.info("Busca todos os posts de forma ordenada ", sort);
        return postRepository.findAll(sort);
    }

    @Override
    public Page<Post> findByUserId(Long userId, Pageable pageRequest) {
        log.info("Busca lancamento por id do funcionario {}", userId);
        return this.postRepository.findByUserId(userId, pageRequest);
    }
}
