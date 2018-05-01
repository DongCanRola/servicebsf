package cn.dcan.Service;

import cn.dcan.dto.CustomerDTO;

import java.util.List;

/**
 * Created by dongc_000 on 2018/5/1.
 */
public interface CustomerService {

    //根据客户类型获取客户列表
    List<CustomerDTO> getListByType(int type);

    int addCustomer(CustomerDTO customerDTO);
}
