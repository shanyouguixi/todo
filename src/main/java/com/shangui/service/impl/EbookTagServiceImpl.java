package com.shangui.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.shangui.model.EbookTag;
import com.shangui.mapper.EbookTagMapper;
import com.shangui.model.User;
import com.shangui.service.EbookTagService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-04-04
 */
@Service
public class EbookTagServiceImpl extends ServiceImpl<EbookTagMapper, EbookTag> implements EbookTagService {

    @Autowired
    private EbookTagMapper ebookTagMapper;



    @Override
    public List<EbookTag> getEbookList() {
        User userNow = (User) SecurityUtils.getSubject().getPrincipal();
        int userId = userNow.getId();
        Wrapper<EbookTag> wrapper =new EntityWrapper<>();
        wrapper.eq("user_id",userId);
        return ebookTagMapper.selectList(wrapper);
    }

    @Override
    public int saveEbook(EbookTag ebookTag) {
        User userNow = (User) SecurityUtils.getSubject().getPrincipal();
        int userId = userNow.getId();
        ebookTag.setUserId(userId);
        return ebookTagMapper.insert(ebookTag);
    }

    @Override
    public int updateEbook(EbookTag ebookTag) {
        User userNow = (User) SecurityUtils.getSubject().getPrincipal();
        int userId = userNow.getId();
        ebookTag.setUserId(userId);
        return ebookTagMapper.updateById(ebookTag);
    }

    @Override
    public int delEbook(int id) {
        return ebookTagMapper.deleteById(id);
    }
}
