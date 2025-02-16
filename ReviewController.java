package com.example.moviereview.controller;

import com.example.moviereview.model.Review;
import com.example.moviereview.service.ReviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{movieId}")
    public Review addReview(@PathVariable Long movieId, @RequestBody Review review) {
        return reviewService.addReview(movieId, review);
    }

    @PutMapping("/{reviewId}")
    public Review updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
        return reviewService.updateReview(reviewId, review);
    }
}
