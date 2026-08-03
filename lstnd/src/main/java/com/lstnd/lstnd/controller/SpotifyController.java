package com.lstnd.lstnd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lstnd.lstnd.DTO.AuthDTO;
import com.lstnd.lstnd.service.SpotifyService;

@RestController
@RequestMapping("/spotify")
public class SpotifyController {
    SpotifyService service;

    public SpotifyController(SpotifyService service) {
        this.service = service;
    }
}
