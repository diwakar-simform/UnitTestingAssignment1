package com.hibernate.AssignmentOne.service.review;

import com.hibernate.AssignmentOne.entities.review.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    Review addReview(Review review);
    String deleteReviewById(Long id);
    String addReviewWithCourse(Review review, Long id);
}
