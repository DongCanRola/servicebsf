package cn.dcan.Service.impl;

import cn.dcan.Service.ProcessService;
import cn.dcan.constrain.ConcreteDataFormat;
import cn.dcan.dto.ProcessDTO;
import cn.dcan.dto.ProcessOrderDTO;
import cn.dcan.dto.SampleDTO;
import cn.dcan.entity.*;
import cn.dcan.entity.Process;
import cn.dcan.mapper.*;
import org.apache.tomcat.jni.Proc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongc_000 on 2018/5/13.
 */
@Service
public class ProcessServiceImpl implements ProcessService{

    @Autowired
    SampleMapper sampleMapper;
    @Autowired
    ProcessMapper processMapper;
    @Autowired
    SaleOrderMapper saleOrderMapper;
    @Autowired
    ProcessOrderMapper processOrderMapper;
    @Autowired
    MaterialUseMapper materialUseMapper;

    private ConcreteDataFormat concreteDataFormat = new ConcreteDataFormat();

    @Override
    public int addSample(SampleDTO sampleDTO) {
        Sample sample = sampleDtoToEntity(sampleDTO);
        int count = sampleMapper.insertSelective(sample);
        return sample.getId();
    }

    @Override
    public List<SampleDTO> getSampleByProduct(int productid) {
        List<Sample> samples = sampleMapper.selectByProduct(productid);
        List<SampleDTO> sampleDTOS = new ArrayList<>();
        for(Sample sample : samples) {
            sampleDTOS.add(sampleEntityToDto(sample));
        }
        return sampleDTOS;
    }

    //增加生产处理
    @Override
    public int addProcess(ProcessDTO processDTO) {
        Process process = processDtoToEntity(processDTO);
        int count = processMapper.insertSelective(process);
        //更新销售状态，设置成本
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setId(process.getSaleid());
        saleOrder.setState(3);
        Sample sample = sampleMapper.selectByPrimaryKey(process.getSampleid());
        double cost = sample.getMaterialcost() + sample.getProcesscost() + sample.getHumancost();
        saleOrder.setCost(cost);
        saleOrderMapper.updateByPrimaryKeySelective(saleOrder);
        //if(process.getState() == 1) {

        //}
        return process.getId();
    }

    @Override
    public List<ProcessDTO> getAllProcess() {
        List<Process> processes = processMapper.selectAll();
        List<ProcessDTO> processDTOS = new ArrayList<>();
        for(Process process : processes) {
            processDTOS.add(processEntityToDto(process));
        }
        return processDTOS;
    }

    @Override
    public int updateProcess(ProcessDTO processDTO) {
        Process process = processDtoToEntity(processDTO);
        int count = processMapper.updateByPrimaryKeySelective(process);
        if(processDTO.getProcess_state() == 4) {
            SaleOrder saleOrder = new SaleOrder();
            saleOrder.setId(processDTO.getSale_orderId());
            saleOrder.setState(5);
            saleOrderMapper.updateByPrimaryKeySelective(saleOrder);
        }
        if(processDTO.getProcess_state() == 5) {
            SaleOrder saleOrder = new SaleOrder();
            saleOrder.setId(processDTO.getSale_orderId());
            saleOrder.setState(6);
            saleOrderMapper.updateByPrimaryKeySelective(saleOrder);
        }
        return count;
    }

    @Override
    public int addMaterialList(ProcessOrderDTO processOrderDTO) {
        ProcessOrder processOrder = poDtoToEntity(processOrderDTO);
        int count = processOrderMapper.insertSelective(processOrder);
        return processOrder.getId();
    }

    @Override
    public List<ProcessOrderDTO> getMaterialListByState(int state) {
        List<ProcessOrder> processOrders = processOrderMapper.selectByState(state);
        //List<ProcessOrderDTO> processOrderDTOS = ;
        return calculateRemaining(processOrders);
    }

    @Override
    public List<ProcessOrderDTO> getMaterialListByProcess(int processid) {
        List<ProcessOrder> processOrders = processOrderMapper.selectByProcess(processid);
        //List<ProcessOrderDTO> processOrderDTOS = calculateRemaining(processOrders);
        return calculateRemaining(processOrders);
    }

    private List<ProcessOrderDTO> calculateRemaining(List<ProcessOrder> processOrders) {
        List<ProcessOrderDTO> processOrderDTOS = new ArrayList<>();
        for(ProcessOrder processOrder : processOrders) {
            int processOrderId = processOrder.getId();
            List<MaterialUse> materialUses = materialUseMapper.selectByProcessOrder(processOrderId);
            int providedNum = 0;
            for(MaterialUse materialUse : materialUses) {
                providedNum = providedNum + materialUse.getUsenum();
            }
            int remaining = processOrder.getNum() - providedNum;
            processOrderDTOS.add(poEntityToDto(processOrder, remaining));
        }
        return processOrderDTOS;
    }

