package com.jk.service;

import com.jk.entity.HobbyEntity;
import com.jk.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/error")
@Component
public class UserServiceFallback implements UserServiceFeign {

    @Override
    public List<UserEntity> findUserList() {

        System.out.println("熔断处理，方法查询用户集合");

        return null;
    }

    @Override
    public String hello(String name) {
        System.out.println(" 进入了 hello方法 熔断器 ");

        return "请求失败，请检查电脑或手机网络。";
    }

    @Override
    public void deleteUser(Integer userId) {
        System.out.println("熔断处理，删除");
    }

    @Override
    public List<HobbyEntity> findUserTypeList() {
        System.out.println("熔断处理，类型查询");
        return null;
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        System.out.println("熔断处理，新增数据");
    }

    @Override
    public Object saveOrder(Integer userId, Integer productId) {
        System.out.println(" 进入订单，熔断器 ");
        return null;
    }
}
