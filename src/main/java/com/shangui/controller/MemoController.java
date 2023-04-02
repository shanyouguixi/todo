package com.shangui.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangui.common.dto.MemoDto;
import com.shangui.common.util.Result;
import com.shangui.model.Memo;
import com.shangui.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-05
 */
@RestController
@RequestMapping("/api/memo")
public class MemoController {

    @Autowired
    private MemoService memoService;

    @GetMapping("/getMemoList")
    public Result getMemoList(@RequestParam("pageSize") Integer pageSize,
                              @RequestParam("pageNum") Integer pageNum,
                              @RequestParam(name = "tagId", required = false) Integer tagId,
                              @RequestParam(name = "searchWord", required = false) String searchWord,
                              @RequestParam(name = "workspaceId", required = false) Integer workspaceId,
                              @RequestParam(name = "startDate", required = false) Integer startDate,
                              @RequestParam(name = "endDate", required = false) Integer endDate) {
        PageHelper.startPage(pageNum, pageSize);
        MemoDto memoDto = new MemoDto();
        memoDto.setTagId(tagId);
        memoDto.setSearchWord(searchWord);
        memoDto.setWorkspaceId(workspaceId);
        memoDto.setStartDate(startDate);
        memoDto.setEndDate(endDate);
        List<Memo> memos = memoService.getMemoList(memoDto);
        PageInfo<Memo> pageInfo = new PageInfo<>(memos);
        Result result = new Result();
        result.setData(pageInfo);
        return result;
    }

    @PostMapping("/addMemo")
    public Result addMemo(@RequestBody MemoDto memoDto){
        Integer id = memoService.addMemo(memoDto);
        Result result = new Result();
        result.setData(id);
        return result;
    }


    @PostMapping("/updateMemo")
    public Result updateMemo(@RequestBody MemoDto memoDto){
        Integer updateCount = memoService.updateMemo(memoDto);  
        Result result = new Result();
        result.setData(updateCount);
        return result;
    }
    @DeleteMapping("/delMemo")
    public Result delMemo(@RequestParam("id")int id){
        memoService.deleteById(id);
        Result result = new Result();
        return result;
    }


}

