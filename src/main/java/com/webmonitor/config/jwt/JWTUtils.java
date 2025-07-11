package com.webmonitor.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;


public class JWTUtils {
  /**
   * 过期时间6小时
   */
  private static final long EXPIRE_TIME = 6 * 60 * 60 * 1000;

  /**
   * 校验token是否正确
   *
   * @param token  密钥
   * @param secret 用户的密码
   * @return 是否正确
   */
  public static boolean verify(String token, String username, String secret) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      JWTVerifier verifier = JWT.require(algorithm)
              .withClaim("username", username)
              .build();
      DecodedJWT jwt = verifier.verify(token);
      return true;
    } catch (Exception exception) {
      return false;
    }
  }

  /**
   * 获得token中的信息无需secret解密也能获得
   *
   * @return token中包含的用户名
   */
  public static String getUsername(String token) {
    try {
      DecodedJWT jwt = JWT.decode(token);
      return jwt.getClaim("username").asString();
    } catch (JWTDecodeException e) {
      return null;
    }
  }

  /**
   * 生成签名,设置过期时间
   *
   * @param username 用户名
   * @param secret   用户的密码
   * @return 加密的token
   */
  public static String sign(String username, String secret) {
    try {
      Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
      Algorithm algorithm = Algorithm.HMAC256(secret);
      // 附带username信息
      return JWT.create()
              .withClaim("username", username)
              // todo 永久
//              .withExpiresAt(date)
              .sign(algorithm);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 判断过期
   *
   * @param token
   * @return
   */
  public static boolean isExpire(String token) {
    DecodedJWT jwt = JWT.decode(token);
    return System.currentTimeMillis() > jwt.getExpiresAt().getTime();
  }
}
