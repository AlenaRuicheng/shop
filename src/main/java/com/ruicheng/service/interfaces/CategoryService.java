package com.ruicheng.service.interfaces;


import com.ruicheng.entity.ProductCategory;

import java.util.List;

/**
 * Created by Ruicheng
 * on 2019/2/16.
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
