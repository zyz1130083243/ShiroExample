package com.zyz.example.service;

import com.zyz.example.entity.SysRole;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SysRoleService {
    public List<SysRole> getRoleCacheByUserId(Long userId) {
        // 从缓存获取角色


        return Collections.emptyList();

    }
}
