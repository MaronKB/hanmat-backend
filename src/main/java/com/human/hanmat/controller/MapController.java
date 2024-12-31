package com.human.hanmat.controller;

import com.human.hanmat.dto.PosDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/map")
public class MapController {
    @PostMapping("/search")
    public void search(@RequestBody PosDTO pos) {
    }
}
