package com.dtdream.mysell.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dtdream.mysell.config.SupportConfig;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.dto.ShopDto;
import com.dtdream.mysell.dto.ShopImagesDto;
import com.dtdream.mysell.enums.ErrorMessage;
import com.dtdream.mysell.enums.ShopEnum;
import com.dtdream.mysell.manage.ShopManage;
import com.dtdream.mysell.mapper.ShopDetailMapper;
import com.dtdream.mysell.mapper.ShopMapper;
import com.dtdream.mysell.mapper.ShopScoreMapper;
import com.dtdream.mysell.model.Shop;
import com.dtdream.mysell.model.ShopDetail;
import com.dtdream.mysell.model.ShopScore;
import com.dtdream.mysell.model.SupportDto;
import com.dtdream.mysell.service.ShopService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author yxiumei
 * @Data 2018/12/10 22:32
 */
@Slf4j
@Service
public class ShopServiceImp implements ShopService {

    @Autowired(required = false)
    private ShopMapper shopMapper;
    @Autowired
    private ShopManage shopManage;
    @Autowired(required = false)
    private ShopDetailMapper shopDetailMapper;
    @Autowired
    private SupportConfig supportConfig;
    @Autowired(required = false)
    private ShopScoreMapper shopScoreMapper;

    @Override
    public Response<Boolean> save(ShopImagesDto shop) {
        try {
            return Response.ok(shopManage.save(shop));
        }catch (Exception e){
            log.error("OP[]ShopServiceImp[]save[]save shop info fail：error:{}",e.getStackTrace());
            return Response.fail("save shop info fail");
        }
    }

    @Override
    public Response<Boolean> update(ShopImagesDto shopImagesDto) {
        try{
            shopManage.update(shopImagesDto);
            return Response.ok(Boolean.TRUE);
        }catch (Exception e){
            log.error("OP[]ShopServiceImp[]update[]update shop info fail:{}", e.getStackTrace());
            return Response.fail("update.shop.info.fail");
        }
    }

    @Override
    public Response<Boolean> cancel(String shopId) {
        Shop shop = shopMapper.selectByPrimaryKey(shopId);
        shop.setStatus(ShopEnum.CANCEL.getCode());
        shop.setUpdateTime(new Date());
        shopMapper.updateByPrimaryKeySelective(shop);
        return Response.ok(Boolean.TRUE);
    }

    @Override
    public Response<ShopImagesDto> findOne(String shopId) {
        ShopImagesDto shopImagesDto = new ShopImagesDto();
        if (null == shopId) {
            log.error("OP[]ShopServiceImp[]findOne[] shop is null");
            return Response.fail(ErrorMessage.PARAM_IS_NULL.toString());
        }
        Shop shop = shopMapper.selectByPrimaryKey(shopId);
        if (null == shop) {
            log.error("OP[]ShopServiceImp[]findOne[]find shop info fail");
            return Response.fail("find.shop.fail");
        }
        shopImagesDto.setShop(shop);
        ShopDetail shopDetail = shopDetailMapper.selectByShopId(shopId);
        if (shopDetail == null) {
            log.error("OP[]ShopServiceImp[]findOne[]find shop detail fail");
            return Response.fail("find shop detail fail");
        }
        String bulletin = shopDetail.getBulletin();
        shopImagesDto.setBulletin(bulletin);
        shopDetail.setInfos(shopDetail.getInfos());
        String pics = shopDetail.getPics();
        List<String> list = JSON.parseArray(pics, String.class);
        shopImagesDto.setShopImg1(list.get(0));
        shopImagesDto.setShopImg2(list.get(1));
        shopImagesDto.setShopImg3(list.get(2));
        shopImagesDto.setShopImg4(list.get(3));
        return Response.ok(shopImagesDto);
    }

    @Override
    public Response<PageInfo<Shop>> findAll(Integer pageNo, Integer pageSize, String key) {
        Page page = PageHelper.startPage(pageNo, pageSize);
        List<Shop> all = shopMapper.findAll(key);
        PageInfo<Shop> pageInfo = new PageInfo<>(page);
        pageInfo.setList(all);
        return Response.ok(pageInfo);
    }

    @Override
    public Response<ShopDto> getShopInfo(String shopId) {
        List<SupportDto> supportDtos = supportConfig.getSupportDtos();
        Shop shop = shopMapper.selectByPrimaryKey(shopId);
        ShopDetail shopDetail = shopDetailMapper.selectByShopId(shopId);
        ShopScore shopScore = shopScoreMapper.findShopScoreByShopId(shopId);
        if (null == shop || null == shopDetail || null == shopScore) {
            log.error("OP[]ShopService[]getShopInfo[]shop is null :shopId:{}", shopId);
            return Response.fail(ErrorMessage.PARAM_IS_NULL.toString());
        }

        ShopDto shopDto = new ShopDto();
        BeanUtils.copyProperties(shop, shopDto);
        shopDto.setName(shop.getShopName());
        shopDto.setBulletin(shopDetail.getBulletin());
        String pics = shopDetail.getPics();
        String infos = shopDetail.getInfos();
        if (!StringUtils.isEmpty(pics)) {
            List<String> list = JSONArray.parseArray(pics, String.class);
            shopDto.setPics(list);
        }
        if (!StringUtils.isEmpty(infos)) {
            List<String> list = JSONArray.parseArray(infos, String.class);
            shopDto.setInfos(list);
        }

        shopDto.setFoodScore(shopScore.getFoodScore());
        shopDto.setDifferOpinion(shopScore.getDifferOpinion());
        shopDto.setHighOpinion(shopScore.getHighOpinion());
        shopDto.setRatingCount(shopScore.getSellCount());
        shopDto.setScore(shopScore.getScore());
        shopDto.setRankRate(shopScore.getRankRate());
        shopDto.setSellCount(shopScore.getSellCount());
        shopDto.setServiceScore(shopScore.getServiceScore());
        shopDto.setSupports(supportDtos);
        return Response.ok(shopDto);
    }
}
