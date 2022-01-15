package com.ldh.modules.order.model;

import com.ldh.modules.order.entity.OrderPhysicalDistribution;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class OrderPhysicalDistributionModel extends OrderPhysicalDistribution implements Serializable {

    /** 当前位置 */
    private String nowPositionName;

    /** 下一位置 */
    private String nextPositionName;
}
