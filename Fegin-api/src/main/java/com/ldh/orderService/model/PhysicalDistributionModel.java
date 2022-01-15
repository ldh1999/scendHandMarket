package com.ldh.orderService.model;

import com.ldh.orderService.pojo.PhysicalDistribution;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class PhysicalDistributionModel extends PhysicalDistribution implements Serializable {
}
