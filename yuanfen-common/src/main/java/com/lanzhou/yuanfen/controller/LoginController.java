package com.lanzhou.yuanfen.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lanzhou.yuanfen.response.ServerResponseResult;
import com.lanzhou.yuanfen.signin.EmailService;
import com.lanzhou.yuanfen.sys.entity.User;
import com.lanzhou.yuanfen.sys.service.IUserService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


/**
 * @author Administrator
 */
@Controller
public class LoginController {


    /**
     * 跳转
     *
     * @return
     */
    @RequestMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    /**
     * 跳转
     *
     * @return
     */
    @RequestMapping("/emailLogin")
    public String emailLogin() {
        return "emailLogin";
    }

    /**
     * 跳转
     *
     * @return
     */
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 跳转
     *
     * @return
     */
    @RequestMapping({"/index", "/home", "/"})
    public String home() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 思路, 匿名不给进咯, 可以考虑在session 做手脚
        return "home";
    }

    @Resource
    private IUserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;


    /**
     * 注册用户(加个锁)
     *
     * @return
     */
    @RequestMapping("/registerUser")
    @ResponseBody
    public synchronized ServerResponseResult registerUser(UserDto userDto) {
        User nameExist = userService.getOne(new QueryWrapper<User>().eq("username", userDto.username));
        if (nameExist != null) {
            return ServerResponseResult.fail("您输入的用户名已存在 !");
        }
        User emailExist = userService.getOne(new QueryWrapper<User>().eq("email", userDto.email));
        if (emailExist != null) {
            return ServerResponseResult.fail("当前邮箱已经注册用户 !");
        }
        User user = userDto.buildUser();
        user.setPassword(passwordEncoder.encode(userDto.password));
        boolean save = userService.save(user);
        return save ? ServerResponseResult.success() : ServerResponseResult.fail();
    }

    @Resource
    private EmailService emailService;

    /**
     * 注册用户(加个锁)
     *
     * @return
     */
    @RequestMapping("/sendEmail")
    @ResponseBody
    public synchronized ServerResponseResult sendEmail(@RequestParam("username") String username,
                                                       @RequestParam("email") String email) {
        User nameExist = userService.getOne(new QueryWrapper<User>().eq("username", username).eq("email", email));
        if (nameExist == null) {
            return ServerResponseResult.fail("您输入的用户信息错误, 请重新输入 !");
        }
        String emailCode = generateEmailCode();
        String text = "尊敬的" + username + " : 您此次登入的验证码为: " + emailCode;
        emailService.sendSimpleMail("【猿粪】关于猿粪网的重置密码的验证码通知", text, nameExist.getEmail());
        userService.update(new UpdateWrapper<User>().eq("user_key", nameExist.getUserKey()).set("eml_code", emailCode));
        return ServerResponseResult.success();
    }

    /**
     * 生成Email登入验证码(6位数)
     *
     * @return
     */
    private String generateEmailCode() {
        int i = (int) ((Math.random() * 9 + 1) * 100000);
        return "" + i;
    }


    @Data
    @EqualsAndHashCode(callSuper = false)
    static class UserDto {
        private String username;
        private String password;
        private String email;

        private User buildUser() {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            return user;
        }
    }


}
