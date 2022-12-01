package com.zyz.example.service;

import com.zyz.example.entity.SysResource;
import com.zyz.example.mapper.SysResourceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class SysResourceService {
    @Resource
    private SysResourceMapper sysResourceMapper;

    public List<SysResource> getSysResourceCacheByRoleId(Long roleId) {
        // 从缓存获取


        return Collections.emptyList();
    }
}
