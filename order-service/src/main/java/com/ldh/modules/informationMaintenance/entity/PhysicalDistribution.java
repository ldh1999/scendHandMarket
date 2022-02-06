package com.ldh.modules.informationMaintenance.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 物流信息
 * @Author: ldh
 * @Date:   2022-01-11
 * @Version: V1.0
 */
@Data
@TableName("physical_distribution")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="physical_distribution对象", description="物流信息")
public class PhysicalDistribution {
    
	/**物流id*/
	@TableId(type = IdType.UUID)
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
	@TableField(fill = FieldFill.INSERT)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**createBy*/
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**updateTime*/
	@TableField(fill = FieldFill.UPDATE)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**updateBy*/
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
}
