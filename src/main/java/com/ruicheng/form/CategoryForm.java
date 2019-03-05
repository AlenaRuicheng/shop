package com.ruicheng.form;

import lombok.Data;

/**
 * Created by Ruicheng
 * on 2019/2/23.
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
