package br.com.anodes.apifaceframe.repositories;

import br.com.anodes.apifaceframe.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    @Transactional(readOnly = true)
    Comments findById(String id);
}
