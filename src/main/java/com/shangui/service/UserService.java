package com.shangui.service;

import com.shangui.common.dto.UserDto;
import com.shangui.common.exception.MyException;
import com.shangui.model.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-23
 */
public interface UserService extends IService<User> {
    User login(String userName);
    User getInfoById(int id);
    void register(String userName,String password,String newPassword) throws MyException;
    int updateUserInfo(UserDto userDto);
}
