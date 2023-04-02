package com.shangui.mapper;

import com.shangui.model.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-23
 */
public interface UserMapper extends BaseMapper<User> {
    User login(String userName);

    User getInfoById(int id);
}
