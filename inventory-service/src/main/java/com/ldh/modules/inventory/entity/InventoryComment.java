package com.ldh.modules.inventory.entity;

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
 * @Description: 商品评论
 * @Author: ldh
 * @Date:   2022-01-29
 * @Version: V1.0
 */
@Data
@TableName("inventory_comment")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="inventory_comment对象", description="商品评论")
public class InventoryComment implements Serializable{
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private String inventoryCommentId;
	/**编码*/
    @ApiModelProperty(value = "编码")
	private String inventoryCommentCode;
	/**父级id 默认为 -1*/
    @ApiModelProperty(value = "父级id 默认为 -1")
	private String fatherId;
	/** 商品id */
	@ApiModelProperty(value = "商品id")
    private String inventoryId;
	/**被回复评论id*/
    @ApiModelProperty(value = "被回复评论id")
	private String replayCommentId;
	/**被恢复用户id*/
    @ApiModelProperty(value = "被恢复用户id")
	private String replayUserId;
	/**内容*/
    @ApiModelProperty(value = "内容")
	private String content;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@TableField(fill = FieldFill.INSERT)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改时间*/
	@TableField(fill = FieldFill.UPDATE)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**修改人*/
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**状态*/
    @ApiModelProperty(value = "状态")
	private String sts;
}
