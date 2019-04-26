<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>SHOP - 商品列表</title>
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
            text-align: left;
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
            <div id="page-content-wrapper" style="padding-top: 10px;">
                <div class="container-fluid">
                    <div class="row clearfix">
                        <div class="col-md-10 column" style="margin-right: 90px;">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th style="min-width:140px">商品ID</th>
                                    <th style="min-width:110px">名称</th>
                                    <th>图片</th>
                                    <th>单价(元)</th>
                                    <th>库存</th>
                                    <th style="min-width:200px">描述</th>
                                    <th>类目</th>
                                    <th>创建时间</th>
                                    <th>修改时间</th>
                                    <th colspan="2" style="text-align: center">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list productInfoPage.content as productInfo>
                                <tr>
                                    <td>${productInfo.productId}</td>
                                    <td>${productInfo.productName}</td>
                                    <td><img width="100px" height="100" src="${productInfo.productIcon}"></td>
                                    <td>${productInfo.productPrice}</td>
                                    <td>${productInfo.productStock}</td>
                                    <td>${productInfo.productDescription}</td>
                                    <td>${productInfo.categoryType}</td>
                                    <td>${productInfo.createTime}</td>
                                    <td>${productInfo.updateTime}</td>
                                    <td style="text-align: center">
                                        <button type="button"
                                                class="btn btn-default btn-primary btn-xs"
                                                style="outline: none"
                                                onclick="location.href='new_product?productId=${productInfo.productId}'">
                                            修改
                                        </button>
                                    </td>
                                    <td style="text-align: center">
                                        <#if productInfo.getProductStatusEnum().message == "${productLack}">
                                            <button type="button"
                                                    class="btn btn-success btn-xs"
                                                    style="outline: none"
                                                    onclick="location.href='on_sale?productId=${productInfo.productId}'">
                                                上架
                                            </button>
                                        <#else >
                                            <button type="button"
                                                    class="btn btn-danger btn-xs"
                                                    style="outline: none"
                                                    onclick="location.href='off_sale?productId=${productInfo.productId}'">
                                                下架
                                            </button>
                                        </#if>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                        <div >
                            <ul class="pagination">
                            <#if currentPage lte 1>
                                <li class="disabled"><a href="#">上一页</a></li>
                            <#else>
                                <li><a href="list?page=${currentPage-1}&size=${pageSize}">上一页</a></li>
                            </#if>

                            <#list 1..productInfoPage.getTotalPages() as index>
                                <#if index == currentPage>
                                    <li class="disabled"><a href="#">${index}</a></li>
                                <#else>
                                    <li><a href="list?page=${index}&size=${pageSize}">${index}</a></li>
                                </#if>

                            </#list>
                            <#if currentPage gte productInfoPage.getTotalPages()>
                                <li class="disabled"><a href="#">下一页</a></li>
                            <#else >
                                <li><a href="list?page=${currentPage+1}&size=${pageSize}">下一页</a></li>
                            </#if>
                            </ul>
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