<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Shop - 类目列表</title>
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
            <div id="page-content-wrapper" >
                <div class="container-fluid">
                    <div class="row clearfix">
                        <div class="col-md-8 column col-md-offset-2">
                            <table class="table table-bordered">
                                <thead>
                                <tr >
                                    <th width="220px">类目ID</th>
                                    <th width="150px">名称</th>
                                    <th>类型</th>
                                    <th width="150px">创建时间</th>
                                    <th width="150px">修改时间</th>
                                    <th style="text-align: center">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list categoryList as category>
                                <tr>
                                    <td>${category.categoryId}</td>
                                    <td>${category.categoryName}</td>
                                    <td>${category.categoryType}</td>
                                    <td>${category.createTime}</td>
                                    <td>${category.updateTime}</td>
                                    <td>
                                        <button type="button"
                                                class="btn btn-default btn-primary btn-xs"
                                                style="outline: none"
                                                onclick="location.href='new_category?categoryId=${category.categoryId}'">
                                            修改
                                        </button>
                                    </td>
                                </tr >
                                </#list>
                                </tbody>
                            </table>
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