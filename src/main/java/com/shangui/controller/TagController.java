package com.shangui.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangui.common.util.Result;
import com.shangui.model.Memo;
import com.shangui.model.Tag;
import com.shangui.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 标签 前端控制器
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-05
 */
@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/getTagList")
    public Result getTagList(@RequestParam("pageSize") Integer pageSize,
                             @RequestParam("pageNum") Integer pageNum){
        PageHelper.startPage(pageNum, pageSize);
        Result result = new Result();
        List<Tag> tagList = tagService.getTagList();
        PageInfo<Tag> pageInfo = new PageInfo<>(tagList);
        result.setData(pageInfo);
        return result;
    }

    @PostMapping("/addTag")
    public Result addTag(@RequestBody Tag tag){
        Integer id = tagService.addTag(tag);
        Result result = new Result();
        result.setData(id);
        return result;
    }


    @PostMapping("/updateTag")
    public Result updateTag(@RequestBody Tag tag){
        Integer updateCount = tagService.updateTag(tag);
        Result result = new Result();
        result.setData(updateCount);
        return result;
    }

    @DeleteMapping("/delTag")
    public Result delTag(@RequestParam("id")int id){
        tagService.deleteById(id);
        Result result = new Result();
        return result;
    }

}

