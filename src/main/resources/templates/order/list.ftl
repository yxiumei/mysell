<html>
    <head>
        <meta charset="UTF-8">
        <title>订单列表</title>
        <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
        <script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/bootstrap.min.js"></script>
        <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>订单号</th>
                        <th>姓名</th>
                        <th>手机号</th>
                        <th>地址</th>
                        <th>金额</th>
                        <th>订单状态</th>
                        <th>支付状态</th>
                        <th>创建时间</th>
                        <#--<th>更新时间</th>-->
                        <th colspan="2">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list orderPage.list as orderDto>
                        <tr>
                            <td>${orderDto_index}</td>
                            <td>${orderDto.orderId}</td>
                            <td>${orderDto.buyerName}</td>
                            <td>${orderDto.buyerPhone}</td>
                            <td>${orderDto.buyerAddress}</td>
                            <td>${orderDto.oderAmount}</td>
                            <td>${orderDto.orderStatusStr}</td>
                            <td>${orderDto.payStatusStr}</td>
                            <td>${orderDto.createTime?string('yyyy.MM.dd HH:mm:ss')}</td>
                            <#--<td>${orderDto.updateTime?string('yyyy.MM.dd HH:mm:ss')}</td>-->
                            <td>详情</td>
                            <td>
                                <#if orderDto.getOrderStatusEnum().getMsg() =="新单">
                                    <a id="modal-630214" href="#modal-container-630214" role="button" class="btn" data-toggle="modal">取消</a>

                                    <div class="modal fade" id="modal-container-630214" role="dialog"
                                         aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-hidden="true">×
                                                    </button>
                                                    <h4 class="modal-title" id="myModalLabel">
                                                        取消订单
                                                    </h4>
                                                </div>
                                                <div class="modal-body">
                                                    是否确认取消订单？
                                                </div>
                                                <div class="modal-footer">
                                                    <a type="button" class="btn btn-primary" data-dismiss="modal">否</a>
                                                    <a class="btn btn-primary" href="/sell/order/cancel?orderId=${orderDto.getOrderId()}">
                                                        是
                                                    </a>
                                                </div>
                                            </div>

                                        </div>

                                    </div>

                                </#if>

                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
                <!--分页-->
                <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li ><a href="/sell/order/list?page=${currentPage - 1}&pageSize=${size}">上一页</a></li>
                    </#if>

                    <#list 1..orderPage.pages as index>
                      <#if currentPage == index>
                          <li class="disabled"><a href="/sell/order/list?page=${index}&pageSize=${size}">${index}</a></li>
                      <#else>
                          <li><a href="/sell/order/list?page=${index}&pageSize=${size}">${index}</a></li>
                      </#if>
                    </#list>

                    <#if currentPage gte orderPage.pages>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li ><a href="/sell/order/list?page=${currentPage + 1}&pageSize=${size}">下一页</a></li>
                    </#if>
                </ul>
            </div>

            </div>
        </div>
    </div>
    </body>
</html>

<#--<html xmlns="http://www.w3.org/1999/html">-->
    <#--<head>-->
        <#--<meta charset="UTF-8">-->
        <#--<title>订单列表</title>-->
        <#--<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>-->
        <#--<script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/bootstrap.min.js"></script>-->
        <#--<link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">-->
    <#--</head>-->
    <#--<body>-->
    <#--<div class="container">-->
        <#--<div class="row clearfix">-->
            <#--<div class="col-md-12 column">-->
                <#--<a id="modal-630214" href="#modal-container-630214" role="button" class="btn" data-toggle="modal">触发遮罩窗体</a>-->

                <#--<div class="modal fade" id="modal-container-630214" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">-->
                    <#--<div class="modal-dialog">-->
                        <#--<div class="modal-content">-->
                            <#--<div class="modal-header">-->
                                <#--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>-->
                                <#--<h4 class="modal-title" id="myModalLabel">-->
                                   <#--<a href="http://www.baidu.com">标题</a>-->
                                <#--</h4>-->
                            <#--</div>-->
                            <#--<div class="modal-body">-->
                                <#--内容...-->
                            <#--</div>-->
                            <#--<div class="modal-footer">-->
                                <#--<a type="button" class="btn btn-primary" data-dismiss="modal">关闭</a>-->
                                <#--<a class="btn btn-primary" href="http://www.baidu.com">-->
                                <#--&lt;#&ndash;<a class="btn btn-primary" href="http://www.baidu.com"></a>&ndash;&gt;-->
                                <#--标题-->
                                <#--</a>-->
                            <#--</div>-->
                        <#--</div>-->

                    <#--</div>-->

                <#--</div>-->

            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
    <#--</body>-->
    <#--</html>-->