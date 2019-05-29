package com.example.demo.controller;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {


    @Autowired
    private UserMapper userMapper;


    @Autowired
    private TransactionTemplate template;


    @RequestMapping("/getUser")
    public void test() {

        boolean result = result();
        System.out.println(result);
    }



    public boolean result() {
        return template.execute(action -> {
            try {
                int result = userMapper.insert(new User());
                if (result == 1) {
                    action.setRollbackOnly();
                    return false;
                } else {
                    return true;
                }
            } catch (Exception e) {
                action.setRollbackOnly();
            }
            return false;
        });
    }

}
