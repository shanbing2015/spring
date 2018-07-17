package top.shanbing.configuration.routerFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import top.shanbing.handler.TimeHandler;


@Configuration
public class RouterConfig {
    /**
     * RouterFunction:
     *      顾名思义，路由，相当于@RequestMapping
     *
     */


    @Autowired
    private TimeHandler timeHandler;

    @Bean
    public RouterFunction<ServerResponse> timerRoter(){
        return RouterFunctions.route(RequestPredicates.GET("/time"), req -> timeHandler.getTime(req))
                .andRoute(RequestPredicates.GET("/date"),timeHandler::getDate)
                .andRoute(RequestPredicates.GET("/times"),timeHandler::sendTimePerSec);
    }

//    public RouterFunction<ServerResponse> test(){
//        return RouterFunctions.
//    }
}
