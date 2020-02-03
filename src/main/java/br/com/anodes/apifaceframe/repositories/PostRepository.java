package br.com.anodes.apifaceframe.repositories;

import br.com.anodes.apifaceframe.constants.ConstantsQuerys;
import br.com.anodes.apifaceframe.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
        @NamedQuery(name = "PostRepository.findByUserId", query = ConstantsQuerys.POST_REPOSITORY_FIND_BY_USER_ID) })

public interface PostRepository extends JpaRepository<Post, Long> {

    @Transactional(readOnly = true)
    Post findByAuthorContaining(String name);

    Page<Post> findByUserId(Long userId, Pageable pageRequest);
}
