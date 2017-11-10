package pe.infast.security.service;

import org.springframework.stereotype.Service;
import pe.infast.security.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface UserService {

    Mono<Void> save(Mono<User> user);
    Mono<Void> update(String userId, User user);
    Mono<Void> delete(String userId);
    Mono<User> findById(String careCenterId);
    Flux<User> findAllByIdCareCenter(String careCenterId);




}
