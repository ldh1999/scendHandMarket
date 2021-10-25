package com.ldh.modules.authority.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class AuthorityInformation implements Serializable {

    private static final long seriaVsersionUID=1L;
    /**
     * id
     */
    @TableId(type = IdType.UUID)
    private String authorityId;

    /**
     * 用户名
     */
    private String authorityUsername;

    /**
     * 密码
     */
    private String authorityPassword;

    /**
     * 用户昵称
     */
    private String authorityName;

    /**
     * 真实名称
     */
    private String realName;

    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private String age;
    /**
     * 手机号
     */
    private String phone;
    private String sts;
    private String remark;
    /** 权限码 */
    private String token;


    private String createBy;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    private String updateBy;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
