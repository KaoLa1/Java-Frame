package com.muye.kl.demo.controller;

import com.muye.kl.demo.entity.po.Person;
import com.muye.kl.demo.utils.excel.ReadExcelUtil;
import com.muye.kl.demo.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author : gwh
 * @date : 2019-11-26 11:08
 **/
@RestController
@RequestMapping("/user")
@Api(value = "用户信息", tags = "用户信息")
public class PersonController {


    @Autowired
    PersonService userService;

    @GetMapping("/list")
    @ApiOperation("查询用户列表")
    public ResponseEntity query(Person person, @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        return ResponseEntity.ok(userService.query(person, pageSize, pageNum));
    }

    @GetMapping("/upload")
    @ApiOperation("上传文件")
    public ResponseEntity<Integer> upload(@RequestParam("path") String path) {
        List<Person> user = new ArrayList<>();
        try {
            List<List<String>> list = ReadExcelUtil.readExcelInfo(path);
            for (int i = 1; i < list.size(); i++) {
                Person user1 = new Person();
                user1.setId(list.get(i).get(0));
                user1.setName(list.get(i).get(1));
                user1.setAge(list.get(i).get(2));
                user1.setSex(list.get(i).get(3));
                user1.setAddr(list.get(i).get(4));
                user.add(user1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(userService.insertBatch(user));
    }

}
