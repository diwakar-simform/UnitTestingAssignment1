package com.hibernate.AssignmentOne.repository;

import com.hibernate.AssignmentOne.entities.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review,Long> {

}
