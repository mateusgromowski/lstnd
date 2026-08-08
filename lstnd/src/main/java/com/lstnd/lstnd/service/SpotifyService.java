package com.lstnd.lstnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.lstnd.lstnd.DTO.AuthDTO;
import com.lstnd.lstnd.DTO.IdRequestDTO;
import com.lstnd.lstnd.DTO.SearchRequestDTO;
import com.lstnd.lstnd.exception.EmptyNameException;
import com.lstnd.lstnd.model.Album;

@Service
public class SpotifyService {
	private String clientId;
	private String clientSecret;
	private RestClient restClientAuth;
	private RestClient restClientRequest;

	public SpotifyService(@Value("${spotify.client.id}") String clientId,
			@Value("${spotify.client.secret}") String clientSecret,
			@Qualifier("restClientAuth") RestClient restClientAuth,
			@Qualifier("restClientRequest") RestClient restClientRequest) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.restClientAuth = restClientAuth;
		this.restClientRequest = restClientRequest;
	}

	public AuthDTO getToken() {
		return restClientAuth.post()
				.body(String.format(
						"grant_type=client_credentials&client_id=%s&client_secret=%s", clientId,
						clientSecret))
				.retrieve()
				.body(AuthDTO.class);
	}

	public List<Album> findAlbumByName(String name) throws EmptyNameException {
		if (name.isEmpty() || name.isBlank()) {
			throw new EmptyNameException("Empty album name.");
		}
		String uri = String.format("search?q=%s&type=album", name);
		String token = getToken().accessToken();
		SearchRequestDTO dto = restClientRequest.get().uri(uri).header("Authorization", "Bearer " + token).retrieve()
				.body(SearchRequestDTO.class);
		return toAlbumList(dto);
	}

	private List<Album> toAlbumList(SearchRequestDTO dto) {
		return dto.albums().items().stream()
				.map(album -> new Album(album.id(), album.images().getFirst().capeUrl(),
						album.artists().getFirst().name(), album.title(), album.releaseDate()))
				.toList();
	}

	private Album toAlbum(IdRequestDTO dto) {
		return Album.builder().id(dto.id()).author(dto.artists().getFirst().name())
				.capeUrl(dto.images().getFirst().capeUrl()).title(dto.name())
				.releaseDate(dto.releaseDate().substring(0, 4)).build();
	}

	public Album findAlbumById(String id) {
		String token = getToken().accessToken();
		IdRequestDTO dto = restClientRequest.get().uri("albums/{id}", id).header("Authorization", "Bearer " + token)
				.retrieve()
				.body(IdRequestDTO.class);
		return toAlbum(dto);
	}

}
