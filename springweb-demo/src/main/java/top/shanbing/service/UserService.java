package top.shanbing.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.shanbing.bean.User;

import java.util.List;

public interface UserService{

    List<User> list();

    Flux<User> getById(final Flux<String> ids);

    Mono<User> getById(final String id);
}
