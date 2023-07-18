package com.hibernate.AssignmentOne.repository;

import com.hibernate.AssignmentOne.entities.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course,Long> {

}
