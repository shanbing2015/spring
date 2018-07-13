package top.shanbing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.shanbing.service.BaseService;

@RestController
public class BaseController {

    @Autowired
    private BaseService baseService;

    @RequestMapping("/")
    public String index(){
        System.out.println(baseService);
        return "index";
    }
}
