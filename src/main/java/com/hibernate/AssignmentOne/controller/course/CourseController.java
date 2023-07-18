package com.hibernate.AssignmentOne.controller.course;

import com.hibernate.AssignmentOne.entities.course.Course;
import com.hibernate.AssignmentOne.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> allCourses = courseService.getAllCourses();
        if (allCourses == null) {
            return new ResponseEntity<>(allCourses,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(allCourses,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        if (course == null) {
            return new ResponseEntity<>(course,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(course,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        if (course == null) {
            return new ResponseEntity<>(course,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(courseService.addCourse(course),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public String deleteCourseById(@PathVariable Long id) {
        return courseService.deleteCourseById(id);
    }
}
