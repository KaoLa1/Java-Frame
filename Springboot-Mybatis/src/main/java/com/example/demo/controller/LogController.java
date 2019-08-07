package com.example.demo.controller;

import com.example.demo.entity.Log;
import com.example.demo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Repository
@RequestMapping("/sysc_log")
public class LogController {

    @Autowired
    LogService logService;

    @ResponseBody
    @RequestMapping("/getLogInfo")
    public  List<Map<String,Object>> getId(){
        return logService.getId();
    }

}
