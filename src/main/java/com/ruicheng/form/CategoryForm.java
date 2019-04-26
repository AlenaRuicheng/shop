package com.ruicheng.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by Ruicheng
 * on 2019/2/23.
 */
@Data
public class CategoryForm {
    private Integer categoryId;

    /** 类目名字. */
    @NotEmpty(message = "类目名称不能为空")
    private String categoryName;

    /** 类目编号. */
    @NotNull(message = "类型不能为空")
    private Integer categoryType;
}
