package cn.dcan.controller;

import cn.dcan.Service.GoodsService;
import cn.dcan.Service.ProductService;
import cn.dcan.dto.GoodsDTO;
import cn.dcan.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.util.List;

import cn.dcan.constrain.*;

/**
 * Created by dongc_000 on 2018/5/1.
 */
@RestController
public class GoodsController {

    @Autowired
    GoodsService goodsService;
    @Autowired
    ProductService productService;

    @RequestMapping(value = "/goods/material/list", method = RequestMethod.GET)
    @ResponseBody
    public Response getGoods(HttpServletRequest request) {
        int type = Integer.parseInt(request.getHeader("materialType"));
        List<GoodsDTO> goodsDTOS = goodsService.getMaterialByType(type);
        return Response.ok(goodsDTOS).build();
    }

    @RequestMapping(value = "/goods/material/add", method = RequestMethod.POST)
    @ResponseBody
    public Response addGoods(@RequestBody GoodsDTO goodsDTO) {
        int newGoods = goodsService.addGoods(goodsDTO);
        return Response.ok(new SimpleResponse(SimpleResponse.OK,Integer.toString(newGoods))).build();
    }

    @RequestMapping(value = "/goods/product/list", method = RequestMethod.GET)
    @ResponseBody
    public Response getProductList() {
        List<ProductDTO> productDTOS = productService.getProduct();
        return Response.ok(productDTOS).build();
    }

    @RequestMapping(value = "/goods/product/add", method = RequestMethod.POST)
    @ResponseBody
    public Response addProduct(@RequestBody ProductDTO productDTO) {
        int newProduct = productService.addProduct(productDTO);
        return Response.ok(new SimpleResponse(SimpleResponse.OK,Integer.toString(newProduct))).build();
    }
}
