package com.shangui.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangui.common.dto.EbookDto;
import com.shangui.common.exception.MyException;
import com.shangui.common.util.Result;
import com.shangui.model.Ebook;
import com.shangui.model.EbookTag;
import com.shangui.model.Memo;
import com.shangui.service.EbookService;
import com.shangui.service.EbookTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-04-04
 */
@RestController
@RequestMapping("/api/ebook")
public class EbookController {

    @Autowired
    private EbookService ebookService;

    @Autowired
    private EbookTagService ebookTagService;

    @GetMapping("/getEbook")
    public Result getEbook(@RequestParam("pageSize") Integer pageSize,
                           @RequestParam("pageNum") Integer pageNum,
                           @RequestParam(name = "tagId", required = false) Integer tagId,
                           @RequestParam(name = "name", required = false) String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<Ebook> ebookList = ebookService.getEbookList(tagId, name);
        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        Result result = new Result();
        result.setData(pageInfo);
        return result;
    }
    //F:\cshap\WPF学习资料\深入浅出WPF.pdf

    @PostMapping("/updateEbook")
    public Result updateEbook(@RequestBody EbookDto ebookDto) {
        ebookService.updateEbook(ebookDto);
        Result result = new Result();
        return result;
    }

    @PostMapping("/saveEbook")
    public Result saveEbook(@RequestBody EbookDto ebookDto) {
        ebookService.saveEbook(ebookDto);
        Result result = new Result();
        return result;
    }

    @DeleteMapping("/delEbook")
    public Result delEbook(@RequestParam("id") int id) {
        Result result = new Result();
        try {
            ebookService.delEbook(id);
        } catch (MyException e) {
            result.setCode(1);
            result.setMsg(e.getErrorMsg());
        } catch (Exception e) {
            result.setCode(1);
            result.setMsg("删除失败");
        }
        return result;
    }


    @GetMapping("/getEbookTag")
    public Result getEbookTag() {
        PageHelper.startPage(1, 20);
        List<EbookTag> ebookList = ebookTagService.getEbookList();
        PageInfo<EbookTag> pageInfo = new PageInfo<>(ebookList);
        Result result = new Result();
        result.setData(pageInfo);
        return result;
    }

    @PostMapping("/updateEbookTag")
    public Result updateEbookTag(@RequestBody EbookTag ebookTag) {
        ebookTagService.updateEbook(ebookTag);
        Result result = new Result();
        return result;
    }

    @PostMapping("/saveEbookTag")
    public Result saveEbookTag(@RequestBody EbookTag ebookTag) {
        ebookTagService.saveEbook(ebookTag);
        Result result = new Result();
        return result;
    }

    @DeleteMapping("/delEbookTag")
    public Result delEbookTag(@RequestParam("id") int id) {
        Result result = new Result();
        ebookTagService.delEbook(id);
        return result;
    }
}

