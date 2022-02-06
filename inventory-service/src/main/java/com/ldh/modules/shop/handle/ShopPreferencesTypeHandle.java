package com.ldh.modules.shop.handle;

import User.AuthorityInformation;
import com.ldh.constant.ShopPreferencesChange;
import com.ldh.modules.shop.model.ShopPreferencesTypeModel;
import com.ldh.modules.shop.service.ShopPreferencesTypeService;
import com.ldh.util.RedisSessionUtil;
import common.Result;
import constant.UserOperationConstant;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description: 购物偏好对外接口
 * @Author: ldh
 * @Date:   2022-02-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags="购物偏好对外接口")
@RestController
@RequestMapping("/inventory/shopPreferencesType/handle")
public class ShopPreferencesTypeHandle {

    @Autowired
    private ShopPreferencesChange shopPreferencesChange;

    @Autowired
    private ShopPreferencesTypeService shopPreferencesTypeService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 用户进行操作时 偏好值进行改变
     * @param typeId
     * @return
     */
  /*  @GetMapping("operation")
    public Result<?> look(@RequestParam("typeId") String typeId, String operation){
        Result result = new Result();
        try{
            switch (operation){
                case UserOperationConstant.LOOK:
                    shopPreferencesTypeService.increasesValue(typeId, shopPreferencesChange.getLook());
                    break;
                case UserOperationConstant.JOIN_TROLLEY:
                    shopPreferencesTypeService.increasesValue(typeId, shopPreferencesChange.getJoinTrolley());
                    break;
                case UserOperationConstant.BUY:


                    break;
                default:
                    throw new Exception("operation not find");
            }
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }*/

    /**
     * 获取该用户的偏好列表
     * @return
     */
    @GetMapping("getList")
    public Result<ShopPreferencesTypeModel> joinTrolley(){
        Result result = new Result();
        try{
            List<ShopPreferencesTypeModel> list = shopPreferencesTypeService.getPreferenceList(this.getUser().getAuthorityId());
            result.setResult(list);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    private AuthorityInformation getUser(){
        HttpSession session = request.getSession();
        AuthorityInformation authorityInformation = (AuthorityInformation) RedisSessionUtil.sessionAttributeToEntity(session.getAttribute("user"), AuthorityInformation.class);
        return authorityInformation;
    }

}
