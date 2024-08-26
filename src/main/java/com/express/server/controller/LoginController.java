package com.express.server.controller;

import com.alibaba.fastjson2.JSON;
import com.express.server.common.LoginSession;
import com.express.server.common.Response;
import com.express.server.filter.RequestContext;
import com.express.server.param.req.UserLoginDTO;
import com.express.server.param.resp.UserLoginVO;
import com.express.server.service.LoginService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author youping.tan
 * @date 2024/8/6 10:17
 */
@RestController
public class LoginController {


    @Resource
    private LoginService loginService;

    /**
     * 登录
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/open/login")
    public Response<UserLoginVO> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        UserLoginVO userLoginVO = loginService.login(userLoginDTO);
        return new Response<>(userLoginVO);
    }


    /**
     * 注销
     *
     * @return
     */
    @PostMapping("/logout")
    public Response<UserLoginVO> logout() {
        LoginSession loginSession = RequestContext.get();
        System.out.println(JSON.toJSONString(loginSession));
        return new Response<>();
    }

}
