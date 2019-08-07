package com.example.demo.controller;

import com.example.demo.entity.Source;
import com.example.demo.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Repository
@RequestMapping("sysc_source")
public class SourceController {

    @Autowired
    SourceService sourceService;

    @ResponseBody
    @RequestMapping("/getSourceInfo")
    public List<Map<String, Object>> getSource() {
        return sourceService.getSource();
    }

    @ResponseBody
    @RequestMapping("/insertSource")
    public Source insertSource(Source source) {
        return sourceService.insertSource(source);
    }
}
