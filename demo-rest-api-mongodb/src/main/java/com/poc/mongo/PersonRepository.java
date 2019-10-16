package com.poc.mongo;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path="people") // Lets use people endpoint instead of person
public interface PersonRepository extends MongoRepository<Person,String>{
    List<Person> findByLastName(@Param("name") String name);
}
