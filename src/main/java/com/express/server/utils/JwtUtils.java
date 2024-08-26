package com.express.server.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.express.server.common.BusinessException;
import com.express.server.common.LoginSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

/**
 * @author youping.tan
 * @date 2024/8/6 14:49
 */
public class JwtUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    public static String createToken(Integer expires, String secret, LoginSession loginSession) {
        try {
            String phone = loginSession.getPhone();
            Long memberId = loginSession.getMemberId();
            String realName = loginSession.getRealName();
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DateTime dateTime = DateUtil.offsetHour(new Date(), expires);
            return JWT.create()
                    .withExpiresAt(dateTime)
                    .withClaim("memberId", memberId)
                    .withClaim("phone", phone)
                    .withClaim("realName", realName)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            LOGGER.error("createToken error：", exception);
            throw new BusinessException("签发用户令牌失败");
        }
    }

    public static boolean verifyToken(String token, String secret) {
        try {
            com.auth0.jwt.JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public static LoginSession parseToken(String token, String secret) {
        try {
            DecodedJWT decodedJWT = com.auth0.jwt.JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
            Map<String, Claim> claims = decodedJWT.getClaims();
            Long memberId = claims.get("memberId").asLong();
            String phone = claims.get("phone").asString();
            String realName = claims.get("realName").asString();

            LoginSession loginSession = new LoginSession();
            loginSession.setMemberId(memberId);
            loginSession.setPhone(phone);
            loginSession.setRealName(realName);
            return loginSession;

        } catch (JWTVerificationException exception) {
            LOGGER.error("createToken error：", exception);
            throw new BusinessException("签发用户令牌失败");
        }
    }
}
