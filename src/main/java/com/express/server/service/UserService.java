package com.express.server.service;

import com.express.server.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author youping.tan
 * @date 2024/8/6 10:30
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

}
