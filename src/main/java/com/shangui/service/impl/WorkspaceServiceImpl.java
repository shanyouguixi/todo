package com.shangui.service.impl;

import com.shangui.model.User;
import com.shangui.model.Workspace;
import com.shangui.mapper.WorkspaceMapper;
import com.shangui.service.WorkspaceService;
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
 * @since 2023-03-09
 */
@Service
public class WorkspaceServiceImpl extends ServiceImpl<WorkspaceMapper, Workspace> implements WorkspaceService {

    @Autowired
    private WorkspaceMapper workspaceMapper;
    @Override
    public List<Workspace> getWorkspaceList() {
        User userNow=(User) SecurityUtils.getSubject().getPrincipal();
        int userId = userNow.getId();
        return workspaceMapper.getWorkspaceList(userId);
    }

    @Override
    public int addWorkspace(Workspace workspace) {
        User userNow=(User) SecurityUtils.getSubject().getPrincipal();
        int userId = userNow.getId();
        workspace.setUserId(userId);
        return workspaceMapper.insert(workspace);
    }

    @Override
    public int updateWorkspace(Workspace workspace) {
        return workspaceMapper.updateById(workspace);
    }
}
