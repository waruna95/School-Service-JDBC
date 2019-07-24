package com.school.cloud.Service;


import com.school.cloud.Modal.QueryGenerator;
import com.school.cloud.Modal.School;
import org.apache.tomcat.util.digester.ObjectCreateRule;

import java.sql.SQLException;
import java.util.List;

public interface SchoolService {
    School save(School school);
    List<School> fetchAllSchools();
    School fetchSchool(String Id);

    List<School> getOnlySchools();

    List<School> fetchQuery(QueryGenerator query);

    Object fetchAnyQuery(QueryGenerator query);
}
