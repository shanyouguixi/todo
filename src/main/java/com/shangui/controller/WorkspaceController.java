package com.shangui.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangui.common.util.Result;
import com.shangui.model.Tag;
import com.shangui.model.Workspace;
import com.shangui.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-09
 */
@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {
    @Autowired
    private WorkspaceService workspaceService;

    @GetMapping("/getWorkspaceList")
    public Result getTagList(@RequestParam("pageSize") Integer pageSize,
                             @RequestParam("pageNum") Integer pageNum){
        PageHelper.startPage(pageNum, pageSize);
        Result result = new Result();
        List<Workspace> tagList = workspaceService.getWorkspaceList();
        PageInfo<Workspace> workspaceInfo = new PageInfo<>(tagList);
        result.setData(workspaceInfo);
        return result;
    }

    @PostMapping("/addWorkspace")
    public Result addWorkspace(@RequestBody Workspace workspace){
        Integer id = workspaceService.addWorkspace(workspace);
        Result result = new Result();
        result.setData(id);
        return result;
    }


    @PostMapping("/updateWorkspace")
    public Result updateWorkspace(@RequestBody Workspace workspace){
        Integer updateCount = workspaceService.updateWorkspace(workspace);
        Result result = new Result();
        result.setData(updateCount);
        return result;
    }

    @DeleteMapping("/delWorkspace")
    public Result delWorkspace(@RequestParam("id")int id){
        workspaceService.deleteById(id);
        Result result = new Result();
        return result;
    }

}

