package com.ldh.orderService.model;

import com.ldh.orderService.pojo.AfterSales;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class AfterSalesDetailsModel extends AfterSales implements Serializable {

    private String userName;
    private String userPhone;
    private String userAuthorityName;

    private String addressName;

    private String inventoryName;
    private String inventoryCode;

    private List<String> reasonImageList;

    private String merchantCode;
    private String merchantPhone;
    private String merchantName;

}
