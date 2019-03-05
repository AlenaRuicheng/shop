package com.ruicheng.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Ruicheng
 * on 2019/2/23.
 */
@Data
public class ProductForm {


    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 商品图标(链接). */
    private String productIcon;

    /** 类目编号. */
    private Integer categoryType;
}
