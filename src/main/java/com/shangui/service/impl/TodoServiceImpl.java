package com.shangui.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.shangui.common.dto.TodoDto;
import com.shangui.model.Todo;
import com.shangui.mapper.TodoMapper;
import com.shangui.model.User;
import com.shangui.service.TodoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-22
 */
@Service
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo> implements TodoService {

    @Autowired
    private TodoMapper todoMapper;

    @Override
    public List<Todo> getTodoList(TodoDto todoDto) {
        User userNow=(User) SecurityUtils.getSubject().getPrincipal();
        int userId = userNow.getId();
        todoDto.setUserId(userId);
        return todoMapper.getTodoList(todoDto);
    }

    @Override
    public int updateTodo(Todo todo) {
        User userNow=(User) SecurityUtils.getSubject().getPrincipal();
        todo.setUserId(userNow.getId());
        todo.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        return todoMapper.updateById(todo);
    }

    @Override
    public int addTodo(Todo todo) {
        User userNow=(User) SecurityUtils.getSubject().getPrincipal();
        todo.setUserId(userNow.getId());
        todo.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        todo.setCreateTime((int) (System.currentTimeMillis() / 1000));
        return todoMapper.insert(todo);
    }

    @Override
    public List<Todo> getTodoListByRemarkDate() {
        Wrapper<Todo> wrapper = new EntityWrapper<>();
        User userNow=(User) SecurityUtils.getSubject().getPrincipal();
        wrapper.eq("user_id",userNow.getId());
        wrapper.isNotNull("remark_date");
        wrapper.ge("remark_date",new Date());
        wrapper.orderBy("remark_date",true);
        return todoMapper.selectList(wrapper);
    }
}
