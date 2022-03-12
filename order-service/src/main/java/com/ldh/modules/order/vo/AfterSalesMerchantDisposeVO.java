package com.ldh.modules.order.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class AfterSalesMerchantDisposeVO implements Serializable {

    /** 是否同意 */
    private String dispose;
    /** 处理方式 */
    private String mode;
    /** 售后id */
    private String afterSalesId;
    /** 商家留言 */
    private String merchantReason;

}
