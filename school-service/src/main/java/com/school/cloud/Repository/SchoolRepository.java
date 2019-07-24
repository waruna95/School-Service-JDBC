package com.school.cloud.Repository;

import com.school.cloud.Modal.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School,Integer> {

    Optional<School> findById(String id);

    @Query("select c from School c WHERE c.name like :Name")
    List<School> find(@Param("Name") String name);

    //@Query(value = "queryBody")
    //List<School> findQuery();su

}
