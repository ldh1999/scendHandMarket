package com.ldh.orderService.model;

import com.ldh.orderService.pojo.OrderPhysicalDistribution;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class OrderPhysicalDistributionModel extends OrderPhysicalDistribution implements Serializable {
}
