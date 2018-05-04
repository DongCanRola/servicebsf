package cn.dcan.controller;

import cn.dcan.Service.SavingsService;
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
public class SavingsController {

    @Autowired
    SavingsService savingsService;

    @RequestMapping(value = "/savings/list", method = RequestMethod.GET)
    @ResponseBody
    public Response getAllSavings() {
        List<SavingsDTO> savingsDTOList = savingsService.getAllSavings();
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
        String newSavings = savingsService.addSavings(savingsDTO);
        if(newSavings != null) {
            System.out.println("成功增加存储！");
            return Response.ok(new SimpleResponse(SimpleResponse.OK,newSavings)).build();
        } else {
            System.out.println("增加存储失败！");
            return Response.ok(new SimpleResponse(SimpleResponse.ERROR,"新增存储失败！")).build();
        }
    }
}
