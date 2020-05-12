package br.com.anodes.apifaceframe.services.impl;

import br.com.anodes.apifaceframe.entities.User;
import br.com.anodes.apifaceframe.repositories.UserRepository;
import br.com.anodes.apifaceframe.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    public  Optional<User> findByCpf(String cpf) {
        log.info("Buscando um post por o nome autor {} ", cpf);
        return  Optional.ofNullable(userRepository.findByCpf(cpf));
    };

    public Optional<User> findByEmail(String email) {
        log.info("Buscando um post por o nome autor {} ", email);
        return  Optional.ofNullable(userRepository.findByEmail(email));
    };

    public User presist(User user) {
        log.info("Persistindo post {}", user.toString());
        return this.userRepository.save(user);
    };

    public List<User> findAll(Sort sort) {
        log.info("Busca todos os posts de forma ordenada ", sort);
        return userRepository.findAll(sort);
    };

    public Optional<User> findById(Long id) {
        log.info("Busca usario por id ", id);
        return userRepository.findById(id);
    };

    public Optional<User> findByCpfOrEmail(String cpf, String email) {
        log.info("Busca usario por id ", email);
        return Optional.ofNullable(userRepository.findByCpfOrEmail(cpf, email));
    };
}
