package cn.dcan.Service.impl;

import cn.dcan.Service.SaleService;
import cn.dcan.dto.SaleDTO;
import cn.dcan.entity.Process;
import cn.dcan.entity.SaleOrder;
import cn.dcan.mapper.ProcessMapper;
import cn.dcan.mapper.SaleOrderMapper;
import cn.dcan.constrain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongc_000 on 2018/5/10.
 */
@Service
public class SaleServiceImpl implements SaleService{

    @Autowired
    SaleOrderMapper saleOrderMapper;
    @Autowired
    ProcessMapper processMapper;

    private ConcreteDataFormat concreteDataFormat = new ConcreteDataFormat();

    @Override
    public List<SaleDTO> getOrdersByState(int state) {
        List<SaleOrder> saleOrders = saleOrderMapper.selectByState(state);
        List<SaleDTO> saleDTOS = new ArrayList<>();
        for(SaleOrder saleOrder : saleOrders) {
            saleDTOS.add(saleEntityToDto(saleOrder));
        }
        return saleDTOS;
    }

    @Override
    public int addOrder(SaleDTO saleDTO) {
        SaleOrder saleOrder = saleDtoToEntity(saleDTO);
        int count = saleOrderMapper.insertSelective(saleOrder);
        return saleOrder.getId();
    }

    @Override
    public void updateOrder(SaleDTO saleDTO) {
        SaleOrder saleOrder = saleDtoToEntity(saleDTO);
        int result = saleOrderMapper.updateByPrimaryKeySelective(saleOrder);
        if(saleDTO.getSale_state() == 4) {
            Process process = processMapper.selectBySaleOrder(saleDTO.getSale_orderId());
            process.setState(6);
            processMapper.updateByPrimaryKeySelective(process);
        }
        System.out.println("update sale order result: " + result);
    }

    private SaleOrder saleDtoToEntity(SaleDTO saleDTO) {
        SaleOrder saleOrder = new SaleOrder();
        if(saleDTO.getSale_orderId() != 0) {
            saleOrder.setId(saleDTO.getSale_orderId());
        }
        saleOrder.setProductid(saleDTO.getSale_productId());
        if(saleDTO.getSale_num() != 0) {
            saleOrder.setNum(saleDTO.getSale_num());
        }
        if(saleDTO.getSale_cost() != 0.0) {
            saleOrder.setCost(saleDTO.getSale_cost());
        }
        if(saleDTO.getSale_price() != 0.0) {
            saleOrder.setPrice(saleDTO.getSale_price());
        }
        if(saleDTO.getSale_consumerId() != 0) {
            saleOrder.setConsumerid(saleDTO.getSale_consumerId());
        }
        if(saleDTO.getSale_state() != 0) {
            saleOrder.setState(saleDTO.getSale_state());
        }
        saleOrder.setOrdertime(concreteDataFormat.StringToDate(saleDTO.getSale_orderTime()));
        if(saleDTO.getSale_user() != 0) {
            saleOrder.setUserid(saleDTO.getSale_user());
        }
        return saleOrder;
    }

    private SaleDTO saleEntityToDto(SaleOrder saleOrder) {
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setSale_orderId(saleOrder.getId());
        saleDTO.setSale_productId(saleOrder.getProductid());
        if(saleOrder.getNum() != null) {
            saleDTO.setSale_num(saleOrder.getNum());
        }
        if(saleOrder.getCost() != null) {
            saleDTO.setSale_cost(saleOrder.getCost());
        }
        if(saleOrder.getPrice() != null) {
            saleDTO.setSale_price(saleOrder.getPrice());
        }
        if(saleOrder.getConsumerid() != null) {
            saleDTO.setSale_consumerId(saleOrder.getConsumerid());
        }
        if(saleOrder.getState() != null) {
            saleDTO.setSale_state(saleOrder.getState());
        }
        saleDTO.setSale_orderTime(concreteDataFormat.DateToString(saleOrder.getOrdertime()));
        if(saleOrder.getUserid() != null) {
            saleDTO.setSale_user(saleOrder.getUserid());
        }
        return saleDTO;
    }
}
