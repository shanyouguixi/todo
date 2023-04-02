package com.shangui.service;

import com.shangui.common.dto.MemoDto;
import com.shangui.model.Memo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-05
 */
public interface MemoService extends IService<Memo> {
    List<Memo> getMemoList(MemoDto memoDto);

    Integer addMemo(MemoDto memoDto);
    Integer updateMemo(MemoDto memoDto);
}
