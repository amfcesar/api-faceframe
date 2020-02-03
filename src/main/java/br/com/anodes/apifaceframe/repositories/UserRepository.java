package br.com.anodes.apifaceframe.repositories;


import br.com.anodes.apifaceframe.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional(readOnly = true)
    User findByCpf(String cpf);

    @Transactional(readOnly = true)
    User findByEmail(String email);

    @Transactional(readOnly = true)
    User findById(String id);


}
