package com.express.server.filter;

import cn.hutool.http.ContentType;
import com.alibaba.fastjson2.JSON;
import com.express.server.common.ErrorCodes;
import com.express.server.common.ResponseData;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.nio.charset.StandardCharsets;

/**
 * @author youping.tan
 * @date 2024/8/6 16:23
 */
public abstract class AbstractCommonFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(AbstractCommonFilter.class);

    protected void resp(HttpServletResponse response, ErrorCodes errorCodes) {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(ContentType.JSON.getValue());
        response.setStatus(HttpStatus.OK.value());
        try {
            String result = JSON.toJSONString(ResponseData.result(errorCodes));
            response.getWriter().write(result);
            response.getWriter().flush();
        } catch (Exception e) {
            log.error("响应失败", e);
        }
    }
}
