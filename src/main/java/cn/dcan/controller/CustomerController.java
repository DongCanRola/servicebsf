package cn.dcan.controller;

import cn.dcan.Service.CustomerService;
import cn.dcan.dto.CustomerDTO;
import cn.dcan.constrain.SimpleResponse;
import cn.dcan.constrain.DataResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import java.util.*;

/**
 * Created by dongc_000 on 2018/5/1.
 */
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/customer/list", method = RequestMethod.GET)
    @ResponseBody
    public Response getCustomers(HttpServletRequest request) {
        int type = Integer.parseInt(request.getHeader("customerType"));
        List<CustomerDTO> result = customerService.getListByType(type);
        return Response.ok(result).build();
    }

    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    @ResponseBody
    public Response addCustomer(@RequestBody CustomerDTO customerDTO) {
        int cId = customerService.addCustomer(customerDTO);
        return Response.ok(new SimpleResponse(SimpleResponse.OK,Integer.toString(cId))).build();
    }
}
