package com.neosoft.mongopoc.repositroy;

import com.neosoft.mongopoc.model.EmployeeState;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.xml.ws.RespectBinding;

@Repository
public interface EmployeeRepositroy extends MongoRepository<EmployeeState,String> {
    EmployeeState getById(String id);
}
