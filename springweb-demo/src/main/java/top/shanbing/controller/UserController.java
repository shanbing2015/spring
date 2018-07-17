package top.shanbing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.shanbing.bean.User;
import top.shanbing.result.JsonResult;
import top.shanbing.service.UserService;

import java.time.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *  Mono 和 Flux 是由 Project Reactor 提供的 Reactive 类型
 *  Mono 是一个用来发送 0 或者单值数据的发布器，Flux 可以用来发送 0 到 N 个值
 *  与命令式的编程风格相比，我们并不会真正返回 User/List<User> ，因为这将导致线程的执行被堵塞，直到 User/List<User> 数据可用并从方法中返回。我们只是返回一个流的引用，这个流将最终返回 User/List<Users> 数据。
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;


    @GetMapping("")
    public Mono<JsonResult> list() {
        System.out.println("list");
        Mono<JsonResult> mono = Mono.just(new JsonResult(this.userService.list())).delayElement(Duration.ofMillis(100));
        return mono;
    }

    @GetMapping("/list")
    public JsonResult getList() {
        System.out.println("getList");
        try {
            TimeUnit.MILLISECONDS.sleep(100);   // 1
        } catch (InterruptedException e) {
        }
        return new JsonResult(this.userService.list());
    }

    @GetMapping("/{id}")
    public Mono<User> getById(@PathVariable("id") final String id) {
        return this.userService.getById(id);
    }

    @GetMapping("/test1")
    public Flux<User> test1(){
        return Flux.empty();
    }


}
