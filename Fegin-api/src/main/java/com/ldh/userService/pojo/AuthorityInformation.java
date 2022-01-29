package com.ldh.userService.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class AuthorityInformation implements Serializable {

//    private static final long seriaVsersionUID=1L;
    /**
     * id
     */
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
    /** 头像路径 */
    private String imgPath;
    private String sts;
    private String remark;



    private String createBy;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    private String updateBy;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
