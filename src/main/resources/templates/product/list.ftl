
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
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">我的商品</a>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li class="nav">
                                    <a href="#">商品名称</a>
                                </li>
                            </ul>
                            <form class="navbar-form navbar-left" role="search" action="/sell/admin/product/list" method="get">
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
                            <th>商品编号</th>
                            <th>商品名称</th>
                            <th>商品图片</th>
                            <th>商品价格</th>
                            <th>商品库存</th>
                            <th>商品描述</th>
                            <th>商品类型</th>
                            <th>商品状态</th>
                            <th>创建时间</th>
                            <#--<th>更新时间</th>-->
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
            <#list productPage.list as productDto>
            <tr>
                <td>${productDto_index}</td>
                <td>${productDto.productInfo.productId}</td>
                <td>${productDto.productInfo.productName}</td>
                <td><img src="${productDto.productInfo.productImg}" height="60px" width="80px"></td>
                <td>${productDto.productInfo.productPrice}</td>
                <td>${productDto.productInfo.productStock}</td>
                <td>${productDto.productInfo.productDescription}</td>
                <td>${productDto.categoryName}</td>
                <td>${productDto.productStatus}</td>
                <td>${productDto.productInfo.createTime?string('yyyy.MM.dd HH:mm:ss')}</td>
                <#--<td>${productDto.productInfo.updateTime?string('yyyy.MM.dd HH:mm:ss')}</td>-->
                <td>
                    <a href="/sell/admin/product/index?productId=${productDto.productInfo.productId}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <#if productDto.productStatus == "下架">
                        <a id="modal-630214" href="#modal-container-630214" role="button" data-toggle="modal">上架</a>
                        <div class="modal fade" id="modal-container-630214" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-hidden="true">×
                                        </button>
                                        <h4 class="modal-title" id="myModalLabel">
                                            商品上架
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        商品是否上架？
                                    </div>
                                    <div class="modal-footer">
                                        <a class="btn btn-primary" href="/sell/admin/product/productUpFrom/${productDto.productInfo.productId}">是</a>
                                        <a type="button" class="btn btn-primary" data-dismiss="modal">否</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <#else>
                        <a id="modal-630214" href="#modal-container-630215" role="button" data-toggle="modal">下架</a>
                        <div class="modal fade" id="modal-container-630215" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-hidden="true">×
                                        </button>
                                        <h4 class="modal-title" id="myModalLabel">
                                            商品下架
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        商品是否下架？
                                    </div>
                                    <div class="modal-footer">
                                        <a class="btn btn-primary" href="/sell/admin/product/productDownFrom/${productDto.productInfo.productId}">是</a>
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
                <li><a href="/sell/admin/product/list?page=${currentPage - 1}&pageSize=${size}">上一页</a></li>
                </#if>

                <#list 1..productPage.pages as index>
                    <#if currentPage == index>
                        <li class="disabled">
                            <a href="/sell/admin/product/list?page=${index}&pageSize=${size}">${index}</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/sell/admin/product/list?page=${index}&pageSize=${size}">${index}</a>
                        </li>
                    </#if>
                </#list>
                <#if currentPage gte productPage.pages>
                <li class="disabled"><a href="#">下一页</a></li>
                <#else>
                <li><a href="/sell/admin/product/list?page=${currentPage + 1}&pageSize=${size}">下一页</a></li>
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
