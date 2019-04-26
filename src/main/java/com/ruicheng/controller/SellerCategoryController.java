package com.ruicheng.controller;

import com.ruicheng.entity.ProductCategory;
import com.ruicheng.exceptions.SellException;
import com.ruicheng.form.CategoryForm;
import com.ruicheng.service.interfaces.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by Ruicheng
 * on 2019/4/21.
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> model){
        List<ProductCategory> categoryList = categoryService.findAll();
        model.put("categoryList", categoryList);
        return new ModelAndView("category/list", model);
    }

    @GetMapping("/new_category")
    public ModelAndView modifyCategory(@RequestParam(value = "categoryId", required = false)Integer categoryId,
                                       Map<String, Object> model){
        if (categoryId != null){
            ProductCategory productCategory = categoryService.findOne(categoryId);
            model.put("productCategory", productCategory);
        }
        return new ModelAndView("category/newCategory", model);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String, Object> model){
        if(bindingResult.hasErrors()){
            model.put("msg", bindingResult.getFieldError().getDefaultMessage());
            model.put("redirect_uri", "new_category");
            return new ModelAndView("components/error", model);
        }

        Integer categoryId = categoryForm.getCategoryId();
        ProductCategory productCategory = new ProductCategory();
        try {
            if(categoryId != null){
                productCategory = categoryService.findOne(categoryId);
            }
            BeanUtils.copyProperties(categoryForm, productCategory);
            categoryService.save(productCategory);
        }catch (SellException se){
            model.put("msg", se.getMessage());
            model.put("redirect_uri", "new_category");
            return new ModelAndView("components/error", model);
        }

        model.put("msg", "类目信息修改成功");
        model.put("redirect_uri", "list");
        return new ModelAndView("components/succeed", model);
    }
}
