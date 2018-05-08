package cn.dcan.controller;

import cn.dcan.Service.AccountService;
import cn.dcan.Service.PurchaseService;
import cn.dcan.dto.PurchaseDTO;
import cn.dcan.constrain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Created by dongc_000 on 2018/5/5.
 */
@RestController
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;
    @Autowired
    AccountService accountService;

    private ConcreteDataFormat concreteDataFormat = new ConcreteDataFormat();

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
        System.out.println("select state: " + state);
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

    @RequestMapping(value = "/purchase/order/stateChange", method = RequestMethod.PUT)
    @ResponseBody
    public Response changeState(@RequestBody PurchaseDTO purchaseDTO) {
        //int changedId = Integer.parseInt(request.getHeader("order_id"));
        //int toState = Integer.parseInt(request.getHeader("state"));
        //System.out.println("更改定订单" + changedId + "的状态为" + toState);
        if(purchaseDTO.getPurchase_state() == 2) {
            Date date = new Date();
            purchaseDTO.setPurchase_time(concreteDataFormat.DateToString(date));
            accountService.addPurchasePay(purchaseDTO);
        }
        boolean result = false;
        result = purchaseService.changeOrderContent(purchaseDTO);
        if(result) {
            return Response.ok(new SimpleResponse(SimpleResponse.OK,"订单状态更改成功！")).build();
        } else {
            return Response.ok(new SimpleResponse(SimpleResponse.ERROR,"订单状态更改失败！")).build();
        }
    }
}
