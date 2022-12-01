package com.zyz.example.service;

import com.zyz.example.entity.SysUser;
import com.zyz.example.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    public SysUser authLogin(String userName, String password) {
        return sysUserMapper.findLoginUser(userName, password);
    }

    public SysUser getByUserName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return null;
        }
        return sysUserMapper.getByUserName(userName);
    }
}
