package com.ruicheng.dao;

import com.ruicheng.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Ruicheng
 * 2019/02/07.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {
    @Autowired
    private ProductCategoryDao dao;

    @Test
    public void findOne(){
        ProductCategory productCategory = dao.findById(1).get();
        Assert.assertNotEquals(null, productCategory);
    }

    @Test
    public void save(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(3);
        productCategory.setCategoryName("午餐");
        productCategory.setCategoryType(2);
        dao.save(productCategory);
    }

    @Test
    public void update(){
        ProductCategory productCategory = dao.findById(3).get();
        productCategory.setCategoryType(3);
        dao.save(productCategory);
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<Integer> integers = Arrays.asList(1,2,3,4);
        List<ProductCategory> productCategoryList = dao.findByCategoryTypeIn(integers);
        System.out.println("\n" + productCategoryList.size() + "\n");
        Assert.assertNotEquals(null, productCategoryList);
    }

}