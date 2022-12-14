package com.apidemo.controller;


import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sample")
public class SampleController {

    @ApiOperation("Sample GET doA")
    @GetMapping("/doA")
    public List<String> doA() {
        return Arrays.asList();
    }

}


