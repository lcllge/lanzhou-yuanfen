package com.lanzhou.yuanfen.sys.service.impl;

import com.lanzhou.yuanfen.sys.entity.User;
import com.lanzhou.yuanfen.sys.mapper.UserMapper;
import com.lanzhou.yuanfen.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lcllge
 * @since 2019-12-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
