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
        table td, th{
            text-align: left;
        }
        table th {
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
                        <div class="col-md-10 column col-md-offset-1">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>订单ID</th>
                                    <th style="min-width: 70px;">姓名</th>
                                    <th>手机号</th>
                                    <th style="min-width: 200px;">地址</th>
                                    <th>金额</th>
                                    <th>订单状态</th>
                                    <th>支付状态</th>
                                    <th style="min-width: 100px;">创建时间</th>
                                    <th colspan="2" style="text-align: center">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list orderDTOPage.content as orderDTO>
                                <tr>
                                    <td>${orderDTO.orderId}</td>
                                    <td>${orderDTO.buyerName}</td>
                                    <td>${orderDTO.buyerPhone}</td>
                                    <td>${orderDTO.buyerAddress}</td>
                                    <td>${orderDTO.orderAmount}</td>
                                    <td>${orderDTO.getOrderStatusEnum().getMessage()}</td>
                                    <td>${orderDTO.getPayStatusEnum().getMessage()}</td>
                                    <td>${orderDTO.createTime}</td>
                                    <td style="text-align: center">
                                        <button type="button"
                                                class="btn btn-default btn-primary btn-xs"
                                                style="outline: none"
                                                onclick="location.href='detail?orderId=${orderDTO.orderId}'">
                                            查看详情
                                        </button>
                                    </td>
                                    <td style="text-align: center">
                                        <#if orderDTO.getOrderStatusEnum().message == "${orderNew}">
                                            <button type="button"
                                                    class="btn btn-danger btn-xs"
                                                    style="outline: none"
                                                    onclick="location.href='cancel?orderId=${orderDTO.orderId}'">
                                                取消订单
                                            </button>
                                        <#else >
                                            <button type="button"
                                                    class="btn btn-danger btn-xs disabled"
                                                    style="outline: none">
                                                取消订单
                                            </button>
                                        </#if>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                            <div style="width: 100%; text-align: center; clear: both">
                                <ul class="pagination">
                                <#if currentPage lte 1>
                                    <li class="disabled"><a href="#">上一页</a></li>
                                <#else>
                                    <li><a href="list?page=${currentPage-1}&size=${pageSize}">上一页</a></li>
                                </#if>

                                <#list 1..orderDTOPage.getTotalPages() as index>
                                    <#if index == currentPage>
                                        <li class="disabled"><a href="#">${index}</a></li>
                                    <#else>
                                        <li><a href="list?page=${index}&size=${pageSize}">${index}</a></li>
                                    </#if>

                                </#list>
                                <#if currentPage gte orderDTOPage.getTotalPages()>
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
            <div class="modal fade" id="messageModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="myModalLabel">
                                新消息
                            </h4>
                        </div>
                        <div class="modal-body">
                            您有新的订单
                        </div>
                        <div class="modal-footer">
                            <button onclick="location.reload()" type="button" class="btn btn-primary">好的</button>
                            <button onclick="javascript:document.getElementById('notice').pause()" type="button"
                                    class="btn btn-default" data-dismiss="modal">关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<audio id="notice" loop="loop">
    <source src="/shop/audio/notice.mp3" type="audio/mpeg">
</audio>
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

    var webSocket = null;
    if ('WebSocket' in window) {
        webSocket = new WebSocket('ws://localhost:8080/shop/websocket');
    } else {
        alert("该浏览器不支持WebSocket")
    }
    webSocket.onopen = function (event) {
        console.log('open');
    }

    webSocket.onclose = function (event) {
        console.log('close');
    }

    webSocket.onmessage = function (event) {
        console.log('有新消息:' + event.data);
        $('#messageModal').modal('show');
        document.getElementById('notice').play();
    }

    webSocket.onerror = function (event) {
        alert("error");
    }

    webSocket.onbeforeunload = function () {
        webSocket.close();
    }
</script>
</body>
</html>