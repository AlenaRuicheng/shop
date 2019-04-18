package com.ruicheng.dao;

import com.ruicheng.entity.ProductInfo;
import com.ruicheng.util.KeyGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Ruicheng
 * 2019/02/08.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {
    @Autowired
    ProductInfoDao dao;

    /**查询所有在架的商品.*/
    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> productInfoList = dao.findByProductStatus(0);
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1551773196842944997");
        productInfo.setProductName("银耳粥");
        productInfo.setProductPrice(new BigDecimal(5.3));
        productInfo.setProductIcon("http://xxx1.jpg");
        productInfo.setProductStock(77);
        productInfo.setProductDescription("恩不错噢");
        productInfo.setCategoryType(1);

        dao.save(productInfo);
    }

}