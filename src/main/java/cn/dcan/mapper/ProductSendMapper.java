package cn.dcan.mapper;

import cn.dcan.entity.ProductSend;

public interface ProductSendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductSend record);

    int insertSelective(ProductSend record);

    ProductSend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductSend record);

    int updateByPrimaryKey(ProductSend record);
}