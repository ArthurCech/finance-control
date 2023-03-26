package com.github.arthurcech.financecontrol.resource;

import com.github.arthurcech.financecontrol.config.property.ApiProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/tokens")
public class TokenResource {

    private final ApiProperty apiProperty;

    public TokenResource(ApiProperty apiProperty) {
        this.apiProperty = apiProperty;
    }

    @DeleteMapping("/revoke-refresh-token")
    public void revoke(HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(apiProperty.getSeguranca().isEnableHttps());
        cookie.setPath(req.getContextPath() + "/oauth/token");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        resp.setStatus(HttpStatus.NO_CONTENT.value());
    }

}
