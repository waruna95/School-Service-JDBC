package com.school.cloud.Service;


import com.school.cloud.Modal.QueryGenerator;
import com.school.cloud.Modal.School;
import com.school.cloud.Repository.SchoolJDBC;
import com.school.cloud.Repository.SchoolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService{

    @Autowired
    SchoolJDBC schoolJDBC;

    @Autowired
    SchoolRepository schoolRepository;

    @Override
    public School save(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public List<School> fetchAllSchools() {
        return schoolRepository.findAll();
    }


    @Override
    public School fetchSchool(String Id) {
        Optional<School> optional = schoolRepository.findById(Id);
        School school1 = optional.get();
        return school1;
    }

    @Override
    public List<School> getOnlySchools() {
        return  schoolRepository.find("Thurstan College");
    }

    @Override
    public List<School> fetchQuery(QueryGenerator query) {
        String connName = query.getRoot();
        String connPassword = query.getPassword();
        String exeQuery = query.getQuery();
        String database = query.getDatabase();
        String url =  query.getMyUrl();

        String myUrl = url+database;

        School school = new School();
        List<School> schools = new ArrayList<>();

        // create our mysql database connection
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            //String myUrl = "jdbc:mysql://localhost:3306/schooldb";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, connName, connPassword);

            // SQL SELECT query.
            // if you only need a few columns, specify them by name instead of using "*"
            // String query = "SELECT * FROM users";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(exeQuery);


            while (rs.next()) {
                school.setId(rs.getString("id"));
                school.setName(rs.getString("name"));
                school.setCity(rs.getString("city"));

                // print the results
                //System.out.format("%s, %s, %s\n", id, name, city);
                schools.add(school);
            }
            st.close();
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println(school);
        return schools;
    }

    @Override
    public Object fetchAnyQuery(QueryGenerator query) {
        return schoolJDBC.fetchQuery(query);
    }
}
