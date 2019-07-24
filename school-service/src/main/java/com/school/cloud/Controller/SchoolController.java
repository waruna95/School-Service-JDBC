package com.school.cloud.Controller;

import com.school.cloud.Modal.QueryGenerator;
import com.school.cloud.Modal.School;
import com.school.cloud.Service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/smscloud")
public class SchoolController {

    @Autowired
    SchoolService schoolService;


    //save the schools
    @RequestMapping(value = "/school", method = RequestMethod.POST)
    public ResponseEntity<School> save(@RequestBody School school) {
        return ResponseEntity.ok(schoolService.save(school));
    }

    //get all schools
    @RequestMapping(value = "/school", method = RequestMethod.GET)
    public ResponseEntity<List<School>> fetchAllSchools(){
        return ResponseEntity.ok(schoolService.fetchAllSchools());
    }

    //get one school
    @RequestMapping(value = "/school/{id}", method = RequestMethod.GET)
    public ResponseEntity<School> fetchSchool(@PathVariable String id){
        School school = schoolService.fetchSchool(id);
        if(school == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(school);
        }

    }
    @RequestMapping(value = "/school/names", method = RequestMethod.GET)
    public ResponseEntity<?> fetchSchool(){
        List<School> schools= schoolService.getOnlySchools();
        if(schools == null) {
            return ResponseEntity.notFound().build();
        }else {
            return new ResponseEntity<>(schools, HttpStatus.ACCEPTED);
        }


    }


//    @RequestMapping(value = "/school/query", method = RequestMethod.GET)
//    @ResponseBody
//    public  ResponseEntity<?> getQuery(@RequestBody QueryGenerator query){
//        //System.out.println(query);
//        List<School> schools = schoolService.fetchQuery(query);
//        if(schools.equals(null)) {
//            return ResponseEntity.notFound().build();
//        }else {
//            return new ResponseEntity<>(schools, HttpStatus.ACCEPTED);
//        }
//
//    }

    @RequestMapping(value = "/school/query", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getQuery(@RequestBody QueryGenerator query) {
        Object queryResult =  schoolService.fetchAnyQuery(query);
        if(queryResult.equals(null)) {
            return ResponseEntity.notFound().build();
        }else {
            return new ResponseEntity<>(queryResult, HttpStatus.ACCEPTED);
        }
    }
}
