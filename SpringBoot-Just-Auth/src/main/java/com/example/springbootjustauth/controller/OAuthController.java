package com.example.springbootjustauth.controller;

import com.example.springbootjustauth.config.GitHubOAuthProp;
import com.example.springbootjustauth.utils.AuthUtils;
import lombok.Data;
import me.zhyd.oauth.cache.AuthDefaultStateCache;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * ClassName: OAuthController
 * Package: com.example.springbootjustauth.controller
 * Description:
 *
 * @Author ms
 * @Create 2024/11/23 10:51
 * @Version 1.0
 */

@RestController
@RequestMapping("/oauth")
@Data
public class OAuthController {

    private final GitHubOAuthProp gitHubOAuthProp;


    /**
     * 登录
     *
     * @return
     */
    @RequestMapping("/render")
    public void login(String source, HttpServletResponse response) throws IOException {
        // 创建一个AuthRequest对象
        AuthRequest authRequest = getAuthRequest(source);
        // 生成授权链接(登录链接)
        String authorizeUrl = authRequest.authorize(AuthStateUtils.createState());
        // 重定向到授权链接
        response.sendRedirect(authorizeUrl);
    }

    // 回调
    @RequestMapping("/callback/{source}")
    public Object login(@PathVariable("source") String source, AuthCallback callback) {
        AuthRequest authRequest = getAuthRequest(source);
        AuthResponse response = authRequest.login(callback);
        return response;
    }


    private AuthRequest getAuthRequest(String source) {
        final String redirectUrl = gitHubOAuthProp.getRedirectUri();
        final String clientId = gitHubOAuthProp.getClientId();
        final String clientSecret = gitHubOAuthProp.getClientSecret();
        return AuthUtils.getAuthRequest(source, clientId, clientSecret, redirectUrl, AuthDefaultStateCache.INSTANCE);
    }
}
