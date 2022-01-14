package com.ldh.modules.order.vo;

import com.ldh.modules.order.entity.OrderPhysicalDistribution;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class OrderPhysicalDistributionVO extends OrderPhysicalDistribution implements Serializable {

    /** 快递员userId */
    private String courierUserId;

    /** 快递员编号（username） */
    private String courierUsername;
}
