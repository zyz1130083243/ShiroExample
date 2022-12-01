package com.zyz.example.controller;

import com.zyz.example.common.ApiResponse;
import com.zyz.example.constants.AdminConstants;
import com.zyz.example.constants.StateCode;
import com.zyz.example.entity.SysUser;
import com.zyz.example.service.SysUserService;
import com.zyz.example.vo.UserLoginVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Resource
    private SysUserService sysUserService;


    @PostMapping("/login")
    public ApiResponse login(@RequestBody UserLoginVO userLoginVO) {

        String userName = userLoginVO.getUserName();
        String password = userLoginVO.getPassword();

        // 校验输入是否合法
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            return ApiResponse.error(StateCode.ILLEGAL_PARAM);
        }

        // 查询redis是否存在该用户登录令牌，存在则代表登录过，刷新登录有效期(暂时不做)


        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);

        // 判断是否已经认证
        Session currentUser = subject.getSession();
        if (subject.isAuthenticated()) {
            currentUser.setTimeout(24 * 60 * 60 * 1000);
            // 获取用户详细信息，存放到session中
            SysUser sysUser = sysUserService.getByUserName(userName);
            currentUser.setAttribute(AdminConstants.SESSION_USER_INFO, sysUser);
        }

        Map<String, String> data = new HashMap<>(2);
        data.put("token", currentUser.getId().toString());
        return ApiResponse.ok(data);
    }

    @RequestMapping("logout")
    public ApiResponse logout() {
        SecurityUtils.getSubject().logout();
        return ApiResponse.ok();
    }

}
