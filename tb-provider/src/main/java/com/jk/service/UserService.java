package com.jk.service;

import com.jk.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface UserService {

    @RequestMapping("/findUserList")
    List<UserEntity> findUserList();

}
