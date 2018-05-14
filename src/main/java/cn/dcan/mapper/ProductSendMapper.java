package cn.dcan.mapper;

import cn.dcan.entity.ProductSend;

import java.util.List;

public interface ProductSendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductSend record);

    int insertSelective(ProductSend record);

    ProductSend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductSend record);

    int updateByPrimaryKey(ProductSend record);

    List<ProductSend> selectByStore(Integer storeid);
}