package cn.dcan.Service.impl;

import cn.dcan.Service.GoodsService;
import cn.dcan.dto.GoodsDTO;
import cn.dcan.dto.ProductDTO;
import cn.dcan.entity.Goods;
import cn.dcan.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongc_000 on 2018/5/2.
 */
@Service
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<GoodsDTO> getMaterialByType(int type) {
        List<Goods> goodsList = goodsMapper.selectByType(type);
        List<GoodsDTO> goodsDTOS = new ArrayList<>();
        for(Goods goods : goodsList) {
            goodsDTOS.add(entityToDto(goods));
        }
        return goodsDTOS;
    }

    @Override
    public int addGoods(GoodsDTO goodsDTO) {
        Goods goods = dtoToEntity(goodsDTO);
        int count = goodsMapper.insertSelective(goods);
        return goods.getId();
    }

    private GoodsDTO entityToDto(Goods goods) {
        GoodsDTO goodsDTO = new GoodsDTO();
        goodsDTO.setGoods_id(goods.getId());
        goodsDTO.setGoods_type(goods.getType());
        goodsDTO.setGoods_name(goods.getName());
        return goodsDTO;
    }

    private Goods dtoToEntity(GoodsDTO goodsDTO) {
        Goods goods = new Goods();
        goods.setType(goodsDTO.getGoods_type());
        goods.setName(goodsDTO.getGoods_name());
        return goods;
    }
}
