<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Shop - 新增类目</title>
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
            <div id="page-content-wrapper">
                <div class="container-fluid">
                    <div class="row clearfix">
                        <div class="col-md-5 column col-md-offset-3">
                            <form role="form" method="post" action="save">
                                <div class="form-group">
                                    <label class="pull-left">名称</label>
                                    <input type="text" name="categoryName" class="form-control" value="${(productCategory.categoryName)!''}" required/>
                                </div>
                                <div class="form-group">
                                    <label class="pull-left">类型</label>
                                    <input type="number" name="categoryType" class="form-control" value="${(productCategory.categoryType)!''}" required/>
                                </div>
                                <input hidden type="text" name="categoryId" value="${(productCategory.categoryId)!''}">
                                <button type="submit" class="btn btn-default btn-success">提交</button>
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