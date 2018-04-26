package cn.dcan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

/**
 * Created by dongc_000 on 2018/4/26.
 */
@RestController
public class userController {

    @RequestMapping("/")
    public String test() {
        System.out.println("hello!");
        return "hello!";
    }

    /*
    public Response login () {

    }
     */

}
