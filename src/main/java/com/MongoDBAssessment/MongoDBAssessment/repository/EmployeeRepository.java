package com.MongoDBAssessment.MongoDBAssessment.repository;

import com.MongoDBAssessment.MongoDBAssessment.model.Employee;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, ObjectId> {
}
