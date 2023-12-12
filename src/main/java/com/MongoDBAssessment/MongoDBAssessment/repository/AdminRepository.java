package com.MongoDBAssessment.MongoDBAssessment.repository;

import com.MongoDBAssessment.MongoDBAssessment.model.Admin;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends MongoRepository<Admin, ObjectId> {
    @Query("{adminEmail:\"?0\"}")
    List<Admin> getAdminByEmail(String adminEmail);
}
