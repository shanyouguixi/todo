package com.shangui.service;

import com.shangui.model.EbookTag;
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
public interface EbookTagService extends IService<EbookTag> {
    List<EbookTag> getEbookList();
    int saveEbook(EbookTag ebookTag);
    int updateEbook(EbookTag ebookTag);
    int delEbook(int id);
}
