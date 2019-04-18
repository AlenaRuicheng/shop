package com.ruicheng.service.impl;

import com.ruicheng.dao.ProductCategoryDao;
import com.ruicheng.entity.ProductCategory;
import com.ruicheng.enums.ResultEnum;
import com.ruicheng.exceptions.SellException;
import com.ruicheng.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Ruicheng
 * on 2019/2/16.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryDao dao;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        Optional<ProductCategory> optional  = dao.findById(categoryId);
        if (!optional.isPresent()){
            throw new SellException(ResultEnum.NO_SUCH_CATEGORY);
        }
        return optional.get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return dao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return dao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return dao.save(productCategory);
    }
}
