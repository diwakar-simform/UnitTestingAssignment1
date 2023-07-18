package com.hibernate.AssignmentOne.controller.review;

import com.hibernate.AssignmentOne.entities.review.Review;
import com.hibernate.AssignmentOne.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @PostMapping("/{id}")
    public String addReviewWithCourse(@RequestBody Review review, @PathVariable Long id) {
        return reviewService.addReviewWithCourse(review,id);
    }

    @PostMapping
    public Review addReview(@RequestBody Review review) {
        return reviewService.addReview(review);
    }

    @DeleteMapping("/{id}")
    public String deleteReviewById(@PathVariable Long id) {
        return reviewService.deleteReviewById(id);
    }


}
