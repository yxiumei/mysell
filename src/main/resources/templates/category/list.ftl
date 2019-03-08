
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
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">我的类目</a>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li class="nav">
                                    <a href="#">类目名称</a>
                                </li>
                            </ul>
                            <form class="navbar-form navbar-left" role="search" action="/sell/admin/category/list" method="get">
                                <div class="form-group">
                                    <input name="key" type="text" class="form-control" />
                                </div> <button type="submit" class="btn btn-default">搜索</button>
                            </form>
                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                    <a href="/sell/admin/category/index">新增</a>
                                </li>
                            </ul>
                        </div>

                    </nav>

                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>类目编号</th>
                            <th>类目名称</th>
                            <th>类目类型</th>
                            <th>创建时间</th>
                            <th>更新时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
            <#list categoryPage.list as categoryDto>
            <tr>
                <td>${categoryDto_index}</td>
                <td>${categoryDto.categoryId}</td>
                <td>${categoryDto.categoryName}</td>
                <td>${categoryDto.categoryType}</td>
                <td>${categoryDto.createTime?string('yyyy.MM.dd HH:mm:ss')}</td>
                <td>${categoryDto.updateTime?string('yyyy.MM.dd HH:mm:ss')}</td>
                <td>&nbsp;&nbsp;<a href="/sell/admin/category/index?categoryId=${categoryDto.categoryId}">编辑</a>&nbsp;&nbsp;
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
                <li><a href="/sell/category/list?page=${currentPage - 1}&pageSize=${size}">上一页</a></li>
                </#if>

                <#list 1..categoryPage.pages as index>
                    <#if currentPage == index>
                        <li class="disabled">
                            <a href="/sell/category/list?page=${index}&pageSize=${size}">${index}</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/sell/admin/category/list?page=${index}&pageSize=${size}">${index}</a>
                        </li>
                    </#if>
                </#list>
                <#if currentPage gte categoryPage.pages>
                <li class="disabled"><a href="#">下一页</a></li>
                <#else>
                <li><a href="/sell/admin/category/list?page=${currentPage + 1}&pageSize=${size}">下一页</a></li>
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
