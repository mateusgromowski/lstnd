package com.lstnd.lstnd.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;

import com.lstnd.lstnd.DTO.AuthDTO;

public class SpotifyServer {
    private String clientId;
    private String clientSecret;
    private RestClient restClientAuth;
    private RestClient restClientRequest;

    public SpotifyServer(@Value("CLIENT_ID") String clientId, @Value("CLIENT_SECRET") String clientSecret,
            @Qualifier("restClientAuth") RestClient restClientAuth,
            @Qualifier("restClientRequest") RestClient restClientRequest) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.restClientAuth = restClientAuth;
        this.restClientRequest = restClientRequest;
    }

    private AuthDTO getToken() {

    }

}
