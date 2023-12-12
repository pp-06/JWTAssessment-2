package com.MongoDBAssessment.MongoDBAssessment.service;

import com.MongoDBAssessment.MongoDBAssessment.model.Admin;
import com.MongoDBAssessment.MongoDBAssessment.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private AdminRepository adminRepository;

    private TokenService tokenService;

    AdminService(AdminRepository adminRepository, TokenService tokenService)
    {
        this.adminRepository = adminRepository;
        this.tokenService = tokenService;
    }
    //Signup an Admin
    public String signUp(Admin admin){
        Admin savedAdmin = adminRepository.save(admin);
        return "{"+
                "\"message\":"+"Successfully Created User\",\n"+
                "\"data\":"+savedAdmin+",\n"+
                "}";
    }
    //Login Admin
    public String login(String adminEmail, String adminPassword)
    {
        List<Admin> foundAdmins = adminRepository.getAdminByEmail(adminEmail);
        if (foundAdmins.isEmpty())
        {
            return "Authentication failed";
        }
        else if(!foundAdmins.get(0).getAdminPassword().equals(adminPassword))
        {
            return "Incorrect Password";
        }

        String token = tokenService.createToken(foundAdmins.get(0).getId());

        return String.format("{\12\t\"message\": \"Successfully logged in\",\12\t" +
                        "\"data\":{\"Name\": \"%s\", \n\t\t \"Email\": \"%s\" },\12\t\"token\": \"%s\" \n }", foundAdmins.get(0).getAdminName(),
                foundAdmins.get(0).getAdminEmail(),token);
    }

}
