package cn.dcan.controller;

import cn.dcan.Service.ProcessService;
import cn.dcan.dto.ProcessDTO;
import cn.dcan.dto.ProcessOrderDTO;
import cn.dcan.dto.SampleDTO;
import cn.dcan.constrain.*;
import cn.dcan.entity.Process;
import cn.dcan.entity.ProcessOrder;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Created by dongc_000 on 2018/5/13.
 */
@RestController
public class ProcessController {

    @Autowired
    ProcessService processService;

    private ConcreteDataFormat concreteDataFormat = new ConcreteDataFormat();

    @RequestMapping(value = "/process/sample/productList", method = RequestMethod.GET)
    @ResponseBody
    public Response getSampleByProduct(HttpServletRequest request) {
        int product = Integer.parseInt(request.getHeader("sample_productId"));
        List<SampleDTO> sampleDTOS = processService.getSampleByProduct(product);
        return Response.ok(sampleDTOS).build();
    }

    @RequestMapping(value = "/process/sample/add", method = RequestMethod.POST)
    @ResponseBody
    public Response addSample(@RequestBody SampleDTO sampleDTO) {
        int newSample = processService.addSample(sampleDTO);
        if(newSample > 0)
            return Response.ok(new SimpleResponse(SimpleResponse.OK,Integer.toString(newSample))).build();
        return Response.ok(new SimpleResponse(SimpleResponse.ERROR, "添加失败！")).build();
    }

    @RequestMapping(value = "/process/process/list", method = RequestMethod.GET)
    @ResponseBody
    public Response getProcessList() {
        List<ProcessDTO> processDTOS = processService.getAllProcess();
        return Response.ok(processDTOS).build();
    }

    @RequestMapping(value = "/process/process/add", method = RequestMethod.POST)
    @ResponseBody
    public Response addProcess(@RequestBody ProcessDTO processDTO) {
        int newProcess = processService.addProcess(processDTO);
        if(newProcess > 0)
            return Response.ok(new SimpleResponse(SimpleResponse.OK, Integer.toString(newProcess))).build();
        return Response.ok(new SimpleResponse(SimpleResponse.ERROR, "增加处理失败！")).build();
    }

    @RequestMapping(value = "/process/process/update", method = RequestMethod.PUT)
    @ResponseBody
    public Response updateProcess(@RequestBody ProcessDTO processDTO) {
        int count = processService.updateProcess(processDTO);
        if(count > 0) {
            return Response.ok(new SimpleResponse(SimpleResponse.OK,"更新成功!")).build();
        }
        return Response.ok(new SimpleResponse(SimpleResponse.ERROR,"更新失败！")).build();
    }

    //增加加工材料单
    @RequestMapping(value = "/process/process/material/add", method = RequestMethod.POST)
    @ResponseBody
    public Response addProcessMaterialList(ProcessOrderDTO processOrderDTO) {
        Date date = new Date();
        processOrderDTO.setList_time(concreteDataFormat.DateToString(date));
        int newList = processService.addMaterialList(processOrderDTO);
        if(newList > 0)
            return Response.ok(new SimpleResponse(SimpleResponse.OK,Integer.toString(newList))).build();
        return Response.ok(new SimpleResponse(SimpleResponse.ERROR,"增加材料单失败")).build();
    }

    //根据状态查询加工材料单
    @RequestMapping(value = "/process/process/material/stateList", method = RequestMethod.GET)
    @ResponseBody
    public Response getProcessMaterialListByState(HttpServletRequest request) {
        int state = Integer.parseInt(request.getHeader("list_state"));
        List<ProcessOrderDTO> processOrderDTOS = processService.getMaterialListByState(state);
        return Response.ok(processOrderDTOS).build();
    }

    //根据加工处理查询加工材料单
    @RequestMapping(value = "/process/process/material/processList", method = RequestMethod.GET)
    @ResponseBody
    public Response getProcessMaterialListByProcess(HttpServletRequest request) {
        int processId = Integer.parseInt(request.getHeader("list_process"));
        List<ProcessOrderDTO> processOrderDTOS = processService.getMaterialListByProcess(processId);
        return Response.ok(processOrderDTOS).build();
    }
}
