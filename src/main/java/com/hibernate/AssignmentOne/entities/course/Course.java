package com.hibernate.AssignmentOne.entities.course;

import com.hibernate.AssignmentOne.entities.review.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    public Course(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setReview(Review review) {
        reviews.add(review);
    }

}
