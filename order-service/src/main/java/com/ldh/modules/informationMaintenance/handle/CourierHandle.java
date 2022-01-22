package com.ldh.modules.informationMaintenance.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.informationMaintenance.entity.Courier;
import com.ldh.modules.informationMaintenance.service.ICourierService;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags="快递员handle")
@RestController
@RequestMapping("/order/courier/handle")
public class CourierHandle {

    @Autowired
    private ICourierService courierService;

    /**
     * 分页列表查询
     *
     * @param courier
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value="快递员-分页列表查询", notes="快递员-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Courier courier,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   @RequestParam(name="column", required = false) String column,
                                   @RequestParam(name="order", required = false) String order) {

        Result<Page> result = new Result<>();
        Page page = new Page(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(order != null && order.equals("desc")){
            queryWrapper.orderByDesc(StringTo.humpToLine(column));
        }else{
            queryWrapper.orderByAsc(StringTo.humpToLine(column));
        }
        try {
            result.setResult(courierService.list(page, queryWrapper, courier));
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    /**
     * 添加
     *
     * @param courier
     * @return
     */
    @ApiOperation(value="快递员-添加", notes="快递员-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Courier courier) {
        Result result = new Result();
        try {
            courierService.addHandle(courier);
            result.succcess("添加成功");
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    /**
     * 编辑
     *
     * @param courier
     * @return
     */
    @ApiOperation(value="快递员-编辑", notes="快递员-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Courier courier) {
        Result result = new Result();
        try {
            courierService.updateById(courier);
            result.succcess("编辑成功");
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value="快递员-通过id删除", notes="快递员-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        Result result = new Result();
        try {
            courierService.deleteAnyById(id);
            result.succcess("删除成功");
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    /**
     * 根据快递员编码查信息
     *
     * @param code 快递员编码
     * @return
     */
    @ApiOperation(value="快递员-根据快递员编码查信息", notes="快递员-根据快递员编码查信息")
    @GetMapping(value = "/queryAllByCode")
    public Result<?> queryAllByCode(@RequestParam("code")String code) {
        Result result = new Result();
        try {
            result.setResult(courierService.getAllByCode(code));
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }
}
