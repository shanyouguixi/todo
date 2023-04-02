package com.shangui.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangui.common.dto.MemoDto;
import com.shangui.common.dto.TodoDto;
import com.shangui.common.util.Result;
import com.shangui.model.Tag;
import com.shangui.model.Todo;
import com.shangui.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-22
 */
@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/getTodoList")
    public Result getTodoList(@RequestParam("pageSize") Integer pageSize,
                             @RequestParam("pageNum") Integer pageNum,
                              @RequestParam(name = "tagId", required = false) Integer tagId,
                              @RequestParam(name = "searchWord", required = false) String searchWord,
                              @RequestParam(name = "workspaceId", required = false) Integer workspaceId,
                              @RequestParam(name = "startDate", required = false) Integer startDate,
                              @RequestParam(name = "endDate", required = false) Integer endDate){
        PageHelper.startPage(pageNum, pageSize);
        TodoDto todoDto = new TodoDto();
        todoDto.setTagId(tagId);
        todoDto.setSearchWord(searchWord);
        todoDto.setWorkspaceId(workspaceId);
        todoDto.setStartDate(startDate);
        todoDto.setEndDate(endDate);
        Result result = new Result();
        List<Todo> todoList = todoService.getTodoList(todoDto);
        PageInfo<Todo> pageInfo = new PageInfo<>(todoList);
        result.setData(pageInfo);
        return result;
    }

    @GetMapping("/getImportantTodoList")
    public Result getImportantTodo(){
        Result result = new Result();
        List<Todo> todoList = todoService.getTodoListByRemarkDate();
        PageHelper.startPage(1, 10);
        PageInfo<Todo> pageInfo = new PageInfo<>(todoList);
        result.setData(pageInfo);
        return result;
    }

    @PostMapping("/addTodo")
    public Result addTodo(@RequestBody Todo todo){
        Integer id = todoService.addTodo(todo);
        Result result = new Result();
        result.setData(id);
        return result;
    }


    @PostMapping("/updateTodo")
    public Result updateTodo(@RequestBody Todo todo){
        Integer updateCount = todoService.updateTodo(todo);
        Result result = new Result();
        result.setData(updateCount);
        return result;
    }

    @DeleteMapping("/delTodo")
    public Result delTodo(@RequestParam("id")int id){
        todoService.deleteById(id);
        Result result = new Result();
        return result;
    }

}

