package com.example.springbootjustauth.utils;

import com.xkcoding.http.config.HttpConfig;
import me.zhyd.oauth.cache.AuthDefaultStateCache;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.enums.scope.*;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.request.*;
import me.zhyd.oauth.utils.AuthScopeUtils;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

/**
 * 认证授权工具类
 *
 * @author ruoyi
 */
public class AuthUtils {
    @SuppressWarnings("deprecation")
    public static AuthRequest getAuthRequest(String source, String clientId, String clientSecret, String redirectUri,
                                             AuthStateCache authStateCache) {
        AuthRequest authRequest = null;
        switch (source.toLowerCase()) {
            case "dingtalk":
                authRequest = new AuthDingTalkRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build());
                break;
            case "baidu":
                authRequest = new AuthBaiduRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .scopes(Arrays.asList(
                                AuthBaiduScope.BASIC.getScope(),
                                AuthBaiduScope.SUPER_MSG.getScope(),
                                AuthBaiduScope.NETDISK.getScope()
                        ))
//                        .clientId(clientId)
//                        .clientSecret(clientSecret)
//                        .redirectUri("http://localhost:9001/oauth/baidu/callback")
                        .build());
                break;
            case "github":
                authRequest = new AuthGithubRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .scopes(AuthScopeUtils.getScopes(AuthGithubScope.values()))
                        // 针对国外平台配置代理
                        .httpConfig(HttpConfig.builder()
                                .timeout(30000)
                                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10080)))
                                .build())
                        .build(), AuthDefaultStateCache.INSTANCE);
                break;
            case "gitee":
                authRequest = new AuthGiteeRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .scopes(AuthScopeUtils.getScopes(AuthGiteeScope.values()))
                        .build(), AuthDefaultStateCache.INSTANCE);
                break;
            case "weibo":
                authRequest = new AuthWeiboRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .scopes(Arrays.asList(
                                AuthWeiboScope.EMAIL.getScope(),
                                AuthWeiboScope.FRIENDSHIPS_GROUPS_READ.getScope(),
                                AuthWeiboScope.STATUSES_TO_ME_READ.getScope()
                        ))
                        .build());
                break;
            case "coding":
                authRequest = new AuthCodingRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .domainPrefix("")
                        .scopes(Arrays.asList(
                                AuthCodingScope.USER.getScope(),
                                AuthCodingScope.USER_EMAIL.getScope(),
                                AuthCodingScope.USER_PHONE.getScope()
                        ))
                        .build());
                break;
            case "oschina":
                authRequest = new AuthOschinaRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build());
                break;
            case "alipay":
                // 支付宝在创建回调地址时，不允许使用localhost或者127.0.0.1，所以这儿的回调地址使用的局域网内的ip
                authRequest = new AuthAlipayRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .alipayPublicKey("")
                        .redirectUri(redirectUri)
                        .build());
                break;
            case "qq":
                authRequest = new AuthQqRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build());
                break;
            case "wechat_open":
                authRequest = new AuthWeChatOpenRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build());
                break;
            case "csdn":
                authRequest = new AuthCsdnRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build());
                break;
            case "taobao":
                authRequest = new AuthTaobaoRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build());
                break;
            case "google":
                authRequest = new AuthGoogleRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .scopes(AuthScopeUtils.getScopes(AuthGoogleScope.USER_EMAIL, AuthGoogleScope.USER_PROFILE, AuthGoogleScope.USER_OPENID))
                        // 针对国外平台配置代理
                        .httpConfig(HttpConfig.builder()
                                .timeout(15000)
                                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10080)))
                                .build())
                        .build());
                break;
            case "facebook":
                authRequest = new AuthFacebookRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .scopes(AuthScopeUtils.getScopes(AuthFacebookScope.values()))
                        // 针对国外平台配置代理
                        .httpConfig(HttpConfig.builder()
                                .timeout(15000)
                                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10080)))
                                .build())
                        .build());
                break;
            case "douyin":
                authRequest = new AuthDouyinRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build());
                break;
            case "linkedin":
                authRequest = new AuthLinkedinRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .scopes(null)
                        .build());
                break;
            case "microsoft":
                authRequest = new AuthMicrosoftRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .scopes(Arrays.asList(
                                AuthMicrosoftScope.USER_READ.getScope(),
                                AuthMicrosoftScope.USER_READWRITE.getScope(),
                                AuthMicrosoftScope.USER_READBASIC_ALL.getScope(),
                                AuthMicrosoftScope.USER_READ_ALL.getScope(),
                                AuthMicrosoftScope.USER_READWRITE_ALL.getScope(),
                                AuthMicrosoftScope.USER_INVITE_ALL.getScope(),
                                AuthMicrosoftScope.USER_EXPORT_ALL.getScope(),
                                AuthMicrosoftScope.USER_MANAGEIDENTITIES_ALL.getScope(),
                                AuthMicrosoftScope.FILES_READ.getScope()
                        ))
                        .build());
                break;
            case "mi":
                authRequest = new AuthMiRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build());
                break;
            case "toutiao":
                authRequest = new AuthToutiaoRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build());
                break;
            case "teambition":
                authRequest = new AuthTeambitionRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build());
                break;
            case "pinterest":
                authRequest = new AuthPinterestRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        // 针对国外平台配置代理
                        .httpConfig(HttpConfig.builder()
                                .timeout(15000)
                                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10080)))
                                .build())
                        .build());
                break;
            case "renren":
                authRequest = new AuthRenrenRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build());
                break;
            case "stack_overflow":
                authRequest = new AuthStackOverflowRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret("((")
                        .redirectUri(redirectUri)
                        .stackOverflowKey("")
                        .build());
                break;
            case "huawei":
                authRequest = new AuthHuaweiRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .scopes(Arrays.asList(
                                AuthHuaweiScope.BASE_PROFILE.getScope(),
                                AuthHuaweiScope.MOBILE_NUMBER.getScope(),
                                AuthHuaweiScope.ACCOUNTLIST.getScope(),
                                AuthHuaweiScope.SCOPE_DRIVE_FILE.getScope(),
                                AuthHuaweiScope.SCOPE_DRIVE_APPDATA.getScope()
                        ))
                        .build());
                break;
            case "wechat_enterprise":
                authRequest = new AuthWeChatEnterpriseQrcodeRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .agentId("1000003")
                        .build());
                break;
            case "kujiale":
                authRequest = new AuthKujialeRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build());
                break;
            case "gitlab":
                authRequest = new AuthGitlabRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .scopes(AuthScopeUtils.getScopes(AuthGitlabScope.values()))
                        .build());
                break;
            case "meituan":
                authRequest = new AuthMeituanRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build());
                break;
            case "eleme":
                authRequest = new AuthElemeRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build());
                break;
            case "twitter":
                authRequest = new AuthTwitterRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        // 针对国外平台配置代理
                        .httpConfig(HttpConfig.builder()
                                .timeout(15000)
                                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10080)))
                                .build())
                        .build());
                break;
            case "wechat_mp":
                authRequest = new AuthWeChatMpRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri("")
                        .build());
                break;
            case "aliyun":
                authRequest = new AuthAliyunRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build());
                break;
            case "xmly":
                authRequest = new AuthXmlyRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build());
                break;
            case "feishu":
                authRequest = new AuthFeishuRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build());
                break;
            default:
                break;
        }
        if (null == authRequest) {
            throw new AuthException("未获取到有效的Auth配置");
        }
        return authRequest;
    }
}
