package com.example.springbootjustauth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * ClassName: GitHubOAuthConfig
 * Package: com.example.springbootjustauth.config
 * Description:
 *
 * @Author ms
 * @Create 2024/11/23 11:02
 * @Version 1.0
 */

@Data
@ConfigurationProperties(prefix = "oauth.github")
@Configuration
public class GitHubOAuthProp {

    private String clientId;

    private String clientSecret;

    private String redirectUri;


}
