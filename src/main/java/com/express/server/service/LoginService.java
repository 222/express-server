package com.express.server.service;

import cn.hutool.core.util.IdUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.express.server.common.LoginSession;
import com.express.server.mapper.UserMapper;
import com.express.server.model.User;
import com.express.server.param.req.UserLoginDTO;
import com.express.server.param.resp.UserLoginVO;
import com.express.server.properties.TokenProperties;
import com.express.server.utils.JwtUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author youping.tan
 * @date 2024/8/6 10:31
 */
@Service
public class LoginService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private TokenProperties tokenProperties;

    public UserLoginVO login(UserLoginDTO userLoginDTO) {
        String phone = userLoginDTO.getPhone();
        User user = userMapper.loadByPhone(phone);
        if (Objects.isNull(user)) {
            user = new User();
            user.setUserId(IdUtil.getSnowflakeNextId());
            user.setPhone(phone);
            userMapper.insert(user);
        }
        Integer expire = tokenProperties.getExpire();
        String secret = tokenProperties.getSecret();
        LoginSession loginSession = new LoginSession();
        loginSession.setRealName(user.getPhone());
        loginSession.setMemberId(user.getUserId());
        loginSession.setPhone(user.getPhone());

        String token = JwtUtils.createToken(expire, secret, loginSession);
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setToken(token);

        return userLoginVO;

    }

}
