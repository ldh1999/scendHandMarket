package com.ldh.userService.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 用户地址
 * @Author: ldh
 * @Date:   2021-11-27
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="authority_address对象", description="用户地址")
public class AuthorityAddress implements Serializable{
    
	/**id*/
    @ApiModelProperty(value = "addressId")
	private String addressId;
	/**用户id*/
    @ApiModelProperty(value = "用户id")
	private String authorityId;
	/**通讯人姓名*/
    @ApiModelProperty(value = "通讯人姓名")
	private String addressName;

	/**地址经纬度*/
	@ApiModelProperty(value = "地址经纬度")
	private String addressLocation;
	/**通讯人地址*/
    @ApiModelProperty(value = "通讯人地址")
	private String addressSite;
	/**通讯人手机号*/
    @ApiModelProperty(value = "通讯人手机号")
	private String addressPhone;
	/**remark*/
    @ApiModelProperty(value = "remark")
	private String remark;
	/**sts*/
    @ApiModelProperty(value = "sts")
	private String sts;
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
