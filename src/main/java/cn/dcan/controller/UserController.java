package cn.dcan.controller;

import cn.dcan.Service.UserService;
import cn.dcan.dto.UserDTO;
import cn.dcan.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Response;

import cn.dcan.constrain.SimpleResponse;
import cn.dcan.constrain.DataResponse;

import java.util.List;

/**
 * Created by dongc_000 on 2018/4/26.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String test() {
        System.out.println("hello!");
        return "hello!";
    }

    //登录验证
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Response login(HttpServletRequest request) {
        int id = Integer.parseInt(request.getHeader("userName"));
        String password = request.getHeader("password");
        int role = Integer.parseInt(request.getHeader("role"));
        System.out.println(id+","+password+","+role);
        UserDTO userDTO = userService.verifyUser(id, password.getBytes(),role);
        if(userDTO!=null) {
            return Response.ok(new DataResponse<UserDTO>(SimpleResponse.OK,
                    "验证成功！",
                    userDTO)).build();
        } else {
            return Response.ok(new SimpleResponse(SimpleResponse.ERROR, "验证失败！")).build();
        }
        //return Response.ok(new SimpleResponse(SimpleResponse.OK,"连接成功！")).build();
    }

    //获取所有用户
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    @ResponseBody
    public Response getAllUser() {
        System.out.println("Get the list of all users");
        List<UserDTO> users = userService.getUserList();
        return Response.ok(users).build();
    }

    //
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    @ResponseBody
    public Response addUser(@RequestBody UserDTO userDTO) {
        System.out.println("prepare to add user!");
        int newId = userService.addUser(userDTO);
        return Response.ok(new SimpleResponse(SimpleResponse.OK,Integer.toString(newId))).build();
    }

}
