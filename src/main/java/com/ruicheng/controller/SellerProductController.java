package com.ruicheng.controller;

import com.ruicheng.entity.ProductCategory;
import com.ruicheng.entity.ProductInfo;
import com.ruicheng.enums.ProductStatusEnum;
import com.ruicheng.exceptions.SellException;
import com.ruicheng.form.ProductForm;
import com.ruicheng.service.interfaces.CategoryService;
import com.ruicheng.service.interfaces.ProductService;
import com.ruicheng.util.KeyGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 商品列表
     * @param page  查询第几页数据， 从第一页开始
     * @param pageSize  每页数据个数
     * @return 订单列表视图
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1")Integer page,
                             @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                             Map<String, Object> model){
        PageRequest request = PageRequest.of(page - 1, pageSize);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        model.put("productInfoPage", productInfoPage);
        model.put("currentPage", page);
        model.put("pageSize", pageSize);
        model.put("productLack", ProductStatusEnum.OUT_OF_STOCK.getMessage());
        return new ModelAndView("product/list", model);
    }

    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId")String productId,
                               Map<String, Object> model){
        ProductInfo productInfo = null;
        try {
            productInfo = productService.onSale(productId);
        }catch (SellException se){
            model.put("msg", se.getMessage());
            model.put("redirect_uri", "list");
            return new ModelAndView("components/error", model);
        }

        model.put("redirect_uri", "list");
        model.put("msg", "商品上架成功");
        return new ModelAndView("components/succeed", model);
    }

    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId")String productId,
                               Map<String, Object> model){
        ProductInfo productInfo = null;
        try {
            productInfo = productService.offSale(productId);
        }catch (SellException se){
            model.put("msg", se.getMessage());
            model.put("redirect_uri", "list");
            return new ModelAndView("components/error", model);
        }

        model.put("redirect_uri", "list");
        model.put("msg", "商品下架成功");
        return new ModelAndView("components/succeed", model);
    }


    /**
     * 新增商品
     * @param productId
     * @param model
     * @return
     */
    @GetMapping("/new_product")
    public ModelAndView increaseProduct(@RequestParam(value = "productId", required = false)String productId,
                                Map<String, Object> model){
        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            model.put("productInfo", productInfo);
        }

        List<ProductCategory> categoryList = categoryService.findAll();
        model.put("categoryList", categoryList);

        return new ModelAndView("product/newProduct", model);
    }

    /**
     * 商品信息保存和更新
     * @param productForm
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String, Object> model){

        if(bindingResult.hasErrors()){
            model.put("msg", bindingResult.getFieldError().getDefaultMessage());
            model.put("redirect_uri", "new_product");
            return new ModelAndView("components/error", model);
        }

        ProductInfo productInfo = new ProductInfo();
        String productId = productForm.getProductId();
        try {
            //如果是新增的商品需要设置商品ID
            if(StringUtils.isEmpty(productId)){
                productForm.setProductId(KeyGenerator.genUniqueKey());
            }else{
                productInfo = productService.findOne(productId);
            }
            BeanUtils.copyProperties(productForm, productInfo);
            productService.save(productInfo);
        }catch (SellException se){
            model.put("msg", se.getMessage());
            model.put("redirect_uri", "new_product");
            return new ModelAndView("components/error", model);
        }
        model.put("msg", "商品信息更新成功");
        model.put("redirect_uri", "list");
        return new ModelAndView("components/succeed", model);
    }
}
