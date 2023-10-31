package com.haytech.demo_referralmanagement.controller;


import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.entity.Course;
import com.haytech.demo_referralmanagement.service.intrface.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/v1/course")
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        BaseDTO baseDTO =  courseService.createCourse(course);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/courses")
    public  ResponseEntity<?> getAllCourses() {

        BaseDTO baseDTO =  courseService.getAllCourses();
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/course",params = "/{courseId}")
    public  ResponseEntity<?> getCourse(@PathVariable Long courseId) {

        BaseDTO baseDTO =courseService.getCourse(courseId);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/v1/course",params = "/{courseId}")
    public  ResponseEntity<?> updateCourse(@PathVariable Long courseId, @RequestBody Course course) {
        BaseDTO baseDTO = courseService.updateCourse(courseId, course);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/course",params = "/{courseId}")
    public  ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {

        BaseDTO baseDTO = courseService.deleteCourse(courseId);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);

    }
}