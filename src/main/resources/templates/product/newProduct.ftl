<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>SHOP - 添加商品</title>
    <link href="/shop/css/style.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/shop/css/animate.css">
    <link rel="stylesheet" type="text/css" href="/shop/css/iconfont-seller.css">
    <link href="/shop/css/bootstrap.homepage.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/shop/css/jquery-accordion-menu.css">
    <link rel="stylesheet" type="text/css" href="/shop/css/nav.css">
    <script src="/shop/js/jquery.min.js"></script>
    <script src="/shop/js/bootstrap.min.js"></script>
    <script src="/shop/js/jquery-accordion-menu.js" type="text/javascript"></script>
    <style>
        table td, table th {
            white-space: nowrap;
        }
        label{
            float: left;
        }
    </style>
</head>
<body>
<div class="root">
    <div class="left-wrapper">
        <#include "../components/nav.ftl">
    </div>
    <div class="right-wrapper">
        <div class="wallpaper-wrapper"></div>
        <div class="background-wrapper"></div>
        <div class="content-wrapper">
            <div id="page-content-wrapper">
                <div class="container-fluid">
                    <div class="row clearfix">
                        <div class="col-md-6 column col-md-offset-3">
                            <form role="form" method="post" action="save">
                                <div class="form-group">
                                    <label>名称</label>
                                    <input type="text" name="productName" class="form-control" value="${(productInfo.productName)!''}"/>
                                </div>
                                <div class="form-group">
                                    <label>单价</label>
                                    <input type="text" name="productPrice" class="form-control" value="${(productInfo.productPrice)!''}"/>
                                </div>
                                <div class="form-group">
                                    <label>库存</label>
                                    <input type="number" name="productStock" class="form-control" value="${(productInfo.productStock)!''}"/>
                                </div>
                                <div class="form-group">
                                    <label>描述</label>
                                    <input type="text" name="productDescription" class="form-control" value="${(productInfo.productDescription)!''}"/>
                                </div>
                                <div class="form-group">
                                    <label>图片</label>
                                    <img width="200px" height="200px" src="${(productInfo.productIcon)!''}">
                                    <input type="text" name="productIcon" class="form-control" value="${(productInfo.productIcon)!''}"/>
                                </div>
                                <div class="form-group">
                                    <label>商品类别</label>
                                    <select name="categoryType" class="col-md-5 column">
                                    <#list categoryList as category>
                                        <option value="${category.categoryType}"
                                            <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                                selected
                                            </#if>
                                        >${category.categoryName}
                                        </option>
                                    </#list>
                                    </select>
                                </div>
                                <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
                                <button type="submit" class="btn btn-default btn-success clearfix" style="margin-top: 30px">提交</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $("#jquery-accordion-menu").jqueryAccordionMenu();
    });

    $(function () {
        currentItem();
    });

    $(function () {
        $("#product, #category").click(function () {
            $("#demo-list li.active").removeClass("active");
            $(this).addClass("active");
        });
    });
</script>
</body>
</html>