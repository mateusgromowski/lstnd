package com.lstnd.lstnd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lstnd.lstnd.model.Album;
import com.lstnd.lstnd.service.AlbumService;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    private AlbumService service;

    public AlbumController(AlbumService service) {
        this.service = service;
    }

    @GetMapping("/{spotifyId}")
    private Album findAlbumById(@PathVariable String spotifyId) throws IllegalArgumentException {
        return service.findAlbumById(spotifyId);
    }

    @GetMapping
    private List<Album> findAlbumsByName(@RequestParam String name) throws IllegalArgumentException {
        return service.findAlbumsByName(name);
    }
}
