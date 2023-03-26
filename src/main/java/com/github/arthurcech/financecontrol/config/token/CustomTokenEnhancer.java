package com.github.arthurcech.financecontrol.config.token;

import com.github.arthurcech.financecontrol.security.UsuarioSistema;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(
            OAuth2AccessToken token,
            OAuth2Authentication authentication
    ) {
        UsuarioSistema usuarioSistema = (UsuarioSistema) authentication.getPrincipal();

        Map<String, Object> newInfos = new HashMap<>();
        newInfos.put("nome", usuarioSistema.getUsuario().getNome());

        ((DefaultOAuth2AccessToken) token).setAdditionalInformation(newInfos);
        return token;
    }

}
