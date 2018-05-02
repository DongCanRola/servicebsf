package cn.dcan.Service;

import cn.dcan.dto.ProductDTO;

import java.util.List;

/**
 * Created by dongc_000 on 2018/5/2.
 */
public interface ProductService {

    List<ProductDTO> getProduct();

    int addProduct(ProductDTO productDTO);
}
