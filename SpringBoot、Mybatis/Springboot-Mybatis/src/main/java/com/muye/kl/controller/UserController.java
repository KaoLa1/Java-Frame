package com.muye.kl.controller;

import com.muye.kl.entity.User;
import com.muye.kl.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author : gwh
 * @date : 2019-11-26 11:08
 **/
@RestController
@RequestMapping("/user")
@Api(value = "用户信息",tags = "用户信息")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    @ApiOperation("查询用户列表")
    public ResponseEntity query(User user, @RequestParam(value = "pageSize",required = true,defaultValue = "10")Integer pageSize,
                                       @RequestParam(value = "pageNum",required = true,defaultValue = "1") Integer pageNum){
        return ResponseEntity.ok(userService.query(user,pageSize,pageNum));
    }

}
