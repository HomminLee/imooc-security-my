package com.hommin.security.core.properties;

/**
 * 常量
 *
 * @author Hommin
 * 2019年06月20日 3:12 PM
 */
public interface SecurityConst {

    String DEFAULT_LOGIN_UNAUTEHNTICATION_URL = "/authentication/require";

    String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

    String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";

    String DEFAULT_LOGIN_PROCESSING_URL_OPENID = "/authentication/openId";

    String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

    /**
     * openId参数名
     */
    String DEFAULT_PARAMETER_NAME_OPENID = "openId";

    /**
     * providerId参数名
     */
    String DEFAULT_PARAMETER_NAME_PROVIDERID = "providerId";


    String DEFAULT_LOGIN_PAGE = "/hommin-sign-in.html";

    /**
     * 处理验证码url前缀
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
    /**
     * session失效的跳转地址
     */
    String DEFAULT_SESSION_INVALID_URL = "/session/invalid.html";

    /**
     * 退出登录后默认删除的cookie
     */
    String[] DEFAULT_DELETE_COOKIE_ARR = new String[]{"JSESSIONID"};
}
