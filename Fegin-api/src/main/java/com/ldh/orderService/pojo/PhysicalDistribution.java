package com.ldh.orderService.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 物流信息
 * @Author: jeecg-boot
 * @Date:   2022-01-11
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="physical_distribution对象", description="物流信息")
public class PhysicalDistribution {
    
	/**物流id*/
    @ApiModelProperty(value = "物流id")
	private String physicalDistributionId;
	/**物流订单id*/
    @ApiModelProperty(value = "物流订单id")
	private String orderPhysicalDistributionId;
	/**下一位置id*/
    @ApiModelProperty(value = "下一位置id")
	private String nextPositionId;
	/**当前位置id*/
    @ApiModelProperty(value = "当前位置id")
	private String nowPositionId;
	/**下一位置名称*/
    @ApiModelProperty(value = "下一位置名称")
	private String nextPositionName;
	/**当前位置名称*/
    @ApiModelProperty(value = "当前位置名称")
	private String nowPositionName;
	/**sts*/
    @ApiModelProperty(value = "sts")
	private String sts;
	/**remark*/
    @ApiModelProperty(value = "remark")
	private String remark;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**createBy*/
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**updateTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**updateBy*/
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
}
