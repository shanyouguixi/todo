package com.shangui.service;

import com.shangui.model.Tag;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 标签 服务类
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-05
 */
public interface TagService extends IService<Tag> {
    List<Tag> getTagList();
    int updateTag(Tag tag);

    int addTag(Tag tag);
}
