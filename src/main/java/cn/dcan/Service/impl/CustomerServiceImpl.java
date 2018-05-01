package cn.dcan.Service.impl;

import cn.dcan.Service.CustomerService;
import cn.dcan.dto.CustomerDTO;
import cn.dcan.entity.Customer;
import cn.dcan.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongc_000 on 2018/5/1.
 */
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getListByType(int type) {
        List<Customer> customers = customerMapper.selectByType(type);
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for(Customer customer : customers) {
            customerDTOS.add(entityToDto(customer));
        }
        return customerDTOS;
    }

    @Override
    public int addCustomer(CustomerDTO customerDTO) {
        Customer customer = dtoToEntity(customerDTO);
        int count = customerMapper.insertSelective(customer);
        return customer.getId();
    }

    @Override
    public List<CustomerDTO> getListByProvide(int type) {
        List<Customer> customers = customerMapper.selectByProvide(type);
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for(Customer customer : customers) {
            customerDTOS.add(entityToDto(customer));
        }
        return customerDTOS;
    }

    private Customer dtoToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setType(customerDTO.getType());
        Integer provideType = customerDTO.getProvideType();
        if(provideType != null) {
            customer.setProvidetype(customerDTO.getProvideType());
        }
        customer.setName(customerDTO.getName());
        customer.setManager(customerDTO.getManager());
        customer.setTelephone(customerDTO.getTelephone());
        if(customerDTO.getEmail() != null) {
            customer.setEmail(customerDTO.getEmail());
        }
        customer.setAddress(customerDTO.getAddress());
        return customer;
    }

    private CustomerDTO entityToDto(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setType(customer.getType());
        customerDTO.setName(customer.getName());
        Integer provideType = customer.getProvidetype();
        if(provideType != null) {
            customerDTO.setProvideType(customer.getProvidetype());
        }
        customerDTO.setManager(customer.getManager());
        customerDTO.setTelephone(customer.getTelephone());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setAddress(customer.getAddress());
        return customerDTO;
    }
}
