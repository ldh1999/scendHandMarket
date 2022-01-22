package com.ldh.inventoryService.client;

import com.ldh.inventoryService.model.MerchantModel;
import com.ldh.inventoryService.pojo.Merchant;
import common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("inventoryservice/merchant/handle")
public interface MerchantClient {
    @RequestMapping(path = "selectById", method = RequestMethod.GET)
    Result<MerchantModel> selectById(@RequestParam(value = "id",required = true) String id);

    @RequestMapping(path = "/selectByIds", method = RequestMethod.GET)
    Result<List<MerchantModel>> selectByIds(@RequestParam(name = "ids", required = true) String ids);

    @RequestMapping(path = "list", method = RequestMethod.GET)
    Result<?> list(@SpringQueryMap Merchant merchant,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order);

    /** 商家审批管理修改 */
    @RequestMapping(path = "/updateById", method = RequestMethod.POST)
    Result<?> updateById(@RequestBody Merchant merchant);

    /** 根据条件获取Merchant数量 */
    @GetMapping(value = "/getOrderCountByObject")
    Result<?> getMerchantCountByObject(@RequestParam(name = "id", required = false) String obj);
}
