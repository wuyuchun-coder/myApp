package com.myapp.infrastructure.persistence.user.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户表持久化对象，映射 sys_user。
 */
@Data
@TableName("sys_user")
public class SysUserPO {

    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    private String username;

    @TableField("password_hash")
    private String passwordHash;

    private String email;

    private String nickname;

    private Integer status;

    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
