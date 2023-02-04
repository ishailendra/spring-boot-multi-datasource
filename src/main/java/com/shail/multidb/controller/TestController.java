package com.shail.multidb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shail.multidb.prd.entity.Product;
import com.shail.multidb.usr.entity.User;
import com.shail.multidb.prd.repo.ProductRepo;
import com.shail.multidb.usr.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    ProductRepo prdRepo;

    @Autowired
    UserRepo usrRepo;

    @PostMapping(value = "/save")
    public String saveData() {
        prdRepo.save(new Product(1, "Nokia Lumia 300", "450.0", "Mobile", "56.0", "Nokia", "Y", "Y"));
        usrRepo.save(new User(1, "John", "john@mail.com", 45));;
        return "Records Saved successfully";
    }

    @GetMapping(value = "/fetch")
    public String fetchData() throws JsonProcessingException {
        String prds = new ObjectMapper().writeValueAsString(prdRepo.findAll());
        String usrs = new ObjectMapper().writeValueAsString(usrRepo.findAll());
        return "Products:  " + prds + "\nUsers:  " + usrs;
    }
}
