
<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <#--<div class="jumbotron text-center" style="margin-bottom:0;height: 120px;">-->
        <#--<h2>永福记后台管理系统</h2>-->
    <#--</div>-->
<#--主要内容content-->
    <div id="page-content-wrapper" style="margin-top:0px">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">

                    <nav class="navbar navbar-default" role="navigation">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">我的订单</a>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li class="nav">
                                    <a href="#">订单号</a>
                                </li>
                            </ul>
                            <form class="navbar-form navbar-left" role="search">
                                <div class="form-group">
                                    <input type="text" class="form-control" />
                                </div> <button type="submit" class="btn btn-default">搜索</button>
                            </form>
                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                    <a href="#">导出</a>
                                </li>
                            </ul>
                        </div>

                    </nav>

                    <table class="table table-bordered table-condensed">
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
                <td>${orderDto.orderAmount}</td>
                <td>${orderDto.orderStatusStr}</td>
                <td>${orderDto.payStatusStr}</td>
                <td>${orderDto.createTime?string('yyyy.MM.dd HH:mm:ss')}</td>
            <#--<td>${orderDto.updateTime?string('yyyy.MM.dd HH:mm:ss')}</td>-->
                <td><a href="/sell/order/detail/${orderDto.orderId}">详情</a></td>
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
                </div>

                <!--分页-->
            <div class="col-md-12 column" style="margin-right: 50px;">
               <ul class="pagination pull-right ">
                <#if currentPage lte 1>
                <li class="disabled"><a href="#">上一页</a></li>
                <#else>
                <li><a href="/sell/order/list?page=${currentPage - 1}&pageSize=${size}">上一页</a></li>
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
                <li><a href="/sell/order/list?page=${currentPage + 1}&pageSize=${size}">下一页</a></li>
                </#if>
               </ul>
            </div>
        </div>
    </div>
</div>
</div>
<!-- 弹窗 -->
<div class="modal fade" id="mySocket" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    消息提醒
                </h4>
            </div>
            <div class="modal-body">
                你有新的订单
            </div>
            <div class="modal-footer">
                <button type="button" onclick="javascript:document.getElementById('notice').pause();" class="btn btn-primary" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" class="btn btn-primary" "> 查看新订单</button>
            </div>
        </div>
    </div>
</div>

<!-- 播放音乐 -->
<audio id="notice" loop="loop">
    <source src="/sell/mp3/ordernotice.mp3" type="audio/mpeg">
</audio>
<script>
    var webSocket = null;
    if ('WebSocket' in window) {
        webSocket = new WebSocket("ws://47.107.240.107:8082/sell/webSocket");
    } else {
        alert("该浏览器不支持webSocket！");
    }

    webSocket.onopen = function (event) {
        console.log("建立连接");
    }

    webSocket.onclose = function (event) {
        console.log("关闭连接");
    }

    webSocket.onmessage = function (event) {
        console.log("接收到信息:" + event.data);
        // 显示弹出框，播放音乐
        document.getElementById('notice').play();
        $('#mySocket').modal('show');

    }

    webSocket.onerror = function () {
        alert("发送通信错误！")
    }
    // 刷新触发
    window.onbeforeunload = function () {
        console.log("页面刷新，关闭连接");
        webSocket.close();
    }
</script>
</body>
</html>
