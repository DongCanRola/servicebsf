package cn.dcan.Service.impl;

import cn.dcan.Service.ProcessService;
import cn.dcan.dto.ProcessDTO;
import cn.dcan.dto.SampleDTO;
import cn.dcan.entity.Process;
import cn.dcan.entity.SaleOrder;
import cn.dcan.entity.Sample;
import cn.dcan.mapper.ProcessMapper;
import cn.dcan.mapper.SaleOrderMapper;
import cn.dcan.mapper.SampleMapper;
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

    @Override
    public int addProcess(ProcessDTO processDTO) {
        Process process = processDtoToEntity(processDTO);
        int count = processMapper.insertSelective(process);
        if(process.getState() == 1) {
            SaleOrder saleOrder = new SaleOrder();
            saleOrder.setId(process.getSaleid());
            saleOrder.setState(3);
            saleOrderMapper.updateByPrimaryKeySelective(saleOrder);
        }
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
}
