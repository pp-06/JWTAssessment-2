package com.MongoDBAssessment.MongoDBAssessment.service;

import com.MongoDBAssessment.MongoDBAssessment.model.Employee;
import com.MongoDBAssessment.MongoDBAssessment.repository.EmployeeRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    EmployeeService(EmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }

    //Get all movies
    public String getAllEmployees()
    {
        var employeeList = employeeRepository.findAll();
        return  String.format("{\n\t\"message\": \"%d Employee found\", \n\t \"data\": %s \n}", employeeList.size(),employeeList.toString());
    }

    //Add movies
    public String addEmployee(Employee employee)
    {
        Employee savedEmployee = employeeRepository.save(employee);
        return String.format("{\n\t\"message\":\"Employee Details saved\", \n\t \"data\": %s \n}",savedEmployee.toString());
    }

    // update employee
    public String updateEmployee(String id, Employee emp) {

        ObjectId objectId = new ObjectId(id);

        // Use findById to retrieve the entity by its ID
        Optional<Employee> OptionalEmp = employeeRepository.findById(objectId);

        if(OptionalEmp.isEmpty()) {
            throw new RuntimeException("given employee doesn't exist");
        }
        var empRec = OptionalEmp.get();

        if (emp.getEmployeeName() != null)
            empRec.setEmployeeName(emp.getEmployeeName());
        if (emp.getEmployeeSalary() != 0)
            empRec.setEmployeeSalary(emp.getEmployeeSalary());
        Employee savedEmp = employeeRepository.save(empRec);
        return "{" +
                "\"message\":"+"Successfully updated employee\",\n"+
                "\"data\":"+savedEmp+",\n"+
                "}";
    }

    // remove employee
    public String removeEmpById(String id) {

        ObjectId objectId = new ObjectId(id);

        // Use findById to retrieve the entity by its ID
        Optional<Employee> OptionalEmp = employeeRepository.findById(objectId);

        if(OptionalEmp.isEmpty()) {
            throw new RuntimeException("Employee id" + id + "doesn't exist");
        }
        employeeRepository.deleteById(objectId);
        return "{" +
                "\"message\":"+"Successfully deleted employee\",\n"+
                "\"id\":"+ id +",\n"+
                "}";
    }

    //remove all employees
    public void removeAllEmployees() { employeeRepository.deleteAll();}
}
