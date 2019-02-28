
<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
    <#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper" style="margin-top:0">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">

                    <nav class="navbar navbar-default" role="navigation">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">我的店铺</a>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li class="nav">
                                    <a href="#">店铺信息</a>
                                </li>
                            </ul>
                            <form class="navbar-form navbar-left" role="search" action="/sell/admin/shop/list" method="get">
                                <div class="form-group">
                                    <input name="key" type="text" class="form-control" />
                                </div> <button type="submit" class="btn btn-default">搜索</button>
                            </form>
                        </div>

                    </nav>

                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>店铺主图</th>
                            <th>店铺描述</th>
                            <th>配送时间</th>
                            <th>商品最低价格</th>
                            <th>最低起送价格</th>
                            <th>店铺状态</th>
                            <th>店铺名称</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
            <#list shopPage.list as shopDto>
            <tr>
                <td>${shopDto_index}</td>
                <td>${shopDto.shopName}</td>
                <td>${shopDto.description}</td>
                <td>${shopDto.deliveryTime}</td>
                <td>${shopDto.minPrice}</td>
                <td>${shopDto.deliveryPrice}</td>
                <td>${(shopDto.status==1)?string('正常','删除')}</td>
                <td><img src="${shopDto.avatar}" height="60px" width="80px"></td>
                <td>${shopDto.createTime?string('yyyy.MM.dd')}</td>
                <td>${shopDto.updateTime?string('yyyy.MM.dd')}</td>
                <td>
                    <a href="/sell/admin/shop/shopDetail?shopId=${shopDto.id}">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/sell/admin/shop/index?shopId=${shopDto.id}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <#if shopDto.status == 1>
                        <a id="modal-630214" href="#modal-container-630214" role="button" data-toggle="modal">删除</a>
                        <div class="modal fade" id="modal-container-630214" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-hidden="true">×
                                        </button>
                                        <h4 class="modal-title" id="myModalLabel">
                                            注销店铺
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        是否删除店铺？
                                    </div>
                                    <div class="modal-footer">
                                        <a class="btn btn-primary" href="/sell/admin/shop/deleteShop/${shopDto.id}">是</a>
                                        <a type="button" class="btn btn-primary" data-dismiss="modal">否</a>
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
                <li><a href="/sell/admin/shop/list?page=${currentPage - 1}&pageSize=${size}">上一页</a></li>
                </#if>

                <#list 1..shopPage.pages as index>
                    <#if currentPage == index>
                        <li class="disabled">
                            <a href="/sell/admin/shop/list?page=${index}&pageSize=${size}">${index}</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/sell/admin/shop/list?page=${index}&pageSize=${size}">${index}</a>
                        </li>
                    </#if>
                </#list>
                <#if currentPage gte shopPage.pages>
                <li class="disabled"><a href="#">下一页</a></li>
                <#else>
                <li><a href="/sell/admin/shop/list?page=${currentPage + 1}&pageSize=${size}">下一页</a></li>
                </#if>
               </ul>
            </div>
        </div>
    </div>
</div>
</div>
</body>
<style>
    .table th, .table td {
        text-align: center;
        vertical-align: middle!important;
    }
</style>
</html>
