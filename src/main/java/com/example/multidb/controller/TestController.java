package com.example.multidb.controller;

import com.example.multidb.prd.entity.Product;
import com.example.multidb.usr.entity.User;
import com.example.multidb.prd.repo.ProductRepo;
import com.example.multidb.usr.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    ProductRepo prdRepo;

    @Autowired
    UserRepo usrRepo;

    @GetMapping(value = "/test")
    public String tester() {
        prdRepo.save(new Product(1, "Nokia", 45.0));
        usrRepo.save(new User(1, "John", "john@mail.com", 45));;
        return "success";
    }
}
