package com.csy.demo.web.controller;


import com.csy.demo.service.DemoService;
import com.csy.demo.web.dto.DemoDTO;
import com.csy.demo.web.vo.DemoVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    @PostMapping("/test")
    public ResponseEntity<DemoVo> demo(@RequestBody DemoDTO demoDTO){
        return ResponseEntity.ok(demoService.testDemo(demoDTO));
    }

    @PostMapping("/test1")
    public void test1(String id){
        System.out.println("1");
        System.out.println("lueluelue?");
    }
}
