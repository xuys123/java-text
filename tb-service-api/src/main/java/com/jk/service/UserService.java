package com.jk.service;

import com.jk.entity.HobbyEntity;
import com.jk.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface UserService {
    @RequestMapping("/findUserList")
    List<UserEntity> findUserList();

    @RequestMapping("/hello")
    String hello(String name);

    @RequestMapping("/deleteUser")
    void deleteUser(@RequestParam Integer userId);

    @RequestMapping("/findUserTypeList")
    List<HobbyEntity> findUserTypeList();

    @RequestMapping("/saveOrUpdateUser")
    void saveUser(@RequestBody UserEntity userEntity);

    @RequestMapping("/saveOrder")
    Object saveOrder(@RequestParam Integer userId, @RequestParam Integer productId);
}
