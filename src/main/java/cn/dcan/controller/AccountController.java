package cn.dcan.controller;

import cn.dcan.Service.AccountService;
import cn.dcan.dto.*;
import cn.dcan.constrain.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 * Created by dongc_000 on 2018/5/4.
 */
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    private ConcreteDataFormat concreteDataFormat = new ConcreteDataFormat();

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

    @RequestMapping(value = "/purchase/pay/list", method = RequestMethod.GET)
    @ResponseBody
    public Response getPurchasePayList() {
        List<PurchasePayDTO> purchasePayDTOS = accountService.getPurchasePayList();
        return Response.ok(purchasePayDTOS).build();
    }

    @RequestMapping(value = "/purchase/pay/detail/add", method = RequestMethod.POST)
    @ResponseBody
    public Response addPurchasePayDetail(@RequestBody PurchasePayDetailDTO purchasePayDetailDTO) {
        Date date = new Date();
        purchasePayDetailDTO.setPay_time(concreteDataFormat.DateToString(date));
        int newDetail = accountService.addPurchasePayDetail(purchasePayDetailDTO);
        if(newDetail > 0)
            return Response.ok(new SimpleResponse(SimpleResponse.OK,Integer.toString(newDetail))).build();
        return Response.ok(new SimpleResponse(SimpleResponse.ERROR,"付款失败！")).build();
    }

    @RequestMapping(value = "/purchase/pay/detail/allList", method = RequestMethod.GET)
    @ResponseBody
    public Response getPurchasePayDetailAll() {
        List<PurchasePayDetailDTO> purchasePayDetailDTOS = accountService.getPurchasePayDetailList();
        return Response.ok(purchasePayDetailDTOS).build();
    }

    @RequestMapping(value = "/purchase/pay/detail/payList", method = RequestMethod.GET)
    @ResponseBody
    public Response getPurchasePayDetailByPay(HttpServletRequest request) {
        int payId = Integer.parseInt(request.getHeader("purchasePay_id"));
        List<PurchasePayDetailDTO> purchasePayDetailDTOS = accountService.getPurchasePayDetailByPay(payId);
        return Response.ok(purchasePayDetailDTOS).build();
    }

    @RequestMapping(value = "/purchase/pay/detail/savingsList", method = RequestMethod.GET)
    @ResponseBody
    public Response getPurchasePayDetailBySavings(HttpServletRequest request) {
        String savingsId = request.getHeader("savings_id");
        List<PurchasePayDetailDTO> purchasePayDetailDTOS = accountService.getPurchasePayDetailBySavings(savingsId);
        return Response.ok(purchasePayDetailDTOS).build();
    }

    @RequestMapping(value = "/sale/gather/list", method = RequestMethod.GET)
    @ResponseBody
    public Response getSaleGatherList() {
        List<SaleGatherDTO> saleGatherDTOS = accountService.getSaleGatherList();
        return Response.ok(saleGatherDTOS).build();
    }

    @RequestMapping(value = "/sale/gather/detail/add", method = RequestMethod.POST)
    @ResponseBody
    public Response addSaleGatherDetail(@RequestBody SaleGatherDetailDTO saleGatherDetailDTO) {
        Date date = new Date();
        saleGatherDetailDTO.setDetail_time(concreteDataFormat.DateToString(date));
        int newDetail = accountService.addSaleGatherDetail(saleGatherDetailDTO);
        if(newDetail > 0)
            return Response.ok(new SimpleResponse(SimpleResponse.OK, Integer.toString(newDetail))).build();
        return Response.ok(new SimpleResponse(SimpleResponse.ERROR, "收款失败！")).build();
    }

    @RequestMapping(value = "/sale/gather/detail/allList", method = RequestMethod.GET)
    @ResponseBody
    public Response getSaleGatherDetailAll() {
        List<SaleGatherDetailDTO> saleGatherDetailDTOS = accountService.getSaleGatherDetailList();
        return Response.ok(saleGatherDetailDTOS).build();
    }

    @RequestMapping(value = "/sale/gather/detail/gatherList", method = RequestMethod.GET)
    @ResponseBody
    public Response getSaleGatherDetailByGather(HttpServletRequest request) {
        int gatherId = Integer.parseInt(request.getHeader("sale_gatherId"));
        List<SaleGatherDetailDTO> saleGatherDetailDTOS = accountService.getSaleGatherDetailByGather(gatherId);
        return Response.ok(saleGatherDetailDTOS).build();
    }

    @RequestMapping(value = "/sale/gather/detail/savingsList", method = RequestMethod.GET)
    @ResponseBody
    public Response getSaleGatherDetailBySavings(HttpServletRequest request) {
        String savingsId = request.getHeader("savings_id");
        List<SaleGatherDetailDTO> saleGatherDetailDTOS = accountService.getSaleGatherDetailBySavings(savingsId);
        return Response.ok(saleGatherDetailDTOS).build();
    }
}