    private Sample sampleDtoToEntity(SampleDTO sampleDTO) {
        Sample sample = new Sample();
        if(sampleDTO.getSample_id() != 0) {
            sample.setId(sampleDTO.getSample_id());
        }
        if(sampleDTO.getSample_productId() != 0) {
            sample.setProductid(sampleDTO.getSample_productId());
        }
        sample.setDescription(sampleDTO.getSample_description());
        if(sampleDTO.getSample_materialCost() != 0.0) {
            sample.setMaterialcost(sampleDTO.getSample_materialCost());
        }
        if(sampleDTO.getSample_processCost() != 0) {
            sample.setProcesscost(sampleDTO.getSample_processCost());
        }
        if(sampleDTO.getSample_humanCost() != 0) {
            sample.setHumancost(sampleDTO.getSample_humanCost());
        }
        if(sampleDTO.getSample_userId() != 0) {
            sample.setUser(sampleDTO.getSample_userId());
        }
        return sample;
    }
    private SampleDTO sampleEntityToDto(Sample sample) {
        SampleDTO sampleDTO = new SampleDTO();
        sampleDTO.setSample_id(sample.getId());
        sampleDTO.setSample_productId(sample.getProductid());
        sampleDTO.setSample_description(sample.getDescription());
        if(sample.getMaterialcost() != null) {
            sampleDTO.setSample_materialCost(sample.getMaterialcost());
        }
        if(sample.getProcesscost() != null) {
            sampleDTO.setSample_processCost(sample.getProcesscost());
        }
        if(sample.getHumancost() != null) {
            sampleDTO.setSample_humanCost(sample.getHumancost());
        }
        if(sample.getUser() != null) {
            sampleDTO.setSample_userId(sample.getUser());
        }
        return sampleDTO;
    }

    private Process processDtoToEntity(ProcessDTO processDTO) {
        Process process = new Process();
        if(processDTO.getProcess_id() != 0) {
            process.setId(processDTO.getProcess_id());
        }
        if(processDTO.getSale_orderId() != 0) {
            process.setSaleid(processDTO.getSale_orderId());
        }
        if(processDTO.getSample_id() != 0) {
            process.setSampleid(processDTO.getSample_id());
        }
        if(processDTO.getProcess_state() != 0) {
            process.setState(processDTO.getProcess_state());
        }
        if(processDTO.getProcess_userId() != 0) {
            process.setUser(processDTO.getProcess_userId());
        }
        return process;
    }
    private ProcessDTO processEntityToDto(Process process) {
        ProcessDTO processDTO = new ProcessDTO();
        processDTO.setProcess_id(process.getId());
        processDTO.setSale_orderId(process.getSaleid());
        processDTO.setSample_id(process.getSampleid());
        processDTO.setProcess_state(process.getState());
        processDTO.setProcess_userId(process.getUser());
        return processDTO;
    }

    private ProcessOrder poDtoToEntity(ProcessOrderDTO processOrderDTO) {
        ProcessOrder processOrder = new ProcessOrder();
        if(processOrderDTO.getList_id() != 0) {
            processOrder.setId(processOrderDTO.getList_id());
        }
        if(processOrderDTO.getList_process() != 0) {
            processOrder.setProcessid(processOrderDTO.getList_process());
        }
        if(processOrderDTO.getList_goods() != 0) {
            processOrder.setGoodsid(processOrderDTO.getList_goods());
        }
        if(processOrderDTO.getList_total() != 0) {
            processOrder.setNum(processOrderDTO.getList_total());
        }
        if(processOrderDTO.getList_state() != 0) {
            processOrder.setState(processOrderDTO.getList_state());
        }
        processOrder.setPlantime(concreteDataFormat.StringToDate(processOrderDTO.getList_time()));
        return processOrder;
    }
    private ProcessOrderDTO poEntityToDto(ProcessOrder processOrder, int remaining) {
        ProcessOrderDTO processOrderDTO = new ProcessOrderDTO();
        processOrderDTO.setList_id(processOrder.getId());
        processOrderDTO.setList_process(processOrder.getProcessid());
        processOrderDTO.setList_goods(processOrder.getGoodsid());
        processOrderDTO.setList_total(processOrder.getNum());
        processOrderDTO.setList_remaining(remaining);
        processOrderDTO.setList_state(processOrder.getState());
        processOrderDTO.setList_time(concreteDataFormat.DateToString(processOrder.getPlantime()));
        return processOrderDTO;
    }
}
