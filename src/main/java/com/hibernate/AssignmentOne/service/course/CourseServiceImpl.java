package com.hibernate.AssignmentOne.service.course;

import com.hibernate.AssignmentOne.entities.course.Course;
import com.hibernate.AssignmentOne.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseRepo courseRepo;

    public CourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Course addCourse(Course course) {
        course.getReviews().forEach(review -> review.setCourse(course));
        return courseRepo.save(course);
    }

    @Override
    public String deleteCourseById(Long id) {
        String message = "------Wrong Id------";
        if (id != null && id > 0) {
            courseRepo.findById(id).isPresent();
            courseRepo.deleteById(id);
            message = "------Item deleted Successfully-------";
        }
        return message;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Course getCourseById(Long id) {
        Optional<Course> course = courseRepo.findById(id);
        return course.get();
    }


}
