package com.shangui.service.impl;

import com.shangui.common.dto.MemoDto;
import com.shangui.model.Memo;
import com.shangui.mapper.MemoMapper;
import com.shangui.model.User;
import com.shangui.service.MemoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-05
 */
@Service
public class MemoServiceImpl extends ServiceImpl<MemoMapper, Memo> implements MemoService {

    @Autowired
    private MemoMapper memoMapper;

    @Override
    public List<Memo> getMemoList(MemoDto memoDto) {
        User userNow = (User) SecurityUtils.getSubject().getPrincipal();
        memoDto.setUserId(userNow.getId());
        return memoMapper.getMemoList(memoDto);
    }

    @Override
    public Integer addMemo(MemoDto memoDto) {
        Memo memo = new Memo();
        memo.setCreateTime((int) (System.currentTimeMillis() / 1000));
        memo.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        User userNow = (User) SecurityUtils.getSubject().getPrincipal();
        memo.setUserId(userNow.getId());
        memo.setTagId(memoDto.getTagId());
        memo.setTitle(memoDto.getTitle());
        memo.setWorkspaceId(memoDto.getWorkspaceId());
        memo.setContent(memoDto.getContent());
        memoMapper.insert(memo);
        return memo.getId();
    }

    @Override
    public Integer updateMemo(MemoDto memoDto) {
        Memo memo = new Memo();
        memo.setId(memoDto.getId());
        if (memoDto.getContent() != null) memo.setContent(memoDto.getContent());
        if (memoDto.getTitle() != null) memo.setTitle(memoDto.getTitle());
        if (memoDto.getTagId() != null) memo.setTagId(memoDto.getTagId());
        memo.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        return memoMapper.updateById(memo);
    }
}
