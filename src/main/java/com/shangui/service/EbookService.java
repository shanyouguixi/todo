package com.shangui.service;

import com.shangui.common.dto.EbookDto;
import com.shangui.common.exception.MyException;
import com.shangui.model.Ebook;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-04-04
 */
public interface EbookService extends IService<Ebook> {
    List<Ebook> getEbookList(Integer tagId, String name);
    int saveEbook(EbookDto dto);
    int updateEbook(EbookDto dto);
    int delEbook(int id) throws MyException;
}
