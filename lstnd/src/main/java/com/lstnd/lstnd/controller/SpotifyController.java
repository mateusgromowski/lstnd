package com.lstnd.lstnd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lstnd.lstnd.exception.EmptyNameException;
import com.lstnd.lstnd.model.Album;

import com.lstnd.lstnd.service.SpotifyService;

@RestController
@RequestMapping("/spotify")
public class SpotifyController {
    SpotifyService service;

    public SpotifyController(SpotifyService service) {
        this.service = service;
    }

    @GetMapping("/albums")
    public List<Album> findAlbumByName(@RequestParam String name) throws EmptyNameException {
        return service.findAlbumByName(name);
    }

}
