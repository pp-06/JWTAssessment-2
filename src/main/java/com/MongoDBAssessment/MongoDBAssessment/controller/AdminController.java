package com.MongoDBAssessment.MongoDBAssessment.controller;

import com.MongoDBAssessment.MongoDBAssessment.model.Admin;
import com.MongoDBAssessment.MongoDBAssessment.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController {
    private final AdminService adminService;

    AdminController(AdminService adminService)
    {
        this.adminService = adminService;
    }

    @PostMapping("/add")
    public String signUp(@RequestBody Admin admin)
    {
        return adminService.signUp(admin);
    }

    @PostMapping("login")
    public String login(@RequestBody Map<String,Object> map)
    {
        return adminService.login(map.get("adminEmail").toString(), map.get("adminPassword").toString());
    }
}
