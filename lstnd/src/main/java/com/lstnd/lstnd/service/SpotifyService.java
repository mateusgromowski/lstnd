package com.lstnd.lstnd.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.lstnd.lstnd.DTO.AuthDTO;


@Service
public class SpotifyService {
	private String clientId;
	private String clientSecret;
	private RestClient restClientAuth;

	public SpotifyService(@Value("${spotify.client.id}") String clientId,
			@Value("${spotify.client.secret}") String clientSecret,
			@Qualifier("restClientAuth") RestClient restClientAuth) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.restClientAuth = restClientAuth;
	}

	public AuthDTO getToken() {
		return restClientAuth.post()
				.body(String.format(
						"grant_type=client_credentials&client_id=%s&client_secret=%s", clientId,
						clientSecret))
				.retrieve()
				.body(AuthDTO.class);
	}

}
