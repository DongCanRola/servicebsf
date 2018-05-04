package cn.dcan.controller;

import cn.dcan.Service.AccountService;
import cn.dcan.dto.SavingsDTO;
import cn.dcan.constrain.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.*;

/**
 * Created by dongc_000 on 2018/5/4.
 */
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/savings/list", method = RequestMethod.GET)
    @ResponseBody
    public Response getAllSavings() {
        List<SavingsDTO> savingsDTOList = accountService.getAllSavings();
        if(savingsDTOList != null) {
            System.out.println("成功获取存储列表！");
            return Response.ok(savingsDTOList).build();
        } else {
            System.out.println("获取存储列表失败！");
            return Response.ok(new SimpleResponse(SimpleResponse.ERROR,"获取存储列表失败！")).build();
        }
    }

    @RequestMapping(value = "/savings/add", method = RequestMethod.POST)
    @ResponseBody
    public Response addSavings(@RequestBody SavingsDTO savingsDTO) {
        accountService.addSavings(savingsDTO);
        return Response.ok(new SimpleResponse(SimpleResponse.OK,"添加成功！")).build();
    }
}
