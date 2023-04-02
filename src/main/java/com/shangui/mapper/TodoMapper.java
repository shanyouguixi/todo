package com.shangui.mapper;

import com.shangui.common.dto.TodoDto;
import com.shangui.model.Todo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-22
 */
public interface TodoMapper extends BaseMapper<Todo> {
    List<Todo> getTodoList(TodoDto todoDto);
}
