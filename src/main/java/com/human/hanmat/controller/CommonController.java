package com.human.hanmat.controller;

import com.human.hanmat.convert.GeoConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {
    @Autowired
    private GeoConvert geoConvert;

    @GetMapping("/")
    public String index() {
        return "index-/";
    }

    @GetMapping("/api")
    public String api() {
        return "api-/";
    }

    @GetMapping("/convert")
    public String convert() {
        geoConvert.convert();
        return "convert-/";
    }
}
