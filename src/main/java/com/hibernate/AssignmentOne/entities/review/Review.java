package com.hibernate.AssignmentOne.entities.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hibernate.AssignmentOne.entities.course.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewRating rating;

    @Column(nullable = false)
    private String description;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    private Course course;

    public Review(Long id, ReviewRating rating, String description) {
        this.id = id;
        this.rating = rating;
        this.description = description;
    }
}
