package cn.dcan.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    /*
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Response login(@HeaderParam("userName") Integer id,
                          @HeaderParam("password") String password) {
        System.out.println("user_id:" + id);
        System.out.println("password:" + password);
        return Response.ok(new SimpleResponse(SimpleResponse.OK,"连接成功！")).build();
    }
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Response login(HttpServletRequest request) {
        System.out.println(request);
        System.out.println("user_id:" + request.getHeader("userName"));
        System.out.println("password:" + request.getHeader("password"));
        int id = Integer.parseInt(request.getHeader("userName"));
        byte[] password = request.getHeader("password").getBytes();
        return Response.ok(new SimpleResponse(SimpleResponse.OK,"连接成功！")).build();
    }

}
