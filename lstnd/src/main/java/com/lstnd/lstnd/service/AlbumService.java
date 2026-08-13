package com.lstnd.lstnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.lstnd.lstnd.DTO.IdRequestDTO;
import com.lstnd.lstnd.DTO.SearchRequestDTO;
import com.lstnd.lstnd.model.Album;
import com.lstnd.lstnd.repository.ReviewRepository;

@Service
public class AlbumService {
    private RestClient request;
    private SpotifyService spotifyService;
    private ReviewRepository repository;

    public AlbumService(SpotifyService spotifyService,
            @Qualifier("restClientRequest") RestClient restClientRequest, ReviewRepository repository) {
        this.repository = repository;
        this.request = restClientRequest;
        this.spotifyService = spotifyService;
    }

    public Album findAlbumById(String id) throws IllegalArgumentException {
        validateId(id);
        String token = spotifyService.getToken().accessToken();
        IdRequestDTO dto = request.get().uri("albums/{id}", id).header("Authorization", "Bearer " + token)
                .retrieve()
                .body(IdRequestDTO.class);
        return toAlbum(dto);
    }

    private void validateId(String id) throws IllegalArgumentException {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return;
    }

    private Album toAlbum(IdRequestDTO dto) {
        return Album.builder().id(dto.id()).author(dto.artists().getFirst().name())
                .capeUrl(dto.images().getFirst().capeUrl()).title(dto.name())
                .releaseDate(dto.releaseDate().substring(0, 4)).score(repository.getAverageScoreBySpotifyId(dto.id()))
                .build();
    }

    public List<Album> findAlbumsByName(String name) throws IllegalArgumentException {
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Empty album name.");
        }
        String uri = String.format("search?q=%s&type=album", name);
        String token = spotifyService.getToken().accessToken();
        SearchRequestDTO dto = request.get().uri(uri).header("Authorization", "Bearer " + token).retrieve()
                .body(SearchRequestDTO.class);
        return toAlbumList(dto);
    }

    private List<Album> toAlbumList(SearchRequestDTO dto) {
        return dto.albums().items().stream()
                .map(album -> Album.builder()
                        .id(album.id())
                        .author(album.artists().getFirst().name())
                        .capeUrl(album.images().getFirst().capeUrl())
                        .title(album.title())
                        .releaseDate(album.releaseDate().substring(0, 4))
                        .score(repository.getAverageScoreBySpotifyId(album.id()))
                        .build())
                .toList();

    }
}
