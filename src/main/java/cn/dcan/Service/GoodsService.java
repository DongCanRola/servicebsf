package cn.dcan.Service;

import java.util.*;

import cn.dcan.dto.GoodsDTO;
import cn.dcan.dto.ProductDTO;
import cn.dcan.entity.Product;

/**
 * Created by dongc_000 on 2018/5/2.
 */
public interface GoodsService {

    List<GoodsDTO> getMaterialByType(int type);

    int addGoods(GoodsDTO goodsDTO);
}
