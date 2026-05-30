package com.myapp.infrastructure.persistence.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myapp.infrastructure.persistence.user.po.SysUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表 MyBatis-Plus Mapper。
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserPO> {
}
