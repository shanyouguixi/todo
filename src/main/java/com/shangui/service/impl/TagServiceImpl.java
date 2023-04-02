package com.shangui.service.impl;

import com.shangui.model.Tag;
import com.shangui.mapper.TagMapper;
import com.shangui.model.User;
import com.shangui.service.TagService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 标签 服务实现类
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-05
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;
    @Override
    public List<Tag> getTagList() {
        User userNow=(User) SecurityUtils.getSubject().getPrincipal();
        int userId = userNow.getId();
        return tagMapper.getTagList(userId);
    }

    @Override
    public int updateTag(Tag tag) {
        User userNow=(User) SecurityUtils.getSubject().getPrincipal();
        tag.setUserId(userNow.getId());
        tag.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        return tagMapper.updateById(tag);
    }

    @Override
    public int addTag(Tag tag) {
        User userNow=(User) SecurityUtils.getSubject().getPrincipal();
        tag.setUserId(userNow.getId());
        tag.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        tag.setCreateTime((int) (System.currentTimeMillis() / 1000));
        return tagMapper.insert(tag);
    }
}
