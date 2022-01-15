package com.ldh.modules.informationMaintenance.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.informationMaintenance.entity.CourierServicesCompany;
import com.ldh.modules.informationMaintenance.entity.PhysicalDistribution;
import com.ldh.modules.informationMaintenance.model.PhysicalDistributionModel;
import com.ldh.modules.informationMaintenance.service.IPhysicalDistributionService;
import com.ldh.modules.order.entity.OrderPhysicalDistribution;
import com.ldh.modules.order.model.OrderPhysicalDistributionModel;
import com.ldh.modules.order.vo.OrderPhysicalDistributionVO;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags="物流信息对外接口")
@RestController
@RequestMapping("/order/physicalDistribution/handle")
public class PhysicalDistributionHandle {

    @Autowired
    private IPhysicalDistributionService physicalDistributionService;

    /**
     * 快递员物流list
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value="快递员物流list", notes="快递员物流list")
    @GetMapping(value = "/list")
    public Result<?> list(@RequestParam(name = "orderPhysicalDistributionId", required = true) String orderPhysicalDistributionId,
                              @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                              @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                              @RequestParam(name="column", required = false) String column,
                              @RequestParam(name="order", required = false) String order) {
        Page<PhysicalDistributionModel> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(order.equals("desc")){
            queryWrapper.orderByDesc(StringTo.humpToLine(column));
        }else{
            queryWrapper.orderByAsc(StringTo.humpToLine(column));
        }
        Result result = new Result();
        try{
            PhysicalDistribution physicalDistribution = new PhysicalDistribution();
            physicalDistribution.setOrderPhysicalDistributionId(orderPhysicalDistributionId);
            IPage<PhysicalDistributionModel> pageList = physicalDistributionService.list(page, queryWrapper, physicalDistribution);
            result.setResult(pageList);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    /**
     * 快递员增加物流
     * @param physicalDistribution
     * @return
     */
    @ApiOperation(value="快递员增加物流", notes="快递员增加物流")
    @PostMapping(value = "add")
    public Result<?> add(@RequestBody PhysicalDistribution physicalDistribution){
        Result result = new Result();
        try {
            physicalDistributionService.save(physicalDistribution);
            result.succcess("添加成功");
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    /**
     *获取物流最新位置
     * @param orderPhysicalDistributionId
     * @return
     */
    @ApiOperation(value="获取物流最新位置", notes="获取物流最新位置")
    @GetMapping(value = "getNowPositionBuOrderPhyId")
    public Result<?> getNowPositionBuOrderPhyId(@RequestParam(name = "orderPhysicalDistributionId" , required = true)String orderPhysicalDistributionId){
        Result result = new Result();
        try {
            result.setResult(physicalDistributionService.getNowPositionBuOrderPhyId(orderPhysicalDistributionId));
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

}
