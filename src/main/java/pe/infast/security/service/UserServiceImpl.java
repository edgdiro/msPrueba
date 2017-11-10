package pe.infast.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.infast.security.model.User;
import pe.infast.security.repository.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Service
public class UserServiceImpl  {

  /* @Autowired
    public UserRepository repository;

    @Override
    public Mono<Void> save(Mono<User> user) {
        return user.doOnNext(userIn -> repository.save(userIn))
                .thenEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> update(String userId, User user) {

        Mono<User> userMono = Mono.just(user);
        return userMono.doOnNext((userIn) -> repository.findUserByUserId(userId)
                .doOnNext(existingUser -> {
                    existingUser.setLogin(user.getLogin());
                    existingUser.setPassword(user.getPassword());
                    existingUser.setRoles(user.getRoles());
                    existingUser.setState(user.getState());
                    repository.save(existingUser);
                }))
                .thenEmpty(Mono.empty());
   // }

    //@Override
    //public Mono<Void> delete(String userId) {

        /*Mono<User> user = repository.findUserByUserId(userId);
        return user.doOnNext((userIn) -> repository.delete(userIn))
                .thenEmpty(Mono.empty());
    }

    @Override
    public Mono<User> findById(String careCenterId) {

        return Mono.from(repository.findUserByUserId(careCenterId))
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<User> findAllByIdCareCenter(String careCenterId) {
        return Flux.from(repository.findUsersByCareCenterId(careCenterId));
    }
        return null;*/
}
