package com.ldh.modules.inventory.service.Impl;

import User.AuthorityInformation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.inventory.entity.InventoryComment;
import com.ldh.modules.inventory.mapper.InventoryCommentMapper;
import com.ldh.modules.inventory.model.InventoryCommentModel;
import com.ldh.modules.inventory.service.InventoryCommentService;
import com.ldh.userService.client.AuthorityClient;
import com.ldh.userService.model.AuthorityInformationModel;
import com.ldh.util.RedisSessionUtil;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.Request;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Description: 商品评论
 * @Author: ldh
 * @Date:   2022-01-29
 * @Version: V1.0
 */
@Service
public class InventoryCommentServiceImpl extends ServiceImpl<InventoryCommentMapper, InventoryComment> implements InventoryCommentService {

    @Autowired
    private InventoryCommentMapper inventoryCommentMapper;

    @Autowired
    private AuthorityClient authorityClient;

    @Override
    @Transactional
    public void sendComment(InventoryComment inventoryComment) {
        String tempFatherId = inventoryComment.getFatherId();

        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        AuthorityInformation authorityInformation = (AuthorityInformation) RedisSessionUtil.sessionAttributeToEntity(session.getAttribute("user"), AuthorityInformation.class);
        inventoryComment.setCreateBy(authorityInformation.getAuthorityId());
//        inventoryComment.setCreateBy("9e1a1066-61ab-4a02-bec4-67a0d95e2745");
        inventoryComment.setInventoryCommentCode(UUID.randomUUID().toString());

        //父id为空或则表示，我现在就是父
        if (StringUtils.isEmpty(tempFatherId)){
            inventoryComment
                    .setFatherId("-1");
        }else {
            InventoryComment fatherComment = this.getById(tempFatherId);
            //如果我父id为-1，则我的父id为传过来的父id
            if (fatherComment.getFatherId().equals("-1")){
                inventoryComment
                        .setFatherId(fatherComment.getInventoryCommentId());
            }else {
                //如果不为-1，则我的父id为我父id的父id
                inventoryComment
                        .setFatherId(fatherComment.getFatherId());
            }
            inventoryComment
                    .setReplayCommentId(fatherComment.getInventoryCommentId())
                    .setReplayUserId(fatherComment.getCreateBy());
        }
        this.save(inventoryComment);
    }

    @Override
    @Transactional
    public IPage<InventoryCommentModel> getCommentList(Page page, QueryWrapper queryWrapper, String inventoryId, String fatherId) throws Exception {

        IPage<InventoryCommentModel> iPage = inventoryCommentMapper.getCommentList(page, queryWrapper, inventoryId, fatherId);
        List<InventoryCommentModel> list = iPage.getRecords();
        if (list.isEmpty()){
            return iPage;
        }

        StringBuilder stringBuilder = new StringBuilder();
        list.stream().forEach(e -> {
            if (!StringUtils.isEmpty(e.getReplayUserId())) {
                stringBuilder.append(e.getReplayUserId());
                stringBuilder.append(",");
            }
            stringBuilder.append(e.getCreateBy());
            stringBuilder.append(",");
        });

        Result<List<AuthorityInformationModel>> result = authorityClient.selectByIds(stringBuilder.toString());
        Map<String ,AuthorityInformationModel> userMap = null;
        if (result.isSuccess()){
            List<AuthorityInformationModel> list1 = result.getResult();
            userMap = list1.stream().collect(Collectors.toMap(AuthorityInformationModel::getAuthorityId, r->r));
        }else {
            throw new Exception("Fegin error");
        }

        for (InventoryCommentModel inventoryCommentModel : list) {
            if (userMap.containsKey(inventoryCommentModel.getCreateBy())){
                AuthorityInformationModel temp = userMap.get(inventoryCommentModel.getCreateBy());
                inventoryCommentModel
                        .setCreateImgPath(temp.getImgPath())
                        .setCreateByName(temp.getAuthorityName());
            }
            if (userMap.containsKey(inventoryCommentModel.getReplayUserId())){
                AuthorityInformationModel temp = userMap.get(inventoryCommentModel.getReplayUserId());
                inventoryCommentModel.setReplayUserName(temp.getAuthorityName());
            }
            if (inventoryCommentModel.getReplayUserName() == null){
                inventoryCommentModel.setReplayUserName("*该用户不存在*");
            }
        }
        iPage.setRecords(list);

        return iPage;
    }

    @Override
    public IPage<InventoryCommentModel> list(Page page, QueryWrapper queryWrapper, InventoryComment inventoryComment) throws Exception {

        IPage<InventoryCommentModel> iPage = inventoryCommentMapper.list(page, queryWrapper, inventoryComment);
        List<InventoryCommentModel> list = iPage.getRecords();
        if (list.isEmpty()){
            return iPage;
        }
        StringBuilder stringBuilder = new StringBuilder();
        list.stream().forEach(e -> {
            if (!StringUtils.isEmpty(e.getReplayUserId())) {
                stringBuilder.append(e.getReplayUserId());
                stringBuilder.append(",");
            }
            stringBuilder.append(e.getCreateBy());
            stringBuilder.append(",");
        });

        Result<List<AuthorityInformationModel>> result = authorityClient.selectByIds(stringBuilder.toString());
        Map<String ,AuthorityInformationModel> userMap = null;
        if (result.isSuccess()){
            List<AuthorityInformationModel> list1 = result.getResult();
            userMap = list1.stream().collect(Collectors.toMap(AuthorityInformationModel::getAuthorityId, r->r));
        }else {
            throw new Exception("Fegin error");
        }
        for (InventoryCommentModel inventoryCommentModel : list) {
            if (userMap.containsKey(inventoryCommentModel.getCreateBy())){
                AuthorityInformationModel temp = userMap.get(inventoryCommentModel.getCreateBy());
                inventoryCommentModel
                        .setCreateByName(temp.getAuthorityName())
                        .setCreateByUserName(temp.getAuthorityUsername());
            }
            if (userMap.containsKey(inventoryCommentModel.getReplayUserId())){
                AuthorityInformationModel temp = userMap.get(inventoryCommentModel.getReplayUserId());
                inventoryCommentModel
                        .setRUserName(temp.getAuthorityUsername())
                        .setReplayUserName(temp.getAuthorityName());
            }else{
                inventoryCommentModel
                        .setRUserName("无")
                        .setReplayUserName("无");
            }
        }
        iPage.setRecords(list);


        return iPage;
    }
}
