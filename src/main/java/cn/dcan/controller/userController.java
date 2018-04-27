package cn.dcan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Response;

import cn.dcan.constrain.SimpleResponse;
import cn.dcan.constrain.DataResponse;

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

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Response login(@HeaderParam("userName") int id,@HeaderParam("password") String password) {

        return Response.ok(new SimpleResponse(SimpleResponse.ERROR,"登录失败！")).build();
    }

}
