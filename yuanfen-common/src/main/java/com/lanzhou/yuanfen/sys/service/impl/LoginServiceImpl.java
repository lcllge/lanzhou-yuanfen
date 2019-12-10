package com.lanzhou.yuanfen.sys.service.impl;

import com.lanzhou.yuanfen.sys.entity.Login;
import com.lanzhou.yuanfen.sys.mapper.LoginMapper;
import com.lanzhou.yuanfen.sys.service.ILoginService;
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
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements ILoginService {

}
