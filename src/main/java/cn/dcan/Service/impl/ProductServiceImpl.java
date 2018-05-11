package cn.dcan.Service.impl;

import cn.dcan.Service.ProductService;
import cn.dcan.dto.ProductDTO;
import cn.dcan.entity.Product;
import cn.dcan.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongc_000 on 2018/5/2.
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<ProductDTO> getProduct(int state) {
        List<Product> products = productMapper.selectByState(state);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product : products) {
            productDTOS.add(entityToDto(product));
        }
        return productDTOS;
    }

    @Override
    public int addProduct(ProductDTO productDTO) {
        Product product = dtoToEntity(productDTO);
        int count = productMapper.insertSelective(product);
        return product.getId();
    }

    private ProductDTO entityToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct_id(product.getId());
        productDTO.setProduct_name(product.getName());
        productDTO.setProduct_level(product.getLevel());
        productDTO.setProduct_color(product.getColor());
        productDTO.setProduct_style(product.getStyle());
        productDTO.setProduct_state(product.getState());
        return productDTO;
    }

    private Product dtoToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getProduct_name());
        product.setLevel(productDTO.getProduct_level());
        product.setColor(productDTO.getProduct_color());
        product.setStyle(productDTO.getProduct_style());
        product.setState(productDTO.getProduct_state());
        return product;
    }
}
