package com.ldh.modules.informationMaintenance.model;

import com.ldh.modules.informationMaintenance.entity.PhysicalDistribution;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class PhysicalDistributionModel extends PhysicalDistribution implements Serializable {
}
