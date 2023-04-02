package com.shangui.service.impl;

import com.shangui.common.dto.UserDto;
import com.shangui.common.exception.MyException;
import com.shangui.common.util.StringUtil;
import com.shangui.model.User;
import com.shangui.mapper.UserMapper;
import com.shangui.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String userName) {
        return userMapper.login(userName);
    }

    @Override
    public User getInfoById(int id) {
        User user = userMapper.getInfoById(id);
        return user;
    }

    @Override
    public void register(String userName, String password,String newPassword) throws MyException {
        if (!password.equals(newPassword)){
            throw new MyException("密码不一致",1);
        }
        User oldUser = this.login(userName);
        if (oldUser!=null){
            throw new MyException("用户名已被注册",1);
        }
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex(); //生成盐值
        String saltPassword = new Md5Hash(password,salt,5).toString(); //生成的密文，使用md5算法对明文与盐值的组合进行了三次加密

        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setSalt(salt);
        newUser.setPassword(saltPassword);
        newUser.setLoginFlag(1);
        newUser.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        newUser.setCreateTime((int) (System.currentTimeMillis() / 1000));
        userMapper.insert(newUser);
    }

    @Override
    public int updateUserInfo(UserDto userDto) {
        User newUser = new User();
        newUser.setId(userDto.getId());
        if (userDto.getUserName() != null)newUser.setUserName(userDto.getUserName());
        if (userDto.getEmail() != null)newUser.setEmail(userDto.getEmail());
        if (userDto.getAvatar() != null)newUser.setAvatar(userDto.getAvatar());
        newUser.setCreateTime((int) (System.currentTimeMillis() / 1000));
        return userMapper.updateById(newUser);
    }
}
