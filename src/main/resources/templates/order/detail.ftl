<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Shop - 订单详情</title>
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
            <div class="page-content-wrapper" style="margin-top: 40px;">
                <div class="container">
                    <div class="row clearfix">
                        <div class="col-md-5 column">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>订单ID</th>
                                    <th>订单总金额</th>
                                    <th>订单状态</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>${orderDTO.orderId}</td>
                                    <td>${orderDTO.orderAmount}</td>
                                    <td>${orderDTO.getOrderStatusEnum().message}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-12 column">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>商品ID</th>
                                    <th>商品名称</th>
                                    <th>单价</th>
                                    <th>购买数量</th>
                                    <th>总额</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list orderDTO.orderDetailList as orderDetail>
                                <tr>
                                    <td>${orderDetail.productId}</td>
                                    <td>${orderDetail.productName}</td>
                                    <td>${orderDetail.productPrice}</td>
                                    <td>${orderDetail.productQuantity}</td>
                                    <td>${orderDetail.productPrice * orderDetail.productQuantity}</td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                            <div class="col-md-12 column">
                            <#if orderDTO.getOrderStatusEnum().message == "${orderNew}">
                                <a type="button" class="btn btn-default btn-primary"
                                   href="finish?orderId=${orderDTO.orderId}">完结订单</a>
                                <a type="button" class="btn btn-default btn-danger"
                                   href="cancel?orderId=${orderDTO.orderId}">取消订单</a>
                            </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>