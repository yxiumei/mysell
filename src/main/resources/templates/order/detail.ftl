
<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
    <#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper" style="margin-top:0px">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">

                    <nav class="navbar navbar-default" role="navigation">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">订单详情</a>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li class="nav">
                                    <a href="#">订单号：&nbsp;&nbsp;${orderId}</a>
                                </li>
                            </ul>
                            <ul class="nav navbar-nav">
                                <li class="nav">
                                    <a href="#">订单总价：&nbsp;&nbsp;${totalAmount}</a>
                                </li>
                            </ul>
                            <#--<form class="navbar-form navbar-left" role="search">-->
                                <#--<div class="form-group">-->
                                    <#--<input type="text" class="form-control" />-->
                                <#--</div> <button type="submit" class="btn btn-default">搜索</button>-->
                            <#--</form>-->
                        </div>

                    </nav>

                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>订单详情编号</th>
                            <th>商品编号</th>
                            <th>商品名称</th>
                            <th>商品价格</th>
                            <th>购买数量</th>
                            <th>总额</th>
                        <#--<th>更新时间</th>-->
                        </tr>
                        </thead>
                        <tbody>
            <#list orderDto.orderDetails as orderDto>
            <tr>
                <td>${orderDto_index}</td>
                <td>${orderDto.detailId}</td>
                <td>${orderDto.productId}</td>
                <td>${orderDto.productName}</td>
                <td>${orderDto.productPrice}</td>
                <td>${orderDto.productQuantity}</td>
                <td>${orderDto.productPrice * orderDto.productQuantity}</td>
            </tr>
            </#list>
                        </tbody>
                    </table>
                </div>

                <#if orderDto.getOrderStatus() == 0>
                <button id="modal-630214" href="#modal-container-630215" style="margin-left: 20px" role="button" class="btn btn-default btn-info" data-toggle="modal">接单</button>
                <button id="modal-630214" href="#modal-container-630214" style="margin-left: 10px" role="button" class="btn btn-default btn-info" data-toggle="modal">取消</button>
                <div class="modal fade" id="modal-container-630215" role="dialog"
                     aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">×
                                </button>
                                <h4 class="modal-title" id="myModalLabel">
                                    接收订单
                                </h4>
                            </div>
                            <div class="modal-body">
                                是否接收订单？
                            </div>
                            <div class="modal-footer">
                                <a class="btn btn-primary" href="/sell/order/finish/${orderDto.getOrderId()}">
                                    是
                                </a>
                                <a type="button" class="btn btn-primary" data-dismiss="modal">否</a>
                            </div>
                        </div>
                    </div>
                </div>
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
                            <a class="btn btn-primary" href="/sell/order/cancel?orderId=${orderDto.getOrderId()}">
                                是
                            </a>
                            <a type="button" class="btn btn-primary" data-dismiss="modal">否</a>
                        </div>
                    </div>
                </div>
            </div>
                </#if>

                <!--分页-->
                <#--<div class="col-md-12 column" style="margin-right: 50px;">-->
                    <#--<ul class="pagination pull-right ">-->
                <#--<#if currentPage lte 1>-->
                <#--<li class="disabled"><a href="#">上一页</a></li>-->
                <#--<#else>-->
                <#--<li><a href="/sell/order/list?page=${currentPage - 1}&pageSize=${size}">上一页</a></li>-->
                <#--</#if>-->

                <#--<#list 1..orderPage.pages as index>-->
                    <#--<#if currentPage == index>-->
                <#--<li class="disabled"><a href="/sell/order/list?page=${index}&pageSize=${size}">${index}</a></li>-->
                    <#--<#else>-->
                <#--<li><a href="/sell/order/list?page=${index}&pageSize=${size}">${index}</a></li>-->
                    <#--</#if>-->
                <#--</#list>-->
                <#--<#if currentPage gte orderPage.pages>-->
                <#--<li class="disabled"><a href="#">下一页</a></li>-->
                <#--<#else>-->
                <#--<li><a href="/sell/order/list?page=${currentPage + 1}&pageSize=${size}">下一页</a></li>-->
                <#--</#if>-->
                    <#--</ul>-->
                <#--</div>-->
            </div>
        </div>
    </div>
</div>
</body>
</html>
