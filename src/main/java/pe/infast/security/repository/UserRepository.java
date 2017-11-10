package pe.infast.security.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.infast.security.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String> {

    Flux<User> findUsersByCareCenterId(String careCareId);
    //Mono<User> findUserByUserId(String userId);
    Mono<User> save(User userMono);
}
