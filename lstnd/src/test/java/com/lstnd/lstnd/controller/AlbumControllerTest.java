package com.lstnd.lstnd.controller;

import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.lstnd.lstnd.service.AlbumService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.Test;

@WebMvcTest(AlbumController.class)
public class AlbumControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AlbumService service;

    @Test
    public void invalidIdTest() throws Exception {
        when(service.findAlbumById("abc")).thenThrow(new IllegalArgumentException("ID inválido."));
        requestMock();
    }

    private void requestMock() throws Exception {
        mockMvc.perform(get("/albums/abc")).andExpect(content().string("ID inválido."));
    }

}
