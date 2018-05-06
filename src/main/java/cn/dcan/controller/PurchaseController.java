package cn.dcan.controller;

import cn.dcan.Service.PurchaseService;
import cn.dcan.dto.PurchaseDTO;
import cn.dcan.constrain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by dongc_000 on 2018/5/5.
 */
@RestController
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @RequestMapping(value = "/purchase/order/list", method = RequestMethod.GET)
    @ResponseBody
    public Response getAllOrders() {
        List<PurchaseDTO> purchaseDTOS = purchaseService.getAllOrders();
        return Response.ok(purchaseDTOS).build();
    }

    @RequestMapping(value = "/purchase/order/stateList", method = RequestMethod.GET)
    @ResponseBody
    public Response getOrdersByState(HttpServletRequest request) {
        int state = Integer.parseInt(request.getHeader("orderState"));
        List<PurchaseDTO> purchaseDTOS = purchaseService.getOrdersByState(state);
        return Response.ok(purchaseDTOS).build();
    }

    @RequestMapping(value = "/purchase/order/add", method = RequestMethod.POST)
    @ResponseBody
    public Response addOrder(@RequestBody PurchaseDTO purchaseDTO) {
        int newOrder = purchaseService.addPurchaseOrder(purchaseDTO);
        System.out.println("new purchase order: " + newOrder);
        if(newOrder > 0) {
            return Response.ok(new SimpleResponse(SimpleResponse.OK,Integer.toString(newOrder))).build();
        }
        return Response.ok(new SimpleResponse(SimpleResponse.ERROR,"添加订单失败！")).build();
    }
}
