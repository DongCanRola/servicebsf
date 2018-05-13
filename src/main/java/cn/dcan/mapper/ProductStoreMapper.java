package cn.dcan.mapper;

import cn.dcan.entity.ProductStore;

public interface ProductStoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductStore record);

    int insertSelective(ProductStore record);

    ProductStore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductStore record);

    int updateByPrimaryKey(ProductStore record);
}