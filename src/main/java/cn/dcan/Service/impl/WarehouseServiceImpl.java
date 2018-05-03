package cn.dcan.Service.impl;

import cn.dcan.Service.WarehouseService;
import cn.dcan.dto.WarehouseDTO;
import cn.dcan.entity.Warehouse;
import cn.dcan.mapper.WarehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongc_000 on 2018/5/3.
 */
@Service
public class WarehouseServiceImpl implements WarehouseService{

    @Autowired
    WarehouseMapper warehouseMapper;

    @Override
    public List<WarehouseDTO> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseMapper.selectAll();
        List<WarehouseDTO> warehouseDTOS = new ArrayList<>();
        for(Warehouse warehouse : warehouses) {
            warehouseDTOS.add(entityToDto(warehouse));
        }
        return warehouseDTOS;
    }

    @Override
    public int addWarehouse(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = dtoToEntity(warehouseDTO);
        int count = warehouseMapper.insertSelective(warehouse);
        return warehouse.getId();
    }

    private Warehouse dtoToEntity(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseDTO.getWarehouse_name());
        warehouse.setLocation(warehouseDTO.getWarehouse_location());
        warehouse.setSpare(warehouseDTO.getWarehouse_spare());
        return warehouse;
    }

    private WarehouseDTO entityToDto(Warehouse warehouse) {
        WarehouseDTO warehouseDTO = new WarehouseDTO();
        warehouseDTO.setWarehouse_id(warehouse.getId());
        warehouseDTO.setWarehouse_name(warehouse.getName());
        warehouseDTO.setWarehouse_location(warehouse.getLocation());
        warehouseDTO.setWarehouse_spare(warehouse.getSpare());
        return warehouseDTO;
    }
}
