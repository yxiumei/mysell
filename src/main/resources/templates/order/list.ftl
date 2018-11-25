<html>
    <head>
        <meta charset="UTF-8">
        <title>订单列表</title>
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
                            <td>取消</td>
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