package com.MongoDBAssessment.MongoDBAssessment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class Admin {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String adminName;
    private String adminEmail;
    private String adminPassword;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + adminName + '\'' +
                ", email='" + adminEmail + '\'' +
                ", password='" + adminPassword + '\'' +
                '}';
    }
}
