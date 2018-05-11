package cn.dcan.controller;

import cn.dcan.Service.SaleService;
import cn.dcan.dto.SaleDTO;
import cn.dcan.constrain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Created by dongc_000 on 2018/5/10.
 */
@RestController
public class SaleController {

    @Autowired
    SaleService saleService;

    private ConcreteDataFormat concreteDataFormat = new ConcreteDataFormat();

    @RequestMapping(value = "/sale/order/stateList", method = RequestMethod.GET)
    @ResponseBody
    public Response getSaleOrdersByState(HttpServletRequest request) {
        int state = Integer.parseInt(request.getHeader("sale_state"));
        List<SaleDTO> saleDTOList = saleService.getOrdersByState(state);
        return Response.ok(saleDTOList).build();
    }

    @RequestMapping(value = "/sale/order/add", method = RequestMethod.POST)
    @ResponseBody
    public Response addSaleOrder(@RequestBody SaleDTO saleDTO) {
        Date date = new Date();
        saleDTO.setSale_orderTime(concreteDataFormat.DateToString(date));
        int newOrder = saleService.addOrder(saleDTO);
        if(newOrder != 0) {
            return Response.ok(new SimpleResponse(SimpleResponse.OK,Integer.toString(newOrder))).build();
        }
        return Response.ok(new SimpleResponse(SimpleResponse.ERROR,"增加失败！")).build();
    }
}
