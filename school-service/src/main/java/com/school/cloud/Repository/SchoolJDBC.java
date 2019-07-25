package com.school.cloud.Repository;

import com.school.cloud.Modal.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Repository
public class SchoolJDBC {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void setDataSource(QueryGenerator query) {
        String connName = query.getRoot();
        String connPassword = query.getPassword();
        String database = query.getDatabase();
        String url = query.getMyUrl();
        String myUrl = url+database;

        String myDriver = "com.mysql.jdbc.Driver";

        DataSource dataSource;
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(myDriver);
        dataSourceBuilder.url(myUrl);
        dataSourceBuilder.username(connName);
        dataSourceBuilder.password(connPassword);
        dataSource = dataSourceBuilder.build();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Object> fetchQuery(QueryGenerator query) {
        String exeQuery = query.getQuery();

        setDataSource(query);
        return jdbcTemplate.query(exeQuery, new EmployeeRowMapper());
    }


    public class EmployeeRowMapper implements RowMapper<Object> {

        @Override
        public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
            ResultSetMetaData rsMeta = rs.getMetaData();
            int colCount = rsMeta.getColumnCount();

            Map<String, Object> columns = new HashMap<>();
            for (int i = 1; i <= colCount; i++) {
                columns.put(rsMeta.getColumnLabel(i), rs.getObject(i));
            }
            return columns;
        }

    }
}
