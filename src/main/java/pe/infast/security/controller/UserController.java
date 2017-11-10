package pe.infast.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.infast.security.model.User;
import pe.infast.security.repository.UserRepository;
import pe.infast.security.utils.Constante;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/security")
public class UserController {


    @Autowired
     public UserRepository userRepository;


    @PostMapping("/users")
    public Mono<ResponseEntity<User>> saveUser(@RequestBody @Valid User user){

        Mono<User> userMono = Mono.just(user);
        return userRepository.findById(user.getUserId())
                .flatMap((userIn)-> {
                   Optional<Mono<User>> userOptional = Optional.ofNullable(Mono.justOrEmpty(userIn));
                      if(!userOptional.isPresent()){
                          //return userMono.map(user1 -> userRepository.save(user1));
                          return userRepository.save(userIn);
                      }
                      else{
                          return Mono.empty();
                      }


                })
                .map(userExisting-> ResponseEntity.ok(userExisting))
                ;
    }

    //validated
    @PutMapping("/users")
    public Mono<ResponseEntity<User>> updateUser(@Valid @RequestBody  User user){

        return this.userRepository.findById(user.getUserId())
                .flatMap((User existingUser) -> {
                    existingUser.setPersonId(user.getPersonId());
                    existingUser.setCareCenterId(user.getCareCenterId());
                    existingUser.setLogin(user.getLogin());
                    existingUser.setPassword(user.getPassword());
                    existingUser.setRoles(user.getRoles());
                    existingUser.setState(user.getState());
                    return userRepository.save(existingUser);
                })
                .map(updateUser-> new ResponseEntity<>(updateUser, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    //validated
    @DeleteMapping("/users/{id}")
    public Mono<ResponseEntity<User>> deleteUser(@PathVariable(value="id") String id){

        return userRepository.findById(id)
                .flatMap(existingUser-> {
                    existingUser.setState(Constante.DISABLE);
                    return userRepository.save(existingUser);
                })
                .map(modifiedUser-> new ResponseEntity<>(modifiedUser,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //validated
    @GetMapping("/users/centers/{id}")
    public Mono<ResponseEntity<List<User>>> getUsers(@PathVariable  String id){

        return this.userRepository.findAll()
                .filter(user -> user.getCareCenterId().equalsIgnoreCase(id))
                .collectList()
                .flatMap((List<User> list) -> list.size() > 0 ? Mono.just(list) : Mono.empty())
                .map(list -> ResponseEntity.ok(list))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }


    //validated
    @GetMapping("/users/{id}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable String id){
        return this.userRepository.findById(id)
                .map(foundUser ->  ResponseEntity.ok(foundUser))
                .defaultIfEmpty(ResponseEntity.notFound().build());


    }

}
