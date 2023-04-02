package com.shangui.controller;


import com.shangui.common.dto.UserDto;
import com.shangui.common.util.Result;
import com.shangui.model.User;
import com.shangui.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-23
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/updateUser")
    public Result updateUserInfo(@RequestBody UserDto userDto){
        User userNow=(User) SecurityUtils.getSubject().getPrincipal();
        userDto.setId(userNow.getId());
        userService.updateUserInfo(userDto);
        Result result = new Result();
        User user = userService.getInfoById(userNow.getId());
        result.setData(user);
        return  result;
    }

}

