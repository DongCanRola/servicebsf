package cn.dcan.controller;

import cn.dcan.Service.WarehouseService;
import cn.dcan.constrain.*;

import cn.dcan.dto.WarehouseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.*;

/**
 * Created by dongc_000 on 2018/5/3.
 */
@RestController
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

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
}
