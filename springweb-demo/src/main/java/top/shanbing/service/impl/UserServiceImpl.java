package top.shanbing.service.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.shanbing.bean.User;
import top.shanbing.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {
    private final Map<String, User> data = new ConcurrentHashMap<>();
    private final List<User> list = new ArrayList();

    @PostConstruct
    public void init(){
        data.put("张三",new User(1,"张三","110"));
        data.put("李四",new User(2,"李四","119"));
        data.put("王五",new User(3,"王五","120"));
        data.put("赵六",new User(4,"赵六","114"));

        list.add(new User(1,"张三","110"));
        list.add(new User(2,"李四","119"));
        list.add(new User(3,"王五","120"));
        list.add(new User(4,"赵六","114"));
        System.out.println("data init");
    }

    @Override
    public List<User> list() {
        return list;
    }

    @Override
    public Flux<User> getById(Flux<String> ids) {
        return ids.flatMap(id -> Mono.justOrEmpty(this.data.get(id)));
    }

    @Override
    public Mono<User> getById(String id) {
        return Mono.justOrEmpty(this.data.get(id)).switchIfEmpty(Mono.error(new Exception()));
    }
}
