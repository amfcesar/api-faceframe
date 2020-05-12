package br.com.anodes.apifaceframe.services;

import br.com.anodes.apifaceframe.entities.User;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     *
     * @param name
     * @return
     */
    Optional<User> findByCpf(String name);

    /**
     *
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);

    /**
     *
     * @param user
     * @return
     */
    User presist(User user);

    /**
     *
     * @param sort
     * @return
     */
    List<User> findAll(Sort sort);

    /**
     *
     * @param id
     * @return
     */
    Optional<User> findById(Long id);

    /**
     *
     * @param cpf
     * @param email
     * @return
     */

    Optional<User> findByCpfOrEmail(String cpf, String email);
}
