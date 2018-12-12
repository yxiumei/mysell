
<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
    <#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper" style="margin-left: 200px;">
    <form action="/sell/admin/shop/saveOrEdit" class="form-horizontal" method="post">
         <div class="form-group">
              <div class="col-sm-1"><label for="uname3" class="control-label" >店铺名称</label></div>
               <div class="col-sm-5"> <input type="text" name="shop.shopName" value="${(shopImgDto.shop.shopName)!''}" class="form-control" id="uname3" placeholder="请输入店铺名称"></div>
         </div>
        <div class="form-group">
            <div class="col-sm-1"><label for="upwd2" class="control-label">店铺描述</label></div>
            <div class="col-sm-5"> <input type="text" name="shop.description" value="${(shopImgDto.shop.description)!''}" class="form-control" id="upwd2" placeholder="请输入店铺描述"></div>
        </div>
        <div class="form-group">
            <div class="col-sm-1"><label for="upwd2" class="control-label">店铺主图</label></div>
            <div class="col-sm-5">
                <img height="100px" width="100px" src="${(product.productImg)!''}" ALT="">
                <input type="text" name="shop.avatar"  class="form-control" id="upwd2" value="${(shopImgDto.shop.avatar)!''}" placeholder="请输入主图片地址">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-1"><label for="upwd2" class="control-label">最低价</label></div>
            <div class="col-sm-5">
                <input id="area" class="form-control" name="shop.minPrice" placeholder="请输入店铺所买商品最低价"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-1"><label for="upwd2" class="control-label">配送时间</label></div>
            <div class="col-sm-5">
                <input id="area" class="form-control" name="shop.deliveryTime" placeholder="请输入配送时间"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-1"><label for="upwd2" class="control-label">配送价格</label></div>
            <div class="col-sm-5">
                <input id="area" class="form-control" name="shop.deliveryPrice" placeholder="请输入配送价格"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-1"><label for="upwd2" class="control-label">店铺详情</label></div>
            <div class="col-sm-5">
                <textarea id="area" class="form-control" rows="3" name="infos" placeholder="请输入店铺详情">${(shopImgDto.infos)!''}</textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-1"><label for="upwd2" class="control-label">公告</label></div>
            <div class="col-sm-5">
                <textarea id="area" class="form-control" rows="3" name="bulletin" placeholder="请输入公告">${(shopImgDto.bulletin)!''}</textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-1"><label for="upwd2" class="control-label">店铺图片1</label></div>
            <div class="col-sm-5">
                <img height="100px" width="100px" src="${(shopImg1)!''}" ALT="">
                <input type="text" name="shopImg1"  class="form-control" id="upwd2" value="${(shopImg1)!''}" placeholder="请输入店铺图片地址">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-1"><label for="upwd2" class="control-label">店铺图片1</label></div>
            <div class="col-sm-5">
                <img height="100px" width="100px" src="${(shopImg2)!''}" ALT="">
                <input type="text" name="shopImg2"  class="form-control" id="upwd2" value="${(shopImg2)!''}" placeholder="请输入店铺图片地址">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-1"><label for="upwd2" class="control-label">店铺图片3</label></div>
            <div class="col-sm-5">
                <img height="100px" width="100px" src="${(shopImg3)!''}" ALT="">
                <input type="text" name="shopImg3"  class="form-control" id="upwd2" value="${(shopImg3)!''}" placeholder="请输入店铺图片地址">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-1"><label for="upwd2" class="control-label">店铺图片4</label></div>
            <div class="col-sm-5">
                <img height="100px" width="100px" src="${(shopImg4)!''}" ALT="">
                <input type="text" name="shopImg4"  class="form-control" id="upwd2" value="${(shopImg4)!''}" placeholder="请输入店铺图片地址">
            </div>
        </div>
        <div class="form-group" style="margin-left: 220px">
            <div class="col-sm-1"> <input type="submit" class="btn btn-success" value="提交"></div>
            <div class="col-sm-1"> <input type="button" class="btn btn-danger" value="取消"></div>
        </div>
        <input type="hidden" name="shop.id" value="${(shop.id)!''}">
    </form>
    </div>
</div>
</body>

</html>
