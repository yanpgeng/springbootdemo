package com.example.springbootdemotoken.tokens;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springbootdemotoken.consts.SysConst;
import com.example.springbootdemotoken.exceptions.TokenException;
import com.example.springbootdemotoken.utils.RedisCacheUtil;
import com.example.springbootdemotoken.utils.TimeUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaozhengyang
 * @date 2018/05/18 10:04
 */
public class JsonWebToken {
    static Logger logger = LoggerFactory.getLogger(JsonWebToken.class);

    /**
     * 标识唯一用户信息
     */
    private static String KEY_USERID = "userId";

    /**
     * 标识用户信息登陆的唯一性
     */
    private static String KEY_TIME = "timeStamp";

    /**
     * token过期时间
     */
    private static final long EXPIRE_TIME = 4 * 60 * 60 * 1000;

    private static String createToken(Map<String, String> claims, Date date) {

        if (null == claims || claims.size() == 0) {
            throw new TokenException(500, "Create Token id failed,cause of [ The Claim cannot be null.] ");
        }

        Map<String, Object> map = new HashMap<>(16);
        map.put("alg", SysConst.TOKENALG.getValue());
        map.put("typ", SysConst.TOKENTYP.getValue());

        JWTCreator.Builder builder = JWT.create();
        builder.withHeader(map);
        String key_time_value = "";
        for (String key : claims.keySet()) {
            String value = claims.get(key);
            //第一次登陆验证
            if (KEY_TIME.equals(key)) {
                if (StrUtil.isEmpty(value)) {
                    value = DigestUtils.md5Hex(String.valueOf(Instant.now().getEpochSecond()));
                }
                //登陆后验证登陆时间戳
                key_time_value = value;
            }
            if (StrUtil.isEmpty(value)) {
                throw new TokenException(500, "Create Token id failed,cause of  The Claim-Value [" + key + "] cannot be null.] ");
            }
            builder.withClaim(key, value);
        }

        builder.withClaim("exp", date);
        builder.withKeyId(String.valueOf(Instant.now().getEpochSecond()));
        builder.withExpiresAt(date);

        String token = null;
        try {
            token = builder.sign(Algorithm.HMAC256(SysConst.TOKENSECRET.getValue()));
        } catch (UnsupportedEncodingException e) {
            logger.error("生成token异常{}", e.getLocalizedMessage());
        } finally {
            if (null != token) {
                logger.info(claims.get(KEY_USERID));
                logger.info(DigestUtils.md5Hex(claims.get(KEY_USERID)));
                RedisCacheUtil.set(SysConst.TOKENPREFIX.getMessage()+DigestUtils.md5Hex(claims.get(KEY_USERID)), token);
            }
        }
        return token;
    }

    public static String createToken(Map<String, String> claims, Long times) {
        return createToken(claims, TimeUtils.plusSeconds2Date(times));
    }

    public static String createToken(Map<String, String> claims) {
//        return createToken(claims, TimeUtils.plusSeconds2Date(Long.valueOf(SysConst.TOKENEXPTIME.getValue()) * 60 * 60 * 1000));
        return createToken(claims, TimeUtils.plusSeconds2Date(Long.valueOf(SysConst.TOKENEXPTIME.getValue()) * 1000));
    }

    public static String updateToken(String token) {
        Map<String, String> claimMap = verifyToken(token);
        destroyMap(claimMap);
        return createToken(claimMap);
    }

    public static String destroyToken(String token) {
        Map<String, String> claims = verifyToken(token);
        if (null != token && null != claims) {
            destroyMap(claims);
        }
        return null;
    }

    private static String destroyMap(Map<String, String> claims) {
        if (null != claims) {
            String key = DigestUtils.md5Hex(claims.get(KEY_USERID));
            if (RedisCacheUtil.exists(SysConst.TOKENPREFIX.getMessage()+key)) {
                RedisCacheUtil.del(SysConst.TOKENPREFIX.getMessage()+ key);
            }
        }
        return null;
    }

    private static Map<String, String> Claim2map(Map<String, Claim> claimMap) {
        if (null != claimMap) {
            Map<String, String> map = new HashMap<>();
            for (String key : claimMap.keySet()) {
                if ("exp".equals(key)) {
                    continue;
                }
                map.put(key, claimMap.get(key).asString());
            }
            return map;
        }
        return null;
    }

    public static Map<String, String> verifyToken(String token) {
        DecodedJWT jwt = null;
        if (null != token) {
            try {
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SysConst.TOKENSECRET.getValue()))
                        .build();
                jwt = verifier.verify(token);

            } catch (Exception e) {
                return null;
            }
            Map<String, Claim> claims = jwt.getClaims();

            if (null != claims) {
                Map<String, String> clamap = Claim2map(claims);
                System.out.println(clamap.get(KEY_USERID));
                return clamap;
                /*String redisKey = DigestUtils.md5Hex(clamap.get(KEY_USERID));
                System.out.println(RedisCacheUtil.exists(redisKey));
                if (RedisCacheUtil.exists(redisKey)) {
                    if (token.equals(RedisCacheUtil.get(redisKey))) {
                        return clamap;
                    }
                    return null;
                }*/
            }
            return null;
        }
        return null;
    }

    public static Date getTokenExpireTime(String token){
        DecodedJWT jwt = null;
        if (null != token) {
            try {
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SysConst.TOKENSECRET.getValue()))
                        .build();
                jwt = verifier.verify(token);

            } catch (Exception e) {
                return null;
            }
            Date expiresAt = jwt.getExpiresAt();
            return expiresAt;
        }else{
            return null;
        }
    }

}
