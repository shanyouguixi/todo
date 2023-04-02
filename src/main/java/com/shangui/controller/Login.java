package com.shangui.controller;

import com.shangui.common.exception.MyException;
import com.shangui.common.util.Result;
import com.shangui.model.LoginDto;
import com.shangui.common.dto.RegisterDto;
import com.shangui.model.User;
import com.shangui.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Login {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginDto.getUserName(), loginDto.getPassword());
        Result result = new Result();

        try {
            Map<String, Object> map = new HashMap<>();
            subject.login(token);
            User m = (User) SecurityUtils.getSubject().getPrincipal();
            User info = userService.getInfoById(m.getId());
            map.put("user", info);
            map.put("JSESSIONID", subject.getSession().getId());
            SecurityUtils.getSubject().getSession().setTimeout(86400000);
            result.setData(map);
            result.setMsg("登录成功");
            result.setCode(0);
        } catch (IncorrectCredentialsException e) {
            result.setMsg("密码错误");
            result.setCode(1);
        } catch (LockedAccountException e) {
            result.setMsg("登录失败，该用户已被冻结");
            result.setCode(1);
        } catch (AuthenticationException e) {
            result.setMsg("该用户不存在");
            result.setCode(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterDto registerDto){
        Result result = new Result();
        try {
            userService.register(registerDto.getUserName(),registerDto.getPassword(),registerDto.getNewPassword());
        }catch (MyException e){
            result.setMsg(e.getErrorMsg());
            result.setCode(e.getErrorCode());
        }catch (Exception e){
            result.setMsg("注册失败");
            result.setCode(0);
        }
        return result;
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     *
     * @return
     */
    @RequestMapping(value = "/unauth", method = RequestMethod.GET)
    public Result unauth() {
        Result result = new Result();
        result.setCode(1000000);
        result.setMsg("未登录");
        return result;
    }

    /**
     * 登出  这个方法没用到,用的是shiro默认的logout
     *
     * @return
     */
    @PostMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        Result result = new Result();
        result.setCode(0);
        result.setMsg("安全退出");
        return result;
    }
}
