package com.ldh.modules.inventory.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.inventory.entity.InventoryComment;
import com.ldh.modules.inventory.model.InventoryCommentModel;
import com.ldh.modules.inventory.service.InventoryCommentService;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "商品评论对外接口")
@RestController
@RequestMapping("/inventory/inventoryComment/handle")
public class InventoryCommentHandle {

    @Autowired
    private InventoryCommentService inventoryCommentService;

    @GetMapping("list")
    @ApiOperation(value = "商品评论-全部评论", notes = "商品评论-全部评论")
    public Result<?> list(InventoryComment inventoryComment,
                                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                @RequestParam(name="column", required = false) String column,
                                @RequestParam(name="order", required = false) String order){

        Result<IPage> result = new Result<>();
        Page<InventoryComment> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(order.equals("desc")){
            queryWrapper.orderByDesc(StringTo.humpToLine(column));
        }else{
            queryWrapper.orderByAsc(StringTo.humpToLine(column));
        }
        try {
            IPage<InventoryCommentModel> iPage = inventoryCommentService.list(page, queryWrapper, inventoryComment);
            result.setResult(iPage);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @ApiOperation(value="商品评论-删除评论(伪删除)", notes="删除评论（伪删除）")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(name = "id", required = true) String id){
        Result<?> result = new Result<>();
        try {
            InventoryComment inventoryComment = new InventoryComment();
            inventoryComment
                    .setInventoryCommentId(id)
                    .setSts("-1");
            inventoryCommentService.updateById(inventoryComment);
            result.succcess("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            result.error(e.getMessage());
        }
        return result;
    }


}
