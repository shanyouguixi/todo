package com.shangui.service;

import com.shangui.common.dto.TodoDto;
import com.shangui.model.Todo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-22
 */
public interface TodoService extends IService<Todo> {
    List<Todo> getTodoList(TodoDto todoDto);
    int updateTodo(Todo todo);
    int addTodo(Todo todo);
    List<Todo> getTodoListByRemarkDate();

}
