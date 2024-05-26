package com.es.esspringstu.canal.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.es.esspringstu.canal.empty.User;
import com.es.esspringstu.canal.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
}
