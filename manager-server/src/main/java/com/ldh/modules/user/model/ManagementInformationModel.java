package com.ldh.modules.user.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class ManagementInformationModel implements Serializable {

    private Integer orderSum;
    private Integer orderDay;
    private Integer orderDayPrice;

    private Integer UserSum;
    private Integer UserWeek;

    private Integer MerchantSum;
    private Integer MerchantWeek;
}
