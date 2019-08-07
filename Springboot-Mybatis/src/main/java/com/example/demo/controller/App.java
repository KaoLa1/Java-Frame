package com.example.demo.controller;

import com.example.demo.entity.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

public class App {

    LogController logController = new LogController();
    SourceController sourceController = new SourceController();


    @ResponseBody
    @RequestMapping("/dbUpdate")
    public String getUpdate(){
        List<Map<String,Object>> listParam = logController.getId();
        for(Map map:listParam){
            String oid = map.get("oid").toString();
        }

        List<Map<String, Object>> source = sourceController.getSource();

        return null;
    }


}
