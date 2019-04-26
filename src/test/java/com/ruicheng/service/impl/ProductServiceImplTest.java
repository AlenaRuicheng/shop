package com.ruicheng.service.impl;

import com.ruicheng.dto.CartDTO;
import com.ruicheng.entity.ProductInfo;
import com.ruicheng.service.interfaces.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ruicheng
 * on 2019/02/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;

    @Test
    public void findOne() throws Exception {
        productService.findOne("12586578");
    }

    @Test
    public void findAvailableProducts() throws Exception {
        List<ProductInfo> productInfoList = productService.findAvailableProducts();
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest request = PageRequest.of(0, 2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        System.out.println(productInfoPage.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("129632");
        productInfo.setProductName("馄饨");
        productInfo.setProductPrice(new BigDecimal("4.5"));
        productInfo.setProductIcon("http://xxx1.jpg");
        productInfo.setProductStock(55);
        productInfo.setProductDescription("味道鲜");
        productInfo.setCategoryType(0);

        productService.save(productInfo);
    }

    @Test
    public void increaseStock() throws Exception {
        List<CartDTO> cartDTOList = Arrays.asList(new CartDTO("129632", 50),
                new CartDTO("12586578", 30));
        productService.increaseStock(cartDTOList);
    }

    @Test
    public void onSale() throws Exception {
        ProductInfo productInfo = productService.onSale("129632");
        System.out.println(productInfo);
    }

    @Test
    public void offSale() throws Exception {
        ProductInfo productInfo = productService.offSale("129632");
        System.out.println(productInfo);
    }

}