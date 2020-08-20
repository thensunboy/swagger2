package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "UserController",description = "处理用户信息")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    public static Map<String,User> userMap = Collections.synchronizedMap(new HashMap<String,User>());

    @ApiOperation(value="获取用户列表信息", notes="")
    @RequestMapping(value={"/getUserList"}, method=RequestMethod.GET)
    public List<User> getUserList(){
        List<User> users = new ArrayList<>(userMap.values());
        return users;
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="postUser", method=RequestMethod.POST)
    public String postUser(@RequestBody User user) {
        userMap.put(user.getName(), user);
        return "success";
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return userMap.get(id);
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable String id, @RequestBody User user) {
        User u = userMap.get(id);
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        userMap.put(id, u);
        return "success";
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        userMap.remove(id);
        return "success";
    }
}
