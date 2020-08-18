package com.jk.controller;

import com.jk.entity.HobbyEntity;
import com.jk.entity.UserEntity;
import com.jk.service.UserServiceFeign;
import com.jk.utils.Constant;
import com.jk.utils.RedisUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceFeign userService;

    @Resource
    private RedisUtil redisUtil;



    @RequestMapping("/selectUserList")
    @ResponseBody
    public List<UserEntity> selectUserList() {

        List<UserEntity> userList = (List<UserEntity>) redisUtil.get(Constant.SELECT_USER_LIST);

        // 1. 有值   2. 没有值
        if(userList == null || userList.size() <= 0 || userList.isEmpty()) {
            // 从数据库查询，存redis
            userList = userService.findUserList();
            redisUtil.set(Constant.SELECT_USER_LIST, userList, 30);
        }

       return userList;

    }
    @RequestMapping("/hello")
    @ResponseBody
    private String hello(String name){
        return userService.hello(name);
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    private Boolean deleteUser(Integer userId){
        try {
            userService.deleteUser(userId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    @RequestMapping("findUserTypeList")
    @ResponseBody
    public List<HobbyEntity> findUserTypeList(){

        return userService.findUserTypeList();
    }
    @RequestMapping("saveOrUpdateUser")
    @ResponseBody
    public Boolean saveOrUpdateUser(UserEntity userEntity){
        try {
            userService.saveUser(userEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    @RequestMapping("/saveOrder")
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    @ResponseBody
    public Object saveOrder(Integer userId,Integer productId) {
        return userService.saveOrder(userId,productId);

    }

    private Object saveOrderFail(Integer userId, Integer productId){

        System.out.println(" controller 保存订单降级方法 ");

        Map<String, Object> map = new HashMap<>();
        map.put("code",-1);
        map.put("message","抢购排队人数过多，请您稍后重试。");
        return map;
    }

}
