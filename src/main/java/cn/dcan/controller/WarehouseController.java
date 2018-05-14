package cn.dcan.controller;

import cn.dcan.Service.WarehouseService;
import cn.dcan.constrain.*;

import cn.dcan.dto.*;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 * Created by dongc_000 on 2018/5/3.
 */
@RestController
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    private ConcreteDataFormat concreteDataFormat = new ConcreteDataFormat();

    @RequestMapping(value = "/warehouse/list", method = RequestMethod.GET)
    @ResponseBody
    public Response getAllWarehouses() {
        List<WarehouseDTO> warehouseDTOList = warehouseService.getAllWarehouses();
        if(warehouseDTOList != null) {
            System.out.println("已获取仓库列表！");
            return Response.ok(warehouseDTOList).build();
        } else {
            System.out.println("获取仓库列表失败！");
            return Response.ok(new SimpleResponse(SimpleResponse.ERROR,"获取仓库列表失败")).build();
        }
    }

    @RequestMapping(value = "/warehouse/add", method = RequestMethod.POST)
    @ResponseBody
    public Response addWarehouse(@RequestBody WarehouseDTO warehouseDTO) {
        int newWarehouse = warehouseService.addWarehouse(warehouseDTO);
        System.out.println("增加仓库！");
        if(newWarehouse > 0) {
            return Response.ok(new SimpleResponse(SimpleResponse.OK,Integer.toString(newWarehouse))).build();
        } else {
            return Response.ok(new SimpleResponse(SimpleResponse.ERROR,"增加失败！")).build();
        }
    }

    @RequestMapping(value = "/warehouse/stock/warehouse", method = RequestMethod.GET)
    @ResponseBody
    public Response getStockByWarehouse(HttpServletRequest request) {
        int warehouseid = Integer.parseInt(request.getHeader("stock_warehouseId"));
        List<MaterialStockDTO> materialStockDTOS =warehouseService.getStockByWarehouse(warehouseid);
        return Response.ok(materialStockDTOS).build();
    }

    @RequestMapping(value = "/warehouse/stock/goods", method = RequestMethod.GET)
    @ResponseBody
    public Response getStockByGoods(HttpServletRequest request) {
        int goodsid = Integer.parseInt(request.getHeader("stock_goodsid"));
        List<MaterialStockDTO> materialStockDTOS = warehouseService.getStockByGoods(goodsid);
        return Response.ok(materialStockDTOS).build();
    }

    @RequestMapping(value = "/warehouse/goods/num", method = RequestMethod.GET)
    @ResponseBody
    public Response getGoodsTotal() {
        List<GoodsDTO> goodsDTOS = warehouseService.getTotalByGoods();
        return Response.ok(goodsDTOS).build();
    }

    @RequestMapping(value = "/warehouse/goods/singleNum", method = RequestMethod.GET)
    @ResponseBody
    public Response getSingleGoodsNum(HttpServletRequest request) {
        int goodsId = Integer.parseInt(request.getHeader("goodsId"));
        int result = warehouseService.getNumByGoods(goodsId);
        return Response.ok(result).build();
    }

    @RequestMapping(value = "/warehouse/purchase/store", method = RequestMethod.POST)
    @ResponseBody
    public Response storePurchase(@RequestBody PurchaseStoreDTO purchaseStoreDTO) {
        Date date = new Date();
        purchaseStoreDTO.setPurchase_storeTime(concreteDataFormat.DateToString(date));
        int newStore = warehouseService.storePurchase(purchaseStoreDTO);
        if(newStore > 0) {
            return Response.ok(new SimpleResponse(SimpleResponse.OK,Integer.toString(newStore))).build();
        }
        return Response.ok(new SimpleResponse(SimpleResponse.ERROR,"添加存储分配失败！")).build();
    }

    @RequestMapping(value = "/warehouse/purchase/storeList", method = RequestMethod.GET)
    @ResponseBody
    public Response getPurchaseStore() {
        List<PurchaseStoreDTO> purchaseStoreDTOS = warehouseService.getPurchaseStore();
        return Response.ok(purchaseStoreDTOS).build();
    }

    @RequestMapping(value = "/warehouse/purchase/storeByGoods", method = RequestMethod.GET)
    @ResponseBody
    public Response getPurchaseStoreByGoods(HttpServletRequest request) {
        int goodsid = Integer.parseInt(request.getHeader("goods_id"));
        List<PurchaseStoreDTO> purchaseStoreDTOS = warehouseService.getPurchaseStoreByGoods(goodsid);
        return Response.ok(purchaseStoreDTOS).build();
    }

    @RequestMapping(value = "/warehouse/purchase/storeUpdate", method = RequestMethod.PUT)
    @ResponseBody
    public Response updatePurchaseStore(@RequestBody PurchaseStoreDTO purchaseStoreDTO) {
        return Response.ok().build();
    }

    @RequestMapping(value = "/warehouse/process/materialUse/add", method = RequestMethod.POST)
    @ResponseBody
    public Response addProcessMaterialUse(@RequestBody MaterialUseDTO materialUseDTO) {
        Date date = new Date();
        materialUseDTO.setUse_time(concreteDataFormat.DateToString(date));
        int newUse = warehouseService.useMaterial(materialUseDTO);
        if(newUse > 0)
            return Response.ok(new SimpleResponse(SimpleResponse.OK,Integer.toString(newUse))).build();
        return Response.ok(new SimpleResponse(SimpleResponse.ERROR,"调度失败！")).build();
    }

    @RequestMapping(value = "/warehouse/process/materialUse/list", method = RequestMethod.GET)
    @ResponseBody
    public Response getProcessMaterialUse() {
        return Response.ok().build();
    }

    @RequestMapping(value = "/warehouse/process/materialUse/processList", method = RequestMethod.GET)
    @ResponseBody
    public Response getProcessMaterialUseByProcess(HttpServletRequest request) {
        int processOrderId = Integer.parseInt(request.getHeader("processOrderId"));
        List<MaterialUseDTO> materialUseDTOS = warehouseService.getMaterialUseByList(processOrderId);
        return Response.ok(materialUseDTOS).build();
    }

    @RequestMapping(value = "/warehouse/process/materialUse/storeList", method = RequestMethod.GET)
    @ResponseBody
    public Response getProcessMaterialUseByStore(HttpServletRequest request) {
        int purchaseStoreId = Integer.parseInt(request.getHeader("purchaseStoreId"));
        List<MaterialUseDTO> materialUseDTOS = warehouseService.getMaterialUseByStore(purchaseStoreId);
        return Response.ok(materialUseDTOS).build();
    }
}
