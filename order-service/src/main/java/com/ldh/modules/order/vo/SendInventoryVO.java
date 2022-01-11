package com.ldh.modules.order.vo;

import com.ldh.modules.order.entity.OrderPhysicalDistribution;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class SendInventoryVO extends OrderPhysicalDistribution implements Serializable {

    /**订单id*/
    private String orderId;

}
