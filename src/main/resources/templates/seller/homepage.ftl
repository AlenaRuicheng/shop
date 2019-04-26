<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Shop - 卖家端</title>
    <link href="/shop/css/style.css" rel="stylesheet">
    <link href="/shop/css/bootstrap.homepage.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/shop/css/jquery-accordion-menu.css">
    <link rel="stylesheet" type="text/css" href="/shop/css/iconfont-seller.css">
    <link rel="stylesheet" type="text/css" href="/shop/css/animate.css">
    <link rel="stylesheet" type="text/css" href="/shop/css/nav.css">
    <script src="/shop/js/jquery.min.js"></script>
    <script src="/shop/js/bootstrap.min.js"></script>
    <script src="/shop/js/jquery-accordion-menu.js" type="text/javascript"></script>
    <script type="text/javascript">
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
            <h1 class="site-sysmbol animated zoomIn"><span class="iconfont icon-smile smile"></span>欢迎使用</h1>
        </div>
    </div>
</div>
<script type="text/javascript">
</script>
</body>
</html>