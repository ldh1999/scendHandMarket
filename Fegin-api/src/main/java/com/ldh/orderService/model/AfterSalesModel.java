package com.ldh.orderService.model;

import com.ldh.orderService.pojo.AfterSales;
import lombok.Data;

import java.io.Serializable;

@Data
public class AfterSalesModel extends AfterSales implements Serializable {

    private String merchantName;
    private String inventoryName;
    private String firstImage;
}
