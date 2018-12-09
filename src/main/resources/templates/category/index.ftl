
<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
    <#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper" style="margin-left: 200px;">
        <form action="/sell/admin/category/saveOrEdit" class="form-horizontal" method="post">
            <div class="form-group">
                <div class="col-sm-1"><label for="uname3" class="control-label" >类目名称</label></div>
                <div class="col-sm-5"> <input type="text" name="categoryName" value="${(category.categoryName)!''}" class="form-control" id="uname3" placeholder="请输入类目名称"></div>
                <div class="col-sm-3"> <span class="help-block">必填</span></div>
            </div>
            <div class="form-group">
                <div class="col-sm-1"><label for="upwd2" class="control-label">类目类型</label></div>
                <div class="col-sm-5"> <input type="text" name="categoryType" value="${(category.categoryType)!''}"
                <#if (category.categoryType)??>disabled="disabled"</#if>
                class="form-control" id="upwd2" placeholder="请输入类目类型"></div>
                <div class="col-sm-3"> <span class="help-block">类型必须唯一</span></div>
            </div>
            <div class="form-group" style="margin-left: 220px">
                <div class="col-sm-1"> <input type="submit" class="btn btn-success" value="提交"></div>
                <div class="col-sm-1"> <input type="button" class="btn btn-danger" value="取消"></div>
            </div>
            <input type="hidden" name="categoryId" value="${(category.categoryId)!''}">
        </form>
    </div>
</div>
</body>

</html>
