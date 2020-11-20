package com.example.shop.controller;

import com.example.shop.dto.Result;
import com.example.shop.dto.User;
import com.example.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Result getAll() {
        return new Result(userService.getAll());
    }

    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.POST)
    public Result getUserById(@PathVariable Long id) {
        return new Result(userService.getUserById(id));
    }

    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    public Result pageList(@RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "3") Integer pageSize) {
        return new Result(userService.pageList(pageNum, pageSize));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<User> add(@RequestBody User user) {
        int n = userService.add(user);
        if(0 < n) {
            LOGGER.debug("add user success ====>");
            return new Result(n,"add success", new User());
        } else {
            LOGGER.debug("add user failed ====>");
            return new Result(n,"add failed", new User());
        }

    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Result<User> update(@PathVariable Long id, @RequestBody User user) {
        int n = userService.update(id, user);
        if(0 < n) {
            LOGGER.debug("update user success ====>");
            return new Result(n,"update success", new User());
        } else {
            LOGGER.debug("update user failed ====>");
            return new Result(n,"update failed", new User());
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Result<User> delete(@PathVariable Long id) {
        int n = userService.delete(id);
        if(0 < n) {
            LOGGER.debug("delete user success ====> id=");
            return new Result(n,"delete success", new User());
        } else {
            LOGGER.debug("delete user failed ====> id=");
            return new Result(n,"delete failed", new User());
        }
    }
    @RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
    public Result<User> deleteBatch(@RequestParam List<String> ids) {
        List<Long> ids1 = new ArrayList<>();
        for (int i=0; i<ids.size(); i++) {
            ids1.add(Long.parseLong(ids.get(i)));
        }
        int n = userService.deleteBatch(ids1);
        if(0 < n) {
            LOGGER.debug("deleteBath user success ====> ids=");
            return new Result(n,"deleteBatch success", new User());
        } else {
            LOGGER.debug("deleteBath user failed ====> ids=");
            return new Result(n,"deleteBatch failed", new User());
        }
    }
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
