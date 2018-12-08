
<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
    <#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper" style="margin-left: 200px;">
    <form action="/sell/admin/product/saveOrEdit" class="form-horizontal" method="post">
         <div class="form-group">
              <div class="col-sm-1"><label for="uname3" class="control-label" >商品名称</label></div>
               <div class="col-sm-5"> <input type="text" name="productName" value="${(product.productName)!''}" class="form-control" id="uname3" placeholder="请输入商品名称"></div>
         </div>
        <div class="form-group">
            <div class="col-sm-1"><label for="upwd2" class="control-label">商品价格</label></div>
            <div class="col-sm-5"> <input type="text" name="productPrice" value="${(product.productPrice)!''}" class="form-control" id="upwd2" placeholder="请输入商品价格"></div>
        </div>
        <div class="form-group">
            <div class="col-sm-1"><label for="upwd2" class="control-label">商品库存</label></div>
            <div class="col-sm-5"> <input type="text" name="productStock" value="${(product.productStock)!''}" class="form-control" id="upwd2" placeholder="请输入商品价格"></div>
        </div>
        <div class="form-group">
            <div class="col-sm-1"><label for="upwd2" class="control-label">商品描述</label></div>
            <div class="col-sm-5">
                <textarea id="area" class="form-control" rows="3" name="productDescription" placeholder="请输入商品描述">${(product.productDescription)!''}</textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-1"><label for="upwd2" class="control-label">商品图片</label></div>
            <div class="col-sm-5">
                <img height="100px" width="100px" src="${(product.productImg)!''}" ALT="">
                <input type="text" name="productImg"  class="form-control" id="upwd2" value="${(product.productImg)!''}" placeholder="请输入图片地址">
            </div>

        </div>
        <div class="form-group">
            <div class="col-sm-1"><label for="upwd2" class="control-label">商品分类</label></div>
            <select id="organizationId" class="selectpicker" name="categoryType" style="text-align: center">
                <option>------请选择-------</option>
                <#list categoryList as category>
                    <option value="${category.categoryType}"
                        <#if (product.categoryType)?? && product.categoryType == category.categoryType>
                            selected
                        </#if>>
                        ${category.categoryName}
                    </option>
                </#list>
            </select>
        </div>
        <div class="form-group" style="margin-left: 220px">
            <div class="col-sm-1"> <input type="submit" class="btn btn-success" value="提交"></div>
            <div class="col-sm-1"> <input type="button" class="btn btn-danger" value="取消"></div>
        </div>
        <input type="hidden" name="productId" value="${(product.productId)!''}">
    </form>
    </div>
</div>
</body>

</html>
