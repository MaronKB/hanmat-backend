package com.human.hanmat.controller;

import com.human.hanmat.dto.MapDTO;
import com.human.hanmat.dto.PosDTO;
import com.human.hanmat.entity.Response;
import com.human.hanmat.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/map")
public class MapController {
    @Autowired
    private MapService mapService;

    @PostMapping("/search")
    public void search(@RequestBody PosDTO pos) {
    }

    @GetMapping("/{name}")
    public Response<?> get(@PathVariable String name) throws Exception {
        return new Response<>(mapService.get(name), "success", true, null);
    }
}
